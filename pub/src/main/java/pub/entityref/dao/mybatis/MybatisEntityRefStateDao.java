package pub.entityref.dao.mybatis;

import org.springframework.stereotype.Repository;

import framework.orm.mybatis.MybatisDaoBase;
import pub.entityref.dao.EntityRefStateDao;
import pub.entityref.domain.EntityInfo;
import pub.entityref.domain.EntityRefState;

@Repository("entityRefStateDao")
public class MybatisEntityRefStateDao extends MybatisDaoBase<EntityInfo>
		implements EntityRefStateDao {


	public EntityRefState getEntityRefStateByEntityInfo(EntityInfo entityInfo) {
		EntityRefState entityRefState = this.sqlSession.selectOne(
				"entityRefState.getEntityRefStateByEntityInfo", entityInfo);
		return entityRefState;
	}


	public void save(EntityRefState state) throws Throwable {
		this.sqlSession.insert("entityRefState.save", state);
	}


	public void updateRefCount(EntityRefState state) {
		this.sqlSession.update("entityRefState.update", state);
	}

}
