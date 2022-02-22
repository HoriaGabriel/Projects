package library.view;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartDashboard extends AppFrame{

    @Autowired
    private MemberDashboard memberDashboard;

    @Autowired
    private BookDashboard bookDashboard;

    @Autowired
    private UpdateBookDashboard updatebookDashboard;

    @Autowired
    private UpdateMemberDashboard updatememberDashboard;

    @Autowired
    private DeleteMemberDashboard deletememberDashboard;

    @Autowired
    private DeleteBookDashboard deletebookDashboard;

    @Autowired
    private SearchBookDashboard searchbookDashboard;

    @Autowired
    private ReturnBookDashboard returnbookDashboard;

    private JButton addMemberButton;
    private JButton addBookButton;
    private JButton searchBookButton;
    private JButton addUpdateBookButton;
    private JButton addUpdateMemberButton;
    private JButton deleteMemberButton;
    private JButton deleteBookButton;
    private JButton returnBookButton;

    public void initialize(){

        this.setSize(470,350);
        this.setTitle("Start");
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

        addMemberButton.addActionListener(e->{

            memberDashboard.initialize();
            this.setVisible(false);

        });

        addBookButton.addActionListener(e->{

            bookDashboard.initialize();
            this.setVisible(false);
        });

        addUpdateBookButton.addActionListener(e->{

            updatebookDashboard.initialize();
            this.setVisible(false);
        });

        addUpdateMemberButton.addActionListener(e->{

            updatememberDashboard.initialize();
            this.setVisible(false);
        });

        deleteMemberButton.addActionListener(e->{

            deletememberDashboard.initialize();
            this.setVisible(false);
        });

        deleteBookButton.addActionListener(e->{

            deletebookDashboard.initialize();
            this.setVisible(false);
        });

        searchBookButton.addActionListener(e->{

            searchbookDashboard.initialize();
            this.setVisible(false);
        });

        returnBookButton.addActionListener(e->{

            returnbookDashboard.initialize();
            this.setVisible(false);
        });
    }

    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Welcome! What would you like to do?");
        introLabel.setBounds(130,10,500,30);

        addMemberButton = new JButton("Add Member");
        addMemberButton.setBounds(10,70,200,30);

        addBookButton = new JButton("Add Book");
        addBookButton.setBounds(230,70,200,30);

        searchBookButton = new JButton("Search Book");
        searchBookButton.setBounds(230,220,200,30);

        addUpdateBookButton = new JButton("Update Book");
        addUpdateBookButton.setBounds(230,120,200,30);

        addUpdateMemberButton = new JButton("Update Member");
        addUpdateMemberButton.setBounds(10,120,200,30);

        deleteMemberButton = new JButton("Delete Member");
        deleteMemberButton.setBounds(10,170,200,30);

        deleteBookButton = new JButton("Delete Book");
        deleteBookButton.setBounds(230,170,200,30);

        returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(10,220,200,30);



        panel.add(introLabel);
        panel.add(addMemberButton);
        panel.add(addBookButton);
        panel.add(searchBookButton);
        panel.add(addUpdateBookButton);
        panel.add(addUpdateMemberButton);
        panel.add(deleteMemberButton);
        panel.add(deleteBookButton);
        panel.add(returnBookButton);
    }
}
