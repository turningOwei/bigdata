package pub.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.sys.dao.SysUserDao;
import pub.sys.domain.SysUser;
import pub.sys.service.SysUserService;
import pub.user.dao.AccountDao;
import pub.user.domain.Account;
import pub.user.domain.LoginResult;


/**
 * @author zhaowei
 * @date 2016-01-25
 */
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private AccountDao accountDao;


	public LoginResult login(Account account) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isUnique(String loginName) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean addSysUser(SysUser sysUser) throws Throwable {
		// 账户是否已存在
		if (this.isUnique(sysUser.getSysAccount().getLoginName())) {
			sysUser.getSysAccount().encryptPass();
			sysUserDao.saveSysUser(sysUser);
			return true;
		} else {
			return false;
		}
	}


	public SysUser getSysUserById(String id) throws Throwable {
		return sysUserDao.getSysUserById(id);
	}


	public boolean updateSysUser(SysUser sysUser) throws Throwable {
		// TODO Auto-generated method stub
		return false;
	}


	public int deleteSysUser(SysUser sysUser) throws Throwable {
		// TODO Auto-generated method stub
		return 0;
	}


	public Page<SysUser> getSysUsers(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<SysUser> getSysUsersByNameLike(String name,
			PageRequest buildPageRequest) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<SysUser> getSysUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	public SysUser getSysUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}

}
