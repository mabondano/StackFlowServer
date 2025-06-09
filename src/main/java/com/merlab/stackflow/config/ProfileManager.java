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
 *
 * @author merly
 */
/**
 * Manages application profiles (dev, prod, test, etc.).
 */
public class ProfileManager {

    private String activeProfile = "dev";

    public String getActiveProfile() {
        return activeProfile;
    }

    public void setActiveProfile(String profile) {
        this.activeProfile = profile;
    }

    public boolean isProd() {
        return "prod".equalsIgnoreCase(activeProfile);
    }

    public boolean isDev() {
        return "dev".equalsIgnoreCase(activeProfile);
    }

    public boolean isTest() {
        return "test".equalsIgnoreCase(activeProfile);
    }
}