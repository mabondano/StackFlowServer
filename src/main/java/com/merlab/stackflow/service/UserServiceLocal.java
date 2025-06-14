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






