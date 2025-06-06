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
package org.dbflute.logic.generate.language.framework;

/**
 * @author jflute
 */
public class DfLanguageFrameworkCSharp implements DfLanguageFramework {

    public String getDBFluteDiconNamespace() {
        return "DBFlute";
    }

    public String getDBFluteDiconFileName() {
        return "DBFlute.dicon";
    }

    public String getJ2eeDiconResourceName() {
        return "${topNamespace}/Resources/Ado.dicon";
    }

    public String getDBFluteDiXmlNamespace() {
        return "DBFlute";
    }

    public String getDBFluteDiXmlFileName() {
        return "DBFlute.xml";
    }

    public String getRdbDiXmlResourceName() {
        return "${topNamespace}/Resources/Rdb.xml";
    }

    public boolean isMakeDaoInterface() {
        return true;
    }
}