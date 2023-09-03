package by.tms.calculator.storage.userStorage;

import by.tms.calculator.config.JdbcPostgresConfig;
import by.tms.calculator.enums.Role;
import by.tms.calculator.interfaces.UserStorage;
import by.tms.calculator.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcUserStorage implements UserStorage {
    private static Connection connection;

    private final String ADD_USER = "insert into \"user\" values (?, ?, ?, ?)";
    private final String GET_USER_BY_ID = "select * from \"user\" where id = ?";
    private final String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from \"user\" where name = ? and password = ?";
    private final String GET_ALL_USERS = "select * from \"user\"";
    private final String DELETE_BY_ID = "delete from \"user\" where id = ?";
    private final String UPDATE = "update \"user\" set name = ?, password = ? where id = ?";

    public JdbcUserStorage() {
        connection = JdbcPostgresConfig.getConnection();
    }

    @Override
    public void add(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().toString());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(UUID id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UUID resultSetId = UUID.fromString(resultSet.getString(1));
                String resultSetUsername = resultSet.getString(2);
                String resultSetPassword = resultSet.getString(3);
                String resultSetRole = resultSet.getString(4);

                preparedStatement.close();
                return new User(resultSetId, resultSetUsername, resultSetPassword, Role.valueOf(resultSetRole));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<User> get(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UUID resultSetId = UUID.fromString(resultSet.getString(1));
                String resultSetUsername = resultSet.getString(2);
                String resultSetPassword = resultSet.getString(3);
                String resultSetRole = resultSet.getString(4);

                preparedStatement.close();
                return Optional.of(new User(resultSetId, resultSetUsername, resultSetPassword, Role.valueOf(resultSetRole)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

            while (resultSet.next()) {
                UUID resultSetId = UUID.fromString(resultSet.getString(1));
                String resultSetUsername = resultSet.getString(2);
                String resultSetPassword = resultSet.getString(3);
                Role resultSetRole = Role.valueOf(resultSet.getString(4));

                User currentUser = new User(
                        resultSetId,
                        resultSetUsername,
                        resultSetPassword,resultSetRole
                );

                allUsers.add(currentUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public boolean deleteById(UUID id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setString(1, id.toString());

            int index = preparedStatement.executeUpdate();
            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getId().toString());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
