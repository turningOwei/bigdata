package framework.orm.mongodb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 基于mongodb的dao基类。子类通过继承，获得其中的工具方法
 * 
 * @author wang xp
 * @date 2015-07-15
 */
public abstract class MongodbDaoBase<T> {

	@Autowired
	protected MongoTemplate mongoTemplate;

	/**
	 * 获得mongoTemplate
	 * 
	 * @return
	 */
	protected MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	/**
	 * 获取需要操作的实体类class
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();

	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 * @return T
	 */
	public T getBeanById(String id) {
		return mongoTemplate.findById(id, this.getEntityClass());
	}

	/**
	 * 保存实体
	 * 
	 * @param bean
	 */
	public void addBean(T bean) {
		mongoTemplate.insert(bean);
	}

	/**
	 * 通过条件查询更新数据
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	public void upsertBean(String id, Map<String, Object> map) {
		Query query = new Query();
		query.addCriteria(new Criteria("id").is(id));
		Update update = new Update();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			update.set(entry.getKey(), entry.getValue());
		}
		mongoTemplate.upsert(query, update, this.getEntityClass());
	}

	/**
	 * 通过id删除记录
	 * 
	 * @param id
	 */
	public void deleteBean(String id) {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(id));
		mongoTemplate.remove(query, this.getEntityClass());
	}

	/**
	 * 根据传入的参数获取分页列表
	 * 
	 * @param eqMap
	 *            精确匹配
	 * @param reMap
	 *            模糊匹配
	 * @param start
	 *            第几条开始
	 * @param size
	 *            多少条
	 * @return list<T>
	 * @author wang xp
	 */
	public List<T> getPageList(Map<String, Object> eqMap,
			Map<String, Object> reMap, int start, int size) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		if (eqMap != null) {
			for (Map.Entry<String, Object> entry : eqMap.entrySet()) {
				criteria.and(entry.getKey()).is(entry.getValue());
			}
		}
		if (reMap != null) {
			for (Map.Entry<String, Object> entry : reMap.entrySet()) {
				criteria.and(entry.getKey()).regex(entry.getValue().toString());
			}
		}

		query.addCriteria(criteria);
		query.skip(start);
		query.limit(size);
		List<T> list = mongoTemplate.find(query, this.getEntityClass());
		return list;
	}

	public List<T> getPageList(int start, int size) {
		List<T> list = this.getPageList(null, null, start, size);
		return list;
	}

	/**
	 * 根据传入的参数获取分页个数
	 * 
	 * @param eqMap
	 *            精确匹配
	 * @param reMap
	 *            模糊匹配
	 * @return Long
	 * @author wang xp
	 */
	public Long getTotalBean(Map<String, Object> eqMap,
			Map<String, Object> reMap) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		if (eqMap != null) {
			for (Map.Entry<String, Object> entry : eqMap.entrySet()) {
				criteria.and(entry.getKey()).is(entry.getValue());
			}
		}

		if (reMap != null) {
			for (Map.Entry<String, Object> entry : reMap.entrySet()) {
				criteria.and(entry.getKey()).regex(entry.getValue().toString());
			}
		}
		query.addCriteria(criteria);
		Long count = mongoTemplate.count(query, this.getEntityClass());
		return count;
	}

	public Long getTotalBean() {
		Long count = this.getTotalBean(null, null);
		return count;
	}

}
