package PresentationLayer;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class MainView extends JFrame{

    private JButton clientButton;
    private JButton administratorButton;
    private JButton employeeButton;

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

        administratorButton = new JButton("AdministratorOpButton");
        administratorButton.setBounds(300,70,170,30);

        employeeButton = new JButton("EmployeeOpButton");
        employeeButton.setBounds(500,70,170,30);

        panel.add(introLabel);
        panel.add(clientButton);
        panel.add(administratorButton);
        panel.add(employeeButton);
    }

    /**
     * The function initializes the actionListener of the clientButton
     * @param  actionListener the actionListener of the client button
     */
    public void clientButtonListener(ActionListener actionListener){

        clientButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the administratorButton
     * @param  actionListener the actionListener of the administrator button
     */
    public void administratorButtonListener(ActionListener actionListener){

        administratorButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the employeeButton
     * @param  actionListener the actionListener of the employee button
     */
    public void employeeButtonListener(ActionListener actionListener){

        employeeButton.addActionListener(actionListener);
    }
}

