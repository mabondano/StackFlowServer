package com.merlab.stackflow.service;

import com.merlab.stackflow.persistence.dao.UserDaoLocal;
import com.merlab.stackflow.persistence.entity.Users;

import java.util.*;
public class InMemoryUserDao implements UserDaoLocal {
    private final Map<Long,Users> store = new HashMap<>();
    private long seq = 1;
    @Override
    public Users findById(Long id) {
        return store.get(id);
    }
    @Override
    public List<Users> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public void save(Users user) {
        user.setId(seq++);
        store.put(user.getId(), user);
    }
    @Override
    public void update(Users user) {
        store.put(user.getId(), user);
    }
    @Override
    public void delete(Users user) {
        store.remove(user.getId());
    }
    @Override
    public Users findByUsername(String username) {
        return store.values()
                    .stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
    }
}
        