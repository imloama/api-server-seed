package seed.orm.dialect;

/**
 * SQLite数据库方言
 * 
 **/
public class SQLiteDialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return SQLitePageHepler.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return SQLitePageHepler.getCountString(sql);
    }
}
