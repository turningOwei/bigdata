package pub.codesys;


import framework.tree.TreeNode;

/**
 * 编码生成器节点
 * @author wang xp
 * @date 2015-07-21
 */
public class HierarchyCodeNode extends TreeNode {
	
	private String code;
	
	private int codeValue;
	
	private Integer depth;
	
	public HierarchyCodeNode copy() {
		HierarchyCodeNode cn = new HierarchyCodeNode();
		cn.setCode(code);
		cn.setCodeValue(codeValue);
		cn.setDepth(depth);
		return cn;
	}
	

	public String getNodeId() {
		return code;
	}


	public String getName() {
		return code;
	}


	public String getParentNodeId() {
		return parentNode.getNodeId();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(int codeValue) {
		this.codeValue = codeValue;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

}
