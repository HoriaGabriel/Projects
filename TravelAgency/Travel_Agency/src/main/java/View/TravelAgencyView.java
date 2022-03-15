package View;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TravelAgencyView extends JFrame{

    private JButton addDestinationButton;
    private JButton addPackageButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton viewButton;
    private JButton deleteDestButton;
    private JButton backButton;


    private AddDestionationView addDestionationView = new AddDestionationView();
    private AddPackageView addPackageView = new AddPackageView();
    private DeletePackageView deletePackageView = new DeletePackageView();
    private EditPackageView editPackageView = new EditPackageView();
    private ViewPackagesView viewPackagesView = new ViewPackagesView();
    private DeleteDestinationView deleteDestinationView = new DeleteDestinationView();
    private MainView mView;
    private TravelAgencyView tView;

    public void initialize(MainView mView, TravelAgencyView tView){

        this.setTitle("TravelAgencyPage");
        this.setSize(600, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.mView=mView;
        this.tView=tView;

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

        addDestinationButton = new JButton("Add Destionation");
        addDestinationButton.setBounds(100,70,170,30);

        addPackageButton = new JButton("Add Package");
        addPackageButton.setBounds(320,70,170,30);

        deleteButton = new JButton("Delete Package");
        deleteButton.setBounds(100,120,170,30);

        editButton = new JButton("Edit Package");
        editButton.setBounds(320,120,170,30);

        viewButton = new JButton("View Packages");
        viewButton.setBounds(100,170,170,30);

        deleteDestButton = new JButton("Delete Destination");
        deleteDestButton.setBounds(320,170,170,30);

        backButton = new JButton("Back Button");
        backButton.setBounds(320,220,170,30);

        panel.add(introLabel);
        panel.add(addPackageButton);
        panel.add(addDestinationButton);
        panel.add(deleteButton);
        panel.add(editButton);
        panel.add(viewButton);
        panel.add(deleteDestButton);
        panel.add(backButton);
    }

    private void initializeListeners() {

        addDestinationButton.addActionListener(e -> {

            addDestionationView.initialize(mView,tView);
            this.setVisible(false);
        });

        addPackageButton.addActionListener(e -> {

            addPackageView.initialize(mView,tView);
            this.setVisible(false);
        });

        deleteButton.addActionListener(e->{

            deletePackageView.initialize(mView,tView);
            this.setVisible(false);
        });

        editButton.addActionListener(e->{

            editPackageView.initialize(mView,tView);
            this.setVisible(false);
        });

        viewButton.addActionListener(e->{

            viewPackagesView.initialize(mView,tView);
            this.setVisible(false);
        });

        deleteDestButton.addActionListener(e->{

            deleteDestinationView.initialize(mView,tView);
            this.setVisible(false);
        });

        backButton.addActionListener(e->{

            mView.initialize(mView);
            this.setVisible(false);
        });
    }
}
