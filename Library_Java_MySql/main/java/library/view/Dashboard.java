package library.view;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dashboard extends AppFrame{

    private JButton setupLibrarianButton;
    private JButton loginLibrarianButton;


    @Autowired
    private SetupDashboard setupDashboard;

    @Autowired
    private LoginDashboard loginDashboard;


    public void initialize(){

        this.setTitle("StartingPage");
        this.setSize(400, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeListeners() {
        setupLibrarianButton.addActionListener(e -> {

            setupDashboard.initialize();
            this.setVisible(false);

        });

        loginLibrarianButton.addActionListener(e->{

            loginDashboard.initialize();
            this.setVisible(false);
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel librarianIntroLabel = new JLabel("Welcome! Please login or set-up account");
        librarianIntroLabel.setBounds(70,10,500,30);

        setupLibrarianButton = new JButton("SetUp");
        setupLibrarianButton.setBounds(100,70,70,30);

        loginLibrarianButton = new JButton("Login");
        loginLibrarianButton.setBounds(190,70,70,30);

        panel.add(librarianIntroLabel);
        panel.add(setupLibrarianButton);
        panel.add(loginLibrarianButton);

    }


}
