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
package org.dbflute.helper.token.file;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author jflute
 */
@FunctionalInterface
public interface FileTokenizingCallback {

    /**
     * Handle the row per one record data. <br>
     * You can get the data from row resources.
     * @param resource The resource of row for the file-tokenizing. (NotNull)
     * @throws IOException When the IO handling fails in the row handling process.
     * @throws SQLException When the SQL handling fails in the row handling process.
     */
    void handleRow(FileTokenizingRowResource resource) throws IOException, SQLException;
}
