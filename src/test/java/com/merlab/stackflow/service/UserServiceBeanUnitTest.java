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

import com.merlab.stackflow.persistence.dao.memory.InMemoryUserDao;
import com.merlab.stackflow.persistence.entity.Users;
import com.merlab.stackflow.service.impl.UserServiceBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceBeanUnitTest {

    private UserServiceBean svc;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        svc = new UserServiceBean();
        // Inyectamos el DAO de prueba
        //svc.userDao = new InMemoryUserDao();
        
        // Inyectamos el DAO mock via reflexión
        InMemoryUserDao inMem = new InMemoryUserDao();
        Field daoField = UserServiceBean.class.getDeclaredField("userDao");
        daoField.setAccessible(true);
        daoField.set(svc, inMem);
    }

    @Test
    void register_and_retrieve_user() {
        Users u = new Users();
        u.setUsername("alice");
        u.setEmail("alice@example.com");
        u.setPasswordHash("secret");
        Users saved = svc.registerUser(u);

        assertNotNull(saved.getId(), "El ID no debe ser null");
        assertNotEquals("secret", saved.getPasswordHash(), "La contraseña debe estar hasheada");

        Users fetched = svc.getUserByUsername("alice");
        assertEquals(saved.getId(), fetched.getId());
        assertEquals("alice", fetched.getUsername());
    }
}
