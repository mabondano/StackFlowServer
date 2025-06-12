/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merlab.stackflow.persistence.entity;

import jakarta.persistence.Basic;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author merly
 */
@Entity
@Table(name = "pipeline_steps")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PipelineSteps.findAll", query = "SELECT p FROM PipelineSteps p"),
    @NamedQuery(name = "PipelineSteps.findById", query = "SELECT p FROM PipelineSteps p WHERE p.id = :id"),
    @NamedQuery(name = "PipelineSteps.findByStepOrder", query = "SELECT p FROM PipelineSteps p WHERE p.stepOrder = :stepOrder"),
    @NamedQuery(name = "PipelineSteps.findByOperation", query = "SELECT p FROM PipelineSteps p WHERE p.operation = :operation")})
public class PipelineSteps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "step_order")
    private int stepOrder;
    @Size(max = 100)
    @Column(name = "operation")
    private String operation;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "params")
    private String params;
    @JoinColumn(name = "pipeline_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pipelines pipelineId;

    public PipelineSteps() {
    }

    public PipelineSteps(Long id) {
        this.id = id;
    }

    public PipelineSteps(Long id, int stepOrder) {
        this.id = id;
        this.stepOrder = stepOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Pipelines getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Pipelines pipelineId) {
        this.pipelineId = pipelineId;
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
        if (!(object instanceof PipelineSteps)) {
            return false;
        }
        PipelineSteps other = (PipelineSteps) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.PipelineSteps[ id=" + id + " ]";
    }
    
}
