package View;

import controller.PackageController;
import entity.Status;
import entity.User;
import entity.Vacation_Package;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PackagesView extends JFrame{

    private JTable table;
    private JTextField priceTextField;
    private JTextField periodTextField;
    private JTextField destinationTextField;

    private JButton filterButton;
    private JButton reserveButton;
    private JButton viewButton;
    private JButton returnButton;

    private MainView mView;
    private ClientView cView;
    private LoginView lView;
    private PackagesView pView;

    private ReservationView reservationView = new ReservationView();
    private ViewReservationView viewReservationView = new ViewReservationView();

    PackageController pc = new PackageController();
    User user;

    private final String[] columnNames = {"id", "availablePlaces", "extraDetails", "packageName", "period", "price", "status","destination"};

    public void initialize(MainView mView, ClientView cView, LoginView lView, PackagesView pView, User user) {

        this.setTitle("PackagesPage");
        this.setSize(900, 600);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.mView=mView;
        this.cView=cView;
        this.lView=lView;
        this.pView=pView;
        this.user=user;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeListeners() {

        filterButton.addActionListener(e -> {

            List<Vacation_Package> lvp = pc.getPackages();
            List<Vacation_Package> lvp2 = pc.getPriceFilteredPackages(lvp,priceTextField.getText());
            List<Vacation_Package> lvp3 = pc.getDestinationFilteredPackages(lvp2,destinationTextField.getText());
            List<Vacation_Package> lvp4 = pc.getPeriodFilteredPackages(lvp3,periodTextField.getText());


            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);

            model.setColumnIdentifiers(columnNames);

            for(Vacation_Package v: lvp4){
                if(v.getStatus()== Status.NOT_BOOKED || v.getStatus()== Status.IN_PROGRESS){
                        Object[] o = new Object[8];
                        o[0] = v.getId();
                        o[1] = v.getAvailablePlaces();
                        o[2] = v.getExtraDetails();
                        o[3] = v.getPackageName();
                        o[4] = v.getPeriod();
                        o[5] = v.getPrice();
                        o[6] = v.getStatus();
                        o[7] = v.getVacation_destination().getDestinationName();

                        model.addRow(o);
                }
            }

        });

        reserveButton.addActionListener(e->{

            reservationView.initialize(mView,cView,lView,pView,user);
            this.setVisible(false);
        });

        viewButton.addActionListener(e->{

            viewReservationView.initialize(mView,cView,lView,pView,user);
            this.setVisible(false);
        });

        returnButton.addActionListener(e -> {

            lView.initialize(mView,cView,lView);
            this.setVisible(false);
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(100,370,500,30);

        priceTextField = new JTextField();
        priceTextField.setBounds(100,400,100,30);

        JLabel periodLabel = new JLabel("Period");
        periodLabel.setBounds(300,370,500,30);

        periodTextField = new JTextField();
        periodTextField.setBounds(300,400,100,30);

        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(500,370,500,30);

        destinationTextField = new JTextField();
        destinationTextField.setBounds(500,400,100,30);

        filterButton = new JButton("Filter");
        filterButton.setBounds(100,450,170,30);

        returnButton = new JButton("Return");
        returnButton.setBounds(100,500,170,30);

        reserveButton = new JButton("Make Reservation");
        reserveButton.setBounds(300,450,170,30);

        viewButton = new JButton("View Reservations");
        viewButton.setBounds(500,450,170,30);


        List<Vacation_Package> lvp = pc.getPackages();

        table = new JTable();

        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        model.setColumnIdentifiers(columnNames);

        for(Vacation_Package v: lvp){
            if(v.getStatus()== Status.NOT_BOOKED || v.getStatus()== Status.IN_PROGRESS){
                Object[] o = new Object[8];
                o[0] = v.getId();
                o[1] = v.getAvailablePlaces();
                o[2] = v.getExtraDetails();
                o[3] = v.getPackageName();
                o[4] = v.getPeriod();
                o[5] = v.getPrice();
                o[6] = v.getStatus();
                o[7] = v.getVacation_destination().getDestinationName();

                model.addRow(o);
            }
        }

        JScrollPane tableContainer = new JScrollPane(table);
        tableContainer.setBounds(100,20,700,300);
        panel.add(tableContainer);
        panel.add(priceTextField);
        panel.add(priceLabel);
        panel.add(destinationTextField);
        panel.add(destinationLabel);
        panel.add(filterButton);
        panel.add(periodLabel);
        panel.add(periodTextField);
        panel.add(reserveButton);
        panel.add(returnButton);
        panel.add(viewButton);
    }
}
