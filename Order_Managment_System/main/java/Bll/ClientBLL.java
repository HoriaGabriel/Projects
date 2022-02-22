package Bll;

import Bll.validators.ClientAddressValidator;
import Bll.validators.ClientNameValidator;
import Bll.validators.ClientTelephoneValidator;
import Bll.validators.Validator;
import DAO.ClientDAO;
import Model.Client;
import View.ClientView;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */

public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO = new ClientDAO();

    /**
     * The function sets up a list of validators where it inserts the various validators
     * for the client such as client name, address or telephone
     */

    public void setupValidators(){

        this.validators = new ArrayList<Validator<Client>>();
        this.validators.add(new ClientNameValidator());
        this.validators.add(new ClientAddressValidator());
        this.validators.add(new ClientTelephoneValidator());
    }

    /**
     * The function sets up the the DAO of the client with the clientView and then inserts the client
     * into the mysql table
     * @param clientView the user interface for the client operations
     */

    public void insertOp(ClientView clientView){

        clientDAO.setupClientDAO(clientView);
        clientDAO.insertClient(validators);
    }

    /**
     * The function searches for the name of the client in the table and returns it if it is in
     * the table and then calls the delete client method to delete it
     * @param clientView the user interface for the client operations
     */

    public void deleteOp(ClientView clientView){

        Client c;
        c = findClientByName(clientView.getName());
        clientDAO.deleteClient(c);
    }

    /**
     * The function searches for the name of the client in the table and returns it if it is in
     * the table and then verifies if there are fields that need updating and if they are the
     * updateClient method is called with the new client with the updated data
     * @param clientView the user interface for the client operations
     */

    public void updateOp(ClientView clientView){

        Client c;
        c = findClientByName(clientView.getUpdateName());

        if(clientView.getName().isEmpty()==false){
            c.setName(clientView.getName());
        }
        if(clientView.getTelephone().isEmpty()==false){
            c.setTelephone(clientView.getTelephone());
        }
        if(clientView.getAddress().isEmpty()==false){
            c.setAddress(clientView.getAddress());
        }

        clientDAO.updateClient(c,validators);
    }

    /**
     * The function calls the showClients method in the clientDAO in order to show the table
     * @param clientView the user interface for the client operations
     */

    public void showOp(ClientView clientView) throws SQLException {
        clientDAO.showClients(clientView);
    }

    /**
     * The function calls the showClients method in the clientDAO in order to show the table
     * @param name the string from the user interface of the name of the client that needs to be searched for in
     *             the table of clients
     * @return returns the client with the found name
     */

    public Client findClientByName(String name) {
        Client c = clientDAO.findByName(name);
        if (c == null) {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "The client with name " + name + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return c;
    }
}

