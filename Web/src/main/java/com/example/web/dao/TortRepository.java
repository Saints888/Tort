package com.example.web.dao;

import com.example.web.err.CustomException;
import com.example.web.err.Errors;
import com.example.web.service.TortInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class TortRepository  {

    /**
     * Класс представляет CRUD репозиторий к таблице TortInfo
     */
    @Autowired
    private Connection connection;


    public TortRepository() {

    }

    public List<Tort> getAll() {
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM TortInfo")) {
            List<Tort> tortList = new ArrayList<>();
            while (resultSet.next()) {
                tortList.add(getTortInfo(resultSet));
            }
            return tortList;
        } catch (SQLException ex) {
            throw new CustomException("Failed in method .getAll", ex, Errors.DATABASE_ERROR);
        }
    }


    public TortInfo getTortInfo(String tortId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM TortInfo WHERE id = ?")) {
            statement.setString(1, tortId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return getTortInfo(resultSet);
            }
        } catch (SQLException ex) {
            throw new CustomException("Failed in method .getTort", ex, Errors.DATABASE_ERROR);
        }
    }

    public Tort create(TortInfo info) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO TortInfo (id, name, description, price, weight) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, info.getId());
            statement.setString(2, info.getName());
            statement.setString(3, info.getDescription());
            statement.setInt(4, info.getPrice());
            statement.setInt(5, info.getWeight());

            int rows = statement.executeUpdate();
            System.out.println(">> Create Tort complete, rows=" + rows);

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedId = generatedKeys.getString(1);
                    return getTort(generatedId);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new CustomException("Failed in method .create", ex, Errors.DATABASE_ERROR);
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
        } catch (SQLException ex) {
            throw new CustomException("Failed in method .update", ex, Errors.DATABASE_ERROR);
        }
    }



    public Tort delete(String tortId) {
        Tort tort = getTort(tortId);
        if (tort != null) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM TortInfo WHERE id = ?")) {
                statement.setString(1, tortId);
                int rows = statement.executeUpdate();
                System.out.println(">> Delete TortInfo complete, rows=" + rows);
                return tort;
            } catch (SQLException ex) {
                throw new CustomException("Failed in method .delete", ex, Errors.DATABASE_ERROR);
            }
        }
        return null;
    }


        private TortInfo getTort(ResultSet resultSet) throws SQLException {

        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int price = resultSet.getInt("price");
        int weight = resultSet.getInt("weight");

        TortInfo tortInfo = new TortInfo();
        tortInfo.setId(id);
        tortInfo.setName(name);
        tortInfo.setDescription(description);
        tortInfo.setPrice(price);
        tortInfo.setWeight(weight);

        return tortInfo;

    }





        public void createTable() {

        try (Statement statement = connection.createStatement()){
            String script = "CREATE TABLE TortInfo (" +
                    "id varchar(100) primary key," +
                    "name varchar(50) , " +
                    "description varchar(1500), " +
                    "price int," +
                    "weight int);" ;
            int rows = statement.executeUpdate(script);
            System.out.println(">>Create table complete, rows=" + rows);

        } catch (Exception e) {
            throw new CustomException("Failed is method. createTable", Errors.DATABASE_ERROR);
        }

        }

        public void dropTable() {
            try (Statement statement = connection.createStatement()) {
                int rows = statement.executeUpdate("DROP TABLE TortInfo");
                System.out.println(">>Delete table complete, rows=" + rows);

            } catch (SQLException e) {
                throw new CustomException("Failed is method .dropTable", Errors.DATABASE_ERROR);
            }

        }
}

