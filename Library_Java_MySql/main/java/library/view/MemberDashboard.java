package library.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import library.controller.PersonController;
import library.exception.ValidationFailedException;
import library.model.Person;

import java.sql.SQLIntegrityConstraintViolationException;


@Component
public class MemberDashboard extends AppFrame {

    @Autowired
    private PersonController personController;

    @Autowired
    private StartDashboard dashboard;

    MemberForm memberForm= new MemberForm();

    private JButton savePersonButton;
    private JButton returnPersonButton;

    public void initialize() {
        this.setTitle("MemberRegistration");
        this.setSize(350, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        memberForm.initialize(panel);
        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeForm(JPanel panel) {

        savePersonButton = new JButton("Save");
        savePersonButton.setBounds(10, 240, 100, 30);

        returnPersonButton = new JButton("Return");
        returnPersonButton.setBounds(120, 240, 100, 30);

        panel.add(savePersonButton);
        panel.add(returnPersonButton);
    }

    private void initializeListeners() {
        savePersonButton.addActionListener(e -> {
            Person person = createPerson(memberForm.getPersonNameTextField(),memberForm.getPersonEmailTextField(),memberForm.getPersonPasswordTextField());
            try {
                personController.save(person);
                displayInformationMessage("Person successfully saved");
            } catch (ValidationFailedException exception) {
                displayErrorMessage(exception);
            }
        });

        returnPersonButton.addActionListener(e->{
            this.setVisible(false);
            dashboard.initialize();
        });
    }

    private Person createPerson(String name, String email, String password) {
        Person person = new Person();
        person.setName(name);
        person.setEmail(email);
        person.setSection(password);
        return person;
    }

}
