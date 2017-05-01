package pub.permission.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pub.permission.dao.UserRoleDao;
import pub.permission.dao.mongodb.repositories.UserRoleRepository;
import pub.permission.domain.UserRole;

@Repository
public class MongodbUserRoleDao implements UserRoleDao {
	@Autowired
	private UserRoleRepository repository;
	@Autowired
	private MongoTemplate mongoTempalte;


	public UserRole getUserRoleByUserId(String userId) {
		return repository.findOneByUserId(userId);
	}

	public void saveUserRole(UserRole userRole) {
		repository.save(userRole);
	}

	public List<UserRole> getUserRolesByUserId(String userId) {
		List<UserRole> list = mongoTempalte.find(
				new Query(Criteria.where("userId").is(userId)), UserRole.class);
		return list;
	}

}
