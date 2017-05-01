package framework.orm.mybatis.page;

import java.sql.Connection;
import java.util.Properties;

import framework.orm.Dialect;
import framework.orm.MySqlDialect;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;


/**
 * 通过拦截prepare方法，重写sql语句实现物理分页
 * @author wang xp
 * @date 2015-05-21
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationInterceptor implements Interceptor {

	private static final Log logger = LogFactory.getLog(PaginationInterceptor.class);
	
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
	    BoundSql boundSql = statementHandler.getBoundSql();
	    MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
	    RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");
	    
	    if(rowBounds==null || rowBounds==RowBounds.DEFAULT){
	    	return invocation.proceed();
	    }
	    
	    Configuration configuration = (Configuration)metaStatementHandler.getValue("delegate.configuration");
	    Dialect.Type databaseType = null;
	    databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
	    
	    if(databaseType == null){
	    	throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "+ configuration.getVariables().getProperty("dialect"));
	    }
	    
	    Dialect dialect = null;
    	switch(databaseType){
    		case MYSQL:
    			dialect = new MySqlDialect();
    			break;
    		case ORACLE:
    			//dialect = new OracleDialect();
    			break;
    	}
    	
    	String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
        metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()) );

        // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET );
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT );

        if(logger.isDebugEnabled()){
        	logger.debug("生成分页SQL : "+ boundSql.getSql());
        }
	    
		return invocation.proceed();
	}


	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
	}


	public void setProperties(Properties arg0) {
		
	}

}
