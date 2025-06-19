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

package com.merlab.stackflow.persistence.dao.bean;

import com.merlab.stackflow.persistence.dao.local.RoleDaoLocal;
import com.merlab.stackflow.persistence.entity.Roles;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleDaoBean implements RoleDaoLocal {

    @PersistenceContext(unitName = "StackflowPU") 
    private EntityManager em;

    @Override
    public void create(Roles role) {
        em.persist(role);
    }

    @Override
    public void save(Roles role) {
        em.merge(role);
    }

    @Override
    public void update(Roles role) {
        em.merge(role);
    }

    @Override
    public void delete(Roles role) {
        Roles managed = em.contains(role) ? role : em.merge(role);
        em.remove(managed);
    }

    @Override
    public Roles findById(Long id) {
        return em.find(Roles.class, id);
    }

    @Override
    public Roles findByName(String name) {
        try {
            return em.createNamedQuery("Roles.findByName", Roles.class)
                     .setParameter("name", name)
                     .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Roles> findAll() {
        return em.createNamedQuery("Roles.findAll", Roles.class)
                 .getResultList();
    }
}
