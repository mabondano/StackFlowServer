/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merlab.stackflow.service;

/**
 *
 * @author merly
 */
import com.merlab.stackflow.persistence.dao.PipelineVersionDaoLocal;
import com.merlab.stackflow.persistence.entity.PipelineVersion;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class PipelineVersionServiceBean implements PipelineVersionServiceLocal {

    @EJB
    private PipelineVersionDaoLocal dao;

    @EJB
    private PipelineExecutorLocal pipelineExecutor;

    private static final int MAX_HISTORY = 4;

    @Override
    public void applyOperation(Long pipelineId, String operation, String inputsJson, byte[] outputData) {
        int ver = dao.nextVersion(pipelineId);
        PipelineVersion v = new PipelineVersion();
        v.setPipelineId(pipelineId);
        v.setVersionNumber(ver);
        v.setOperation(operation);
        v.setInputsJson(inputsJson);
        v.setOutputData(outputData);
        dao.save(v);
        dao.deleteOlderThan(pipelineId, MAX_HISTORY);
    }

    @Override
    public byte[] rollback(Long pipelineId) {
        PipelineVersion latest = dao.findLatest(pipelineId);
        if (latest == null) {
            throw new IllegalStateException("No hay operaciones para rollback");
        }
        int prevVer = latest.getVersionNumber() - 1;
        if (prevVer < 1) {
            throw new IllegalStateException("Ya estás en la versión original");
        }
        PipelineVersion previous = dao.findByVersion(pipelineId, prevVer);
        pipelineExecutor.loadState(pipelineId, previous.getOutputData());
        return previous.getOutputData();
    }
}