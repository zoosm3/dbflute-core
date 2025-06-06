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
package org.dbflute.helper.dataset.states;

import java.util.ArrayList;
import java.util.List;

import org.dbflute.helper.dataset.DfDataColumn;
import org.dbflute.helper.dataset.DfDataRow;
import org.dbflute.helper.dataset.DfDataTable;

/**
 * @author modified by jflute (originated in Seasar2)
 * @since 0.8.3 (2008/10/28 Tuesday)
 */
public class DfDtsRemovedState extends DfDtsAbstractRowState {

    @Override
    public String toString() {
        return "REMOVED";
    }

    protected DfDtsSqlContext getSqlContext(DfDataRow row) {
        final DfDataTable table = row.getTable();
        final StringBuffer sb = new StringBuffer(100);
        final List<Object> argList = new ArrayList<Object>();
        final List<Class<?>> argTypeList = new ArrayList<Class<?>>();
        sb.append("delete from ");
        sb.append(table.getTableSqlName());
        sb.append(" where ");
        for (int i = 0; i < table.getColumnSize(); ++i) {
            final DfDataColumn column = table.getColumn(i);
            if (column.isPrimaryKey()) {
                sb.append(column.getColumnSqlName());
                sb.append(" = ? and ");
                argList.add(row.getValue(i));
                argTypeList.add(column.getColumnType().getType());
            }
        }
        sb.setLength(sb.length() - 5);
        return createDtsSqlContext(sb.toString(), argList, argTypeList);
    }
}