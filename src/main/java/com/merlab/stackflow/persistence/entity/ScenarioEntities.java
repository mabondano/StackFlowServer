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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author merly
 */
@Entity
@Table(name = "scenario_entities")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScenarioEntities.findAll", query = "SELECT s FROM ScenarioEntities s"),
    @NamedQuery(name = "ScenarioEntities.findById", query = "SELECT s FROM ScenarioEntities s WHERE s.id = :id"),
    @NamedQuery(name = "ScenarioEntities.findByEntityType", query = "SELECT s FROM ScenarioEntities s WHERE s.entityType = :entityType"),
    @NamedQuery(name = "ScenarioEntities.findByEntityId", query = "SELECT s FROM ScenarioEntities s WHERE s.entityId = :entityId"),
    @NamedQuery(name = "ScenarioEntities.findByRole", query = "SELECT s FROM ScenarioEntities s WHERE s.role = :role")})
public class ScenarioEntities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "entity_type")
    private String entityType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entity_id")
    private long entityId;
    @Size(max = 100)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "scenario_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Scenarios scenarioId;

    public ScenarioEntities() {
    }

    public ScenarioEntities(Long id) {
        this.id = id;
    }

    public ScenarioEntities(Long id, String entityType, long entityId) {
        this.id = id;
        this.entityType = entityType;
        this.entityId = entityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Scenarios getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Scenarios scenarioId) {
        this.scenarioId = scenarioId;
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
        if (!(object instanceof ScenarioEntities)) {
            return false;
        }
        ScenarioEntities other = (ScenarioEntities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.ScenarioEntities[ id=" + id + " ]";
    }
    
}
