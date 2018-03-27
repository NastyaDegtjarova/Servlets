package ua.goit.service;

import ua.goit.model.BaseEntity;

import java.util.List;

public interface BaseServise <T extends BaseEntity>  {

    /**
     * Get all entities.
     * @return  List<T> Entities list.
     * */
    List<T> getAll();

    /**
     * Get entity where id.
     * @param id Entity id.
     * @return T Entity object.
     * */
    T getById(Long id);

    /**
     * Save entity.
     * @param entity Entity object.
     * */
    void save(T entity);

    /**
     * Delete entity where id.
     * @param id Entity id.
     * */
    void delete(Long id);

    /**
     * Update entity.
     * @param entity Entity object.
     * @return T entity object.
     * */
    T update(T entity);

}
