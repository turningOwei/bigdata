package pub.permission.domain;

import java.util.ArrayList;
import java.util.List;

import pub.entityref.domain.EntityInfo;
import pub.entityref.domain.EntityInfosGenerator;

/**
 * 生成入库作业需要更新引用状态的相关实体信息。
 * 
 * @author zhaowei
 *
 */
public class RoleEISG implements EntityInfosGenerator {

	private List<Resource> resourcelist;

	public RoleEISG(List<Resource> resource) {
		this.resourcelist = resource;
	}


	public List<EntityInfo> getEntityInfos() {
		List<EntityInfo> infos = new ArrayList<EntityInfo>();
		for (Resource resource : resourcelist) {
			infos.add(new EntityInfo(resource, resource.getId().toString()));
		}
		return infos;
	}
}
