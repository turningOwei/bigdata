package pub.permission.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pub.permission.dao.RoleDao;
import pub.permission.dao.mongodb.repositories.RoleRepository;
import pub.permission.domain.Resource;
import pub.permission.domain.Role;

@Repository
public class MongodbRoleDao implements RoleDao {

	@Autowired
	private RoleRepository repository;

	@Autowired
	private MongoTemplate mongoTempalte;


	@Deprecated
	public List<Role> getRoleList(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Deprecated
	public List<Resource> getResourceByRole(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer deleteRole(Role role) throws Throwable {
		repository.delete(role);
		return 1;
	}


	public Integer deleteUserRole(String roleId) {
		return 1;
	}


	public Integer deleteRoleResource(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer saveRole(Role role) throws Throwable {
		repository.save(role);
		return 1;
	}


	public Integer updateRole(Role role) throws Throwable {
		repository.save(role);
		return 1;
	}


	public Role getRoleById(String id) {
		return repository.findOne(id);
	}


	public Integer grantRoleToUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer removeRoleFromUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer grantResourceToRole(Long roleId, Long[] resourceIds) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer removeResourceFromRole(Long roleId, Long[] resourceIds) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Role> getRoleForList(int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}


	public int getTotalRole() {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<Role> getRoleList(String userId) {
		return null;
	}


	public List<Resource> getResourceByRole(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Role> getRoles(PageRequest buildPageRequest) {
		return repository.findAll(buildPageRequest);
	}


	public List<Role> getUserRoleByIds(List<String> ids) {
		List<Role> list = mongoTempalte.find(new Query(Criteria.where("_id")
				.in(ids)), Role.class);
		return list;
	}
}
