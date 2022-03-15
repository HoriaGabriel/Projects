package View;

import controller.PackageController;
import entity.User;
import entity.Vacation_Package;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewReservationView extends JFrame{

    private MainView mView;
    private ClientView cView;
    private LoginView lView;
    private PackagesView pView;

    private JTable table;
    private JButton returnButton;

    User user;

    PackageController pc = new PackageController();

    public void initialize(MainView mView, ClientView cView, LoginView lView, PackagesView pView, User user) {

        this.setTitle("ViewReservationsPage");
        this.setSize(900, 500);
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

    private void initializeForm(JPanel panel){

        List<Vacation_Package> lvp = user.getVacation_package();

        table = new JTable();

        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        String[] columnNames = {"id", "packageName"};

        model.setColumnIdentifiers(columnNames);

        for(Vacation_Package v: lvp){
            Object[] o = new Object[8];
            o[0] = v.getId();
            o[1] = v.getPackageName();

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

            pView.initialize(mView,cView,lView,pView,user);
            this.setVisible(false);
        });
    }
}
