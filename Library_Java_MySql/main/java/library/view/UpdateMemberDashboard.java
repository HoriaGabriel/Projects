package library.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import library.controller.*;
import library.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;
import java.sql.*;
import java.util.*;

@Component
public class UpdateMemberDashboard extends AppFrame{

    @Autowired
    private PersonController personController;

    @Autowired
    private StartDashboard dashboard;

    MemberForm memberForm = new MemberForm();


    private JButton updateMemberButton;
    private JButton returnBookButton;
    private JTextField membrTextField;

    private JFrame frame;
    private JTable table;
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    public void initialize(){

        this.setSize(600,400);
        this.setTitle("Update");
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

        updateMemberButton.addActionListener(e->{

            if(searchPerson(membrTextField.getText())!=null){

                JPanel panel2= new JPanel();
                panel2.setLayout(null);
                memberForm.initialize(panel2);

                memberForm.setPersonNameTextField(searchPerson(membrTextField.getText()).getName());
                memberForm.setPersonEmailTextField(searchPerson(membrTextField.getText()).getEmail());
                memberForm.setPersonPasswordTextField(searchPerson(membrTextField.getText()).getSection());

                JButton updateButton = new JButton("Update");
                updateButton.setBounds(10, 230, 120, 30);

                JButton returnButton = new JButton("Return");
                returnButton.setBounds(150, 230, 120, 30);

                panel2.add(updateButton);
                panel2.add(returnButton);

                this.setContentPane(panel2);
                this.setVisible(true);

                this.setSize(400,400);

                updateButton.addActionListener(f->{

                   Person temp = searchPerson(membrTextField.getText());

                    if(StringUtils.isNotBlank(memberForm.getPersonNameTextField())){

                        if(temp.getName().compareTo(memberForm.getPersonNameTextField())!=0){

                            temp.setName(memberForm.getPersonNameTextField());
                            personController.save(temp);
                        }
                    }

                    if(StringUtils.isNotBlank(memberForm.getPersonEmailTextField())){

                        if(temp.getEmail().compareTo(memberForm.getPersonEmailTextField())!=0){

                            temp.setEmail(memberForm.getPersonEmailTextField());
                            personController.save(temp);
                        }
                    }

                    if(StringUtils.isNotBlank(memberForm.getPersonPasswordTextField())){

                        if(temp.getSection().compareTo(memberForm.getPersonPasswordTextField())!=0){

                            temp.setSection(memberForm.getPersonPasswordTextField());
                            personController.save(temp);
                        }
                    }

                    displayInformationMessage("Update successfully made");

                });

                returnButton.addActionListener(f->{
                    this.setVisible(false);
                    dashboard.initialize();
                });

            }
            else{

                Exception exception = new ValidationFailedException("Member does not exist");
                displayErrorMessage(exception);
            }

        });

        returnBookButton.addActionListener(e->{
            this.setVisible(false);
            dashboard.initialize();
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Write name of the member to be updated");
        introLabel.setBounds(200,10,500,30);

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
            String sql = "select * from member";
            statement = conn.prepareStatement(sql);

            rs = statement.executeQuery();

            table = new JTable(buildTableModel(rs));

            JScrollPane tableContainer = new JScrollPane(table);
            tableContainer.setBounds(40,60,500,100);
            panel.add(tableContainer);


        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        membrTextField = new JTextField();
        membrTextField.setBounds(200, 180, 200, 30);

        updateMemberButton = new JButton("Update");
        updateMemberButton.setBounds(200,230,200,30);

        returnBookButton = new JButton("Return");
        returnBookButton.setBounds(200,280,200,30);

        panel.add(updateMemberButton);
        panel.add(returnBookButton);
        panel.add(introLabel);
        panel.add(membrTextField);

    }

    private Person searchPerson(String name) {

        for (Person temp : personController.findAll()) {
            if(temp.getName().compareTo(name)==0) {
                return temp;
            }
            continue;
        }

        return null;
    }

    private TableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames;
        columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }


        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {

            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {

                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);

        }
        return new DefaultTableModel(data, columnNames);
    }
}
