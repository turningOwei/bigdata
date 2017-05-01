package pub.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pub.user.dao.UserContextDao;
import pub.user.domain.UserContext;
import pub.user.service.UserContextService;


public class UserContextServiceImpl implements UserContextService {

	@Autowired
	private UserContextDao userContextDao;


	public void addUserContext(UserContext userContext) throws Throwable {
		userContextDao.save(userContext);
	}

}
