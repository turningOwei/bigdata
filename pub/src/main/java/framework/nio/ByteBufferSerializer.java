package framework.nio;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 常用类型的ByteBuffer化工具
 * 
 * @author zhaowei
 *
 */
public class ByteBufferSerializer {
	/**
	 * 把字符串序列化到ByteBuffer.<br/>
	 * 数据格式：4个字节代表字符串长度n（字节数），后面n个字节为字符串数据。
	 * 
	 * @param str
	 * 
	 * @param bb
	 * 
	 * @throws Throwable
	 */
	public static void stringToByteBuffer(String str, ByteBuffer bb)
			throws Throwable {
		byteArrayToByteBuffer(str.getBytes("UTF-8"), bb);
	}

	/**
	 * 数据格式：4个字节代表数组长度n（字节数），后面n个字节数据。
	 * 
	 * @param bytes
	 * @param bb
	 */
	private static void byteArrayToByteBuffer(byte[] bytes, ByteBuffer bb) {
		bb.putInt(bytes.length);
		bb.put(bytes);
	}

	/**
	 * ByteBuffer反序列化成字符串.<br/>
	 * 数据格式：4个字节代表字符串长度n（字节数），后面n个字节为字符串数据。
	 * 
	 * @param bb
	 * @return
	 * @throws Throwable
	 */
	public static String byteBufferToString(ByteBuffer bb) throws Throwable {
		return new String(byteBufferTobyteArray(bb), "UTF-8");
	}

	/**
	 * 数据格式：4个字节代表数组长度n（字节数），后面n个字节数据。
	 * 
	 * @param bb
	 * @return
	 */
	private static byte[] byteBufferTobyteArray(ByteBuffer bb) {
		int length = bb.getInt();
		byte[] bytes = new byte[length];
		bb.get(bytes, 0, length);
		return bytes;
	}

	/**
	 * 把list序列化到ByteBuffer.<br/>
	 * 数据格式：4个字节代表list长度，后面为一个挨着一个的内容。
	 * 
	 * @param list
	 * @param bb
	 * @throws Throwable
	 */
	public static <L extends List<? extends ByteBufferAble>> void listToByteBuffer(
			L list, ByteBuffer bb) throws Throwable {
		bb.putInt(list.size());
		for (ByteBufferAble bba : list) {
			objToByteBuffer(bba, bb);
		}
	}

	/**
	 * 反序列化list.<br/>
	 * 数据格式：4个字节代表list长度，后面为一个挨着一个的内容。
	 * 
	 * @param bb
	 * @return
	 */
	public static <L extends List<? extends ByteBufferAble>> L byteBufferToList(
			ByteBuffer bb) throws Throwable {
		int size = bb.getInt();
		List<ByteBufferAble> list = new ArrayList<ByteBufferAble>(size);
		for (int i = 0; i < size; i++) {
			list.add(byteBufferToObj(bb));
		}
		return (L) list;
	}

	/**
	 * 把ByteBufferAble对象序列化到ByteBuffer。<br/>
	 * 数据格式：首先序列化ByteBufferAble对象的类型字符串，然后序列化ByteBufferAble对象本身。
	 * 
	 * @param obj
	 * @param bb
	 * @throws Throwable
	 */
	public static void objToByteBuffer(ByteBufferAble obj, ByteBuffer bb)
			throws Throwable {
		stringToByteBuffer(obj.getClass().getName(), bb);
		obj.toByteBuffer(bb);
	}

	/**
	 * ByteBufferAble对象反序列化。<br/>
	 * 数据格式：首先反序列化ByteBufferAble对象的类型字符串，然后反射实例化对象， 然后填充对象。
	 * 
	 * @param bb
	 * @return
	 */
	public static <E extends ByteBufferAble> E byteBufferToObj(ByteBuffer bb)
			throws Throwable {
		String typeStr = byteBufferToString(bb);
		ByteBufferAble obj = (ByteBufferAble) Class.forName(typeStr)
				.newInstance();
		obj.fillByByteBuffer(bb);
		return (E) obj;
	}

}
