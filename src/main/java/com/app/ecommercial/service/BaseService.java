package com.app.ecommercial.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    public List<T> getAll() {
        return getRepository().findAll();
    }

    public T getById(ID id) {
        return getRepository().findById(id).orElseThrow();
    }

    public T create(T entity) {
        return getRepository().save(entity);
    }

    public T update(ID id, T entity) {
        if (getRepository().existsById(id)) {
            return getRepository().save(entity);
        }
        return null;
    }

    public boolean delete(ID id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
            return true;
        }
        return false;
    }
}
