package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

    @Override
    public void createUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists user (id int auto_increment, name varchar(256), lastName varchar(256), age int(100), primary key (id))");
        statement.close();
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from user where id=?");
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from user");
        ResultSet result = statement.getResultSet();
        List<User> clients = new ArrayList<>();
        while (result.next()) {
            clients.add(new User(result.getString("name"), result.getString("lastName"), result.getByte("age")));
        }
        result.close();
        statement.close();
        return clients;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}