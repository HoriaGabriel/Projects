package View;

import controller.PackageController;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeletePackageView extends JFrame{

    private JTextField nameTextField;
    private JButton deleteButton;
    private JButton returnButton;

    private TravelAgencyView tView;
    private MainView mView;

    PackageController pc = new PackageController();

    public void initialize(MainView mView, TravelAgencyView tView) {

        this.setTitle("DeletePage");
        this.setSize(300, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.tView=tView;
        this.mView=mView;

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Delete Package");
        introLabel.setBounds(100,10,500,30);

        JLabel nameLabel = new JLabel("Package Name");
        nameLabel.setBounds(100,70,500,30);

        nameTextField = new JTextField();
        nameTextField.setBounds(100,100,100,30);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(70,160,170,30);

        returnButton = new JButton("Return");
        returnButton.setBounds(70,210,170,30);

        panel.add(introLabel);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(deleteButton);
        panel.add(returnButton);
    }

    private void initializeListeners() {

        deleteButton.addActionListener(e -> {

            String s = nameTextField.getText();
            pc.deletePackage(s);
        });

        returnButton.addActionListener(e -> {

            tView.initialize(mView,tView);
            this.setVisible(false);
        });
    }


}
