package View;

import controller.DestinationController;
import controller.PackageController;
import entity.Vacation_Destination;
import entity.Vacation_Package;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.List;

public class EditPackageView extends JFrame{

    private JTextField nameTextField;
    private JTextField nameTextField2;
    private JTextField placesTextField;
    private JTextField extraDetailsTextField;
    private JTextField periodTextField;
    private JTextField priceTextField;
    private JTextField destinationTextField;
    private JButton editButton;
    private JButton searchButton;
    private JButton returnButton;

    private TravelAgencyView tView;
    private MainView mView;

    PackageController pc = new PackageController();
    DestinationController vc = new DestinationController();
    Vacation_Package vp;
    Vacation_Destination vd;
    Integer id;

    public void initialize(MainView mView, TravelAgencyView tView) {

        this.setTitle("EditPackagePage");
        this.setSize(500, 800);
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

    private void initializeListeners() {

        searchButton.addActionListener(e->{

            vp = pc.findPackage(nameTextField2.getText());

            nameTextField.setText(vp.getPackageName());
            placesTextField.setText(String.valueOf(vp.getAvailablePlaces()));
            periodTextField.setText(String.valueOf(vp.getPeriod()));
            priceTextField.setText(String.valueOf(vp.getPrice()));
            extraDetailsTextField.setText(vp.getExtraDetails());
            destinationTextField.setText(vp.getVacation_destination().getDestinationName());
            id= vp.getId();
        });

        editButton.addActionListener(e->{

            String name = nameTextField.getText();
            String places = placesTextField.getText();
            String period = periodTextField.getText();
            String price = priceTextField.getText();
            String extraDetails = extraDetailsTextField.getText();

            try{
                List<Vacation_Destination> lvd =vc.getDestinations();
                for(Vacation_Destination v: lvd){
                    if(v.getDestinationName().equals(destinationTextField.getText())){
                        vd=v;
                    }
                }

                if(vd==null){
                    String message = "Destination not found";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch(Exception e1){
                String message = "Destinations not found";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            if(vd!=null){
                pc.updatePackage(name,places,period,price,extraDetails, vd, id);
            }
        });

        returnButton.addActionListener(e -> {

            tView.initialize(mView,tView);
            this.setVisible(false);
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Edit Package");
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

        destinationTextField = new JTextField();
        destinationTextField.setBounds(100,500,300,30);

        JLabel nameLabel2 = new JLabel("NameOfPackage");
        nameLabel2.setBounds(100,550,500,30);

        nameTextField2 = new JTextField();
        nameTextField2.setBounds(100,580,300,30);

        editButton = new JButton("Edit");
        editButton.setBounds(260,630,120,30);

        searchButton = new JButton("Search");
        searchButton.setBounds(100,630,120,30);

        returnButton = new JButton("Return");
        returnButton.setBounds(100,680,120,30);

        panel.add(introLabel);
        panel.add(nameLabel);
        panel.add(nameLabel2);
        panel.add(placesLabel);
        panel.add(periodLabel);
        panel.add(priceLabel);
        panel.add(extraDetailsLabel);
        panel.add(destinationLabel);
        panel.add(nameTextField);
        panel.add(nameTextField2);
        panel.add(placesTextField);
        panel.add(periodTextField);
        panel.add(priceTextField);
        panel.add(extraDetailsTextField);
        panel.add(destinationTextField);
        panel.add(editButton);
        panel.add(searchButton);
        panel.add(returnButton);
    }




}
