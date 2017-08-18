/*
 * Copyright 2014-2017 the original author or authors.
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
package org.dbflute.logic.jdbc.metadata.supplement;

import java.util.Map;
import java.util.Set;

/**
 * @author jflute
 * @since 1.1.4 (2017/08/12 Saturday at ikspiari)
 */
public interface DfDatePrecisionExtractor {

    Map<String, Map<String, Integer>> extractDatePrecisionMap(Set<String> tableSet);
}