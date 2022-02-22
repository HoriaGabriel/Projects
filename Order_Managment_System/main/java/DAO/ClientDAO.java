package DAO;

import Bll.validators.Validator;
import Model.Client;
import View.ClientView;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.ConnectionFactory;

public class ClientDAO extends AbstractDAO<Client> {

    private Client client = new Client();

    /**
     * The function sets up the the DAO of the client by putting the data from the user interface in the
     * clients data and the id is setup by selecting the maximum id from the table and then adding 1
     * @param clientView the user interface for the product operations
     */

    public void setupClientDAO(ClientView clientView) {

        client.setName(clientView.getName());
        client.setAddress(clientView.getAddress());
        client.setTelephone(clientView.getTelephone());
        StringBuilder qC = new StringBuilder();
        qC.append("SELECT max(id) from schooldb.client");
        Connection con = ConnectionFactory.getConnection();
        int clientId = 0;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(qC.toString());
            while (rs.next()) {
                clientId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        client.setId(clientId+1);
    }

    /**
     * The function goes through the list of validators and verifies every condition for the client
     * @param client the client that needs to be validated
     * @param validators the list of validators
     * @param i parameter to check if the method is called in the context of inserting or updating
     * @return returns 1 if all is okay with the data and zero otherwise
     */

    public int validation(Client client, List<Validator<Client>> validators,int i){

        try{
            for(Validator v2: validators){

                v2.validate(client,i);
            }
        } catch(NumberFormatException e){
            e.printStackTrace();
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        } catch(IllegalArgumentException e){
            e.printStackTrace();
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        return 1;
    }

    /**
     * The function calls the validation method to check if the client is okay and if it is then
     * the insert in the abstract class will be called.
     * @param validators the list of validators for the client
     */

    public void insertClient(List<Validator<Client>> validators){

        if(validation(client,validators,0)==1) {
            insert(client);
        }
    }

    /**
     * The function calls the delete method in the abstract class
     * @param c the client to be deleted
     */

    public void deleteClient(Client c){
        delete(c);
    }

    /**
     * The function calls the validation method to check if the client is okay and if it is then
     * the update in the abstract class will be called.
     * @param validators the list of validators for the client
     * @param c the client to be updated
     */

    public void updateClient(Client c, List<Validator<Client>> validators) {

        if(validation(c,validators,1)==1) {
            update(c);
        }
    }

    /**
     * The function selects everything from the table and calls the createObject method in order to make
     * a list of clients out of the result and then creates a JTable with the list then shows the table
     * @param clientView the user interface of the client
     */

    public void showClients(ClientView clientView) throws SQLException {

        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * from schooldb.client";
        PreparedStatement statement = null;
        statement = con.prepareStatement(sql);

        ResultSet rs = null;
        rs = statement.executeQuery();
        List<Client> cList = new ArrayList<>();
        cList = createObjects(rs);
        JTable table = findAll(cList);

        JScrollPane tableContainer = new JScrollPane(table);
        tableContainer.setBounds(320,50,300,200);
        clientView.add(tableContainer);
    }
}

