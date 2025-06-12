/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merlab.stackflow.persistence.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author merly
 */
@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "value")
    private String value;
    /*
    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private int id;
    */
    
    /*
    @Column(nullable = false)
    private String value;
    */

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }


    @Override
    public String toString() {
        return "com.merlab.stackflow.persistence.entity.TestEntity[ id=" + id + " ]";
    }

    public TestEntity() {
    }

    public TestEntity(Integer id) {
        this.id = id;
    }

    public TestEntity(Integer id, String value) {
        this.id = id;
        this.value = value;
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
        if (!(object instanceof TestEntity)) {
            return false;
        }
        TestEntity other = (TestEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
