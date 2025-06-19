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

package com.merlab.stackflow.api.mapper;

import com.merlab.stackflow.api.dto.MemorySlotDTO;
import com.merlab.stackflow.persistence.entity.MemorySlots;

public class MemorySlotMapper {

    public static MemorySlotDTO toDTO(MemorySlots entity) {
        if (entity == null) return null;

        MemorySlotDTO dto = new MemorySlotDTO();
        // TODO: mapear atributos reales
        return dto;
    }

    public static MemorySlots toEntity(MemorySlotDTO dto) {
        if (dto == null) return null;

        MemorySlots entity = new MemorySlots();
        // TODO: mapear atributos reales
        return entity;
    }

    @Override
    public String toString() {
        return "MemorySlotMapper{}";
    }
}

