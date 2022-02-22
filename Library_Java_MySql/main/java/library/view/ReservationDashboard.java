package library.view;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import library.controller.BookController;
import library.controller.PersonController;
import library.controller.ReservationController;
import library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;

@Component
public class ReservationDashboard extends AppFrame{

    private JTextField reserveBookTextField;
    private JTextField reserveMemberTextField;
    private JButton reserveBookButton;
    private JButton returnButton;


    @Autowired
    private BookController bookController;

    @Autowired
    private ReservationController reservationController;

    @Autowired
    private PersonController personController;

    @Autowired
    private StartDashboard dashboard;


    public void initialize(){

        this.setSize(400,400);
        this.setTitle("Table");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        initializeListeners();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeListeners() {

            reserveBookButton.addActionListener(e->{
                if(searchBook(reserveBookTextField.getText())!=null && searchBook(reserveBookTextField.getText()).getAvailable()>0){

                    Reservation reservation = createReservation(searchBook(reserveBookTextField.getText()).getId(), searchPerson(reserveMemberTextField.getText()).getId());
                    try{
                        reservationController.save(reservation);
                        Book b =searchBook(reserveBookTextField.getText());
                        b.setAvailable(b.getAvailable()-1);
                        bookController.save(b);
                        displayInformationMessage("Reservation successfully made");
                    } catch (ValidationFailedException exception) {
                        displayErrorMessage(exception);
                    }

                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.transport.protocol", "smtp");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.socketFactory.port", "587");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

                    Session session = Session.getDefaultInstance(props);

                    try {
                        InternetAddress fromAddress = new InternetAddress("rusu.horia@gmail.com");
                        InternetAddress toAddress = new InternetAddress("rusu.horia@gmail.com");

                        Message message = new MimeMessage(session);
                        message.setFrom(fromAddress);
                        message.setRecipient(Message.RecipientType.TO, toAddress);
                        message.setSubject("BookReservation");
                        message.setText("The book is yours!");

                        Transport.send(message, "Horia Rusu","gabriel20");
                        displayInformationMessage("Email successfully made");
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            returnButton.addActionListener(e->{
                this.setVisible(false);
                dashboard.initialize();
            });
    }

    private void initializeForm(JPanel panel) {


        JLabel bookLabel = new JLabel("Name of the book");
        bookLabel.setBounds(10,20,280,30);
        panel.add(bookLabel);

        reserveBookTextField = new JTextField();
        reserveBookTextField.setBounds(10, 70, 280, 30);
        panel.add(reserveBookTextField);

        reserveBookButton = new JButton("Reserve");
        reserveBookButton.setBounds(170,230,120,30);
        panel.add(reserveBookButton);

        JLabel memberLabel = new JLabel("Name of the member");
        memberLabel.setBounds(10,130,280,30);
        panel.add(memberLabel);

        reserveMemberTextField = new JTextField();
        reserveMemberTextField.setBounds(10, 170, 280, 30);
        panel.add(reserveMemberTextField);

        returnButton = new JButton("Return");
        returnButton.setBounds(10,230,120,30);
        panel.add(returnButton);

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

    private Reservation createReservation(Long BookID, Long MemberID) {

        Reservation reservation = new Reservation();
        reservation.setBookID(BookID);
        reservation.setMemberID(MemberID);
        reservation.setReturnDate(LocalDate.now());
        reservation.setStatus("NotReturned");
        reservation.setOverdue("No");

        return reservation;

    }
}
