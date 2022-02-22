package Bll;

import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Client;
import Model.Product;
import View.OrderView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OrderBLL {

    private OrderDAO orderDAO = new OrderDAO();
    private ProductDAO productDAO = new ProductDAO();

    /**
     * The function creates a new order by searching for the client in the user interface in the table and the
     * product the same way. The order is the setup and the products stock is updated after the placement of the order
     * @param orderView the user interface for the order operations
     */

    public void makeOrder(OrderView orderView) {

        ClientBLL cBLL = new ClientBLL();
        Client c = new Client();
        c = cBLL.findClientByName(orderView.getClientName());

        ProductBLL pBLL = new ProductBLL();
        Product p = new Product();
        p =pBLL.findProductByName(orderView.getProductName());
        new File("C:\\PT2021_30421_Rusu_Horia_Assignment_3\\ Bill.txt");
        try{
            FileWriter myWriter = new FileWriter("Bill.txt",true);
            myWriter.append("Client is: " + c.getName() + "\n");
            myWriter.append("Product is: " + p.getName() + "\n");
            myWriter.append("Quantity is: " + orderView.getQuantity() + "\n");
            myWriter.append("Price is: " + Integer.parseInt(orderView.getQuantity())*p.getPrice() + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int aux = orderDAO.setUpOrder(c,p,orderView);
        orderDAO.insertOrder();
        p.setStock(aux);
        productDAO.update(p);
    }
}

