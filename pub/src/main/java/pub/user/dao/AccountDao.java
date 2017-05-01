package pub.user.dao;


import pub.user.domain.Account;

public interface AccountDao {
	Account getAccountByLoginName(String loginName);
}
