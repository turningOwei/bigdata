package pub.permission.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pub.permission.dao.RoleResourceDao;
import pub.permission.dao.mongodb.repositories.RoleResourceRepository;
import pub.permission.domain.RoleResource;

@Repository
public class MongodbRoleResourceDao implements RoleResourceDao {
	@Autowired
	private RoleResourceRepository repository;
	@Autowired
	private MongoTemplate mongoTempalte;


	public RoleResource getRoleResourceByRoleId(String roleId) {
		return repository.findOneByRoleId(roleId);
	}


	public void saveRoleResource(RoleResource roleResource) {
		repository.save(roleResource);
	}


	public List<RoleResource> getRoleResourcesByRoleIds(List<String> roleIds) {
		Order order = new Order(Direction.ASC, "disOrder");
		Sort sort = new Sort(order);
		List<RoleResource> list = mongoTempalte.find(
				new Query(Criteria.where("roleId").in(roleIds)).with(sort),
				RoleResource.class);

		return list;
	}
}
