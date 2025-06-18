package com.merlab.stackflow.persistence.dao.bean;



import com.merlab.stackflow.persistence.dao.local.PipelineVersionDaoLocal;


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

/**
 *
 * @author merly
 */
import com.merlab.stackflow.persistence.entity.PipelineVersion;
import jakarta.ejb.Stateless;
import jakarta.ejb.Local;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Local(PipelineVersionDaoLocal.class)
public class PipelineVersionDao implements PipelineVersionDaoLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public int nextVersion(Long pid) {
        Integer max = em.createQuery(
            "SELECT MAX(v.versionNumber) FROM PipelineVersion v WHERE v.pipelineId = :pid", Integer.class)
            .setParameter("pid", pid)
            .getSingleResult();
        return (max == null ? 0 : max) + 1;
    }

    @Override
    public void save(PipelineVersion v) {
        em.persist(v);
    }

    @Override
    public PipelineVersion findByVersion(Long pid, int ver) {
        return em.createQuery(
            "FROM PipelineVersion v WHERE v.pipelineId=:pid AND v.versionNumber=:ver", PipelineVersion.class)
            .setParameter("pid", pid)
            .setParameter("ver", ver)
            .getSingleResult();
    }

    @Override
    public PipelineVersion findLatest(Long pid) {
        return em.createQuery(
            "FROM PipelineVersion v WHERE v.pipelineId=:pid ORDER BY v.versionNumber DESC", PipelineVersion.class)
            .setParameter("pid", pid)
            .setMaxResults(1)
            .getSingleResult();
    }

    @Override
    public void deleteOlderThan(Long pid, int keep) {
        int latest = nextVersion(pid) - 1;
        int threshold = latest - keep + 1;
        em.createQuery("DELETE FROM PipelineVersion v WHERE v.pipelineId=:pid AND v.versionNumber < :th")
          .setParameter("pid", pid)
          .setParameter("th", threshold)
          .executeUpdate();
    }
}