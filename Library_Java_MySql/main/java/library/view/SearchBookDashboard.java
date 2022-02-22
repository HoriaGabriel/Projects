package library.view;


import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.time.LocalDate;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import library.controller.BookController;
import library.controller.GenreController;
import library.controller.PublisherController;
import library.controller.AuthorController;
import library.controller.PersonController;
import library.controller.ReservationController;
import library.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.JFrame;


@Component
public class SearchBookDashboard extends AppFrame{

    private JTextField publisherBookTextField;
    private JTextField genreBookTextField;
    private JTextField titleBookTextField;
    private JTextField authorBookTextField;
    private JTextField reserveBookTextField;
    private JTextField reserveMemberTextField;
    private JButton searchBookButton;
    private JButton reserveBookButton;
    private JButton returnBookButton;
    private JComboBox genreBox;
    private JComboBox publisherBox;

    private JFrame frame;
    private JTable table;
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    @Autowired
    private BookController bookController;

    @Autowired
    private GenreController genreController;

    @Autowired
    private PublisherController publisherController;

    @Autowired
    private AuthorController authorController;

    @Autowired
    private PersonController personController;

    @Autowired
    private ReservationController reservationController;

    @Autowired
    private ReservationDashboard reservationDashboard;

    @Autowired
    private StartDashboard dashboard;

    private TableDashboard tableDashboard;

    public void initialize() {

        this.setSize(550,500);
        this.setTitle("Start");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners(panel);

        this.setContentPane(panel);
        this.setVisible(true);

    }

