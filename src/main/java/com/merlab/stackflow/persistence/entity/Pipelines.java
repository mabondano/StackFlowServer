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
import jakarta.persistence.OneToOne;
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
@Table(name = "pipelines")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pipelines.findAll", query = "SELECT p FROM Pipelines p"),
    @NamedQuery(name = "Pipelines.findById", query = "SELECT p FROM Pipelines p WHERE p.id = :id"),
    @NamedQuery(name = "Pipelines.findByName", query = "SELECT p FROM Pipelines p WHERE p.name = :name"),
    @NamedQuery(name = "Pipelines.findByCreatedAt", query = "SELECT p FROM Pipelines p WHERE p.createdAt = :createdAt")})
public class Pipelines implements Serializable {

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
    @Size(max = 2147483647)
    @Column(name = "config")
    private String config;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pipelineId", fetch = FetchType.LAZY)
    private List<PipelineVersion> pipelineVersionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pipelineId", fetch = FetchType.LAZY)
    private List<PipelineSteps> pipelineStepsList;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users userId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pipelines", fetch = FetchType.LAZY)
    private PipelineCurrentVersion pipelineCurrentVersion;

    public Pipelines() {
    }

    public Pipelines(Long id) {
        this.id = id;
    }

    public Pipelines(Long id, String name) {
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
    public List<PipelineVersion> getPipelineVersionList() {
        return pipelineVersionList;
    }

    public void setPipelineVersionList(List<PipelineVersion> pipelineVersionList) {
        this.pipelineVersionList = pipelineVersionList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<PipelineSteps> getPipelineStepsList() {
        return pipelineStepsList;
    }

    public void setPipelineStepsList(List<PipelineSteps> pipelineStepsList) {
        this.pipelineStepsList = pipelineStepsList;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public PipelineCurrentVersion getPipelineCurrentVersion() {
        return pipelineCurrentVersion;
    }

    public void setPipelineCurrentVersion(PipelineCurrentVersion pipelineCurrentVersion) {
        this.pipelineCurrentVersion = pipelineCurrentVersion;
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
        if (!(object instanceof Pipelines)) {
            return false;
        }
        Pipelines other = (Pipelines) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.Pipelines[ id=" + id + " ]";
    }
    
}
