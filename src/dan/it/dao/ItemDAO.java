package dan.it.dao;

import dan.it.model.Client;
import dan.it.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
    public static void save(Item item){

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO item(article_id, name_item, price) VALUES(?,?,?)";
        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, item.getArticleId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }


    }
    public static Item get(String articleId){
        Item item = new Item();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String sql = "SELECT * FROM  item WHERE article_id='"+articleId+"'";
        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            rSet = statement.executeQuery();
            while(rSet.next()){
                item.setArticleId(rSet.getString("article_id"));
                item.setName(rSet.getString("name_item"));
                item.setPrice(rSet.getInt("price"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(rSet, statement, connection);
        }
        return item;

    }
    public static void update(Item item){
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "UPDATE item SET name_item=?, price=? WHERE article_id=?";

        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, item.getArticleId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getPrice());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }
    }
    public static void delete(String articleId){

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "DELETE FROM item WHERE article_id=?";

        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, articleId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }
    }
}
