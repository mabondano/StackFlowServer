/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merlab.stackflow.service;

/**
 *
 * @author merly
 */
import jakarta.ejb.Stateless;

@Stateless
public class PipelineExecutorBean implements PipelineExecutorLocal {
    @Override
    public void loadState(Long pipelineId, byte[] state) {
        // aquí la lógica para inyectar el estado en el pipeline
    }
}