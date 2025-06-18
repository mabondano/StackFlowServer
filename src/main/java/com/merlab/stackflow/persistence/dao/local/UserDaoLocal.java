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

package com.merlab.stackflow.persistence.dao.local;

import com.merlab.stackflow.persistence.entity.Users;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author merly
 */
@Local
public interface UserDaoLocal {
    
    /**
     * Busca un usuario por su identificador.
     */
    Users findById(Long id);

    /**
     * Devuelve todos los usuarios.
     */
    List<Users> findAll();

    /**
     * Persiste un nuevo usuario.
     */
    void save(Users user);

    /**
     * Actualiza un usuario existente.
     */
    void update(Users user);

    /**
     * Elimina un usuario.
     */
    void delete(Users user);

    /**
     * (Opcional) Busca un usuario por su nombre de usuario.
     */
    Users findByUsername(String username);
    
}
