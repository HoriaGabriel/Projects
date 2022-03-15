package View;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientView extends JFrame{

    private JButton setupButton;
    private JButton loginButton;
    private JButton returnButton;

    private SetUpView setUpView = new SetUpView();
    private LoginView loginView = new LoginView();

    private MainView mView;
    private ClientView cView;

    public void initialize(MainView mView, ClientView cView) {

        this.setTitle("ClientPage");
        this.setSize(600, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.mView=mView;
        this.cView=cView;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Choose the operation");
        introLabel.setBounds(230,10,500,30);

        setupButton = new JButton("Set Up");
        setupButton.setBounds(100,70,170,30);

        loginButton = new JButton("Login");
        loginButton.setBounds(300,70,170,30);

        returnButton = new JButton("Return");
        returnButton.setBounds(100,120,170,30);

        panel.add(introLabel);
        panel.add(setupButton);
        panel.add(loginButton);
        panel.add(returnButton);
    }

    private void initializeListeners() {

        setupButton.addActionListener(e -> {

            setUpView.initialize(mView,cView);
            this.setVisible(false);
        });

        loginButton.addActionListener(e->{

            loginView.initialize(mView,cView,loginView);
            this.setVisible(false);
        });

        returnButton.addActionListener(e->{

            mView.initialize(mView);
            this.setVisible(false);
        });
    }


}
