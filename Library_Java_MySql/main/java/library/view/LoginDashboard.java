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
public class LoginDashboard extends AppFrame{

    @Autowired
    private Dashboard dashboard;

    @Autowired
    private LibrarianController librarianController;

    @Autowired
    private StartDashboard startDashboard;

    private JTextField librarianNameTextField;
    private JTextField librarianPasswordTextField;
    private JButton okLibrarianButton;
    private JButton returnLibrarianButton;

    public void initialize(){

        this.setSize(300,300);
        this.setTitle("Login");
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

        okLibrarianButton.addActionListener(e->{
              Librarian librarian = searchLibrarian(librarianNameTextField.getText(), librarianPasswordTextField.getText());

              try{
                  librarianController.validate(librarian);
                  startDashboard.initialize();
                  this.setVisible(false);


              }catch (ValidationFailedException exception) {
                  displayErrorMessage(exception);
              }

        });

        returnLibrarianButton.addActionListener(e->{
            this.setVisible(false);
            dashboard.initialize();
        });

    }

    private Librarian searchLibrarian(String name, String password) {

        for (Librarian temp : librarianController.findAll()) {
            if(temp.getName().compareTo(name)==0 && temp.getPassword().compareTo(password)==0) {
                return temp;
            }
            continue;
        }

        return null;
    }

    private void initializeForm(JPanel panel) {

        JLabel librarianNameLabel = new JLabel("Name: ");
        librarianNameLabel.setBounds(20,30,200,30);

        librarianNameTextField=new JTextField();
        librarianNameTextField.setBounds(20,60,200,30);

        JLabel librarianPasswordLabel = new JLabel("Password: ");
        librarianPasswordLabel.setBounds(20,100,200,30);

        librarianPasswordTextField=new JPasswordField();
        librarianPasswordTextField.setBounds(20,130,200,30);

        okLibrarianButton = new JButton("Ok");
        okLibrarianButton.setBounds(20,190,70,30);

        returnLibrarianButton = new JButton("Return");
        returnLibrarianButton.setBounds(120,190,100,30);

        panel.add(librarianNameLabel);
        panel.add(librarianNameTextField);
        panel.add(librarianPasswordLabel);
        panel.add(librarianPasswordTextField);
        panel.add(okLibrarianButton);
        panel.add(returnLibrarianButton);

    }
}
