package View;

import controller.UserController;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SetUpView extends JFrame {

    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField userNameTextField;
    private JPasswordField passwordTextField;
    private JButton okButton;
    private JButton returnButton;

    private MainView mView;
    private ClientView cView;

    UserController uc = new UserController();

    public void initialize(MainView mView, ClientView cView) {

        this.setTitle("SetUpPage");
        this.setSize(500, 600);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.mView=mView;
        this.cView=cView;

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);

    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Set Up Account");
        introLabel.setBounds(100,10,500,30);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(100,70,500,30);

        firstNameTextField = new JTextField();
        firstNameTextField.setBounds(100,100,300,30);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(100,150,500,30);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(100,180,300,30);

        JLabel userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(100,230,500,30);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(100,260,300,30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100,310,500,30);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(100,340,300,30);

        okButton = new JButton("OK");
        okButton.setBounds(170,400,170,30);

        returnButton = new JButton("Return");
        returnButton.setBounds(170,450,170,30);

        panel.add(introLabel);
        panel.add(firstNameLabel);
        panel.add(lastNameLabel);
        panel.add(userNameLabel);
        panel.add(passwordLabel);
        panel.add(firstNameTextField);
        panel.add(lastNameTextField);
        panel.add(userNameTextField);
        panel.add(passwordTextField);
        panel.add(okButton);
        panel.add(returnButton);
    }

    private void initializeListeners() {

        okButton.addActionListener(e -> {

            uc.insertClient(firstNameTextField.getText(),lastNameTextField.getText(),userNameTextField.getText(),passwordTextField.getText());

        });

        returnButton.addActionListener(e -> {

            cView.initialize(mView,cView);
            this.setVisible(false);
        });
    }
}
