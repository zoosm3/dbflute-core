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

/**
 * The callback interface of file-token option.
 * @param <OP> The type of file-token option to be set up.
 * @author jflute
 * @since 1.1.0 (2014/12/02 Tuesday)
 */
@FunctionalInterface
public interface FileTokenOptionCall<OP> {

    /**
     * @param op The option of file-token to be set up. (NotNull)
     */
    void callback(OP op);
}
