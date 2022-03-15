package View;

import controller.DestinationController;
import controller.PackageController;
import entity.Vacation_Destination;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class AddPackageView extends JFrame{

    private JTextField nameTextField;
    private JTextField placesTextField;
    private JTextField extraDetailsTextField;
    private JTextField periodTextField;
    private JTextField priceTextField;
    private JComboBox destinationBox;
    private JButton okButton;
    private JButton returnButton;

    private TravelAgencyView tView;
    private MainView mView;

    DestinationController dc = new DestinationController();
    PackageController pc = new PackageController();

    List<Vacation_Destination> vDests = new ArrayList<>();

    public void initialize(MainView mView, TravelAgencyView tView) {

        this.setTitle("AddPackagePage");
        this.setSize(500, 700);
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

        JLabel introLabel = new JLabel("Add Package");
        introLabel.setBounds(100,10,500,30);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(100,70,500,30);

        nameTextField = new JTextField();
        nameTextField.setBounds(100,100,300,30);

        JLabel placesLabel = new JLabel("Nr. of Places");
        placesLabel.setBounds(100,150,500,30);

        placesTextField = new JTextField();
        placesTextField.setBounds(100,180,300,30);

        JLabel periodLabel = new JLabel("Time Period");
        periodLabel.setBounds(100,230,500,30);

        periodTextField = new JTextField();
        periodTextField.setBounds(100,260,300,30);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(100,310,500,30);

        priceTextField = new JTextField();
        priceTextField.setBounds(100,340,300,30);

        JLabel extraDetailsLabel = new JLabel("Extra Details");
        extraDetailsLabel.setBounds(100,390,500,30);

        extraDetailsTextField = new JTextField();
        extraDetailsTextField.setBounds(100,420,300,30);

        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(100,470,500,30);


        vDests=dc.getDestinations();

        String[] res = new String[100];
        int i=0;

        for(Vacation_Destination d: vDests){
            res[i]=d.getDestinationName();
            i++;
        }

        destinationBox = new JComboBox(res);
        destinationBox.setBounds(100,500,300,30);

        okButton = new JButton("OK");
        okButton.setBounds(170,570,170,30);

        returnButton = new JButton("Return");
        returnButton.setBounds(170,620,170,30);

        panel.add(introLabel);
        panel.add(nameLabel);
        panel.add(placesLabel);
        panel.add(periodLabel);
        panel.add(priceLabel);
        panel.add(extraDetailsLabel);
        panel.add(destinationLabel);
        panel.add(nameTextField);
        panel.add(placesTextField);
        panel.add(periodTextField);
        panel.add(priceTextField);
        panel.add(extraDetailsTextField);
        panel.add(destinationBox);
        panel.add(okButton);
        panel.add(returnButton);
    }

    private void initializeListeners() {

        okButton.addActionListener(e -> {

            Vacation_Destination vd = new Vacation_Destination();

            String s1 = nameTextField.getText();
            String s2 = extraDetailsTextField.getText();

            try{
                String s3 = destinationBox.getSelectedItem().toString();

                for(Vacation_Destination d: vDests){

                    if(d.getDestinationName().equals(s3)){
                        vd = d;
                    }
                }

                if(!placesTextField.getText().isEmpty() && !periodTextField.getText().isEmpty() && !priceTextField.getText().isEmpty()){

                    try{
                        Integer i1 = Integer.valueOf(placesTextField.getText());
                        Integer i2 = Integer.valueOf(periodTextField.getText());
                        Integer i3 = Integer.valueOf(priceTextField.getText());

                        pc.insertPackage(s1,i1,i2,i3,s2,vd);

                    } catch(Exception e2){
                        String message = "Incorrect input -> not numbers";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }else{
                    String message = "Incorrect input -> some fields are empty";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }


            } catch(Exception e3){
                String message = "Incorrect input -> destination not selected";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        });

        returnButton.addActionListener(e -> {

            tView.initialize(mView,tView);
            this.setVisible(false);
        });
    }
}
