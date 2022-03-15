package View;

import controller.PackageController;
import controller.UserController;
import entity.Status;
import entity.User;
import entity.Vacation_Package;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.List;

public class ReservationView extends JFrame{

    private JTextField userNameTextField;
    private JTextField packageTextField;
    private JButton okButton;
    private JButton returnButton;

    UserController uc = new UserController();
    PackageController pc = new PackageController();


    private MainView mView;
    private ClientView cView;
    private LoginView lView;
    private PackagesView pView;
    User user;

    public void initialize(MainView mView, ClientView cView, LoginView lView, PackagesView pView,User user){

        this.setTitle("ReservationPage");
        this.setSize(500, 400);
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

        okButton.addActionListener(e->{

            User u = uc.findClientWithName(userNameTextField.getText());
            Vacation_Package p = pc.findPackage(packageTextField.getText());

            List<Vacation_Package> ulist = u.getVacation_package();
            ulist.add(p);
            u.setVacation_package(ulist);

            List<User> plist = p.getUser();
            plist.add(u);
            p.setUser(plist);

            Integer aux = p.getAvailablePlaces();
            aux=aux-1;
            p.setAvailablePlaces(aux);

            if(p.getStatus()== Status.NOT_BOOKED){
                p.setStatus(Status.IN_PROGRESS);
            }else if(p.getStatus()==Status.IN_PROGRESS && p.getAvailablePlaces()==0){
                p.setStatus(Status.BOOKED);
            }

            pc.updatePackageAfterReservation(p);
            uc.updateClientAfterReservation(u);

        });

        returnButton.addActionListener(e -> {

            pView.initialize(mView,cView,lView,pView,user);
            this.setVisible(false);
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Make Reservation");
        introLabel.setBounds(100,10,500,30);

        JLabel userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(100,70,500,30);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(100,100,300,30);

        JLabel passwordLabel = new JLabel("Package");
        passwordLabel.setBounds(100,150,500,30);

        packageTextField = new JTextField();
        packageTextField.setBounds(100,180,300,30);

        okButton = new JButton("OK");
        okButton.setBounds(170,230,170,30);

        returnButton = new JButton("Return");
        returnButton.setBounds(170,280,170,30);

        panel.add(introLabel);
        panel.add(userNameLabel);
        panel.add(passwordLabel);
        panel.add(userNameTextField);
        panel.add(packageTextField);
        panel.add(okButton);
        panel.add(returnButton);
    }
}
