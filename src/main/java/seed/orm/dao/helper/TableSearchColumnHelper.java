package seed.orm.dao.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;

import seed.orm.annotation.TableSearchColumn;
import tk.mybatis.mapper.util.StringUtil;

/**
 * Created by hubo on 2015/8/27
 */
public class TableSearchColumnHelper {

    private final static Map<Class<?>, List<TableSearchColumnEntity>> searchColumnMap = new HashMap<>();
    public static List<TableSearchColumnEntity> getTableSearchColumns(Class<?> entityClass) {

        if(!searchColumnMap.containsKey(entityClass)) {

            synchronized (searchColumnMap) {

                if(!searchColumnMap.containsKey(entityClass)) {

                    searchColumnMap.put(entityClass, new ArrayList<TableSearchColumnEntity>());

                    List<Field> fieldList = getAllField(entityClass, null);

                    for (Field field : fieldList) {
                        String columnName;
                        if (field.isAnnotationPresent(TableSearchColumn.class)) {
                            TableSearchColumn joinColumn = field.getAnnotation(TableSearchColumn.class);
                            columnName = joinColumn.column();
                            if (StringUtils.isEmpty(StringUtils.trim(columnName))) {
                                //columnName = EntityHelper.camelhumpToUnderline(field.getName());
                            	columnName = StringUtil.camelhumpToUnderline(field.getName());
                            }
                            TableSearchColumnEntity column = new TableSearchColumnEntity();
                            column.setColumn(columnName);
                            if(!StringUtils.isEmpty(joinColumn.joinTable())) {
                                column.setJoinTable(joinColumn.joinTable());
//                                column.setJoinId(EntityHelper.camelhumpToUnderline(field.getName()));
                                column.setJoinId(StringUtil.camelhumpToUnderline(field.getName()));
                            }
                            searchColumnMap.get(entityClass).add(column);
                        }

                    }
                }
            }
        }

        return searchColumnMap.get(entityClass);
    }

    /**
     * 获取全部的Field
     *
     * @param entityClass
     * @param fieldList
     * @return
     */
    private static List<Field> getAllField(Class<?> entityClass, List<Field> fieldList) {
        if (fieldList == null) {
            fieldList = new LinkedList<Field>();
        }
        if (entityClass.equals(Object.class)) {
            return fieldList;
        }
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fieldList.add(field);
            }
        }
        Class<?> superClass = entityClass.getSuperclass();
        if (superClass != null
                && !superClass.equals(Object.class)
                && (superClass.isAnnotationPresent(Entity.class)
                || (!Map.class.isAssignableFrom(superClass)
                && !Collection.class.isAssignableFrom(superClass)))) {
            return getAllField(entityClass.getSuperclass(), fieldList);
        }
        return fieldList;
    }


    public static class TableSearchColumnEntity {

        private String column;

        private String joinTable;

        private String prefix;

        private String joinId;

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getJoinTable() {
            return joinTable;
        }

        public void setJoinTable(String joinTable) {
            this.joinTable = joinTable;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getJoinId() {
            return joinId;
        }

        public void setJoinId(String joinId) {
            this.joinId = joinId;
        }
    }

}
