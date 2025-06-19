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

package com.merlab.stackflow.api.dto;

import java.io.Serializable;

/**
 * Plantilla base para DTOs.
 * @author merly
 */
public class JobQueueDTO implements Serializable {

    private Long id;
    private String name;

    public JobQueueDTO() {
    }

    public JobQueueDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // --- toString ---
    @Override
    public String toString() {
        return "GenericDTO{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
