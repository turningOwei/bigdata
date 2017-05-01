package pub.sys.dao;


import pub.sys.domain.SysUser;

public interface SysUserDao {

	public SysUser getSysUserById(String id);

	public void saveSysUser(SysUser sysUser);

}
