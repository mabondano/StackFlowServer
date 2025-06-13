/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merlab.stackflow.test;

import com.merlab.stackflow.persistence.dao.UserDaoLocal;
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
        UserDaoLocal inMem = new com.merlab.stackflow.service.InMemoryUserDao();
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

