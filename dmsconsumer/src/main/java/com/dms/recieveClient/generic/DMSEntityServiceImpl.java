package com.dms.recieveClient.generic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class DMSEntityServiceImpl <K extends Serializable & Comparable<K>, E extends DMSEntity<K, ?>>
        implements DMSEntityService<K, E>{


    /**
     * Classe de l'entité, déterminé à partir des paramètres generics.
     */
    private Class<E> objectClass;

    private JpaRepository<E, K> repository;

    public DMSEntityServiceImpl() {
    }

    @SuppressWarnings("unchecked")
    public DMSEntityServiceImpl(JpaRepository<E, K> repository) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.repository = repository;
    }

    public final Class<E> getObjectClass() {
        return objectClass;
    }

    public E getById(K id) {
        return repository.findOne(id);
    }
}
