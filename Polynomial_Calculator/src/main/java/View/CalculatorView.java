package View;

import Controller.CalculatorController;
import Exceptions.UnproperFormatException;
import Model.Polynomial;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalculatorView extends JFrame{

    private JTextField polynomial1TextField;
    private JTextField polynomial2TextField;
    private JTextField resultTextField;
    private JButton plusButton;
    private JButton minusButton;
    private JButton multiplicationButton;
    private JButton derivationButton;
    private JButton integralButton;

    /**
     * initializes the view with the panel and putting all the text field
     * as well as the buttons and what they should do when pressed
     */

    public void initialize(){
        this.setSize(540,340);
        this.setTitle("Calculator");
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
     * initializes the listeneres so as to be able to see what each button has as reaction
     */

    private void initializeListeners(){

        derivationButton.addActionListener(e->{

            CalculatorController c = new CalculatorController();
            Polynomial poly1 = null;
            Polynomial result;

            try {
                poly1= c.createPolynomial(polynomial1TextField.getText());
            } catch (UnproperFormatException unproperFormatException) {
                unproperFormatException.printStackTrace();
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            }

            result= c.derivativeCalculator(poly1);
            String res=c.polynomToString(result);
            resultTextField.setText(res);

        });

        integralButton.addActionListener(e->{

            CalculatorController c = new CalculatorController();
            Polynomial poly1=null;
            Polynomial result;

            try {
                poly1=c.createPolynomial(polynomial1TextField.getText());
            } catch (UnproperFormatException unproperFormatException) {
                unproperFormatException.printStackTrace();
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            }

            result= c.integralCalculator(poly1);
            String res=c.polynomToString(result);
            resultTextField.setText(res);
        });

        plusButton.addActionListener(e->{

            CalculatorController c = new CalculatorController();
            Polynomial poly1=null;
            Polynomial poly2=null;
            Polynomial result;

            try {
                poly1=c.createPolynomial(polynomial1TextField.getText());
            } catch (UnproperFormatException unproperFormatException) {
                unproperFormatException.printStackTrace();
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                poly2=c.createPolynomial(polynomial2TextField.getText());
            } catch (UnproperFormatException unproperFormatException) {
                unproperFormatException.printStackTrace();
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            }

            result= c.sumCalculator(poly1,poly2);
            String res=c.polynomToString(result);
            resultTextField.setText(res);
        });

        minusButton.addActionListener(e->{

            CalculatorController c = new CalculatorController();
            Polynomial poly1=null;
            Polynomial poly2=null;
            Polynomial result;

            try {
                poly1=c.createPolynomial(polynomial1TextField.getText());
            } catch (UnproperFormatException unproperFormatException) {
                unproperFormatException.printStackTrace();
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                poly2=c.createPolynomial(polynomial2TextField.getText());
            } catch (UnproperFormatException unproperFormatException) {
                unproperFormatException.printStackTrace();
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            }

            result= c.differenceCalculator(poly1,poly2);
            String res=c.polynomToString(result);
            resultTextField.setText(res);
        });

        multiplicationButton.addActionListener(e->{

            CalculatorController c = new CalculatorController();
            Polynomial poly1=null;
            Polynomial poly2=null;
            Polynomial result;

            try {
                poly1=c.createPolynomial(polynomial1TextField.getText());
            } catch (UnproperFormatException unproperFormatException) {
                unproperFormatException.printStackTrace();
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                poly2=c.createPolynomial(polynomial2TextField.getText());
            } catch (UnproperFormatException unproperFormatException) {
                unproperFormatException.printStackTrace();
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            }

            result= c.multiplicationCalculator(poly1,poly2);
            String res=c.polynomToString(result);
            resultTextField.setText(res);
        });
    }

    /**
     * initializes the panel with all the buttons and text forms
     */

    private void initializeForm(JPanel panel) {

        JLabel polynomialLabel = new JLabel("Enter the polynomials");
        polynomialLabel.setBounds(220,10,200,30);

        polynomial1TextField = new JTextField();
        polynomial1TextField.setBounds(120,50,200,30);

        JLabel polynomial1Label = new JLabel("polynomial1");
        polynomial1Label.setBounds(340,50,200,30);

        polynomial2TextField = new JTextField();
        polynomial2TextField.setBounds(120,100,200,30);

        JLabel polynomial2Label = new JLabel("polynomial2");
        polynomial2Label.setBounds(340,100,200,30);

        resultTextField = new JTextField();
        resultTextField.setBounds(120,150,200,30);

        JLabel resultLabel = new JLabel("result");
        resultLabel.setBounds(340,150,200,30);

        JLabel operandsLabel = new JLabel("operands");
        operandsLabel.setBounds(20,200,200,30);

        plusButton = new JButton("+");
        plusButton.setBounds(20,230,70,30);

        minusButton = new JButton("-");
        minusButton.setBounds(100,230,70,30);

        multiplicationButton = new JButton("*");
        multiplicationButton.setBounds(180,230,70,30);

        derivationButton = new JButton("deriv");
        derivationButton.setBounds(260,230,70,30);

        integralButton = new JButton("∫");
        integralButton.setBounds(340,230,70,30);

        panel.add(polynomialLabel);
        panel.add(polynomial1Label);
        panel.add(polynomial1TextField);
        panel.add(polynomial2Label);
        panel.add(polynomial2TextField);
        panel.add(resultLabel);
        panel.add(resultTextField);
        panel.add(operandsLabel);
        panel.add(plusButton);
        panel.add(minusButton);
        panel.add(multiplicationButton);
        panel.add(derivationButton);
        panel.add(integralButton);
    }

}

