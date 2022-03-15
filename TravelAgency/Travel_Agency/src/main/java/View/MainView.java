package View;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JFrame{

    private JButton userButton;
    private JButton tAgencyButton;

    private TravelAgencyView tAgencyView = new TravelAgencyView();
    private ClientView clientView = new ClientView();

    MainView mv;

    public void initialize(MainView mv){

        this.setTitle("StartingPage");
        this.setSize(600, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.mv=mv;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeListeners() {
        userButton.addActionListener(e -> {

            clientView.initialize(mv,clientView);
            this.setVisible(false);

        });

        tAgencyButton.addActionListener(e->{

            tAgencyView.initialize(mv,tAgencyView);
            this.setVisible(false);
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Choose the operation");
        introLabel.setBounds(230,10,500,30);

        userButton = new JButton("ClientOpButton");
        userButton.setBounds(100,70,170,30);

        tAgencyButton = new JButton("AgencyOpButton");
        tAgencyButton.setBounds(300,70,170,30);

        panel.add(introLabel);
        panel.add(userButton);
        panel.add(tAgencyButton);
    }

}
