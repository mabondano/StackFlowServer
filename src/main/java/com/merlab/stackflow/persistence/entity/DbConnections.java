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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author merly
 */
@Entity
@Table(name = "db_connections")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbConnections.findAll", query = "SELECT d FROM DbConnections d"),
    @NamedQuery(name = "DbConnections.findById", query = "SELECT d FROM DbConnections d WHERE d.id = :id"),
    @NamedQuery(name = "DbConnections.findByName", query = "SELECT d FROM DbConnections d WHERE d.name = :name"),
    @NamedQuery(name = "DbConnections.findByType", query = "SELECT d FROM DbConnections d WHERE d.type = :type"),
    @NamedQuery(name = "DbConnections.findByHost", query = "SELECT d FROM DbConnections d WHERE d.host = :host"),
    @NamedQuery(name = "DbConnections.findByPort", query = "SELECT d FROM DbConnections d WHERE d.port = :port"),
    @NamedQuery(name = "DbConnections.findByDbName", query = "SELECT d FROM DbConnections d WHERE d.dbName = :dbName"),
    @NamedQuery(name = "DbConnections.findByUser", query = "SELECT d FROM DbConnections d WHERE d.user = :user"),
    @NamedQuery(name = "DbConnections.findByPasswordEncrypted", query = "SELECT d FROM DbConnections d WHERE d.passwordEncrypted = :passwordEncrypted"),
    @NamedQuery(name = "DbConnections.findByCreatedAt", query = "SELECT d FROM DbConnections d WHERE d.createdAt = :createdAt")})
public class DbConnections implements Serializable {

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
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 255)
    @Column(name = "host")
    private String host;
    @Column(name = "port")
    private Integer port;
    @Size(max = 150)
    @Column(name = "db_name")
    private String dbName;
    @Size(max = 100)
    @Column(name = "user")
    private String user;
    @Size(max = 255)
    @Column(name = "password_encrypted")
    private String passwordEncrypted;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users userId;

    public DbConnections() {
    }

    public DbConnections(Long id) {
        this.id = id;
    }

    public DbConnections(Long id, String name) {
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        if (!(object instanceof DbConnections)) {
            return false;
        }
        DbConnections other = (DbConnections) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.DbConnections[ id=" + id + " ]";
    }
    
}
