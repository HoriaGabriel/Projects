package library.view;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import library.controller.LibrarianController;
import library.exception.ValidationFailedException;
import library.model.Librarian;

@Component
public class SetupDashboard extends AppFrame{

    @Autowired
    private LibrarianController librarianController;

    @Autowired
    private Dashboard dashboard;

    private JTextField librarianNameTextField;
    private JPasswordField librarianPasswordTextField;
    private JTextField librarianAddressTextField;
    private JTextField librarianPhoneTextField;
    private JButton saveLibrarianButton;
    private JButton returnLibrarianButton;

    @Override
    public void initialize(){
        this.setSize(400,400);
        this.setTitle("SetUp");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel= new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeListeners() {
        saveLibrarianButton.addActionListener(e -> {
            Librarian librarian = createLibrarian(librarianNameTextField.getText(), librarianPasswordTextField.getText(),
                    librarianAddressTextField.getText(), librarianPhoneTextField.getText());
            try{
                 librarianController.save(librarian);
                 displayInformationMessage("Librarian successfully saved");
            } catch (ValidationFailedException exception) {
                displayErrorMessage(exception);
            }
        });

        returnLibrarianButton.addActionListener(e->{
            this.setVisible(false);
            dashboard.initialize();
        });
    }

    private Librarian createLibrarian(String name, String password, String address, String phone) {

        Librarian librarian = new Librarian();
        librarian.setName(name);
        librarian.setPassword(password);
        librarian.setAddress(address);
        librarian.setPhoneNumber(phone);
        return librarian;

    }

    private void initializeForm(JPanel panel) {

        JLabel librarianNameLabel = new JLabel("Name: ");
        librarianNameLabel.setBounds(20,20,200,30);

        librarianNameTextField=new JTextField();
        librarianNameTextField.setBounds(20,50,200,30);

        JLabel librarianPasswordLabel = new JLabel("Password: ");
        librarianPasswordLabel.setBounds(20,90,200,30);

        librarianPasswordTextField=new JPasswordField();
        librarianPasswordTextField.setBounds(20,120,200,30);

        JLabel librarianAddressLabel = new JLabel("Address: ");
        librarianAddressLabel.setBounds(20,160,200,30);

        librarianAddressTextField=new JTextField();
        librarianAddressTextField.setBounds(20,190,200,30);

        JLabel librarianPhoneLabel = new JLabel("Phone: ");
        librarianPhoneLabel.setBounds(20,230,200,30);

        librarianPhoneTextField=new JTextField();
        librarianPhoneTextField.setBounds(20,260,200,30);

        saveLibrarianButton = new JButton("Save");
        saveLibrarianButton.setBounds(20,300,70,30);

        returnLibrarianButton = new JButton("Return");
        returnLibrarianButton.setBounds(120,300,100,30);

        panel.add(librarianNameLabel);
        panel.add(librarianNameTextField);
        panel.add(librarianPasswordLabel);
        panel.add(librarianPasswordTextField);
        panel.add(librarianAddressLabel);
        panel.add(librarianAddressTextField);
        panel.add(librarianPhoneLabel);
        panel.add(librarianPhoneTextField);
        panel.add(saveLibrarianButton);
        panel.add(returnLibrarianButton);
    }
}
