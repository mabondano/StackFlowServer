
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

package com.merlab.stackflow.test;

import com.merlab.stackflow.persistence.entity.Users;
import com.merlab.stackflow.persistence.entity.Roles;

import com.merlab.stackflow.persistence.dao.local.UserDaoLocal;
import com.merlab.stackflow.persistence.dao.local.RoleDaoLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.Arrays;

import org.mindrot.jbcrypt.BCrypt;

@Stateless
public class TestManager implements TestManagerLocal{
    @EJB
    private UserDaoLocal userDao;

    @EJB
    private RoleDaoLocal roleDao;
    
    @Override
    public void runAllTests(){
        System.out.println("TestManager *** ***                                   *** *** " );
        bootstrapUsers();
    
    }

    public void bootstrapUsers() {
        System.out.println("TestManager *** ***                                   *** *** " );
        // 1. Crear roles si no existen
        createRoleIfMissing("ROLE_SUPERADMIN");
        createRoleIfMissing("ROLE_NORMAL");

        // 2. Crear usuarios
        createUser("admin", "admin@root.com", "admin123", "ROLE_SUPERADMIN");
        createUser("buddy", "buddy@local.com", "buddy123", "ROLE_NORMAL");
        createUser("milly", "milly@local.com", "milly123", "ROLE_NORMAL");
        createUser("milo",  "milo@local.com",  "milo123",  "ROLE_NORMAL");
        createUser("miki",  "milo@local.com",  "milo123",  "ROLE_NORMAL");
        createUser("joe",  "milo@local.com",  "milo123",  "ROLE_NORMAL");

        System.out.println("Usuarios bootstrap completado.");
    }

    private void createRoleIfMissing(String roleName) {
        Roles role = roleDao.findByName(roleName);
        if (role == null) {
            role = new Roles();
            role.setName(roleName);
            roleDao.save(role);
            System.out.println("Rol creado: " + roleName);
        }
    }

    private void createUser(String username, String email, String plainPassword, String roleName) {
        if (userDao.findByUsername(username) != null) {
            System.out.println("Usuario ya existe: " + username);
            return;
        }

        Users user = new Users();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(BCrypt.hashpw(plainPassword, BCrypt.gensalt()));
        user.setRolesList(Arrays.asList(roleDao.findByName(roleName)));

        userDao.save(user);
        System.out.println("Usuario creado: " + username + " [" + roleName + "]");
    }

}
