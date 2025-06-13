/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.merlab.stackflow.service;

import com.merlab.stackflow.persistence.entity.Users;
import java.util.List;
import jakarta.ejb.Local;

/**
 * Servicio de negocio para operaciones sobre la entidad Users.
 * @author merly
 */
@Local
public interface UserServiceLocal {

    /**
     * Registra un nuevo usuario. Debe encargarse de validar y hashear la contraseña.
     *
     * @param user entidad Users con los datos del nuevo usuario
     * @return la entidad Users persistida, con su ID generado
     */
    Users registerUser(Users user);

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return la entidad Users encontrada, o null si no existe
     */
    Users getUserById(Long id);

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario a buscar
     * @return la entidad Users correspondiente, o null si no se encuentra
     */
    Users getUserByUsername(String username);

    /**
     * Devuelve todos los usuarios registrados.
     *
     * @return lista de todas las entidades Users en la base de datos
     */
    List<Users> listUsers();

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param user entidad Users con los datos actualizados (debe incluir ID)
     * @return la entidad Users actualizada
     */
    Users updateUser(Users user);

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario a eliminar
     */
    void deleteUser(Long id);
}






