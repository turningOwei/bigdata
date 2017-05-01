package pub.codesys.dao;


import pub.codesys.HierarchyCodeGenerator;
import pub.codesys.HierarchyCodeNode;

/**
 * 编码生成器dao
 * 
 * @author wang xp
 * @date 2015-07-21
 */
public interface HierarchyCodeGeneratorDao {

	/**
	 * 根据名称获取Generator
	 * 
	 * @param generatorName
	 * @return
	 */
	HierarchyCodeGenerator getGeneratorByName(String generatorName, int... structure);

	/**
	 * 删除层级code
	 *
	 * @param generatorName
	 * @param currentCode
	 */
	void deleteHierarchyCode(String generatorName, String currentCode);

	/**
	 * 为当前节点添加下级code
	 * 
	 * @param generatorName
	 *            当前节点
	 */
	void updateHierarchyGenerator(String generatorName, HierarchyCodeNode newCodeNode);

	/**
	 * 注册新文档
	 * 
	 * @param hcg
	 */
	public void createHierarchyGenerator(HierarchyCodeGenerator hcg);

}