    private void initializeListeners(JPanel panel) {

        searchBookButton.addActionListener(e->{

            if(StringUtils.isNotBlank(titleBookTextField.getText())){

                try{

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
                    String sql = "select title,name,available,shelf from books join authors_books on books.id=booksid join authors on authors.id=authorsid join shelf on shelfid=shelf.id where title=?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1,titleBookTextField.getText());

                    rs = statement.executeQuery();

                    table = new JTable(buildTableModel(rs));

                    JScrollPane tableContainer = new JScrollPane(table);
                    tableContainer.setBounds(230,20,300,100);
                    panel.add(tableContainer);


                } catch (ClassNotFoundException classNotFoundException) {
                   classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                   throwables.printStackTrace();
                }

            } else if(genreBox.getSelectedIndex()!=-1 && publisherBox.getSelectedIndex()==-1
                     && StringUtils.isBlank(authorBookTextField.getText())) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
                    String sql = "select title,available,name,shelf from books join authors_books on books.id=booksid join authors on authors.id=authorsid join genre on books.genreID=genre.id join shelf on shelfid=shelf.id where genre.genre=?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, searchGenre2(genreBox.getSelectedIndex()));

                    rs = statement.executeQuery();

                    table = new JTable(buildTableModel(rs));

                    System.out.println("ok2");

                    JScrollPane tableContainer = new JScrollPane(table);
                    tableContainer.setBounds(230,20,300,100);
                    panel.add(tableContainer);


                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else if(publisherBox.getSelectedIndex()!=-1 &&  genreBox.getSelectedIndex()==-1
                    && StringUtils.isBlank(authorBookTextField.getText())) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
                    String sql = "select title,available,name,shelf from books join authors_books on books.id=booksid join authors on authors.id=authorsid join publisher on books.publisherID=publisher.id join shelf on shelfid=shelf.id where publisher.publisher=?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, searchPublisher2(publisherBox.getSelectedIndex()));


                    rs = statement.executeQuery();

                    table = new JTable(buildTableModel(rs));

                    JScrollPane tableContainer = new JScrollPane(table);
                    tableContainer.setBounds(230,20,300,100);
                    panel.add(tableContainer);

                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else if(StringUtils.isNotBlank(authorBookTextField.getText()) && publisherBox.getSelectedIndex()==-1
                    && genreBox.getSelectedIndex()==-1) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
                    String sql = "select title,name,available,shelf from books join authors_books on books.id=booksid " +
                                 "join authors on authors.id=authorsid join shelf on shelfid=shelf.id where authors.name=?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, authorBookTextField.getText());


                    rs = statement.executeQuery();

                    table = new JTable(buildTableModel(rs));

                    JScrollPane tableContainer = new JScrollPane(table);
                    tableContainer.setBounds(230,20,300,100);
                    panel.add(tableContainer);


                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else if(StringUtils.isNotBlank(authorBookTextField.getText()) && publisherBox.getSelectedIndex()!=-1
                    && genreBox.getSelectedIndex()==-1) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
                    String sql = "select title,name,available,shelf from books join authors_books on books.id=booksid join authors on authors.id=authorsid " +
                                 "join publisher on books.publisherID=publisher.id join shelf on shelfid=shelf.id" +
                                 " where authors.name=? and publisher.publisher = ?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1,authorBookTextField.getText());
                    statement.setString(2,searchPublisher2(publisherBox.getSelectedIndex()));


                    rs = statement.executeQuery();

                    table = new JTable(buildTableModel(rs));

                    JScrollPane tableContainer = new JScrollPane(table);
                    tableContainer.setBounds(230,20,300,100);
                    panel.add(tableContainer);



                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else if(StringUtils.isBlank(authorBookTextField.getText()) && publisherBox.getSelectedIndex()!=-1
                    && genreBox.getSelectedIndex()!=-1) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
                    String sql = "select title,available,name,shelf from books join authors_books on books.id=booksid join authors on authors.id=authorsid join genre on books.genreID=genre.id " +
                            "join publisher on books.publisherID=publisher.id join shelf on shelfid=shelf.id" +
                            " where genre.genre=? and publisher.publisher=?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, searchGenre2(genreBox.getSelectedIndex()));
                    statement.setString(2, searchPublisher2(publisherBox.getSelectedIndex()));

                    rs = statement.executeQuery();

                    table = new JTable(buildTableModel(rs));

                    JScrollPane tableContainer = new JScrollPane(table);
                    tableContainer.setBounds(230,20,300,100);
                    panel.add(tableContainer);

                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else if(StringUtils.isNotBlank(authorBookTextField.getText()) && publisherBox.getSelectedIndex()==-1
                    && genreBox.getSelectedIndex()!=-1) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
                    String sql = "select title,name,available,shelf from books join authors_books on books.id=booksid " +
                                 "join authors on authors.id=authorsid " +
                                 "join genre on books.genreID=genre.id join shelf on shelfid=shelf.id" +
                                 " where authors.name=? and genre.genre = ?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, authorBookTextField.getText());
                    statement.setString(2, searchGenre2(genreBox.getSelectedIndex()));

                    rs = statement.executeQuery();

                    table = new JTable(buildTableModel(rs));

                    JScrollPane tableContainer = new JScrollPane(table);
                    tableContainer.setBounds(230,20,300,100);
                    panel.add(tableContainer);

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

        reserveBookButton.addActionListener(e->{
            reservationDashboard.initialize();
            this.setVisible(false);
        });

    }


    private void initializeForm(JPanel panel) {

        JLabel titleBookLabel = new JLabel("Full title:");
        titleBookLabel.setBounds(10, 10, 200, 30);

        titleBookTextField = new JTextField();
        titleBookTextField.setBounds(10, 40, 200, 30);

        JLabel authorBookLabel = new JLabel("Author:");
        authorBookLabel.setBounds(10, 80, 200, 30);

        authorBookTextField = new JTextField();
        authorBookTextField.setBounds(10, 110, 200, 30);

        JLabel genreBookLabel = new JLabel("Genre:");
        genreBookLabel.setBounds(10, 150, 200, 30);

        String[] genreStrings = new String[100];
        int i=1;

        for (Genre temp : genreController.findAll()) {

            genreStrings[i]=temp.getGenre();
            i++;
        }

        String[] publisherStrings = new String[100];
        int j=1;

        for (Publisher temp2 : publisherController.findAll()) {

            publisherStrings[j]=temp2.getPublisher();
            j++;
        }

        genreBox = new JComboBox(genreStrings);
        genreBox.setBounds(10, 180, 200, 30);

        publisherBox = new JComboBox(publisherStrings);
        publisherBox.setBounds(10, 250, 200, 30);

        JLabel publisherBookLabel = new JLabel("Publisher:");
        publisherBookLabel.setBounds(10, 220, 200, 30);

        //publisherBookTextField = new JTextField();
        //publisherBookTextField.setBounds(10, 250, 200, 30);

        searchBookButton = new JButton("Search");
        searchBookButton.setBounds(10,320,200,30);

        returnBookButton = new JButton("Return");
        returnBookButton.setBounds(10,370,200,30);

        reserveBookButton = new JButton("Ready to reserve");
        reserveBookButton.setBounds(260,130,200,30);

        panel.add(reserveBookButton);
        panel.add(titleBookLabel);
        panel.add(titleBookTextField);
        panel.add(genreBookLabel);
        panel.add(genreBox);
        panel.add(publisherBox);
        panel.add(publisherBookLabel);
        //panel.add(publisherBookTextField);
        panel.add(authorBookLabel);
        panel.add(authorBookTextField);
        panel.add(searchBookButton);
        panel.add(returnBookButton);
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

    private Book searchBook(String text) {

        for (Book temp : bookController.findAll()) {
            if(temp.getTitle().compareTo(text)==0) {
                return temp;
            }
            continue;
        }

        return null;
    }

    private Genre searchGenre(String text) {

        for (Genre temp : genreController.findAll()) {
            if(temp.getGenre().compareTo(text)==0) {
                return temp;
            }
            continue;
        }

        return null;
    }

    private String searchGenre2(int i) {

        for (Genre temp : genreController.findAll()) {
            if(temp.getId() == i) {
                return temp.getGenre();
            }
            continue;
        }

        return null;
    }

    private Publisher searchPublisher(String text) {

        for (Publisher temp : publisherController.findAll()) {
            if(temp.getPublisher().compareTo(text)==0) {
                return temp;
            }
            continue;
        }

        return null;
    }

    private String searchPublisher2(int i) {

        for (Publisher temp : publisherController.findAll()) {
            if(temp.getId() == i) {
                return temp.getPublisher();
            }
            continue;
        }

        return null;
    }

    private Author searchAuthor(String text) {

        for (Author temp : authorController.findAll()) {
            if(temp.getName().compareTo(text)==0) {
                return temp;
            }
            continue;
        }

        return null;

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

    private Reservation createReservation(Long BookID, Long MemberID) {

        Reservation reservation = new Reservation();
        reservation.setBookID(BookID);
        reservation.setMemberID(MemberID);
        reservation.setReturnDate(LocalDate.now());
        reservation.setStatus("NotReturned");

        return reservation;

    }


}
