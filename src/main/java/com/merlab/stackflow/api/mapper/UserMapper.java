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

import com.merlab.stackflow.api.dto.UserDTO;
import com.merlab.stackflow.persistence.entity.Users;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(Users entity) {
        if (entity == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getProjectsList() != null) {
            dto.setProjectsList(entity.getProjectsList().stream()
                .map(ProjectMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getProjectsList1() != null) {
            dto.setProjectsList1(entity.getProjectsList1().stream()
                .map(ProjectMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getRolesList() != null) {
            dto.setRolesList(entity.getRolesList().stream()
                .map(RoleMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getScenarioRunsList() != null) {
            dto.setScenarioRunsList(entity.getScenarioRunsList().stream()
                .map(ScenarioRunMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getScenariosList() != null) {
            dto.setScenariosList(entity.getScenariosList().stream()
                .map(ScenarioMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getUserSettingsList() != null) {
            dto.setUserSettingsList(entity.getUserSettingsList().stream()
                .map(UserSettingMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getPipelinesList() != null) {
            dto.setPipelinesList(entity.getPipelinesList().stream()
                .map(PipelineMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getDbConnectionsList() != null) {
            dto.setDbConnectionsList(entity.getDbConnectionsList().stream()
                .map(DbConnectionMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getSchedulesList() != null) {
            dto.setSchedulesList(entity.getSchedulesList().stream()
                .map(ScheduleMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getModelsList() != null) {
            dto.setModelsList(entity.getModelsList().stream()
                .map(ModelMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getJobQueueList() != null) {
            dto.setJobQueueList(entity.getJobQueueList().stream()
                .map(JobQueueMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getHistoryList() != null) {
            dto.setHistoryList(entity.getHistoryList().stream()
                .map(HistoryMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getSignalGroupsList() != null) {
            dto.setSignalGroupsList(entity.getSignalGroupsList().stream()
                .map(SignalGroupMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getSignalsList() != null) {
            dto.setSignalsList(entity.getSignalsList().stream()
                .map(SignalMapper::toDTO).collect(Collectors.toList()));
        }

        if (entity.getMemorySlotsList() != null) {
            dto.setMemorySlotsList(entity.getMemorySlotsList().stream()
                .map(MemorySlotMapper::toDTO).collect(Collectors.toList()));
        }

        return dto;
    }

    public static Users toEntity(UserDTO dto) {
        if (dto == null) return null;

        Users entity = new Users();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setCreatedAt(dto.getCreatedAt());

        if (dto.getProjectsList() != null) {
            entity.setProjectsList(dto.getProjectsList().stream()
                .map(ProjectMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getProjectsList1() != null) {
            entity.setProjectsList1(dto.getProjectsList1().stream()
                .map(ProjectMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getRolesList() != null) {
            entity.setRolesList(dto.getRolesList().stream()
                .map(RoleMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getScenarioRunsList() != null) {
            entity.setScenarioRunsList(dto.getScenarioRunsList().stream()
                .map(ScenarioRunMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getScenariosList() != null) {
            entity.setScenariosList(dto.getScenariosList().stream()
                .map(ScenarioMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getUserSettingsList() != null) {
            entity.setUserSettingsList(dto.getUserSettingsList().stream()
                .map(UserSettingMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getPipelinesList() != null) {
            entity.setPipelinesList(dto.getPipelinesList().stream()
                .map(PipelineMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getDbConnectionsList() != null) {
            entity.setDbConnectionsList(dto.getDbConnectionsList().stream()
                .map(DbConnectionMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getSchedulesList() != null) {
            entity.setSchedulesList(dto.getSchedulesList().stream()
                .map(ScheduleMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getModelsList() != null) {
            entity.setModelsList(dto.getModelsList().stream()
                .map(ModelMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getJobQueueList() != null) {
            entity.setJobQueueList(dto.getJobQueueList().stream()
                .map(JobQueueMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getHistoryList() != null) {
            entity.setHistoryList(dto.getHistoryList().stream()
                .map(HistoryMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getSignalGroupsList() != null) {
            entity.setSignalGroupsList(dto.getSignalGroupsList().stream()
                .map(SignalGroupMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getSignalsList() != null) {
            entity.setSignalsList(dto.getSignalsList().stream()
                .map(SignalMapper::toEntity).collect(Collectors.toList()));
        }

        if (dto.getMemorySlotsList() != null) {
            entity.setMemorySlotsList(dto.getMemorySlotsList().stream()
                .map(MemorySlotMapper::toEntity).collect(Collectors.toList()));
        }

        return entity;
    }

    @Override
    public String toString() {
        return "UserMapper{}";
    }
}

