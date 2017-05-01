package pub.sys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.sys.domain.SysUser;
import pub.user.domain.Account;
import pub.user.domain.LoginResult;


public interface SysUserService {

	/**
	 * 登录
	 * 
	 * @param account
	 * @return 代表登录结果的对象
	 */
	LoginResult login(Account account);

	/**
	 * 判断登录名是否唯一 true为唯一，false为非唯一
	 */
	boolean isUnique(String loginName);

	/**
	 * @param sysUser
	 * @return true添加成功，false账户重复
	 * @throws Throwable
	 */
	boolean addSysUser(SysUser sysUser) throws Throwable;

	/**
	 * 根据id获取用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Throwable
	 */
	SysUser getSysUserById(String id) throws Throwable;

	/**
	 * 修改用户信息
	 * 
	 * @param sysUser
	 * @return boolean true成功，false账户重复
	 * @throws Throwable
	 */
	boolean updateSysUser(SysUser sysUser) throws Throwable;

	/**
	 * 删除用户
	 */
	int deleteSysUser(SysUser sysUser) throws Throwable;

	public Page<SysUser> getSysUsers(PageRequest pageRequest);

	public Page<SysUser> getSysUsersByNameLike(String name,
											   PageRequest buildPageRequest);

	public List<SysUser> getSysUserByName(String name);

	public SysUser getSysUserByLoginName(String loginName);

}
