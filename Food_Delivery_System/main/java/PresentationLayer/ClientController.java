package PresentationLayer;

import BusinessLayer.*;
import DataLayer.DeliverySerializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ClientController {

    /**
     * The function initializes the methods for the actionListeners of the client
     * @param  clientView the user interface of the client
     * @param  mainView the main user interface
     * @param d the delivery service where various operations are performed with the given data
     * @param cBase the list of clients available
     * @param nameClient the name of the current client
     * @param compositeOrder an array list of products creating an order
     */
    public void clientViewActionListener(ClientView clientView, MainView mainView, DeliveryService d, ClientBase cBase, String nameClient, ArrayList<MenuItem> compositeOrder) {

        backButtonMethod(mainView,clientView);

        showProductMethod(clientView,d);

        addButtomMethod(clientView,d,compositeOrder);

        orderButtonMethod(cBase,nameClient,compositeOrder,d,clientView);

        showOrderMethod(clientView,d);
    }

    /**
     * The function goes through the orders and creates a table with the data showing the
     * orders, clients and the rest of the data
     * @param  clientView the user interface of the client
     * @param d the delivery service where various operations are performed with the given data
     */
    private void showOrderMethod(ClientView clientView, DeliveryService d) {
        clientView.showOrderButtonListener(e->{

            Vector<String> l = new Vector<>();
            l.add("OrderId");l.add("ClientId");l.add("Date");l.add("Hour");l.add("Price");l.add("Products");
            DefaultTableModel myModel = new DefaultTableModel();
            myModel.setColumnIdentifiers(l);

            ArrayList<Order> orders = d.getOrderList();
            Iterator<Order> iterator = orders.iterator();

            while(iterator.hasNext()){

                List<Object> obj = new ArrayList<>();
                Order aux = iterator.next();
                obj.add(aux.getOrderId());obj.add(aux.getClientId());
                obj.add(aux.getOrderDate());obj.add(aux.getHour());obj.add(aux.getPrice());
                ArrayList<MenuItem> lis = d.getOrderMenuMap().get(aux);
                Iterator<MenuItem> itt = lis.iterator();
                String auxl=null;
                while (itt.hasNext()) {

                    auxl = auxl + itt.next().getName();
                    if (itt.hasNext())
                        auxl = auxl + ",";
                }
                auxl = auxl.substring(4);
                obj.add(auxl);
                myModel.addRow(obj.toArray());
            }
            JTable myTable = new JTable(myModel);
            JScrollPane tableContainer = new JScrollPane(myTable);
            tableContainer.setBounds(900,50,500,300);
            clientView.add(tableContainer);
        });
    }

    /**
     * The function creates a new order with the given data and then saves it in the list of orders
     * found in the delivery service.
     * @param  clientView the user interface of the client
     * @param d the delivery service where various operations are performed with the given data
     *@param cBase the list of clients available
     *@param nameClient the name of the current client
     *@param compositeOrder an array list of products creating an order
     */
    private void orderButtonMethod(ClientBase cBase, String nameClient, ArrayList<MenuItem> compositeOrder, DeliveryService d, ClientView clientView) {

        clientView.orderButtonListener(e->{

            Order o = new Order();
            Client c = cBase.searchClient2(nameClient);
            int cId = cBase.getClientList().indexOf(c);
            LocalDate myDate;
            myDate = LocalDate.now();
            int oId = d.getOrderIndex();

            int price=d.priceCalculator(compositeOrder);
            LocalTime myTime = LocalTime.now();
            String a = myTime.toString();
            a=a.substring(0,2);
            int b = Integer.parseInt(a);

            o.setClientId(cId);o.setOrderId(oId);
            o.setOrderDate(myDate);o.setPrice(price);
            o.setHour(b);

            d.createMenuMap(o,compositeOrder);
            d.createOrder(o);
            try {
                d.createBill(o,compositeOrder);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            DeliverySerializator.serialize(d);
        });
    }

    /**
     * The function adds the selected product to the list that will later be part of the order
     * @param  clientView the user interface of the client
     * @param d the delivery service where various operations are performed with the given data
     *@param compositeOrder an array list of products creating an order
     */
    private void addButtomMethod(ClientView clientView, DeliveryService d, ArrayList<MenuItem> compositeOrder) {

        clientView.addButtonListener(e->{

            String name = clientView.chosenItem();
            MenuItem aux = d.searchByName(name);
            compositeOrder.add(aux);
            JTextArea aux2 = clientView.getItems();
            aux2.append(name);
            aux2.append("\n");
        });
    }

    /**
     * The function creates a table which contains the various fields making up the product. The
     * function iterates through the menu found in the delivery service and takes all the fields
     * creating the table
     * @param  clientView the user interface of the client
     * @param d the delivery service where various operations are performed with the given data
     */
    private void showProductMethod(ClientView clientView, DeliveryService d) {

        clientView.showProductButtonListener(e->{
            Vector<String> l = new Vector<>();
            l.add("Name");l.add("Price");l.add("Rating");l.add("Calories");l.add("Protein");l.add("Fat");l.add("Sodium");
            DefaultTableModel myModel = new DefaultTableModel();
            myModel.setColumnIdentifiers(l);

            ArrayList<MenuItem> menu2 = d.getFilteredMenu(clientView.getProductNameTextField(), clientView.getProductCaloriesTextField(),
                    clientView.getProductFatTextField(), clientView.getProductPriceTextField(),
                    clientView.getProductProteinTextField(), clientView.getProductSodiumTextField(), clientView.getProductRatingTextField());
            Iterator<MenuItem> iterator = menu2.iterator();

            while(iterator.hasNext()){

                List<Object> obj = new ArrayList<>();
                MenuItem aux = iterator.next();
                obj.add(aux.getName());obj.add(aux.getPrice());
                obj.add(aux.getRating());obj.add(aux.getCalories());
                obj.add(aux.getProtein());obj.add(aux.getFat());
                obj.add(aux.getSodium());
                myModel.addRow(obj.toArray());
            }

            JTable myTable = new JTable(myModel);
            JScrollPane tableContainer = new JScrollPane(myTable);
            tableContainer.setBounds(320,50,500,300);
            clientView.add(tableContainer);
        });
    }

    /**
     * The function closes the client view and returns to the main view
     *@param  clientView the user interface of the client
     *@param  mainView the main user interface
     */
    private void backButtonMethod(MainView mainView, ClientView clientView) {

        clientView.backButtonListener(e->{
            clientView.setVisible(false);
            mainView.setVisible(true);
        });
    }
}

