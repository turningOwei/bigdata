package framework.file;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 记录日志的文件。顺序写是其特点。
 * 
 * @author zhaowei
 *
 */
public class JournalFile implements Closeable {

	private FileChannel fc;

	private File file;

	private RandomAccessFile rFile;

	/**
	 * 如果文件不存在，创建文件。如果已存在，则到文件最末尾，准备写。
	 * 
	 * @param name
	 *            完整文件名
	 * @throws Exception
	 */
	public JournalFile(String name) throws Exception {
		file = new File(name);
		rFile = new RandomAccessFile(file, "rw");
		fc = rFile.getChannel();
		fc.position(fc.size());
	}

	public void write(ByteBuffer bb) throws Exception {
		fc.write(bb);
	}

	/**
	 * 文件所属目录
	 * 
	 * @return
	 */
	public String getDirectory() {
		return file.getParentFile().getPath();
	}


	public void close() throws IOException {
		fc.close();
		rFile.close();
	}

}
