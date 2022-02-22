package library.view;

import javax.swing.*;

public class MemberForm {

    private JTextField personNameTextField;
    private JTextField personEmailTextField;
    private JTextField personPasswordTextField;


    public void initialize(JPanel panel) {
        JLabel personNameLabel = new JLabel("Full name:");
        personNameLabel.setBounds(10, 10, 200, 30);

        personNameTextField = new JTextField();
        personNameTextField.setBounds(10, 40, 200, 30);

        JLabel personEmailLabel = new JLabel("Email:");
        personEmailLabel.setBounds(10, 80, 200, 30);

        personEmailTextField = new JTextField();
        personEmailTextField.setBounds(10, 110, 200, 30);

        JLabel personPasswordLabel = new JLabel("Section:");
        personPasswordLabel.setBounds(10, 150, 200, 30);

        personPasswordTextField = new JTextField();
        personPasswordTextField.setBounds(10, 180, 200, 30);

        panel.add(personNameLabel);
        panel.add(personNameTextField);
        panel.add(personEmailLabel);
        panel.add(personEmailTextField);
        panel.add(personPasswordLabel);
        panel.add(personPasswordTextField);
    }

    public String getPersonNameTextField() {
        return personNameTextField.getText();
    }

    public void setPersonNameTextField(String name) {
        personNameTextField.setText(name);
    }

    public String getPersonEmailTextField() {
        return personEmailTextField.getText();
    }

    public void setPersonEmailTextField(String email) {
        personEmailTextField.setText(email);
    }

    public String getPersonPasswordTextField() {
        return personPasswordTextField.getText();
    }

    public void setPersonPasswordTextField(String password) {
        personPasswordTextField.setText(password);
    }

}
