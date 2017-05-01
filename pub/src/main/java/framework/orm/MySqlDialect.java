package framework.orm;


/**
 * 
 * @author wang xp
 * @date 2015-05-21
 */
public class MySqlDialect extends Dialect {


	public String getLimitString(String sqlString, int offSet, int pageSize) {
		StringBuffer buffer = new StringBuffer(sqlString);
        buffer.append(" limit ");
        buffer.append(offSet);
        buffer.append(",");
        buffer.append(pageSize);
        return buffer.toString();
	}

}
