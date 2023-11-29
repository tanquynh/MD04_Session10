package com.ra.model.dao;

import java.util.List;

public interface IGenericDAO<Entity, Id> {
    List<Entity> getAll();

    boolean save(Entity entity);

    Entity getById(Id id);

    boolean update(Entity entity, Id id);

    boolean delete(Id id);
}
