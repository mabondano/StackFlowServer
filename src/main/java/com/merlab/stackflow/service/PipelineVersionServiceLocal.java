/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.merlab.stackflow.service;

/**
 *
 * @author merly
 */
import jakarta.ejb.Local;

@Local
public interface PipelineVersionServiceLocal {
    void applyOperation(Long pipelineId, String operation, String inputsJson, byte[] outputData);
    byte[] rollback(Long pipelineId);
}