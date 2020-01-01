/*
 * Copyright 2014-2020 the original author or authors.
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
package org.dbflute.s2dao.rshandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dbflute.jdbc.ValueType;
import org.dbflute.s2dao.jdbc.TnResultSetHandler;

/**
 * @author modified by jflute (originated in S2Dao)
 */
public class TnScalarResultSetHandler implements TnResultSetHandler {

    private final ValueType valueType;

    public TnScalarResultSetHandler(ValueType valueType) {
        this.valueType = valueType;
    }

    public Object handle(ResultSet rs) throws SQLException {
        while (rs.next()) {
            return valueType.getValue(rs, 1);
        }
        return null;
    }
}
