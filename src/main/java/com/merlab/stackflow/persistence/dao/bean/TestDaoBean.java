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

import com.merlab.stackflow.persistence.dao.local.TestDaoLocal;
import com.merlab.stackflow.persistence.entity.TestEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TestDaoBean implements TestDaoLocal{
    
    private static final Logger log = LoggerFactory.getLogger(TestDaoBean.class);

    @PersistenceContext(unitName = "StackflowPU")
    private EntityManager em;

    @Override
    public TestEntity findById(int id) {
        return em.find(TestEntity.class, id);
    }

    @Override
    public List<TestEntity> findAll() {
        return em.createQuery("SELECT t FROM TestEntity t", TestEntity.class).getResultList();
    }

    @Override
    public void save(TestEntity entity) {
        em.persist(entity);
    }

    @Override
    public void update(TestEntity entity) {
        em.merge(entity);
    }

    @Override
    public void delete(TestEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
}
