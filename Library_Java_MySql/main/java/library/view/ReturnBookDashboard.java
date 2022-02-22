package library.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import library.controller.BookController;
import library.controller.ReservationController;
import library.model.Book;
import library.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import library.controller.PersonController;
import library.model.Person;
import java.time.LocalDate;

@Component
public class ReturnBookDashboard extends AppFrame{

    private JTextField reserveBookTextField;
    private JTextField reserveMemberTextField;
    private JButton returnBookButton;
    private JButton backButton;

    @Autowired
    private BookController bookController;

    @Autowired
    private PersonController personController;

    @Autowired
    private ReservationController reservationController;

    @Autowired
    private StartDashboard dashboard;

    public void initialize(){

        this.setTitle("ReturnBook");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListener();

        this.setContentPane(panel);
        this.setVisible(true);

    }

    private void initializeListener() {

        returnBookButton.addActionListener(e->{

            if(searchBook(reserveBookTextField.getText())!=null && searchPerson(reserveMemberTextField.getText())!=null){

                Reservation r = searchReservation(searchBook(reserveBookTextField.getText()).getId(),searchPerson(reserveMemberTextField.getText()).getId());
                r.setStatus("Returned");

                if(LocalDate.now().compareTo(r.getReturnDate())>0){
                    r.setOverdue("Yes");
                    displayInformationMessage("The book is late");
                }
                else{
                    displayInformationMessage("The book was returned on time");
                }

                reservationController.save(r);

                Book b =searchBook(reserveBookTextField.getText());
                b.setAvailable(b.getAvailable()+1);
                bookController.save(b);
                displayInformationMessage("Return successfully made");
            }

        });

        backButton.addActionListener(e->{
            this.setVisible(false);
            dashboard.initialize();
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel bookLabel = new JLabel("Name of the book");
        bookLabel.setBounds(10,30,280,30);
        panel.add(bookLabel);

        reserveBookTextField = new JTextField();
        reserveBookTextField.setBounds(10, 70, 180, 30);
        panel.add(reserveBookTextField);

        returnBookButton = new JButton("Return");
        returnBookButton.setBounds(200,150,130,30);
        panel.add(returnBookButton);

        JLabel memberLabel = new JLabel("Name of the member");
        memberLabel.setBounds(10,120,280,30);
        panel.add(memberLabel);

        backButton = new JButton("Back");
        backButton.setBounds(10,200,130,30);
        panel.add(backButton);

        reserveMemberTextField = new JTextField();
        reserveMemberTextField.setBounds(10, 150, 180, 30);
        panel.add(reserveMemberTextField);

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

    private Person searchPerson(String name) {

        for (Person temp : personController.findAll()) {
            if(temp.getName().compareTo(name)==0) {
                return temp;
            }
            continue;
        }

        return null;
    }

    private Reservation searchReservation(Long book, Long member) {

        for (Reservation temp : reservationController.findAll()) {
            if(temp.getBookID()== book && temp.getMemberID()==member && temp.getStatus().compareTo("NotReturned")==0) {
                return temp;
            }
            continue;
        }

        return null;
    }

}
