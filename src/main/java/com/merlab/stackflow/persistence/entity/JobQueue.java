/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author merly
 */
@Entity
@Table(name = "job_queue")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobQueue.findAll", query = "SELECT j FROM JobQueue j"),
    @NamedQuery(name = "JobQueue.findById", query = "SELECT j FROM JobQueue j WHERE j.id = :id"),
    @NamedQuery(name = "JobQueue.findByType", query = "SELECT j FROM JobQueue j WHERE j.type = :type"),
    @NamedQuery(name = "JobQueue.findByEntityType", query = "SELECT j FROM JobQueue j WHERE j.entityType = :entityType"),
    @NamedQuery(name = "JobQueue.findByEntityId", query = "SELECT j FROM JobQueue j WHERE j.entityId = :entityId"),
    @NamedQuery(name = "JobQueue.findByStatus", query = "SELECT j FROM JobQueue j WHERE j.status = :status"),
    @NamedQuery(name = "JobQueue.findByPriority", query = "SELECT j FROM JobQueue j WHERE j.priority = :priority"),
    @NamedQuery(name = "JobQueue.findByCreatedAt", query = "SELECT j FROM JobQueue j WHERE j.createdAt = :createdAt")})
public class JobQueue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "type")
    private String type;
    @Size(max = 100)
    @Column(name = "entity_type")
    private String entityType;
    @Column(name = "entity_id")
    private BigInteger entityId;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "payload")
    private String payload;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId", fetch = FetchType.LAZY)
    private List<Schedules> schedulesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId", fetch = FetchType.LAZY)
    private List<PostProcessTasks> postProcessTasksList;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users userId;

    public JobQueue() {
    }

    public JobQueue(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public BigInteger getEntityId() {
        return entityId;
    }

    public void setEntityId(BigInteger entityId) {
        this.entityId = entityId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Schedules> getSchedulesList() {
        return schedulesList;
    }

    public void setSchedulesList(List<Schedules> schedulesList) {
        this.schedulesList = schedulesList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<PostProcessTasks> getPostProcessTasksList() {
        return postProcessTasksList;
    }

    public void setPostProcessTasksList(List<PostProcessTasks> postProcessTasksList) {
        this.postProcessTasksList = postProcessTasksList;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof JobQueue)) {
            return false;
        }
        JobQueue other = (JobQueue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.JobQueue[ id=" + id + " ]";
    }
    
}
