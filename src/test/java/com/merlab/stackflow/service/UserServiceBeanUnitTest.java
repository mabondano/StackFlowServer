package com.merlab.stackflow.service;

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
