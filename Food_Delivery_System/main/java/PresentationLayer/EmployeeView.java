package PresentationLayer;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeView extends JFrame implements Observer {

    /**
     * The function initializes the view and sets up the user interface
     */
    public void initialize(){

        this.setTitle("ClientPage");
        this.setSize(900, 650);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.setContentPane(panel);
        this.setVisible(true);
    }

    /**
     * The function displays the message when the employee is notified
     * @param  o the observer
     * @param arg a possible argument for the update function. Not used.
     */
    @Override
    public void update(Observable o,Object arg) {

        JOptionPane.showMessageDialog(null,"Employee was notified!");
    }
}

