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
package org.dbflute.logic.doc.lreverse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.torque.engine.database.model.Table;
import org.dbflute.util.DfCollectionUtil;
import org.dbflute.util.DfCollectionUtil.AccordingToOrderIdExtractor;
import org.dbflute.util.DfCollectionUtil.AccordingToOrderResource;

/**
 * @author jflute
 */
public class DfLReverseOutputResource {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final File _dataFile; // same as order map's key
    protected final List<Table> _tableList; // tables in one xls file or only-one table if delimiter
    protected final Integer _sectionNo; // e.g. 01, 02
    protected final String _mainName; // in current section e.g. MEMBER
    protected final boolean _oneToOneFile;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public DfLReverseOutputResource(File dataFile, Table table, Integer sectionNo, String mainName) {
        _dataFile = dataFile;
        _tableList = DfCollectionUtil.newArrayList(table); // 1:1 for TSV;
        _sectionNo = sectionNo;
        _mainName = mainName;
        _oneToOneFile = true;
    }

    public DfLReverseOutputResource(File dataFile, List<Table> tableList, Integer sectionNo, String mainName) {
        _dataFile = dataFile;
        _tableList = tableList;
        _sectionNo = sectionNo;
        _mainName = mainName;
        _oneToOneFile = false;
    }

    // ===================================================================================
    //                                                                              Accept
    //                                                                              ======
    public void acceptTableOrder(List<String> tableNameList) {
        final List<String> lowerList = new ArrayList<String>();
        for (String tableName : tableNameList) {
            lowerList.add(tableName.toLowerCase());
        }
        final AccordingToOrderResource<Table, String> resource = new AccordingToOrderResource<Table, String>();
        resource.setupResource(lowerList, new AccordingToOrderIdExtractor<Table, String>() {
            public String extractId(Table element) {
                return element.getTableDbName().toLowerCase();
            }
        });
        DfCollectionUtil.orderAccordingTo(_tableList, resource);
    }

    // ===================================================================================
    //                                                                  Table Registration
    //                                                                  ==================
    public void addTable(Table table) {
        if (_oneToOneFile && _tableList.size() == 1) {
            throw new IllegalStateException("Already only-one table exists: " + _tableList + ", " + table);
        }
        _tableList.add(table);
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public File getDataFile() {
        return _dataFile;
    }

    public List<Table> getTableList() {
        return _tableList;
    }

    public int getSectionNo() {
        return _sectionNo;
    }

    public String getMainName() {
        return _mainName;
    }

    public boolean isOneToOneFile() {
        return _oneToOneFile;
    }
}
