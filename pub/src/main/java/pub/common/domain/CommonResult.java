package pub.common.domain;

/**
 * SUCCESS:成功, DUBCODE:code重复, UNKNOWNERR:未知错误 DELFOBFORREFD:被引用不能删除
 * MAXLEVEL:超出最大层级数 HASLEAF:有子节点
 * 
 * @deprecated 作为枚举不适合 “通用-扩展” 的表达，已经被抽象类
 *             <code>cn.com.teemax.pub.common.BsnsServResult</code>替换.
 */
@Deprecated
public enum CommonResult {
	SUCCESS, DUBCODE, UNKNOWNERR, DELFOBFORREFD, MAXLEVEL, HASLEAF
}
