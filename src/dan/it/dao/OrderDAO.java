package dan.it.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import dan.it.model.Item;
import dan.it.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public static void save(Order order){

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO public.order(order_id, item_id, amount, client_id) VALUES(?,?,?,?)";
        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, order.getOrderId());
            statement.setString(2, order.getItemId());
            statement.setInt(3, order.getAmount());
            statement.setString(4, order.getClientId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }


    }
    public static Order get(Integer orderId){
        Order order = new Order();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String sql = "SELECT * FROM  public.order WHERE order_id='"+orderId+"'";
        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            rSet = statement.executeQuery();
            while(rSet.next()){
                order.setOrderId(rSet.getInt("order_id"));
                order.setItemId(rSet.getString("item_id"));
                order.setAmount(rSet.getInt("amount"));
                order.setClientId(rSet.getString("client_id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(rSet, statement, connection);
        }
        return order;

    }
    public static void update(Order order){
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "UPDATE public.order SET item_id=?, amount=?, client_id WHERE order_id=?";

        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, order.getOrderId());
            statement.setString(2, order.getItemId());
            statement.setInt(3, order.getAmount());
            statement.setString(4, order.getClientId());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }
    }
    public static void delete(Integer orderId){

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "DELETE FROM public.order WHERE order_id=?";

        try {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, orderId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionToDB.closeConnection(statement, connection);
        }
    }
    public static ArrayList<String> selectClientItem(String clientId){

        ArrayList<String> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statementItem = null;
        ResultSet rSet = null;

        String sql = "SELECT * FROM public.order WHERE client_id='"+clientId+"'";

        try
        {
            connection = ConnectionToDB.getConnection();
            statement = connection.prepareStatement(sql);
            rSet = statement.executeQuery();
            int nonEmpty = 0;
            while (rSet.next()) {
                nonEmpty = rSet.getInt("order_id");

                String s = rSet.getString("item_id");
                String sqlItem = "SELECT name_item FROM item WHERE article_id='"+s+"'";
                statementItem = connection.prepareStatement(sqlItem);
                ResultSet rSetItem = statementItem.executeQuery();
                rSetItem.next();
                String result = rSetItem.getString("name_item") + " - " + rSet.getInt("amount");
                list.add(result);
                System.out.println(result);
            }
            if (nonEmpty == 0)
                System.out.println("client " + clientId + " has not placed orders yet");
        }

        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionToDB.closeConnection(rSet, statement, connection);
            ConnectionToDB.closeConnection(rSet, statement, connection);
        }
        return list;
    }
}
