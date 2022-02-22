package View;

import Controller.SimulationManager;
import Exceptions.ImproperInputException;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppView extends JFrame{

    private JTextField clientTextField;
    private JTextField queueNumberTextField;
    private JTextField simulationIntervalTextField;
    private JTextField arrivalTimeMinTextField;
    private JTextField arrivalTimeMaxTextField;
    private JTextField serviceTimeMinTextField;
    private JTextField serviceTimeMaxTextField;
    private JButton startButton;
    private JButton clearButton;
    private JScrollPane resScrollPane;
    private JTextArea logsField;

    /**
     * The function initializes the view and sets up the user interface
     */

    public void initialize(){
        this.setSize(800,450);
        this.setTitle("QueueSimulator");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel= new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    /**
     * The function initializes the reactions to the buttons of the user interface
     */

    private void initializeListeners(){

        startButton.addActionListener(e -> {

            try {
                SimulationManager gen = new SimulationManager();
                gen.initialize(clientTextField.getText(), queueNumberTextField.getText(), simulationIntervalTextField.getText(),
                        arrivalTimeMinTextField.getText(), arrivalTimeMaxTextField.getText(), serviceTimeMinTextField.getText(),
                        serviceTimeMaxTextField.getText(), logsField);

                Thread t = new Thread(gen);
                t.start();
            }catch(ImproperInputException r){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException b){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e->{
            logsField.setText("");
        });
    }

    /**
     * The function initializes the panel by adding the elements needed for the parameters
     * @param  panel the panel where the buttons and text fields are put
     */

    private void initializeForm(JPanel panel) {

        JLabel queueLabel = new JLabel("Enter the data");
        queueLabel.setBounds(220,10,200,30);

        JLabel clientsLabel = new JLabel("Clients: ");
        clientsLabel.setBounds(20,60,200,30);

        clientTextField = new JTextField();
        clientTextField.setBounds(80,60,50,30);

        JLabel queueNumberLabel = new JLabel("Queues: ");
        queueNumberLabel.setBounds(20,110,200,30);

        queueNumberTextField = new JTextField();
        queueNumberTextField.setBounds(80,110,50,30);

        JLabel simulationIntervalLabel = new JLabel("Simulation interval: ");
        simulationIntervalLabel.setBounds(20,160,200,30);

        simulationIntervalTextField = new JTextField();
        simulationIntervalTextField.setBounds(140,160,50,30);

        JLabel arrivalTimeMinLabel = new JLabel("Min arrival time: ");
        arrivalTimeMinLabel.setBounds(20,210,200,30);

        arrivalTimeMinTextField = new JTextField();
        arrivalTimeMinTextField.setBounds(120,210,50,30);

        JLabel arrivalTimeMaxLabel = new JLabel("Max arrival time: ");
        arrivalTimeMaxLabel.setBounds(230,210,200,30);

        arrivalTimeMaxTextField = new JTextField();
        arrivalTimeMaxTextField.setBounds(330,210,50,30);

        JLabel serviceTimeMinLabel = new JLabel("Min service time: ");
        serviceTimeMinLabel.setBounds(20,260,200,30);

        serviceTimeMinTextField = new JTextField();
        serviceTimeMinTextField.setBounds(120,260,50,30);

        JLabel serviceTimeMaxLabel = new JLabel("Max service time: ");
        serviceTimeMaxLabel.setBounds(230,260,200,30);

        serviceTimeMaxTextField = new JTextField();
        serviceTimeMaxTextField.setBounds(330,260,50,30);

        startButton = new JButton("start");
        startButton.setBounds(20,310,70,30);

        clearButton = new JButton("clear");
        clearButton.setBounds(140,310,70,30);

        resScrollPane = new JScrollPane();
        resScrollPane.setBounds(450, 50, 250, 300);
        logsField = new JTextArea();
        logsField.setEditable(false);
        resScrollPane.setViewportView(logsField);

        panel.add(queueLabel);
        panel.add(clientsLabel);
        panel.add(clientTextField);
        panel.add(queueNumberLabel);
        panel.add(queueNumberTextField);
        panel.add(simulationIntervalLabel);
        panel.add(simulationIntervalTextField);
        panel.add(arrivalTimeMinLabel);
        panel.add(arrivalTimeMinTextField);
        panel.add(arrivalTimeMaxLabel);
        panel.add(arrivalTimeMaxTextField);
        panel.add(serviceTimeMinLabel);
        panel.add(serviceTimeMinTextField);
        panel.add(serviceTimeMaxLabel);
        panel.add(serviceTimeMaxTextField);
        panel.add(startButton);
        panel.add(clearButton);
        panel.add(resScrollPane);
    }
}
