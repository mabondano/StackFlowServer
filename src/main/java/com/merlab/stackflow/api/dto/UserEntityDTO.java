
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

import com.merlab.stackflow.persistence.entity.DbConnections;
import com.merlab.stackflow.persistence.entity.History;
import com.merlab.stackflow.persistence.entity.JobQueue;
import com.merlab.stackflow.persistence.entity.MemorySlots;
import com.merlab.stackflow.persistence.entity.Models;
import com.merlab.stackflow.persistence.entity.Pipelines;
import com.merlab.stackflow.persistence.entity.Projects;
import com.merlab.stackflow.persistence.entity.Roles;
import com.merlab.stackflow.persistence.entity.ScenarioRuns;
import com.merlab.stackflow.persistence.entity.Scenarios;
import com.merlab.stackflow.persistence.entity.Schedules;
import com.merlab.stackflow.persistence.entity.SignalGroups;
import com.merlab.stackflow.persistence.entity.Signals;
import com.merlab.stackflow.persistence.entity.UserSettings;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO provisional que usa entidades directamente (no mapea a DTOs).
 * Se eliminará cuando todos los mappers y DTOs estén listos.
 */

public class UserEntityDTO implements Serializable {

    public Long id;
    public String username;
    public String email;
    public String password; // Este campo sustituye passwordHash en la entrada
    public Date createdAt;

    public List<Projects> projectsList;
    public List<Roles> rolesList;
    public List<Projects> projectsList1;
    public List<ScenarioRuns> scenarioRunsList;
    public List<Scenarios> scenariosList;
    public List<UserSettings> userSettingsList;
    public List<Pipelines> pipelinesList;
    public List<DbConnections> dbConnectionsList;
    public List<Schedules> schedulesList;
    public List<Models> modelsList;
    public List<JobQueue> jobQueueList;
    public List<History> historyList;
    public List<SignalGroups> signalGroupsList;
    public List<Signals> signalsList;
    public List<MemorySlots> memorySlotsList;

    public UserEntityDTO() {
    }

    public UserEntityDTO(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    public List<Projects> getProjectsList1() {
        return projectsList1;
    }

    public void setProjectsList1(List<Projects> projectsList1) {
        this.projectsList1 = projectsList1;
    }

    public List<ScenarioRuns> getScenarioRunsList() {
        return scenarioRunsList;
    }

    public void setScenarioRunsList(List<ScenarioRuns> scenarioRunsList) {
        this.scenarioRunsList = scenarioRunsList;
    }

    public List<Scenarios> getScenariosList() {
        return scenariosList;
    }

    public void setScenariosList(List<Scenarios> scenariosList) {
        this.scenariosList = scenariosList;
    }

    public List<UserSettings> getUserSettingsList() {
        return userSettingsList;
    }

    public void setUserSettingsList(List<UserSettings> userSettingsList) {
        this.userSettingsList = userSettingsList;
    }

    public List<Pipelines> getPipelinesList() {
        return pipelinesList;
    }

    public void setPipelinesList(List<Pipelines> pipelinesList) {
        this.pipelinesList = pipelinesList;
    }

    public List<DbConnections> getDbConnectionsList() {
        return dbConnectionsList;
    }

    public void setDbConnectionsList(List<DbConnections> dbConnectionsList) {
        this.dbConnectionsList = dbConnectionsList;
    }

    public List<Schedules> getSchedulesList() {
        return schedulesList;
    }

    public void setSchedulesList(List<Schedules> schedulesList) {
        this.schedulesList = schedulesList;
    }

    public List<Models> getModelsList() {
        return modelsList;
    }

    public void setModelsList(List<Models> modelsList) {
        this.modelsList = modelsList;
    }

    public List<JobQueue> getJobQueueList() {
        return jobQueueList;
    }

    public void setJobQueueList(List<JobQueue> jobQueueList) {
        this.jobQueueList = jobQueueList;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public List<SignalGroups> getSignalGroupsList() {
        return signalGroupsList;
    }

    public void setSignalGroupsList(List<SignalGroups> signalGroupsList) {
        this.signalGroupsList = signalGroupsList;
    }

    public List<Signals> getSignalsList() {
        return signalsList;
    }

    public void setSignalsList(List<Signals> signalsList) {
        this.signalsList = signalsList;
    }

    public List<MemorySlots> getMemorySlotsList() {
        return memorySlotsList;
    }

    public void setMemorySlotsList(List<MemorySlots> memorySlotsList) {
        this.memorySlotsList = memorySlotsList;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.api.dto.UserDTO[ id=" + id + " ]";
    }
}
