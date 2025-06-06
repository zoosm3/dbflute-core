/*
 * Copyright 2014-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.dbflute.bhv.core.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dbflute.Entity;
import org.dbflute.cbean.ConditionBean;
import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.info.ColumnInfo;
import org.dbflute.outsidesql.OutsideSqlOption;
import org.dbflute.s2dao.metadata.TnBeanMetaData;
import org.dbflute.s2dao.metadata.TnPropertyType;
import org.dbflute.util.DfTypeUtil;

/**
 * @author jflute
 */
public abstract class AbstractCountableUpdateCommand extends AbstractAllBehaviorCommand<Integer> {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** The instance of condition-bean. (NotNull: but null allowed only when queryDelete() specially) */
    protected Entity _entity;

    // ===================================================================================
    //                                                                   Basic Information
    //                                                                   =================
    public Class<?> getCommandReturnType() {
        return Integer.class;
    }

    // ===================================================================================
    //                                                                  Detail Information
    //                                                                  ==================
    public boolean isConditionBean() {
        return false;
    }

    public boolean isOutsideSql() {
        return false;
    }

    public boolean isProcedure() {
        return false;
    }

    public boolean isSelect() {
        return false;
    }

    public boolean isSelectCount() {
        return false;
    }

    public boolean isSelectCursor() {
        return false;
    }

    public boolean isInsert() {
        return false; // as default
    }

    public boolean isUpdate() {
        return false; // as default
    }

    public boolean isDelete() {
        return false; // as default
    }

    public boolean isBatchUpdateFamily() {
        return false;
    }

    // ===================================================================================
    //                                                                             Factory
    //                                                                             =======
    // -----------------------------------------------------
    //                                          BeanMetaData
    //                                          ------------
    protected TnBeanMetaData createBeanMetaData() {
        return _beanMetaDataFactory.createBeanMetaData(_entity.getClass());
    }

    // ===================================================================================
    //                                                                    Process Callback
    //                                                                    ================
    public void beforeGettingSqlExecution() {
    }

    public void afterExecuting() {
    }

    // ===================================================================================
    //                                                               SqlExecution Handling
    //                                                               =====================
    public String buildSqlExecutionKey() {
        assertStatus("buildSqlExecutionKey");
        final String entityName = DfTypeUtil.toClassTitle(_entity);
        return _tableDbName + ":" + getCommandName() + "(" + entityName + ")";
    }

    public Object[] getSqlExecutionArgument() {
        assertStatus("getSqlExecutionArgument");
        return doGetSqlExecutionArgument();
    }

    protected abstract Object[] doGetSqlExecutionArgument();

    // ===================================================================================
    //                                                                Argument Information
    //                                                                ====================
    public ConditionBean getConditionBean() {
        return null;
    }

    public Entity getEntity() {
        return _entity; // but null when query delete
    }

    public List<Entity> getEntityList() {
        return Collections.emptyList();
    }

    public String getOutsideSqlPath() {
        return null;
    }

    public String getParameterBean() {
        return null;
    }

    public OutsideSqlOption getOutsideSqlOption() {
        return null;
    }

    // ===================================================================================
    //                                                                       Assist Helper
    //                                                                       =============
    /**
     * Find DB meta. <br>
     * Basically this method should be called when initializing only.
     * @return DB meta. (NullAllowed: If the entity does not its DB meta)
     */
    protected DBMeta findDBMeta() {
        // /- - - - - - - - - - - - - - - - - - - - - - - - 
        // get from the entity instance 
        // because the customize-entity is contained here.
        // - - - - - - - - - -/
        final Class<?> beanType = _entity.getClass();
        if (beanType == null) {
            return null;
        }
        if (!Entity.class.isAssignableFrom(beanType)) {
            return null;
        }
        final Entity entity;
        try {
            entity = (Entity) beanType.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        return entity.asDBMeta();
    }

    /**
     * Get persistent property names. <br>
     * Basically this method should be called when initializing only.
     * @param bmd The bean meta data. (NotNull)
     * @return Persistent property names. (NotNull)
     */
    protected String[] getPersistentPropertyNames(TnBeanMetaData bmd) {
        final DBMeta dbmeta = findDBMeta();
        if (dbmeta != null) {
            final List<ColumnInfo> columnInfoList = dbmeta.getColumnInfoList();
            final List<String> propertyNameList = new ArrayList<String>();
            for (ColumnInfo columnInfo : columnInfoList) {
                propertyNameList.add(columnInfo.getPropertyName());
            }
            return propertyNameList.toArray(new String[] {});
        } else {
            // when the entity does not have its DB meta.
            return createNonOrderedPropertyNames(bmd);
        }
    }

    private String[] createNonOrderedPropertyNames(TnBeanMetaData bmd) {
        final List<String> propertyNameList = new ArrayList<String>();
        final List<TnPropertyType> ptList = bmd.getPropertyTypeList();
        for (TnPropertyType pt : ptList) {
            if (pt.isPersistent()) {
                propertyNameList.add(pt.getPropertyName());
            }
        }
        return propertyNameList.toArray(new String[propertyNameList.size()]);
    }

    // ===================================================================================
    //                                                                       Assert Helper
    //                                                                       =============
    protected void assertStatus(String methodName) {
        assertBasicProperty(methodName);
        assertComponentProperty(methodName);
        assertEntityProperty(methodName);
    }

    protected void assertEntityProperty(String methodName) {
        if (_entity == null) {
            throw new IllegalStateException(buildAssertMessage("_entity", methodName));
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public void setEntity(Entity entity) {
        _entity = entity;
    }
}
