package pub.user.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import pub.user.dao.AccountDao;
import pub.user.dao.mongodb.repositories.UserRepository;
import pub.user.domain.Account;
import pub.user.domain.User;


public class MongodbAccountDao implements AccountDao {

	@Autowired
	private UserRepository repository;


	public Account getAccountByLoginName(String loginName) {
		User user = repository.findOneByAccountLoginName(loginName);
		if (user != null) {
			return user.getAccount();
		} else {
			return null;
		}
	}
}
