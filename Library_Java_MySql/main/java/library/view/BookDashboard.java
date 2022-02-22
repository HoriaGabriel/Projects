package library.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import library.controller.*;
import library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;


@Component
public class BookDashboard extends AppFrame {

    @Autowired
    private GenreController genreController;

    @Autowired
    private PublisherController publisherController;

    @Autowired
    private AuthorController authorController;

    @Autowired
    private BookController bookController;

    @Autowired
    private AuthorBookController authorbookController;

    @Autowired
    private ShelfController shelfController;

    @Autowired
    private StartDashboard dashboard;

    BookForm bookForm = new BookForm();

    private JButton saveBookButton;
    private JButton returnPersonButton;

    public void initialize(){

        this.setTitle("Book");
        this.setSize(400, 580);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        bookForm.initialize(panel);
        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeListeners() {

        saveBookButton.addActionListener(e->{

            if(searchBook(bookForm.getTitleBookTextField())==null) {
                if(searchGenre(bookForm.getGenreBookTextField())==null){

                    Genre genre = createGenre(bookForm.getGenreBookTextField());
                    try {
                        genreController.save(genre);
                        displayInformationMessage("Genre successfully saved!");
                    } catch (ValidationFailedException exception) {
                        displayErrorMessage(exception);
                    }
                }

                if(searchPublisher(bookForm.getPublisherBookTextField())==null){

                    Publisher publisher = createPublisher(bookForm.getPublisherBookTextField());
                    try {
                        publisherController.save(publisher);
                        displayInformationMessage("Publisher successfully saved");
                    } catch (ValidationFailedException exception) {
                        displayErrorMessage(exception);
                    }
                }

                if(searchShelf(bookForm.getShelfBookTextField())==null){

                    Shelf shelf = createShelf(bookForm.getShelfBookTextField());
                    try {
                        shelfController.save(shelf);
                        displayInformationMessage("Shelf successfully saved");
                    } catch (ValidationFailedException exception) {
                        displayErrorMessage(exception);
                    }
                }

                if(searchAuthor(bookForm.getAuthorBookTextField())==null){

                    Author author = createAuthor(bookForm.getAuthorBookTextField());
                    try {
                        authorController.save(author);
                        displayInformationMessage("Author successfully saved");
                    } catch (ValidationFailedException exception) {
                        displayErrorMessage(exception);
                    }
                }


                Long s = searchGenre(bookForm.getGenreBookTextField()).getId();
                Long s1 = searchPublisher(bookForm.getPublisherBookTextField()).getId();
                Long s2 = searchShelf(bookForm.getShelfBookTextField()).getId();

                Book book = createBook(bookForm.getTitleBookTextField(), s, s1, bookForm.getISBNBookTextField(), Integer.parseInt(bookForm.getAvailableBookTextField()),s2);
                try{
                    bookController.save(book);
                    displayInformationMessage("Book successfully saved");
                } catch (ValidationFailedException exception) {
                    displayErrorMessage(exception);
                }

                AuthorBook authorbook = createAuthorBook(book.getId(),searchAuthor(bookForm.getAuthorBookTextField()).getId());
                try{
                    authorbookController.save(authorbook);
                    displayInformationMessage("AuthorBookRelation successfully saved");
                } catch (ValidationFailedException exception) {
                    displayErrorMessage(exception);
                }
            }
            else{
                Book temp = searchBook(bookForm.getTitleBookTextField());
                int aux= Integer.parseInt(bookForm.getAvailableBookTextField())+temp.getAvailable();
                temp.setAvailable(aux);
                bookController.save(temp);
                displayInformationMessage("Book already existed, succesfully updated");
            }

        });

        returnPersonButton.addActionListener(e->{

            this.setVisible(false);
            dashboard.initialize();

        });
    }


    private AuthorBook createAuthorBook(Long id, Long id1) {

        AuthorBook authorbook = new AuthorBook();
        authorbook.setBooksID(id);
        authorbook.setAuthorsID(id1);

        return authorbook;
    }

    private Book createBook(String title, Long genre, Long publisher, String ISBN, int nr, Long shelf) {

        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setPublisher(publisher);
        book.setISBN(ISBN);
        book.setAvailable(nr);
        book.setShelf(shelf);

        return book;
    }

    private Author createAuthor(String text) {

        Author author = new Author();
        author.setName(text);
        return author;
    }

    private Shelf createShelf(String text) {

        Shelf shelf = new Shelf();
        shelf.setShelf(text);
        return shelf;
    }


    private Publisher createPublisher(String text) {

        Publisher publisher = new Publisher();
        publisher.setPublisher(text);
        return publisher;
    }

    private Genre createGenre(String text) {

        Genre genre = new Genre();
        genre.setGenre(text);
        return genre;
    }

    private Author searchAuthor(String text) {

        for (Author temp : authorController.findAll()) {
            if (temp.getName().compareTo(text) == 0) {
                return temp;
            }
            continue;
        }

        return null;
    }

    private Shelf searchShelf(String text) {

        for (Shelf temp : shelfController.findAll()) {
            if (temp.getShelf().compareTo(text) == 0) {
                return temp;
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

    private Genre searchGenre(String text) {

        for (Genre temp : genreController.findAll()) {
            if(temp.getGenre().compareTo(text)==0) {
                return temp;
            }
            continue;
        }

        return null;
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

    private void initializeForm(JPanel panel) {

        saveBookButton = new JButton("Save");
        saveBookButton.setBounds(10, 500, 100, 30);

        returnPersonButton = new JButton("Return");
        returnPersonButton.setBounds(130, 500, 100, 30);

        panel.add(saveBookButton);
        panel.add(returnPersonButton);

    }
}
