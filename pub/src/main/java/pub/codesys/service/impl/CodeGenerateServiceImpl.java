package pub.codesys.service.impl;

import framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import pub.codesys.HierarchyCodeGenerator;
import pub.codesys.HierarchyCodeNode;
import pub.codesys.IncreaseCodeGenerator;
import pub.codesys.dao.HierarchyCodeGeneratorDao;
import pub.codesys.dao.IncreaseCodeGeneratorDao;
import pub.codesys.service.CodeGenerateService;

public class CodeGenerateServiceImpl implements CodeGenerateService {

	@Autowired
	private HierarchyCodeGeneratorDao hierarchyCodeGeneratorDao;

	@Autowired
	private IncreaseCodeGeneratorDao increaseCodeGeneratorDao;


	public String getNextHierarchyCode(String generatorName,
			String currentCode, int... structure) throws Throwable {
		HierarchyCodeGenerator hcg = hierarchyCodeGeneratorDao
				.getGeneratorByName(generatorName);
		if (hcg == null) {
			hcg = new HierarchyCodeGenerator(generatorName, structure);
			hierarchyCodeGeneratorDao.createHierarchyGenerator(hcg);
		}
		HierarchyCodeNode codeNode = hcg.getHierarchyCode(currentCode, true);
		hierarchyCodeGeneratorDao.updateHierarchyGenerator(generatorName,
				codeNode);
		return codeNode.getCode();
	}


	public String getVirtualNextHierarchyCode(String generatorName,
			String currentCode, int... structure) {
		HierarchyCodeGenerator hcg = hierarchyCodeGeneratorDao
				.getGeneratorByName(generatorName);
		if (hcg == null) {
			hcg = new HierarchyCodeGenerator(generatorName, structure);
			hierarchyCodeGeneratorDao.createHierarchyGenerator(hcg);
		}
		String code = "";
		try {
			HierarchyCodeNode codeNode = hcg.getHierarchyCode(currentCode,
					false);
			code = codeNode.getCode();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return code;
	}


	public void deleteHierarchyCode(String generatorName, String currentCode,
			int... structure) throws Throwable {
		HierarchyCodeGenerator hcg = hierarchyCodeGeneratorDao
				.getGeneratorByName(generatorName);
		if (hcg == null) {
			hcg = new HierarchyCodeGenerator(generatorName, structure);
			hierarchyCodeGeneratorDao.createHierarchyGenerator(hcg);
		}
		hcg.deleteHierarchyCode(currentCode);
		hierarchyCodeGeneratorDao.deleteHierarchyCode(generatorName,
				currentCode);
	}


	public String getNextIncreaseNum(String generatorName, int digit) {
		IncreaseCodeGenerator generator = increaseCodeGeneratorDao
				.getGeneratorByName(generatorName);
		if (generator == null) {
			generator = new IncreaseCodeGenerator(generatorName, digit);
			increaseCodeGeneratorDao.createIncreaseNumGenerate(generator);
		}
		String code = generator.getIncreaseCode();
		increaseCodeGeneratorDao.updateGenerator(generator);
		return code;
	}


	public String getNextRandomCode(int len) {
		String code = StringUtil.generateRandom(len);
		return code;
	}


	public String getNextSerialCode() {
		String code = StringUtil.generateSerial();
		return code;
	}

	public void setIncreaseCodeGeneratorDao(
			IncreaseCodeGeneratorDao increaseCodeGeneratorDao) {
		this.increaseCodeGeneratorDao = increaseCodeGeneratorDao;
	}

	public void setHierarchyCodeGeneratorDao(
			HierarchyCodeGeneratorDao hierarchyCodeGeneratorDao) {
		this.hierarchyCodeGeneratorDao = hierarchyCodeGeneratorDao;
	}

}
