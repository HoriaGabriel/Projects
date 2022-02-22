package PresentationLayer;

import BusinessLayer.*;
import DataLayer.ClientSerializator;
import DataLayer.DeliverySerializator;

import java.io.*;
import java.util.*;

public class Controller implements Serializable {

    MainView mainView;
    LoginView loginView;
    AdministratorView adminView;
    EmployeeView empView;
    DeliveryService d;
    ClientBase cBase;
    private ArrayList<MenuItem> compositeProduct = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> compositeOrder = new ArrayList<MenuItem>();
    AdminController adminController = new AdminController();

    /**
     * Function initializes the app by deserializing the already recorded data and
     * then initializes the different used user interfaces
     */
    public void initialize() {

        d=DeliverySerializator.DEserialize();

        if(d.getMenu().isEmpty()) {
            d.initialize();
            ArrayList<MenuItem> aux = new ArrayList<MenuItem>();
            FileGetter fg = new FileGetter();
            aux = fg.processInputFile();
            d.setMenu(aux);
            DeliverySerializator.serialize(d);
        }
        cBase= ClientSerializator.DEserialize();
        empView= new EmployeeView();
        d.addObserver(empView);
        mainView = new MainView();
        mainView.initialize();
        mainViewActionListener(mainView);
    }

    /**
     * The function initializes the actionListeners of the main view
     * @param  mainView the main user interface
     */
    private void mainViewActionListener(MainView mainView) {

        mainView.clientButtonListener(e -> {
            loginView=new LoginView();
            mainView.setVisible(false);
            loginView.initialize();
            LoginController loginController = new LoginController();
            loginController.loginViewActionListener(loginView,mainView,d,compositeOrder,cBase);
        });

        mainView.administratorButtonListener(e -> {
            adminView=new AdministratorView();
            mainView.setVisible(false);
            adminView.initialize(d);
            adminController.adminViewListener(adminView,mainView,d,compositeProduct);
        });

        mainView.employeeButtonListener(e -> {
            empView=new EmployeeView();
            mainView.setVisible(false);
            empView.initialize();
        });
    }
}

