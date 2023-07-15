package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.sql.Connection;

public interface TortRepository  extends CrudRepository<TortEntity, Long> {

    /**
     * Класс представляет CRUD репозиторий к таблице TortInfo
     */
    @Component
    private Connection connection;

    @Autowired

    public void setConnection (Connection connection) {
        this.connection = connection;
    }

    public TortRepository() {
        
    }
}
