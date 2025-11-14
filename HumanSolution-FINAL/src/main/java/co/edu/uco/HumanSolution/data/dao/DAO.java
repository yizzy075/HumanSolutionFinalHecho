package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.Entity;

import java.util.List;

public interface DAO<E extends Entity> {

    void create(E entity);

    List<E> read(E filter);

    void update(E entity);

    void delete(java.util.UUID id);
}

