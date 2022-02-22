package library.view;

import javax.swing.*;

public class BookForm {

    private JTextField publisherBookTextField;
    private JTextField genreBookTextField;
    private JTextField titleBookTextField;
    private JTextField isbnBookTextField;
    private JTextField authorBookTextField;
    private JTextField nrAvailableBookTextField;
    private JTextField shelfBookTextField;
    private JButton updateButton;

    public void initialize(JPanel panel){

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

        genreBookTextField = new JTextField();
        genreBookTextField.setBounds(10, 180, 200, 30);

        JLabel publisherBookLabel = new JLabel("Publisher:");
        publisherBookLabel.setBounds(10, 220, 200, 30);

        publisherBookTextField = new JTextField();
        publisherBookTextField.setBounds(10, 250, 200, 30);

        JLabel isbnBookLabel = new JLabel("ISBN:");
        isbnBookLabel.setBounds(10, 290, 200, 30);

        isbnBookTextField = new JTextField();
        isbnBookTextField.setBounds(10, 320, 200, 30);

        JLabel nrAvailableBookLabel = new JLabel("Available:");
        nrAvailableBookLabel.setBounds(10, 360, 200, 30);

        nrAvailableBookTextField = new JTextField();
        nrAvailableBookTextField.setBounds(10, 390, 200, 30);

        JLabel shelfBookLabel = new JLabel("Shelf");
        shelfBookLabel.setBounds(10, 430, 200, 30);

        shelfBookTextField = new JTextField();
        shelfBookTextField.setBounds(10, 460, 200, 30);


        panel.add(titleBookLabel);
        panel.add(titleBookTextField);
        panel.add(genreBookLabel);
        panel.add(genreBookTextField);
        panel.add(publisherBookLabel);
        panel.add(publisherBookTextField);
        panel.add(isbnBookLabel);
        panel.add(isbnBookTextField);
        panel.add(authorBookLabel);
        panel.add(authorBookTextField);
        panel.add(nrAvailableBookLabel);
        panel.add(nrAvailableBookTextField);
        panel.add(shelfBookLabel);
        panel.add(shelfBookTextField);

    }


    public void initialize(JPanel panel,int i){

        JLabel headLabel = new JLabel("Here you can update what you want");
        headLabel.setBounds(100, 20, 200, 30);

        JLabel titleBookLabel = new JLabel("Full title:");
        titleBookLabel.setBounds(10, 60, 200, 30);

        titleBookTextField = new JTextField();
        titleBookTextField.setBounds(10, 100, 200, 30);

        JLabel genreBookLabel = new JLabel("Genre:");
        genreBookLabel.setBounds(10, 150, 200, 30);

        genreBookTextField = new JTextField();
        genreBookTextField.setBounds(10, 180, 200, 30);

        JLabel publisherBookLabel = new JLabel("Publisher:");
        publisherBookLabel.setBounds(10, 220, 200, 30);

        publisherBookTextField = new JTextField();
        publisherBookTextField.setBounds(10, 250, 200, 30);

        JLabel isbnBookLabel = new JLabel("ISBN:");
        isbnBookLabel.setBounds(10, 290, 200, 30);

        isbnBookTextField = new JTextField();
        isbnBookTextField.setBounds(10, 320, 200, 30);

        JLabel nrAvailableBookLabel = new JLabel("Available:");
        nrAvailableBookLabel.setBounds(10, 360, 200, 30);

        nrAvailableBookTextField = new JTextField();
        nrAvailableBookTextField.setBounds(10, 390, 200, 30);

        JLabel shelfBookLabel = new JLabel("Shelf");
        shelfBookLabel.setBounds(10, 430, 200, 30);

        shelfBookTextField = new JTextField();
        shelfBookTextField.setBounds(10, 460, 200, 30);


        panel.add(headLabel);
        panel.add(titleBookLabel);
        panel.add(titleBookTextField);
        panel.add(genreBookLabel);
        panel.add(genreBookTextField);
        panel.add(publisherBookLabel);
        panel.add(publisherBookTextField);
        panel.add(isbnBookLabel);
        panel.add(isbnBookTextField);
        panel.add(nrAvailableBookLabel);
        panel.add(nrAvailableBookTextField);
        panel.add(shelfBookLabel);
        panel.add(shelfBookTextField);

    }


    public String getTitleBookTextField(){

        return titleBookTextField.getText();
    }

    public void setTitleBookTextField(String title){

        this.titleBookTextField.setText(title);
    }

    public String getGenreBookTextField(){

        return genreBookTextField.getText();
    }

    public void setGenreBookTextField(String genre){

        this.genreBookTextField.setText(genre);
    }

    public String getPublisherBookTextField(){

        return publisherBookTextField.getText();
    }

    public void setPublisherBookTextField(String publisher){

        this.publisherBookTextField.setText(publisher);
    }

    public String getISBNBookTextField(){

        return isbnBookTextField.getText();
    }

    public void setISBNBookTextField(String isbn){

        this.isbnBookTextField.setText(isbn);
    }

    public String getAuthorBookTextField(){

        return authorBookTextField.getText();
    }

    public String getAvailableBookTextField(){

        return nrAvailableBookTextField.getText();
    }

    public void setAvailableBookTextField(int available){

        this.nrAvailableBookTextField.setText(String.valueOf(available));
    }

    public String getShelfBookTextField(){

        return shelfBookTextField.getText();
    }

    public void setShelfBookTextField(String shelf){

        this.shelfBookTextField.setText(shelf);
    }

}
