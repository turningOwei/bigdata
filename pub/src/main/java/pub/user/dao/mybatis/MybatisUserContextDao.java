package pub.user.dao.mybatis;


import framework.orm.mybatis.MybatisDaoBase;
import pub.user.dao.UserContextDao;
import pub.user.domain.UserContext;

public class MybatisUserContextDao extends MybatisDaoBase<UserContext>
		implements UserContextDao {


	public void save(UserContext userContext) throws Throwable {
		openSession().insert("userContext.save", userContext);
	}

}