package pub.entityref.domain;

import java.util.List;

/**
 * 一般由聚合根更具具体的业务场景生成相关EntityInfos。
 * 
 * @author zhaowei
 *
 */
public interface EntityInfosGenerator {
	List<EntityInfo> getEntityInfos();
}
