package pub.codesys.dao.mongodb;

import java.util.HashMap;
import java.util.Map;

import framework.orm.mongodb.MongodbDaoBase;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import pub.codesys.IncreaseCodeGenerator;
import pub.codesys.dao.IncreaseCodeGeneratorDao;
import pub.codesys.dao.dto.IncreaseCodeDTO;


public class MongoIncreaseCodeGeneratorDao extends
		MongodbDaoBase<IncreaseCodeDTO> implements IncreaseCodeGeneratorDao {

	private Map<String, IncreaseCodeGenerator> generatorCache = new HashMap<String, IncreaseCodeGenerator>();

	public IncreaseCodeDTO getBeanByName(String name) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(name));
		return mongoTemplate.findOne(query, this.getEntityClass());
	}


	public void createIncreaseNumGenerate(IncreaseCodeGenerator ing) {
		IncreaseCodeDTO increaseNumDto = new IncreaseCodeDTO();
		increaseNumDto.setName(ing.getName());
		increaseNumDto.setDigit(ing.getDigit());
		increaseNumDto.setCurrentIndex(ing.getCurrentIndex().longValue());
		this.addBean(increaseNumDto);
	}


	public IncreaseCodeGenerator getGeneratorByName(String name) {

		IncreaseCodeGenerator generator = generatorCache.get(name);
		if (generator != null) {
			return generator;
		} else {
			IncreaseCodeDTO dto = this.getBeanByName(name);
			if (dto != null) {
				generator = new IncreaseCodeGenerator(name, dto.getDigit(),
						dto.getCurrentIndex());
				generatorCache.put(name, generator);
			}
			return generator;
		}

	}


	protected Class<IncreaseCodeDTO> getEntityClass() {
		return IncreaseCodeDTO.class;
	}


	public void updateGenerator(IncreaseCodeGenerator generator) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(generator.getName()));
		Update update = new Update();

		update.set("currentIndex", generator.getCurrentIndex().longValue());
		this.mongoTemplate.upsert(query, update, this.getEntityClass());
	}

}
