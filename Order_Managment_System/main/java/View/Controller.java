package View;

import Bll.OrderBLL;
import Bll.ProductBLL;
import Bll.ClientBLL;
import java.sql.SQLException;

public class Controller {

    private MainView mainView;
    private ClientView clientView;
    private ProductView productView;
    private OrderView orderView;
    private ClientBLL clientBLL = new ClientBLL();
    private ProductBLL productBLL = new ProductBLL();
    private OrderBLL orderBLL = new OrderBLL();

    /**
     * The function initializes the main view and sets up the user interface
     */
    public void initialize(){

        mainView = new MainView();
        mainView.initialize();
        mainViewActionListener(mainView);
    }

    /**
     * The function initializes the reactions to the buttons of the main user interface
     */
    private void mainViewActionListener(MainView mainView) {

        mainView.clientButtonListener(e -> {
            clientView=new ClientView();
            mainView.setVisible(false);
            clientView.initialize();
            clientViewActionListener(clientView);
        });

        mainView.productButtonListener(e -> {
            productView=new ProductView();
            mainView.setVisible(false);
            productView.initialize();
            productViewActionListener(productView);
        });

        mainView.orderButtonListener(e -> {
            orderView=new OrderView();
            mainView.setVisible(false);
            orderView.initialize();
            orderViewActionListener(orderView);
        });
    }

    /**
     * The function initializes the reactions to the buttons of the order user interface
     */
    private void orderViewActionListener(OrderView orderView) {

        orderView.backButtonListener(e->{
            orderView.setVisible(false);
            mainView.setVisible(true);
        });

        orderView.placeOrderButtonListener(e->{
            orderBLL.makeOrder(orderView);
        });
    }

    /**
     * The function initializes the reactions to the buttons of the product user interface
     */
    private void productViewActionListener(ProductView productView) {

        productView.backButtonListener(e->{
            productView.setVisible(false);
            mainView.setVisible(true);
        });

        productView.insertButtonListener(e->{
            productBLL.setupValidators();
            productBLL.insertOp(productView);
        });

        productView.deleteButtonListener(e->{
            productBLL.setupValidators();
            productBLL.deleteOp(productView);
        });

        productView.updateButtonListener(e->{
            productBLL.setupValidators();
            productBLL.updateOp(productView);
        });

        productView.showAllButtonListener(e->{
            try {
                productBLL.showOp(productView);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    /**
     * The function initializes the reactions to the buttons of the client user interface
     */
    private void clientViewActionListener(ClientView clientView) {

        clientView.backButtonListener(e->{
            clientView.setVisible(false);
            mainView.setVisible(true);
        });

        clientView.insertButtonListener(e->{
            clientBLL.setupValidators();
            clientBLL.insertOp(clientView);
        });

        clientView.deleteButtonListener(e->{
            clientBLL.setupValidators();
            clientBLL.deleteOp(clientView);
        });

        clientView.updateButtonListener(e->{
            clientBLL.setupValidators();
            clientBLL.updateOp(clientView);
        });

        clientView.showAllButtonListener(e->{
            try {
                clientBLL.showOp(clientView);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}

