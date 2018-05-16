package dan.it.dao;

import dan.it.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {
    public static void save(Client client){

        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO client(login, password, first_name, second_name) VALUES(?,?,?,?)";
        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, client.getLogin());
            statement.setString(3, client.getFirstName());
            statement.setString(2, client.getPassword());
            statement.setString(4, client.getSecondName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }


    }
    public static Client get(String login){
        Client client = new Client();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String sql = "SELECT * FROM  client WHERE login='"+login+"'";
        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            rSet = statement.executeQuery();
            while(rSet.next()){
                client.setLogin(rSet.getString("login"));
                client.setPassword(rSet.getString(2));
                client.setFirstName(rSet.getString("first_name"));
                client.setSecondName(rSet.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(rSet, statement, connection);
        }
        return client;

    }
    public static void update(Client client){
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "UPDATE client SET password=?, first_name=?, second_name=? WHERE login=?";

        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(4, client.getLogin());
            statement.setString(2, client.getFirstName());
            statement.setString(1, client.getPassword());
            statement.setString(3, client.getSecondName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }
    }
    public static void delete(String login){

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "DELETE FROM client WHERE login=?";

        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, login);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }
    }
}
