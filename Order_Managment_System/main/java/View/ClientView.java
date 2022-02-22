package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame{

    private JTextField nameTextField;
    private JTextField telephoneTextField;
    private JTextField addressTextField;
    private JTextField nameUpdateTextField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton showButton;
    private JButton backButton;


    /**
     * The function initializes the view and sets up the user interface
     */
    public void initialize(){

        this.setTitle("ClientPage");
        this.setSize(700, 420);
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

        JLabel introLabel = new JLabel("Client Operations");
        introLabel.setBounds(120,10,500,30);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10,70,500,30);

        nameTextField = new JTextField();
        nameTextField.setBounds(70,70,100,30);

        JLabel telephoneLabel = new JLabel("Telephone");
        telephoneLabel.setBounds(10,120,500,30);

        telephoneTextField = new JTextField();
        telephoneTextField.setBounds(90,120,100,30);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(10,170,500,30);

        addressTextField = new JTextField();
        addressTextField.setBounds(80,170,100,30);

        addButton = new JButton("Add Client");
        addButton.setBounds(10,230,120,30);

        deleteButton = new JButton("Delete Client");
        deleteButton.setBounds(140,230,120,30);

        updateButton = new JButton("Update Client");
        updateButton.setBounds(10,280,120,30);

        showButton = new JButton("Show Table");
        showButton.setBounds(140,280,120,30);

        backButton = new JButton("Return");
        backButton.setBounds(10,330,120,30);

        JLabel nameUpdateLabel = new JLabel("Name for updating:");
        nameUpdateLabel.setBounds(380,330,500,30);

        nameUpdateTextField = new JTextField();
        nameUpdateTextField.setBounds(500,330,100,30);

        panel.add(introLabel);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(telephoneLabel);
        panel.add(telephoneTextField);
        panel.add(addressLabel);
        panel.add(addressTextField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(showButton);
        panel.add(backButton);
        panel.add(nameUpdateLabel);
        panel.add(nameUpdateTextField);
    }

    /**
     * The function initializes the actionListener of the backButton
     * @param  actionListener the actionListener of the back button
     */
    public void backButtonListener(ActionListener actionListener){

        backButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the insertButton
     * @param  actionListener the actionListener of the insert button
     */
    public void insertButtonListener(ActionListener actionListener) {

        addButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the deleteButton
     * @param  actionListener the actionListener of the delete button
     */
    public void deleteButtonListener(ActionListener actionListener) {

        deleteButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the updateButton
     * @param  actionListener the actionListener of the update button
     */
    public void updateButtonListener(ActionListener actionListener) {

        updateButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the showAllButton
     * @param  actionListener the actionListener of the show all button
     */
    public void showAllButtonListener(ActionListener actionListener) {

        showButton.addActionListener(actionListener);
    }

    /**
     * Name return
     * @return returns the name written in the text filed
     */
    public String getName(){
        return nameTextField.getText();
    }

    /**
     * Address return
     * @return returns the address written in the text filed
     */
    public String getAddress(){
        return addressTextField.getText();
    }

    /**
     * Telephone return
     * @return returns the telephone written in the text filed
     */
    public String getTelephone(){
        return telephoneTextField.getText();
    }

    /**
     * UpdateName return
     * @return returns the updateName written in the text filed
     */
    public String getUpdateName(){
        return nameUpdateTextField.getText();
    }
}

