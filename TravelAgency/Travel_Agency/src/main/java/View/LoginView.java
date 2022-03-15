package View;

import controller.UserController;
import entity.User;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginView extends JFrame{

    private JTextField userNameTextField;
    private JPasswordField passwordTextField;
    private JButton okButton;
    private JButton returnButton;

    private MainView mView;
    private ClientView cView;
    private LoginView lView;

    private PackagesView packagesView = new PackagesView();

    UserController uc = new UserController();

    public void initialize(MainView mView, ClientView cView, LoginView lView) {

        this.setTitle("LoginPage");
        this.setSize(500, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.mView=mView;
        this.cView=cView;
        this.lView=lView;

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeListeners() {

        okButton.addActionListener(e -> {

            User u = uc.findClient(userNameTextField.getText(),passwordTextField.getText());

            if(u!=null){
                packagesView.initialize(mView,cView,lView,packagesView,u);
                this.setVisible(false);
            }
        });

        returnButton.addActionListener(e -> {

            cView.initialize(mView,cView);
            this.setVisible(false);
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Login");
        introLabel.setBounds(100,10,500,30);

        JLabel userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(100,70,500,30);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(100,100,300,30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100,150,500,30);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(100,180,300,30);

        okButton = new JButton("OK");
        okButton.setBounds(170,230,170,30);

        returnButton = new JButton("Return");
        returnButton.setBounds(170,280,170,30);

        panel.add(introLabel);
        panel.add(userNameLabel);
        panel.add(passwordLabel);
        panel.add(userNameTextField);
        panel.add(passwordTextField);
        panel.add(okButton);
        panel.add(returnButton);
    }
}
