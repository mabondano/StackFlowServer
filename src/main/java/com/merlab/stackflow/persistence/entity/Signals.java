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
import jakarta.persistence.ManyToMany;
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
@Table(name = "signals")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Signals.findAll", query = "SELECT s FROM Signals s"),
    @NamedQuery(name = "Signals.findById", query = "SELECT s FROM Signals s WHERE s.id = :id"),
    @NamedQuery(name = "Signals.findByName", query = "SELECT s FROM Signals s WHERE s.name = :name"),
    @NamedQuery(name = "Signals.findByType", query = "SELECT s FROM Signals s WHERE s.type = :type"),
    @NamedQuery(name = "Signals.findByCreatedAt", query = "SELECT s FROM Signals s WHERE s.createdAt = :createdAt")})
public class Signals implements Serializable {

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
    @Size(max = 100)
    @Column(name = "type")
    private String type;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToMany(mappedBy = "signalsList", fetch = FetchType.LAZY)
    private List<SignalGroups> signalGroupsList;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Projects projectId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "signalId", fetch = FetchType.LAZY)
    private List<SignalData> signalDataList;

    public Signals() {
    }

    public Signals(Long id) {
        this.id = id;
    }

    public Signals(Long id, String name) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<SignalGroups> getSignalGroupsList() {
        return signalGroupsList;
    }

    public void setSignalGroupsList(List<SignalGroups> signalGroupsList) {
        this.signalGroupsList = signalGroupsList;
    }

    public Projects getProjectId() {
        return projectId;
    }

    public void setProjectId(Projects projectId) {
        this.projectId = projectId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<SignalData> getSignalDataList() {
        return signalDataList;
    }

    public void setSignalDataList(List<SignalData> signalDataList) {
        this.signalDataList = signalDataList;
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
        if (!(object instanceof Signals)) {
            return false;
        }
        Signals other = (Signals) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.Signals[ id=" + id + " ]";
    }
    
}
