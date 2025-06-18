/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.merlab.stackflow.persistence.dao.local;

import com.merlab.stackflow.persistence.entity.PipelineVersion;

/**
 *
 * @author merly
 */
public interface PipelineVersionDaoLocal {
    int nextVersion(Long pipelineId);
    void save(PipelineVersion v);
    PipelineVersion findByVersion(Long pipelineId, int version);
    PipelineVersion findLatest(Long pipelineId);
    void deleteOlderThan(Long pipelineId, int keepLastN);
}

