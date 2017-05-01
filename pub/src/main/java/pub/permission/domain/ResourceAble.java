package pub.permission.domain;

/**
 * 实现此接口的实体，能够被权限系统识别从而验证权限。 此接口的实现类应该是对一个原本独立于权限系统的类型比如“菜单”的一个包装类，使其适配权限系统。
 * 
 * @author zhaowei
 *
 */
public interface ResourceAble {

	/**
	 * 此字符串用于权限验证
	 * 
	 * @return
	 */
	String getResourceStr();

}
