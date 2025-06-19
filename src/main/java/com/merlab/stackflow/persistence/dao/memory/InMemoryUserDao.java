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


package com.merlab.stackflow.persistence.dao.memory;

import com.merlab.stackflow.persistence.entity.Users;

import java.util.*;
import com.merlab.stackflow.persistence.dao.local.UserDaoLocal;

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
        