/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

