package utcn.utcluj.librariansite.payload.request;


public class ReturnRequest {

    private String user;
    private String book;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
