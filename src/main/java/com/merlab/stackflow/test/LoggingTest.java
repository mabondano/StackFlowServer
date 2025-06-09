/*
 * Copyright 2025 Merly Abondano
 *
 * Created:   2025-06-09
 * Author:    Merly Abondano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.merlab.stackflow.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggingTest.class);

    public static void main(String[] args) {
        logger.info("INFO: This is an info message.");
        logger.warn("WARN: This is a warning message.");
        logger.error("ERROR: This is an error message.");
        logger.debug("DEBUG: This is a debug message.");
        logger.trace("TRACE: This is a trace message.");

        try {
            throw new Exception("This is a test exception.");
        } catch (Exception e) {
            logger.error("Exception caught!", e);
        }
    }
}
