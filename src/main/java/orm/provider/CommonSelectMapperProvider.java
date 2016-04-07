package orm.provider;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;

import orm.consts.DBConsts;
import orm.helper.TableSearchColumnHelper;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

public class CommonSelectMapperProvider extends MapperTemplate {


    public CommonSelectMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public SqlNode selectLikePage(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);

        List<TableSearchColumnHelper.TableSearchColumnEntity> searchColumns = TableSearchColumnHelper.getTableSearchColumns(entityClass);

        if(searchColumns.isEmpty()) {
            return select(ms, entityClass);
        }else {

            List<SqlNode> sqlNodes = new LinkedList<>();

            String selectColumns = EntityHelper.getSelectColumns(entityClass);

            String tableName = tableName(entityClass);

            int index = 0;

            sqlNodes.add(new StaticTextSqlNode(" select DISTINCT * FROM ( "));

            for (TableSearchColumnHelper.TableSearchColumnEntity searchColumn : searchColumns) {

                if(StringUtils.isEmpty(searchColumn.getJoinTable())) {

                    sqlNodes.add(new StaticTextSqlNode("SELECT "
                            + selectColumns
                            + " FROM "
                            + tableName));

                    sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), new StaticTextSqlNode(" dr = 0 and "+searchColumn.getColumn()+" like CONCAT('%', #{searchValue}, '%') ")));

                }else {

                    sqlNodes.add(new StaticTextSqlNode("SELECT "
                            + getProfixSelectColumns(tableName, selectColumns)
                            + " FROM "
                            + tableName));
                    sqlNodes.add(new StaticTextSqlNode(" left join " + searchColumn.getJoinTable()
                            + " on " + tableName+"." + searchColumn.getJoinId() + "=" + searchColumn.getJoinTable() +"." + DBConsts.FIELD_ID + " "));
                    sqlNodes.add(new WhereSqlNode(ms.getConfiguration(),
                            new StaticTextSqlNode(tableName+".dr = 0 and "+searchColumn.getJoinTable()+"." + searchColumn.getColumn()+" like CONCAT('%', #{searchValue}, '%') ")));
                }

                index++;

                if(searchColumns.size() != index) {
                    sqlNodes.add(new StaticTextSqlNode(" UNION ALL "));
                }

            }

            sqlNodes.add(new StaticTextSqlNode(" ) t "));

            return new MixedSqlNode(sqlNodes);
        }
    }

    public SqlNode selectPage(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);

        return select(ms, entityClass);
    }


    private String getProfixSelectColumns(String profix, String origSelectColumns) {

        String[] cols = origSelectColumns.split(",");

        StringBuilder profixBuilder = new StringBuilder();

        for (String col : cols) {
            profixBuilder.append(profix).append(".").append(col).append(",");
        }

        return profixBuilder.deleteCharAt(profixBuilder.length() - 1).toString();

    }

    /**
     * 查询(dr = 0)
     *
     * @param ms
     * @return SqlNode
     */
    private SqlNode select(MappedStatement ms, Class<?> entityClass) {

        List<SqlNode> sqlNodes = new LinkedList<SqlNode>();
        //静态的sql部分:select column ... from table
        sqlNodes.add(new StaticTextSqlNode("SELECT "
                + EntityHelper.getSelectColumns(entityClass)
                + " FROM "
                + tableName(entityClass)));
        //将if添加到<where>
        sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), new StaticTextSqlNode(" dr = 0")));
        String orderByClause = EntityHelper.getOrderByClause(entityClass);
        if (orderByClause.length() > 0) {
            sqlNodes.add(new StaticTextSqlNode("ORDER BY " + orderByClause));
        }
        return new MixedSqlNode(sqlNodes);
    }


    public SqlNode selectByIdList(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        List<SqlNode> sqlNodes = new LinkedList<SqlNode>();
        //静态的sql部分:select column ... from table
        sqlNodes.add(new StaticTextSqlNode("SELECT "
                + EntityHelper.getSelectColumns(entityClass)
                + " FROM "
                + tableName(entityClass)));

        List<SqlNode> whereNodes = new LinkedList<SqlNode>();

        whereNodes.add(new StaticTextSqlNode(" dr = 0 "));

        ForEachSqlNode forEachSqlNode = new ForEachSqlNode(ms.getConfiguration(),
                new StaticTextSqlNode("#{item}"), "list", "index", "item", " and id in (", ")", ",");
        whereNodes.add(forEachSqlNode);

        sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), new MixedSqlNode(whereNodes)));

        String orderByClause = EntityHelper.getOrderByClause(entityClass);
        if (orderByClause.length() > 0) {
            sqlNodes.add(new StaticTextSqlNode("ORDER BY " + orderByClause));
        }

        return new MixedSqlNode(sqlNodes);
    }


}