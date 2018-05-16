package dan.it;

import com.sun.org.apache.xpath.internal.operations.Or;
import dan.it.dao.ClientDAO;
import dan.it.dao.ItemDAO;
import dan.it.dao.OrderDAO;
import dan.it.model.Client;
import dan.it.model.Item;
import dan.it.model.Order;

public class ApplecationRunner {

    public static void main(String[] args) {
        Client client = new Client();
        ClientDAO.delete("kgkuhku");
      /* client.setLogin("testin");
        client.setPassword("123456");
        client.setFirstName("Aleksei6u52");
        client.setSecondName("Leichenko214112");*/

      // ClientDAO.save(client);

     //   ItemDAO.delete("newspaper");
        Order order = new Order();
        OrderDAO.selectClientItem("Joseph");
    }
}
