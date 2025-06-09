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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads application configuration from config.properties.
 * Author: Merly Abondano
 * Created: 2025-06-09
 */
public class PropertiesConfigLoader {

    private final Properties properties = new Properties();

    public PropertiesConfigLoader() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("config.properties file not found in classpath");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new ConfigException("Error loading configuration", ex);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
