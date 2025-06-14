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

/**
 *
 * @author merly
 */
public class UserServiceBeanTest {
    /*
    static EJBContainer container;
    static Context ctx;

    @BeforeAll
    static void init() {
        // Arranca un contenedor EJB SE (Weld) embebido
        Properties props = new Properties();
        // Si tienes persistence.xml en test/resources, el contenedor lo detecta
        container = EJBContainer.createEJBContainer(props);
        ctx = container.getContext();
    }

    @AfterAll
    static void shutdown() {
        container.close();
    }

    @Test
    void registerAndRetrieve() throws Exception {
        // Lookup de tu bean vía JNDI embebido
        UserServiceLocal svc = (UserServiceLocal)
            ctx.lookup("java:global/classes/com.merlab.stackflow.service.UserServiceBean!com.merlab.stackflow.service.UserServiceLocal");

        // Crea un usuario de prueba
        Users u = new Users();
        u.setUsername("alice");
        u.setEmail("alice@example.com");
        u.setPasswordHash("secret");
        Users saved = svc.registerUser(u);
        assertNotNull(saved.getId(), "El ID debe asignarse al persistir");

        // Recupera por ID y verifica
        Users fetched = svc.getUserById(saved.getId());
        assertEquals("alice", fetched.getUsername());
    }
*/    
}

