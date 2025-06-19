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

package com.merlab.stackflow.api.mapper;

public class GenericEntityMapper {

    public static <E, D> D toDTO(E entity) {
        // TODO: implementar la conversión de entidad a DTO
        System.out.println("toDTO() no implementado para " + entity);
        return null;
    }

    public static <D, E> E toEntity(D dto) {
        // TODO: implementar la conversión de DTO a entidad
        System.out.println("toEntity() no implementado para " + dto);
        return null;
    }

    @Override
    public String toString() {
        return "GenericEntityMapper{}";
    }
}
