package library.view;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import library.controller.*;
import library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;
import javax.swing.*;

@Component
public class DeleteBookDashboard extends AppFrame{

    private JButton deleteBookButton;
    private JButton returnBookButton;
    private JTextField titlBookTextField;

    private JFrame frame;
    private JTable table;
    Connection conn = null;
    PreparedStatement statement = null;

    @Autowired
    private BookController bookController;

    @Autowired
    private StartDashboard dashboard;

    public void initialize(){

        this.setSize(400,300);
        this.setTitle("Delete");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel= new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);

    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Write name of the book to be deleted");
        introLabel.setBounds(80,10,500,30);

        titlBookTextField = new JTextField();
        titlBookTextField.setBounds(80, 70, 200, 30);

        deleteBookButton = new JButton("Delete");
        deleteBookButton.setBounds(80,110,200,30);

        returnBookButton = new JButton("Return");
        returnBookButton.setBounds(80,160,200,30);

        panel.add(deleteBookButton);
        panel.add(introLabel);
        panel.add(titlBookTextField);
        panel.add(returnBookButton);

    }

    private void initializeListeners(){

        deleteBookButton.addActionListener(e->{

            if(searchBook(titlBookTextField.getText())!=null){

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
                    String sql = "delete from books where title=?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1,titlBookTextField.getText());
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record deleted succesfully");
                    conn.close();

                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });

        returnBookButton.addActionListener(e->{
            this.setVisible(false);
            dashboard.initialize();
        });

    }

    private Book searchBook(String text) {

        for (Book temp : bookController.findAll()) {
            if(temp.getTitle().compareTo(text)==0) {
                return temp;
            }
            continue;
        }

        return null;
    }
}
