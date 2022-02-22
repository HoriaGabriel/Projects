package View;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class MainView extends JFrame{

    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;

    /**
     * The function initializes the view and sets up the user interface
     */
    public void initialize(){

        this.setTitle("StartingPage");
        this.setSize(800, 200);
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

        JLabel introLabel = new JLabel("Choose the operation");
        introLabel.setBounds(320,10,500,30);

        clientButton = new JButton("ClientOpButton");
        clientButton.setBounds(100,70,170,30);

        productButton = new JButton("ProductOpButton");
        productButton.setBounds(300,70,170,30);

        orderButton = new JButton("OrderOpButton");
        orderButton.setBounds(500,70,170,30);

        panel.add(introLabel);
        panel.add(clientButton);
        panel.add(productButton);
        panel.add(orderButton);
    }

    /**
     * The function initializes the actionListener of the clientButton
     * @param  actionListener the actionListener of the client button
     */
    public void clientButtonListener(ActionListener actionListener){

        clientButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the productButton
     * @param  actionListener the actionListener of the product button
     */
    public void productButtonListener(ActionListener actionListener){

        productButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the orderButton
     * @param  actionListener the actionListener of the order button
     */
    public void orderButtonListener(ActionListener actionListener){

        orderButton.addActionListener(actionListener);
    }
}

