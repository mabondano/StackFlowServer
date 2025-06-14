/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merlab.stackflow.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO con validaciones
 * @author merly
 */
public class UserDTO {
    public Long   id;
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 100, message = "Username must be 3–100 characters")
    public String username;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    public String email;
    // no enviamos passwordHash    
}
