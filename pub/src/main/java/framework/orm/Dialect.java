package framework.orm;

/**
 * 
 * @author wang xp
 * @date 2015-05-21
 */
public abstract class Dialect {
	public static enum Type{  
        MYSQL,  
        ORACLE
    }  
      
    public abstract String getLimitString(String sqlString, int offSet, int pageSize);
}
