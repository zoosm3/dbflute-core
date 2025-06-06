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

/**
 * @author modified by jflute (originated in Seasar2)
 * @since 0.8.3 (2008/10/28 Tuesday)
 */
public class DfDtsSqlContext {

    private String _sql;
    private Object[] _args;
    private Class<?>[] _argTypes;

    public DfDtsSqlContext(String sql, Object[] args, Class<?>[] argTypes) {
        _sql = sql;
        _args = args;
        _argTypes = argTypes;
    }

    public Object[] getArgs() {
        return _args;
    }

    public Class<?>[] getArgTypes() {
        return _argTypes;
    }

    public String getSql() {
        return _sql;
    }
}