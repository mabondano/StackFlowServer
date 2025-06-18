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

import jakarta.ejb.Stateless;
import com.merlab.stackflow.persistence.dao.local.UserDaoLocal;
import com.merlab.stackflow.persistence.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author merly
 */
@Stateless
public class UserDaoBean implements UserDaoLocal {

    @PersistenceContext(unitName = "StackflowPU")
    private EntityManager em;

    @Override
    public Users findById(Long id) {
        return em.find(Users.class, id);
    }

    @Override
    public List<Users> findAll() {
        return em.createQuery("SELECT u FROM Users u", Users.class)
                 .getResultList();
    }

    @Override
    public void save(Users user) {
        em.persist(user);
    }

    @Override
    public void update(Users user) {
        em.merge(user);
    }

    @Override
    public void delete(Users user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public Users findByUsername(String username) {
        try {
            return em.createQuery(
                    "SELECT u FROM Users u WHERE u.username = :uname", Users.class)
                     .setParameter("uname", username)
                     .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}
