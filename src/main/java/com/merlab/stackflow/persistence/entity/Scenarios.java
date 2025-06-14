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

package com.merlab.stackflow.persistence.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author merly
 */
@Entity
@Table(name = "scenarios")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scenarios.findAll", query = "SELECT s FROM Scenarios s"),
    @NamedQuery(name = "Scenarios.findById", query = "SELECT s FROM Scenarios s WHERE s.id = :id"),
    @NamedQuery(name = "Scenarios.findByName", query = "SELECT s FROM Scenarios s WHERE s.name = :name"),
    @NamedQuery(name = "Scenarios.findByCreatedAt", query = "SELECT s FROM Scenarios s WHERE s.createdAt = :createdAt")})
public class Scenarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "config")
    private String config;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scenarioId", fetch = FetchType.LAZY)
    private List<ScenarioRuns> scenarioRunsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scenarioId", fetch = FetchType.LAZY)
    private List<ScenarioEntities> scenarioEntitiesList;
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users creatorId;

    public Scenarios() {
    }

    public Scenarios(Long id) {
        this.id = id;
    }

    public Scenarios(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<ScenarioRuns> getScenarioRunsList() {
        return scenarioRunsList;
    }

    public void setScenarioRunsList(List<ScenarioRuns> scenarioRunsList) {
        this.scenarioRunsList = scenarioRunsList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<ScenarioEntities> getScenarioEntitiesList() {
        return scenarioEntitiesList;
    }

    public void setScenarioEntitiesList(List<ScenarioEntities> scenarioEntitiesList) {
        this.scenarioEntitiesList = scenarioEntitiesList;
    }

    public Users getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Users creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scenarios)) {
            return false;
        }
        Scenarios other = (Scenarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.Scenarios[ id=" + id + " ]";
    }
    
}
