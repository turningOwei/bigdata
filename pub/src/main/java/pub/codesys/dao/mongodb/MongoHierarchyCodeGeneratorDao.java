package pub.codesys.dao.mongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.orm.mongodb.MongodbDaoBase;
import framework.tree.Tree;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import pub.codesys.HierarchyCodeGenerator;
import pub.codesys.HierarchyCodeNode;
import pub.codesys.dao.HierarchyCodeGeneratorDao;
import pub.codesys.dao.dto.HierarchyCodeDTO;

public class MongoHierarchyCodeGeneratorDao extends
		MongodbDaoBase<HierarchyCodeDTO> implements HierarchyCodeGeneratorDao {

	private Map<String, HierarchyCodeGenerator> hcgMap = new HashMap<String, HierarchyCodeGenerator>();

	public HierarchyCodeDTO getBeanByName(String name) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(name));
		HierarchyCodeDTO dto = mongoTemplate.findOne(query,
				this.getEntityClass());
		return dto;
	}


	public HierarchyCodeGenerator getGeneratorByName(String generatorName,
			int... structure) {
		HierarchyCodeGenerator hcg = hcgMap.get(generatorName);
		if (hcg != null) {
			return hcg;
		} else {
			HierarchyCodeDTO dto = this.getBeanByName(generatorName);
			if (dto != null) {
				int[] struc = dto.getStruc();
				List<HierarchyCodeNode> cnList = dto.getList();
				Tree tree = new Tree(cnList);
				hcg = new HierarchyCodeGenerator(generatorName, struc, tree);
				hcgMap.put(generatorName, hcg);
			}
			return hcg;
		}
	}


	public void updateHierarchyGenerator(String docName,
			HierarchyCodeNode newCodeNode) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(docName));
		Update update = new Update();
		HierarchyCodeNode cnCopy = newCodeNode.copy();
		HierarchyCodeNode cnPCopy = ((HierarchyCodeNode) newCodeNode
				.getParentNode()).copy();
		cnCopy.setParentNode(cnPCopy);
		update.addToSet("list", cnCopy);
		mongoTemplate.upsert(query, update, this.getEntityClass());
	}


	public void createHierarchyGenerator(HierarchyCodeGenerator hcg) {
		try {
			HierarchyCodeDTO dto = this.getBeanByName(hcg.getName());

			if (dto != null) {
				int[] struc = dto.getStruc();
				List<HierarchyCodeNode> cnList = dto.getList();
				Tree tree = new Tree(cnList);
				hcg = new HierarchyCodeGenerator(hcg.getName(), struc, tree);
			} else {
				dto = new HierarchyCodeDTO();
				dto.setName(hcg.getName());
				dto.setStruc(hcg.getStruc());

				List<HierarchyCodeNode> list = new ArrayList<HierarchyCodeNode>();
				HierarchyCodeNode tn = new HierarchyCodeNode();
				String rootNodeStr = hcg.getRootNodeStr();
				tn.setCode(rootNodeStr);
				tn.setCodeValue(0);
				tn.setDepth(0);
				list.add(tn);
				dto.setList(list);
				this.addBean(dto);
			}
			hcgMap.put(hcg.getName(), hcg);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}


	public void deleteHierarchyCode(String docName, String currentCode) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(docName));
		Update update = new Update();
		HierarchyCodeNode tn = new HierarchyCodeNode();
		tn.setCode(currentCode);
		update.pull("list", new BasicDBObject("code", currentCode));
		mongoTemplate.upsert(query, update, this.getEntityClass());
	}


	protected Class<HierarchyCodeDTO> getEntityClass() {
		return HierarchyCodeDTO.class;
	}

}
