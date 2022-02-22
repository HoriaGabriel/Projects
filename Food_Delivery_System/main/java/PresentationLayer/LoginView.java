package PresentationLayer;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField clientNameTextField;
    private JTextField clientPasswordTextField;
    private JButton loginButton;
    private JButton setupAccountButton;
    private JButton backButton;

    /**
     * The function initializes the view and sets up the user interface
     */
    public void initialize(){

        this.setTitle("LoginPage");
        this.setSize(330, 420);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);

        this.setContentPane(panel);
        this.setVisible(true);
    }

    /**
     * Client Name getter
     * @return returns the string representing the client name
     */
    public String getClientNameTextField() {
        return clientNameTextField.getText();
    }

    /**
     * Client Password getter
     * @return returns the string representing the client password
     */
    public String getClientPasswordTextField() {
        return clientPasswordTextField.getText();
    }

    /**
     * The function initializes the panel by adding the elements needed for the parameters
     * @param  panel the panel where the buttons and text fields are put
     */
    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Choose the operation");
        introLabel.setBounds(90,10,500,30);

        JLabel clientNameLabel = new JLabel("Client Name: ");
        clientNameLabel.setBounds(120,70,500,30);

        clientNameTextField = new JTextField();
        clientNameTextField.setBounds(100,100,100,30);

        JLabel clientPasswordLabel = new JLabel("Client Password: ");
        clientPasswordLabel.setBounds(100,150,500,30);

        clientPasswordTextField = new JTextField();
        clientPasswordTextField.setBounds(100,180,100,30);

        loginButton = new JButton("Login");
        loginButton.setBounds(80,230,170,30);

        setupAccountButton = new JButton("SetUp");
        setupAccountButton.setBounds(80,280,170,30);

        backButton = new JButton("Return");
        backButton.setBounds(80,330,170,30);

        panel.add(introLabel);
        panel.add(clientNameLabel);
        panel.add(clientNameTextField);
        panel.add(clientPasswordLabel);
        panel.add(clientPasswordTextField);
        panel.add(loginButton);
        panel.add(backButton);
        panel.add(setupAccountButton);
    }

    /**
     * The function initializes the actionListener of the backButton
     * @param  actionListener the actionListener of the back button
     */
    public void backButtonListener(ActionListener actionListener){

        backButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the loginButton
     * @param  actionListener the actionListener of the login button
     */
    public void loginButtonListener(ActionListener actionListener){

        loginButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the setupAccountButton
     * @param  actionListener the actionListener of the set up account button
     */
    public void setupAccountButtonListener(ActionListener actionListener){

        setupAccountButton.addActionListener(actionListener);
    }

}

