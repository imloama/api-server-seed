package seed.orm.dao.provider;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.*;

import seed.orm.consts.DBConsts;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 通用的批量逻辑删除
 * Created by liuhaiming on 2015/12/28
 */
public class BatchLogicDeleteMapperProvider extends MapperTemplate {
    public BatchLogicDeleteMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public SqlNode batchDelete(MappedStatement ms) {

        //获取实体类型
        Class<?> entityClass = getEntityClass(ms);

        List<SqlNode> sqlNodes = new LinkedList<>();

        //update table
        sqlNodes.add(new StaticTextSqlNode("UPDATE " + tableName(entityClass)));
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);

        EntityColumn drCol = null;

        EntityColumn idCol = null;

        for (EntityColumn entityColumn : columnList) {
            if(DBConsts.FIELD_DR.equals(entityColumn.getColumn())){
                drCol = entityColumn;
            }
            if(entityColumn.isId()) {
                idCol = entityColumn;
            }
        }

        sqlNodes.add(new SetSqlNode(ms.getConfiguration(), new StaticTextSqlNode(drCol.getColumn() + " = 1 ")));

        ForEachSqlNode forEachSqlNode = new ForEachSqlNode(ms.getConfiguration(),
                new StaticTextSqlNode("#{item." + idCol.getProperty() + "}"), "list", "index", "item", " id in (", ")", ",");

        sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), forEachSqlNode));

        return new MixedSqlNode(sqlNodes);
    }
}
