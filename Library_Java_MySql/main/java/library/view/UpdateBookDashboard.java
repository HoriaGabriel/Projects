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
public class UpdateBookDashboard extends AppFrame{

    private JButton updateBookButton;
    private JTextField titlBookTextField;
    private JButton returnBookButton;

    @Autowired
    private BookController bookController;

    @Autowired
    private GenreController genreController;

    @Autowired
    private PublisherController publisherController;

    @Autowired
    private ShelfController shelfController;

    @Autowired
    private StartDashboard dashboard;

    BookForm bookForm = new BookForm();

    private Object ValidationFailedException;

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

            updateBookButton.addActionListener(e->{

                if(searchBook(titlBookTextField.getText())!=null){

                    JPanel panel2= new JPanel();
                    panel2.setLayout(null);
                    bookForm.initialize(panel2,0);
                    bookForm.setTitleBookTextField(searchBook(titlBookTextField.getText()).getTitle());
                    bookForm.setGenreBookTextField(searchGenre2(searchBook(titlBookTextField.getText()).getGenre()));
                    bookForm.setPublisherBookTextField(searchPublisher2(searchBook(titlBookTextField.getText()).getPublisher()));
                    bookForm.setISBNBookTextField(searchBook(titlBookTextField.getText()).getISBN());
                    bookForm.setAvailableBookTextField(searchBook(titlBookTextField.getText()).getAvailable());
                    bookForm.setShelfBookTextField(searchShelf2(searchBook(titlBookTextField.getText()).getShelf()));

                    JButton updateButton = new JButton("Update");
                    updateButton.setBounds(10, 510, 120, 30);

                    JButton returnButton = new JButton("Return");
                    returnButton.setBounds(150, 510, 120, 30);

                    panel2.add(updateButton);
                    panel2.add(returnButton);

                    this.setContentPane(panel2);
                    this.setVisible(true);

                    this.setSize(400,600);

                    updateButton.addActionListener(f -> {

                        Book temp = searchBook(titlBookTextField.getText());

                        if(StringUtils.isNotBlank(bookForm.getAvailableBookTextField())){

                            if(temp.getAvailable() != Integer.parseInt(bookForm.getAvailableBookTextField())){

                                temp.setAvailable(Integer.parseInt(bookForm.getAvailableBookTextField()));
                                bookController.save(temp);
                            }
                        }


                        if(StringUtils.isNotBlank(bookForm.getTitleBookTextField())){

                            if(temp.getTitle().compareTo(bookForm.getTitleBookTextField())!=0){

                                temp.setTitle(bookForm.getTitleBookTextField());
                                bookController.save(temp);
                            }
                        }

                        if(StringUtils.isNotBlank(bookForm.getISBNBookTextField())){

                            if(temp.getISBN().compareTo(bookForm.getISBNBookTextField())!=0){

                                temp.setISBN(bookForm.getISBNBookTextField());
                                bookController.save(temp);
                            }
                        }


                        if(StringUtils.isNotBlank(bookForm.getGenreBookTextField())){

                            if(searchGenre(bookForm.getGenreBookTextField())==null){
                                Genre genre = createGenre(bookForm.getGenreBookTextField());
                                try {
                                    genreController.save(genre);
                                    temp.setGenre(searchGenre(bookForm.getGenreBookTextField()).getId());
                                    bookController.save(temp);
                                } catch (ValidationFailedException exception) {
                                    displayErrorMessage(exception);
                                }
                            }

                            if(temp.getGenre() != searchGenre(bookForm.getGenreBookTextField()).getId()){

                                temp.setGenre(searchGenre(bookForm.getGenreBookTextField()).getId());
                                bookController.save(temp);
                            }
                        }

                        if(StringUtils.isNotBlank(bookForm.getShelfBookTextField())){

                            if(searchShelf(bookForm.getShelfBookTextField())==null){
                                Shelf shelf = createShelf(bookForm.getShelfBookTextField());
                                try {
                                    shelfController.save(shelf);
                                    temp.setShelf(searchShelf(bookForm.getShelfBookTextField()).getId());
                                    bookController.save(temp);
                                } catch (ValidationFailedException exception) {
                                    displayErrorMessage(exception);
                                }
                            }

                            if(temp.getShelf() != searchShelf(bookForm.getShelfBookTextField()).getId()){

                                temp.setShelf(searchShelf(bookForm.getShelfBookTextField()).getId());
                                bookController.save(temp);
                            }
                        }

                        if(StringUtils.isNotBlank(bookForm.getPublisherBookTextField())){

                            if(searchPublisher(bookForm.getPublisherBookTextField())==null){

                                Publisher publisher = createPublisher(bookForm.getPublisherBookTextField());
                                try {
                                    publisherController.save(publisher);
                                    temp.setPublisher(searchPublisher(bookForm.getPublisherBookTextField()).getId());
                                    bookController.save(temp);
                                } catch (ValidationFailedException exception) {
                                    displayErrorMessage(exception);
                                }
                            }

                            if(temp.getPublisher() != searchPublisher(bookForm.getPublisherBookTextField()).getId()){

                                temp.setPublisher(searchPublisher(bookForm.getPublisherBookTextField()).getId());
                                bookController.save(temp);
                            }
                        }

                        displayInformationMessage("Update successfully made");

                    });

                    returnButton.addActionListener(f->{
                        this.setVisible(false);
                        dashboard.initialize();
                    });
                }
                else
                {
                    Exception exception = new ValidationFailedException("Title does not exist");
                    displayErrorMessage(exception);
                }

            });


        returnBookButton.addActionListener(e->{
            this.setVisible(false);
            dashboard.initialize();
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Write title of the book to be updated");
        introLabel.setBounds(200,10,500,30);

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
            String sql = "select title,available,isbn,shelf,publisher,genre from books inner join shelf on shelfID=shelf.id inner join  genre on genreID=genre.id inner join publisher on publisherId=publisher.id";
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

        titlBookTextField = new JTextField();
        titlBookTextField.setBounds(200, 180, 200, 30);

        updateBookButton = new JButton("Update");
        updateBookButton.setBounds(200,230,200,30);

        returnBookButton = new JButton("Return");
        returnBookButton.setBounds(200,280,200,30);

        panel.add(updateBookButton);
        panel.add(introLabel);
        panel.add(titlBookTextField);
        panel.add(returnBookButton);

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

    private Shelf searchShelf(String text) {

        for (Shelf temp : shelfController.findAll()) {
            if(temp.getShelf().compareTo(text)==0) {
                return temp;
            }
            continue;
        }

        return null;
    }

    private String searchShelf2(Long l) {

        for (Shelf temp : shelfController.findAll()) {
            if(temp.getId()==l) {
                return temp.getShelf();
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

    private String searchGenre2(Long l) {

        for (Genre temp : genreController.findAll()) {
            if(temp.getId()==l) {
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

    private String searchPublisher2(Long l) {

        for (Publisher temp : publisherController.findAll()) {
            if(temp.getId()==l) {
                return temp.getPublisher();
            }
            continue;
        }

        return null;
    }

    private Genre createGenre(String text) {

        Genre genre = new Genre();
        genre.setGenre(text);
        return genre;
    }

    private Publisher createPublisher(String text) {

        Publisher publisher = new Publisher();
        publisher.setPublisher(text);
        return publisher;
    }

    private Shelf createShelf(String text) {

        Shelf shelf = new Shelf();
        shelf.setShelf(text);
        return shelf;
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
