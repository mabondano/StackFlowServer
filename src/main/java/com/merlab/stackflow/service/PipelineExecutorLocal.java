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
public interface PipelineExecutorLocal {
    /**
     * Restaura el estado del pipeline a partir de un snapshot serializado.
     */
    void loadState(Long pipelineId, byte[] state);
}