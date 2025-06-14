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


import com.merlab.stackflow.persistence.dao.TestDaoLocal;
import com.merlab.stackflow.persistence.entity.TestEntity;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped // CDI Bean, ciclo de vida de la app
public class TestDBAccess {
    
    private static final Logger log = LoggerFactory.getLogger(TestDBAccess.class);

    @EJB
    private TestDaoLocal testDao;

    // Prueba de escritura
    public void insertTestValue(String value) {
        TestEntity entity = new TestEntity();
        entity.setValue(value);
        testDao.save(entity);
        System.out.println("Valor insertado en BD: " + value);
    }

    // Prueba de lectura
    public void readAllTestValues() {
        var lista = testDao.findAll();
        if (lista.isEmpty()) {
            System.out.println("No hay datos en la tabla test.");
        } else {
            System.out.println("Contenido de la tabla test:");
            lista.forEach(e -> System.out.println("ID: " + e.getId() + " | Value: " + e.getValue()));
        }
    }
    
        // Método público para exponer findAll() del DAO
    public List<TestEntity> findAll() {
        return testDao.findAll();
    }

}

