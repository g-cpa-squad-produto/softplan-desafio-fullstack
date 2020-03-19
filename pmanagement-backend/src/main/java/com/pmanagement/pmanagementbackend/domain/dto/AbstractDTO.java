package com.pmanagement.pmanagementbackend.domain.dto;

import com.pmanagement.pmanagementbackend.domain.entity.PersistentEntity;

/**
 * The basic DTO definition for all the DTO in the application
 * 
 * @param <T> the type of
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Mar 2, 2020
 */
public interface AbstractDTO<T extends PersistentEntity> {

    /**
     * Parse to bean
     * 
     * @return the bean parsed
     */
    T toDomain();
    
    /**
     * Parse the bean
     * 
     * @param value the bean to be parsed  
     */
    void toDto(T value);
}
