package DAO;

import Model.Client;
import Model.Order;
import Model.Product;
import View.ClientView;
import View.OrderView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Connection.ConnectionFactory;

import javax.swing.*;

public class OrderDAO extends AbstractDAO<Order>{

    private Order order = new Order();

    /**
     * The function sets up the order and verifies that the order is good and then
     * sets up by selecting the maximum id from the table and then adding 1
     * @param orderView the user interface for the order
     * @param c the client whose data to introduce in the table
     * @param p the product
     */

    public int setUpOrder(Client c, Product p, OrderView orderView) {

        order.setClientId(c.getId());
        order.setProductId(p.getId());
        order.setOrderQuantity(Integer.parseInt(orderView.getQuantity()));
        int aux2;
        if(order.getOrderQuantity()>p.getStock()){
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Order too big", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NoSuchElementException("Order too big");
        }
        else{
            int aux = p.getStock();
            aux2 =aux - order.getOrderQuantity();
        }
        StringBuilder qC = new StringBuilder();
        qC.append("SELECT max(id) from schooldb.order");
        Connection con = ConnectionFactory.getConnection();
        int orderId = 0;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(qC.toString());
            while (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        order.setId(orderId+1);
        return aux2;
    }

    /**
     * the inserts the order with the abstract class insert.
     */

    public void insertOrder() {
        insert(order);
    }
}

