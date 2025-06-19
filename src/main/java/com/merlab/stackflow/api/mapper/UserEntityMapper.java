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

import com.merlab.stackflow.api.dto.UserEntityDTO;
import com.merlab.stackflow.persistence.entity.*;

import java.util.List;

/**
 * Mapper provisional para UserEntityDTO.
 * Mapea entre UserEntityDTO y Users entity directamente.
 * Se eliminará una vez que existan todos los DTOs y mappers requeridos.
 */
public class UserEntityMapper {

    public static Users toEntity(UserEntityDTO dto) {
        if (dto == null) return null;

        Users user = new Users();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPassword()); // Se supone que viene ya hasheado
        user.setCreatedAt(dto.getCreatedAt());

        user.setProjectsList(dto.getProjectsList());
        user.setProjectsList1(dto.getProjectsList1());
        user.setScenarioRunsList(dto.getScenarioRunsList());
        user.setScenariosList(dto.getScenariosList());
        user.setUserSettingsList(dto.getUserSettingsList());
        user.setPipelinesList(dto.getPipelinesList());
        user.setDbConnectionsList(dto.getDbConnectionsList());
        user.setSchedulesList(dto.getSchedulesList());
        user.setModelsList(dto.getModelsList());
        user.setJobQueueList(dto.getJobQueueList());
        user.setHistoryList(dto.getHistoryList());
        user.setSignalGroupsList(dto.getSignalGroupsList());
        user.setSignalsList(dto.getSignalsList());
        user.setMemorySlotsList(dto.getMemorySlotsList());
        user.setRolesList(user.getRolesList());

        return user;
    }

    public static UserEntityDTO toDTO(Users user) {
        if (user == null) return null;

        UserEntityDTO dto = new UserEntityDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPasswordHash()); // cuidado con exponer esto en la API si no es necesario
        dto.setCreatedAt(user.getCreatedAt());

        dto.setProjectsList(user.getProjectsList());
        dto.setProjectsList1(user.getProjectsList1());
        dto.setScenarioRunsList(user.getScenarioRunsList());
        dto.setScenariosList(user.getScenariosList());
        dto.setUserSettingsList(user.getUserSettingsList());
        dto.setPipelinesList(user.getPipelinesList());
        dto.setDbConnectionsList(user.getDbConnectionsList());
        dto.setSchedulesList(user.getSchedulesList());
        dto.setModelsList(user.getModelsList());
        dto.setJobQueueList(user.getJobQueueList());
        dto.setHistoryList(user.getHistoryList());
        dto.setSignalGroupsList(user.getSignalGroupsList());
        dto.setSignalsList(user.getSignalsList());
        dto.setMemorySlotsList(user.getMemorySlotsList());
        dto.setRolesList(user.getRolesList()); //user.getRolesList()

        return dto;
    }
    
    @Override
    public String toString() {
        return "UserMapper2{}";
    }
}
