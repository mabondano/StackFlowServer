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


package com.merlab.stackflow.api.rest;


import com.merlab.stackflow.test.TestManagerLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.logging.Logger;

@ApplicationPath("api")
public class StackflowApp extends Application {

    private static final Logger LOGGER = Logger.getLogger(StackflowApp.class.getName());

    @EJB
    private TestManagerLocal testManager;

    @PostConstruct
    public void initialize() {
        LOGGER.info("StackflowApp initialization starting...");
        try {
            if (testManager != null) {
                testManager.runAllTests();
                LOGGER.info("TestManager.bootstrapUsers() executed.");
            } else {
                LOGGER.warning("testManager is null — EJB injection failed?");
            }
        } catch (Exception e) {
            LOGGER.severe("Error during initialization: " + e.getMessage());
        }
    }
}

