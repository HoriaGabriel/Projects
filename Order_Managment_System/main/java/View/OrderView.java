package View;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class OrderView extends JFrame{

    private JTextField productNameTextField;
    private JTextField clientNameTextField;
    private JTextField quantityTextField;
    private JButton orderButton;
    private JButton backButton;

    /**
     * The function initializes the view and sets up the user interface
     */
    public void initialize(){

        this.setTitle("OrderPage");
        this.setSize(400, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);

        this.setContentPane(panel);
        this.setVisible(true);
    }

    /**
     * The function initializes the panel by adding the elements needed for the parameters
     * @param  panel the panel where the buttons and text fields are put
     */
    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Order Operation");
        introLabel.setBounds(120,10,500,30);

        clientNameTextField = new JTextField();
        clientNameTextField.setBounds(90,70,100,30);

        JLabel clientNameLabel = new JLabel("Client Name: ");
        clientNameLabel.setBounds(10,70,500,30);

        clientNameTextField = new JTextField();
        clientNameTextField.setBounds(90,70,100,30);

        JLabel productNameLabel = new JLabel("Product Name: ");
        productNameLabel.setBounds(10,120,500,30);

        productNameTextField = new JTextField();
        productNameTextField.setBounds(100,120,100,30);

        JLabel quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setBounds(10,170,500,30);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(80,170,100,30);

        orderButton = new JButton("Place order");
        orderButton.setBounds(10,230,120,30);

        backButton = new JButton("Return");
        backButton.setBounds(140,230,120,30);

        panel.add(introLabel);
        panel.add(clientNameLabel);
        panel.add(clientNameTextField);
        panel.add(productNameLabel);
        panel.add(productNameTextField);
        panel.add(quantityLabel);
        panel.add(quantityTextField);
        panel.add(orderButton);
        panel.add(backButton);
    }

    /**
     * The function initializes the actionListener of the backButton
     * @param  actionListener the actionListener of the back button
     */
    public void backButtonListener(ActionListener actionListener){

        backButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the placeOrderButton
     * @param  actionListener the actionListener of the place order button
     */
    public void placeOrderButtonListener(ActionListener actionListener) {

        orderButton.addActionListener(actionListener);
    }

    /**
     * Name of the client return
     * @return returns the name of the client written in the text filed
     */
    public String getClientName(){
        return clientNameTextField.getText();
    }

    /**
     * Name of the product return
     * @return returns the name of the product written in the text filed
     */
    public String getProductName() { return productNameTextField.getText();}

    /**
     * Quantity return
     * @return returns the quantity written in the text filed
     */
    public String getQuantity() { return quantityTextField.getText();}
}

