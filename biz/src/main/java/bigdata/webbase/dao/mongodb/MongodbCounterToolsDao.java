package bigdata.webbase.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;

import bigdata.webbase.dao.CounterToolsDao;
import bigdata.webbase.dao.mongodb.repositories.CounterToolsRepository;
import bigdata.webbase.domain.CounterTools;

/**
 * @ClassName: MongodbCounterToolsDao
 * @Description: TODO(某个对象自动递增id方法)
 * @author zhaowei 716517@qq.com
 * @date 2016年1月5日 下午1:31:46
 * 
 */
public class MongodbCounterToolsDao implements CounterToolsDao {
	@Autowired
	private CounterToolsRepository repository;

	/**
	 * 获取某个表的自动递增id
	 * 
	 * @param domain
	 *            代表那个实体类或者mongodb表名
	 * @return String
	 */

	public String getAutoIncrementingId(String domain) {

		// seq是CounterTools的字段（做为一个表的计数器，已同步），小心使用
		Update update = new Update().inc("seq", 1);
		CounterTools counterTools = repository.findOne(domain);
		if (counterTools == null) {// 没有domain新增默认
			counterTools = new CounterTools();
			counterTools.setId(domain);
			counterTools.setSeq((long) 1);
			repository.save(counterTools);
		} else {// 更新domain
			counterTools.setId(domain);
			counterTools.setSeq(counterTools.getSeq() + 1);
			repository.save(counterTools);
		}
		return counterTools.getSeq().toString();
	}
}
