
package com.merlab.stackflow.service.impl;


import com.merlab.stackflow.persistence.dao.UserDaoLocal;
import com.merlab.stackflow.persistence.entity.Users;
import com.merlab.stackflow.service.UserServiceLocal;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author merly
 */
@Stateless
public class UserServiceBean implements UserServiceLocal {

    @EJB
    private UserDaoLocal userDao;

    @Override
    public Users registerUser(Users user) {
        // Validaciones básicas
        if (userDao.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username ya existe");
        }
        // Hash de contraseña
        String hashed = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
        user.setPasswordHash(hashed);
        userDao.save(user);
        return user;
    }

    @Override
    public Users getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Users getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<Users> listUsers() {
        return userDao.findAll();
    }

    @Override
    public Users updateUser(Users user) {
        // Opcional: validar que exista
        Users existing = userDao.findById(user.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        // No permitimos cambiar username
        user.setUsername(existing.getUsername());
        // Si llega password nueva, re-hashear
        if (user.getPasswordHash() != null && !user.getPasswordHash().isEmpty()) {
            String hashed = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
            user.setPasswordHash(hashed);
        } else {
            user.setPasswordHash(existing.getPasswordHash());
        }
        userDao.update(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        Users existing = userDao.findById(id);
        if (existing != null) {
            userDao.delete(existing);
        }
    }
}
