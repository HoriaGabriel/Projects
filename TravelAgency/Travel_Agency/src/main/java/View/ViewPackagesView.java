package View;

import controller.PackageController;
import entity.Vacation_Package;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewPackagesView extends JFrame{

    private JTable table;
    PackageController pc = new PackageController();

    private JButton returnButton;

    private TravelAgencyView tView;
    private MainView mView;

    public void initialize(MainView mView, TravelAgencyView tView) {

        this.setTitle("TravelAgencyPage");
        this.setSize(900, 500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.tView=tView;
        this.mView=mView;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);

    }

    private void initializeForm(JPanel panel) {

        List<Vacation_Package> lvp = pc.getPackages();

        table = new JTable();

        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        String[] columnNames = {"id", "availablePlaces", "extraDetails", "packageName", "period", "price", "status","destination"};

        model.setColumnIdentifiers(columnNames);

        for(Vacation_Package v: lvp){
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

        JScrollPane tableContainer = new JScrollPane(table);
        tableContainer.setBounds(100,20,700,300);

        returnButton = new JButton("Return");
        returnButton.setBounds(70,400,170,30);

        panel.add(tableContainer);
        panel.add(returnButton);
    }

    private void initializeListeners() {

        returnButton.addActionListener(e -> {

            tView.initialize(mView,tView);
            this.setVisible(false);
        });
    }

}
