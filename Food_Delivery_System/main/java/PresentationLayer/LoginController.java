package PresentationLayer;

import BusinessLayer.Client;
import BusinessLayer.ClientBase;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import DataLayer.ClientSerializator;

import javax.swing.*;
import java.util.ArrayList;

public class LoginController {

    String nameClient;
    String password;
    ClientView clientView;

    /**
     * The function initializes the actionListeners of the login view
     * @param  mainView the main user interface
     * @param  loginView the login user interface
     * @param  d the delivery service where various operations are performed with the given data
     * @param  compositeOrder an array list of products creating an order
     * @param  cBase the list of clients available
     */
    public void loginViewActionListener(LoginView loginView, MainView mainView, DeliveryService d, ArrayList<MenuItem> compositeOrder, ClientBase cBase) {

        loginView.backButtonListener(e->{
            loginView.setVisible(false);
            mainView.setVisible(true);
        });

        loginView.loginButtonListener(e->{

            nameClient = loginView.getClientNameTextField();
            password = loginView.getClientPasswordTextField();

            if(cBase.searchClient(nameClient,password)==true){

                clientView = new ClientView();
                loginView.setVisible(false);
                clientView.initialize(d);
                ClientController clientController = new ClientController();
                clientController.clientViewActionListener(clientView,mainView,d, cBase,nameClient,compositeOrder);
            }

        });

        loginView.setupAccountButtonListener(e->{

            if(loginView.getClientNameTextField().isEmpty() || loginView.getClientPasswordTextField().isEmpty()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String name = loginView.getClientNameTextField();
                String password = loginView.getClientPasswordTextField();

                Client c = new Client();
                c.setName(name);
                c.setPassword(password);
                cBase.addClient(c);
                ClientSerializator.serialize(cBase);
            }
        });
    }
}

