package Services.LoanManagers;

public interface LoanManager {
    void lendBook(String isbn);
    void returnBook(String isbn);
}
