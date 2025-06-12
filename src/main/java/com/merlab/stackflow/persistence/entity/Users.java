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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "users")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPasswordHash", query = "SELECT u FROM Users u WHERE u.passwordHash = :passwordHash"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToMany(mappedBy = "usersList", fetch = FetchType.LAZY)
    private List<Projects> projectsList;
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Roles> rolesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId", fetch = FetchType.LAZY)
    private List<Projects> projectsList1;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<ScenarioRuns> scenarioRunsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId", fetch = FetchType.LAZY)
    private List<Scenarios> scenariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<UserSettings> userSettingsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Pipelines> pipelinesList;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<DbConnections> dbConnectionsList;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Schedules> schedulesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Models> modelsList;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<JobQueue> jobQueueList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<History> historyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<SignalGroups> signalGroupsList;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Signals> signalsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<MemorySlots> memorySlotsList;

    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    public Users(Long id, String username, String email, String passwordHash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Projects> getProjectsList1() {
        return projectsList1;
    }

    public void setProjectsList1(List<Projects> projectsList1) {
        this.projectsList1 = projectsList1;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<ScenarioRuns> getScenarioRunsList() {
        return scenarioRunsList;
    }

    public void setScenarioRunsList(List<ScenarioRuns> scenarioRunsList) {
        this.scenarioRunsList = scenarioRunsList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Scenarios> getScenariosList() {
        return scenariosList;
    }

    public void setScenariosList(List<Scenarios> scenariosList) {
        this.scenariosList = scenariosList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<UserSettings> getUserSettingsList() {
        return userSettingsList;
    }

    public void setUserSettingsList(List<UserSettings> userSettingsList) {
        this.userSettingsList = userSettingsList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Pipelines> getPipelinesList() {
        return pipelinesList;
    }

    public void setPipelinesList(List<Pipelines> pipelinesList) {
        this.pipelinesList = pipelinesList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<DbConnections> getDbConnectionsList() {
        return dbConnectionsList;
    }

    public void setDbConnectionsList(List<DbConnections> dbConnectionsList) {
        this.dbConnectionsList = dbConnectionsList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Schedules> getSchedulesList() {
        return schedulesList;
    }

    public void setSchedulesList(List<Schedules> schedulesList) {
        this.schedulesList = schedulesList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Models> getModelsList() {
        return modelsList;
    }

    public void setModelsList(List<Models> modelsList) {
        this.modelsList = modelsList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<JobQueue> getJobQueueList() {
        return jobQueueList;
    }

    public void setJobQueueList(List<JobQueue> jobQueueList) {
        this.jobQueueList = jobQueueList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<SignalGroups> getSignalGroupsList() {
        return signalGroupsList;
    }

    public void setSignalGroupsList(List<SignalGroups> signalGroupsList) {
        this.signalGroupsList = signalGroupsList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<Signals> getSignalsList() {
        return signalsList;
    }

    public void setSignalsList(List<Signals> signalsList) {
        this.signalsList = signalsList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<MemorySlots> getMemorySlotsList() {
        return memorySlotsList;
    }

    public void setMemorySlotsList(List<MemorySlots> memorySlotsList) {
        this.memorySlotsList = memorySlotsList;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.Users[ id=" + id + " ]";
    }
    
}
