package pub.entityref.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pub.entityref.dao.EntityRefStateDao;
import pub.entityref.domain.EntityInfo;
import pub.entityref.domain.EntityInfosGenerator;
import pub.entityref.domain.EntityRefState;

/**
 * 引用状态管理
 * 
 * @author zhaowei
 *
 */
@Service("refStateManageService")
public class RefStateManageService {

	@Resource(name = "entityRefStateDao")
	private EntityRefStateDao entityRefStateDao;

	/**
	 * 增加相关实体的引用数
	 * 
	 * @param entityInfosGenerator
	 * @throws Throwable
	 */
	public void increaseRefCountForEntityList(
			EntityInfosGenerator entityInfosGenerator) throws Throwable {
		List<EntityInfo> infos = entityInfosGenerator.getEntityInfos();
		for (EntityInfo info : infos) {
			increaseRefCountForEntity(info);
		}
	}

	/**
	 * 增加实体的引用数
	 * 
	 * @throws Throwable
	 */
	public void increaseRefCountForEntity(EntityInfo info) throws Throwable {
		EntityRefState state = entityRefStateDao
				.getEntityRefStateByEntityInfo(info);
		if (state == null) {
			state = new EntityRefState();
			state.setEntityInfo(info);
			entityRefStateDao.save(state);
		}
		state.increaseRefCount();
		entityRefStateDao.updateRefCount(state);
	}

	/**
	 * 减少相关实体的引用数
	 * 
	 * @param entityInfosGenerator
	 * @throws Throwable
	 */
	public void decreaseRefCountForEntityList(
			EntityInfosGenerator entityInfosGenerator) throws Throwable {
		List<EntityInfo> infos = entityInfosGenerator.getEntityInfos();
		for (EntityInfo info : infos) {
			decreaseRefCountForEntity(info);
		}
	}

	/**
	 * 减少实体的引用数
	 * 
	 */
	public void decreaseRefCountForEntity(EntityInfo info) throws Throwable {
		EntityRefState state = entityRefStateDao
				.getEntityRefStateByEntityInfo(info);
		if (state != null) {
			state.decreaseRefCount();
			entityRefStateDao.updateRefCount(state);
		}
	}

}