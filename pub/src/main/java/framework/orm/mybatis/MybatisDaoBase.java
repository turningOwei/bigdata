package framework.orm.mybatis;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基于mybatis的dao基类。子类通过继承，获得其中的工具方法
 * 
 * @author zhaowei
 * 
 */
public abstract class MybatisDaoBase<T> {

	@Autowired
	protected SqlSession sqlSession;

	/**
	 * 获得一般用途的SqlSession
	 * 
	 * @return
	 */
	protected SqlSession openSession() {
		return sqlSession;
	}

	/**
	 * 为多条sql一次性提交查询优化的SqlSession
	 * 
	 * @return
	 */
	protected SqlSession openSessionForMultiSqlQuery() {
		return ((SqlSessionTemplate) sqlSession).getSqlSessionFactory()
				.openSession(ExecutorType.REUSE, true);
	}

	/**
	 * 为批量插入修改优化的SqlSession
	 * 
	 * @return
	 */
	protected SqlSession openSessionForBatchInsertOrUpdate() {
		return ((SqlSessionTemplate) sqlSession).getSqlSessionFactory()
				.openSession(ExecutorType.BATCH);
	}

	/**
	 * 根据传入的参数获取分页列表
	 * 
	 * @param sqlId
	 * @param object
	 * @param start
	 * @param size
	 * @return list<T>
	 * @author wang xp
	 */
	protected List<T> getPageList(String sqlId, Object object, int start,
			int size) {
		List<T> list = sqlSession.selectList(sqlId, object, new RowBounds(
				start, size));
		return list;
	}

}
