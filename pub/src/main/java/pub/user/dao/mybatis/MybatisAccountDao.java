package pub.user.dao.mybatis;


import framework.orm.mybatis.MybatisDaoBase;
import pub.user.dao.AccountDao;
import pub.user.domain.Account;

public class MybatisAccountDao extends MybatisDaoBase<Account> implements
		AccountDao {


	public Account getAccountByLoginName(String loginName) {
		return openSession().selectOne("account.getAccountByLoginName",
				loginName);
	}

}
