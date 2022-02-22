package PresentationLayer;

import BusinessLayer.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ReportView extends JFrame{

    private DeliveryService d;
    private JTextField startingHourTextField;
    private JTextField endingHourTextField;
    private JTextField numberTimesTextField;
    private JTextField numberTimes2TextField;
    private JTextField amountTextField;
    private JTextField specificDayTextField;
    private JButton rep1;
    private JButton rep2;
    private JButton rep3;
    private JButton rep4;
    private JButton backButton;

    /**
     * Starting hour getter
     * @return returns the string representing the starting hour
     */
    public String getStartingHourTextField() {
        return startingHourTextField.getText();
    }

    /**
     * Ending hour getter
     * @return returns the string representing the ending hour
     */
    public String getEndingHourTextField() {
        return endingHourTextField.getText();
    }

    /**
     * Number of times getter
     * @return returns the string representing the number of times
     */
    public String getNumberTimesTextField() {
        return numberTimesTextField.getText();
    }

    /**
     * Number of times getter
     * @return returns the string representing the number of times
     */
    public String getNumberTimes2TextField() {
        return numberTimes2TextField.getText();
    }

    /**
     * Amount getter
     * @return returns the string representing the amount
     */
    public String getAmountTextField() {
        return amountTextField.getText();
    }

    /**
     * Specific day getter
     * @return returns the string representing the specific day
     */
    public String getSpecificDayTextField() {
        return specificDayTextField.getText();
    }

    /**
     * The function initializes the view and sets up the user interface
     */
    public void initialize(DeliveryService d) {

        this.d=d;
        this.setTitle("ReportPage");
        this.setSize(560, 400);
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

        JLabel introLabel1 = new JLabel("Report 1");
        introLabel1.setBounds(10,10,500,30);

        JLabel startHourLabel = new JLabel("Start Hour: ");
        startHourLabel.setBounds(10,70,500,30);

        startingHourTextField = new JTextField();
        startingHourTextField.setBounds(10,110,70,30);

        JLabel endHourLabel = new JLabel("End Hour: ");
        endHourLabel.setBounds(10,160,500,30);

        endingHourTextField = new JTextField();
        endingHourTextField.setBounds(10,200,70,30);

        JLabel introLabel2 = new JLabel("Report 2");
        introLabel2.setBounds(120,10,500,30);

        JLabel numberTimesLabel = new JLabel("Number of Times: ");
        numberTimesLabel.setBounds(120,70,500,30);

        numberTimesTextField = new JTextField();
        numberTimesTextField.setBounds(120,110,70,30);

        JLabel introLabel3 = new JLabel("Report 3");
        introLabel3.setBounds(270,10,500,30);

        JLabel numberTimesLabel2 = new JLabel("Number of Times: ");
        numberTimesLabel2.setBounds(270,70,500,30);

        numberTimes2TextField = new JTextField();
        numberTimes2TextField.setBounds(270,110,70,30);

        JLabel amountLabel = new JLabel("Amount: ");
        amountLabel.setBounds(270,160,500,30);

        amountTextField = new JTextField();
        amountTextField.setBounds(270,200,70,30);

        JLabel introLabel4 = new JLabel("Report 4");
        introLabel4.setBounds(420,10,500,30);

        JLabel specificDayLabel = new JLabel("Day: ");
        specificDayLabel.setBounds(420,70,500,30);

        specificDayTextField = new JTextField();
        specificDayTextField.setBounds(420,110,70,30);

        rep1 = new JButton("Rep1");
        rep1.setBounds(10,250,70,30);

        rep2 = new JButton("Rep2");
        rep2.setBounds(120,250,70,30);

        rep3 = new JButton("Rep3");
        rep3.setBounds(270,250,70,30);

        rep4 = new JButton("Rep4");
        rep4.setBounds(420,250,70,30);

        backButton = new JButton("Back");
        backButton.setBounds(10,320,120,30);

        panel.add(introLabel1);
        panel.add(startHourLabel);
        panel.add(startingHourTextField);
        panel.add(endHourLabel);
        panel.add(endingHourTextField);
        panel.add(introLabel2);
        panel.add(numberTimesLabel);
        panel.add(numberTimesTextField);
        panel.add(introLabel3);
        panel.add(numberTimesLabel2);
        panel.add(numberTimes2TextField);
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(introLabel4);
        panel.add(specificDayLabel);
        panel.add(specificDayTextField);
        panel.add(rep1);
        panel.add(rep2);
        panel.add(rep3);
        panel.add(rep4);
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
     * The function initializes the actionListener of the r2Button
     * @param  actionListener the actionListener of the report 2 button
     */
    public void r2ButtonListener(ActionListener actionListener){

        rep2.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the r3Button
     * @param  actionListener the actionListener of the report 3 button
     */
    public void r3ButtonListener(ActionListener actionListener){

        rep3.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the r4Button
     * @param  actionListener the actionListener of the report 4 button
     */
    public void r4ButtonListener(ActionListener actionListener){

        rep4.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the r1Button
     * @param  actionListener the actionListener of the report 1 button
     */
    public void r1ButtonListener(ActionListener actionListener){

        rep1.addActionListener(actionListener);
    }
}

