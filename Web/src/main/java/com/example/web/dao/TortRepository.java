package com.example.web.dao;

import com.example.web.controller.Tort;
import com.example.web.err.CustomException;
import com.example.web.err.Errors;
import com.example.web.service.TortInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TortRepository  {

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

    public List<Tort> getAll() {

        try(ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT * FROM TortInfo")) {
            List<Tort> tortList = new ArrayList<>();
            while (resultSet.next()) {
                tortList.add(getTortInfo(resultSet));
            }
            return tortList;
        } catch (Exception ex) {

            throw new CustomException("Failed is method .getAll",ex, Errors.DATABASE_ERROR);
        }


        }


        public TortInfo getTortInfo(String tortId) {

            try (ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT * FROM TortInfo WHERE id = '" + tortId + "'")) {

                if (!resultSet.next()) {
                    return null;
                }
                return getTortInfo(resultSet);
            } catch (Exception ex) {
                throw new CustomException("Failed is method .getTort", ex, Errors.DATABASE_ERROR);
            }
        }

            public TortInfo create(TortInfo info) {
                try (Statement statement = connection.createStatement()) {

                    String script = String.format("INSERT INFO TortInfo (" +
                            "'id", 'name', 'description', 'price', 'weight') " +
                            "VALUES ('%s', '%s, '%s', '%s', '%s', '%s', '%s')",
                    info.getId(), info.getName(), info.getDescription(), info.getPrice(), info.getWeight();

                    int rows = statement.executeUpdate(script);
                    System.out.println(">> Create Tort complete, rows=" + rows);
                    return  getTortInfo(info.getId());

            } catch (Exception ex) {
                    throw new CustomException("Failed is method .create", ex, Errors.DATABASE_ERROR);
                }
    }

    public TortInfo update(TortInfo info) {
        String script = "UPDATE TortInfo SET name = ?, description = ?, price = ?, weight = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(script)) {

            statement.setString(1, info.getName());
            statement.setString(2, info.getDescription());
            statement.setInt(3, info.getPrice());
            statement.setInt(4, info.getWeight());
            statement.setString(5, info.getId());

            int rows = statement.executeUpdate();
            System.out.println(">> Update TortInfo complete, rows=" + rows);
            return getTortInfo(info.getId());

        } catch (Exception ex) {
            throw new CustomException("Failed is method .update", ex, Errors.DATABASE_ERROR);
        }
    }



    public TortInfo deletee(String tortId) {
        TortInfo tort = getTortInfo(tortId);

        if (tort != null) {
            try (PreparedStatement statement =  connection.prepareStatement("DELETE  FROM TortInfo WHERE id =?")) {
                statement.setString(1, to);

            }
        }
    }
}

