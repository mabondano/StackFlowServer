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


package com.merlab.stackflow.config;


/**
 * Central configuration manager for StackFlowServer.
 * Central manager for configuration access.
 * Author: Merly Abondano
 * Created: 2025-06-09
 */
public class ConfigManager {

    private static ConfigManager instance;
    private final PropertiesConfigLoader loader;

    private ConfigManager() {
        loader = new PropertiesConfigLoader();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    public String get(String key) {
        return loader.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        return loader.getProperty(key, defaultValue);
    }
}