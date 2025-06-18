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

import com.merlab.stackflow.persistence.dao.local.UserDaoLocal;
//import com.merlab.stackflow.service.UserServiceLocal;
import com.merlab.stackflow.persistence.entity.Users;
import com.merlab.stackflow.service.impl.UserServiceBean;
//import jakarta.ejb.embeddable.EJBContainer;
import java.lang.reflect.Field;
//import javax.naming.Context;

/**
 *
 * @author merly
 */
public class ServiceTestMain {
    public static void main(String[] args) throws Exception {
        
        // 1) Crear instancia manual del bean
        UserServiceBean svcBean = new UserServiceBean();
        
        // 2) Inyectar el DAO de prueba por reflexión
        UserDaoLocal inMem = new com.merlab.stackflow.persistence.dao.memory.InMemoryUserDao();
        Field daoField = UserServiceBean.class.getDeclaredField("userDao");
        daoField.setAccessible(true);
        daoField.set(svcBean, inMem);

        // 3) Usar el servicio
        Users u = new Users();
        u.setUsername("bob");
        u.setEmail("bob@example.com");
        u.setPasswordHash("mypass");

        Users saved = svcBean.registerUser(u);
        System.out.println("Usuario guardado con ID: " + saved.getId());

        Users fetched = svcBean.getUserByUsername("bob");
        System.out.println("Recuperado: " + fetched.getUsername());
        
        /*
        try (EJBContainer ec = EJBContainer.createEJBContainer()) {
            Context ctx = ec.getContext();
            UserServiceLocal svc = (UserServiceLocal)
                ctx.lookup("java:global/classes/com.merlab.stackflow.service.UserServiceBean!com.merlab.stackflow.service.UserServiceLocal");

            Users u = new Users();
            u.setUsername("bob");
            u.setEmail("bob@example.com");
            u.setPasswordHash("mypass");
            Users saved = svc.registerUser(u);
            System.out.println("Usuario guardado con ID: " + saved.getId());

            Users fetched = svc.getUserByUsername("bob");
            System.out.println("Recuperado: " + fetched.getUsername());
        }
        */
    }
}

