/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merlab.stackflow.persistence.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author merly
 */
@Entity
@Table(name = "pipeline_current_version")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PipelineCurrentVersion.findAll", query = "SELECT p FROM PipelineCurrentVersion p"),
    @NamedQuery(name = "PipelineCurrentVersion.findByPipelineId", query = "SELECT p FROM PipelineCurrentVersion p WHERE p.pipelineId = :pipelineId"),
    @NamedQuery(name = "PipelineCurrentVersion.findByCurrentVersion", query = "SELECT p FROM PipelineCurrentVersion p WHERE p.currentVersion = :currentVersion")})
public class PipelineCurrentVersion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pipeline_id")
    private Long pipelineId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "current_version")
    private int currentVersion;
    @JoinColumn(name = "pipeline_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Pipelines pipelines;

    public PipelineCurrentVersion() {
    }

    public PipelineCurrentVersion(Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public PipelineCurrentVersion(Long pipelineId, int currentVersion) {
        this.pipelineId = pipelineId;
        this.currentVersion = currentVersion;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Pipelines getPipelines() {
        return pipelines;
    }

    public void setPipelines(Pipelines pipelines) {
        this.pipelines = pipelines;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pipelineId != null ? pipelineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PipelineCurrentVersion)) {
            return false;
        }
        PipelineCurrentVersion other = (PipelineCurrentVersion) object;
        if ((this.pipelineId == null && other.pipelineId != null) || (this.pipelineId != null && !this.pipelineId.equals(other.pipelineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.PipelineCurrentVersion[ pipelineId=" + pipelineId + " ]";
    }
    
}
