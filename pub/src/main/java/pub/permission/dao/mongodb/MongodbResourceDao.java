package pub.permission.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pub.permission.dao.ResourceDao;
import pub.permission.dao.mongodb.repositories.ResourceRepository;
import pub.permission.domain.Resource;

@Repository
public class MongodbResourceDao implements ResourceDao {
	@Autowired
	private ResourceRepository repository;
	@Autowired
	private MongoTemplate mongoTempalte;


	public List<Resource> getResourceList(int start, int size) {

		return null;
	}


	public Integer getTotalResource() {
		// TODO Auto-generated method stub
		return null;
	}


	public Resource getResourceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Resource> getResourceByIdList(Long[] resourceIds) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Resource> getResourceByUserId(String userId) {

		return null;
	}


	public Integer updateResource(Resource resource) throws Throwable {
		repository.save(resource);
		return 1;
	}


	public Integer deleteResource(Resource resource) throws Throwable {
		repository.delete(resource);
		return 1;
	}


	public Integer addResource(Resource resource) throws Throwable {
		repository.save(resource);
		return 1;
	}


	public Resource getResourceByResourceStr(String str) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Resource> getAllResources() {
		return repository.findAll();
	}


	public List<Resource> getAllResourcesSort(Sort sort) {
		return repository.findAll(sort);
	}


	public Page<Resource> getResources(PageRequest buildPageRequest) {
		return repository.findAll(buildPageRequest);
	}


	public Resource getResourceByName(String name) {
		return repository.findOneByName(name);
	}


	public Resource getResourceById(String id) {
		return repository.findOne(id);
	}


	public List<Resource> getResourceByIds(List<String> ids) {
		List<Resource> list = mongoTempalte.find(new Query(Criteria
				.where("_id").in(ids)), Resource.class);
		return list;
	}

}
