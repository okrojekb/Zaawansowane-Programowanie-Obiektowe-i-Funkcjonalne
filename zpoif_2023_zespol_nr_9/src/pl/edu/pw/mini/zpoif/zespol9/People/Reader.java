package pl.edu.pw.mini.zpoif.zespol9.People;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Book.Status;
import pl.edu.pw.mini.zpoif.zespol9.System.CatalogueAccess;

import java.time.LocalDate;
import java.util.*;

public class Reader extends Person {

    private List<Book> reservedBooks = new ArrayList<>();
    private double fine;
    private Map<Book, LocalDate> checkedOutBooks = new LinkedHashMap<>();
    private List<Book> toReadBooks = new ArrayList<>();
    private static CatalogueAccess catalogueAccess;

    public Reader(String name, String surname, CatalogueAccess catalogueAccess) {
        super(name, surname);
        this.signInData.setLogin("r" + this.signInData.getLogin());
        this.catalogueAccess = catalogueAccess;
    }

    public void reserveBook(Book book) {
        book.status = Status.Reserved;
        reservedBooks.add(book);
    }

    public void deleteReservedBook(Book book) {
        reservedBooks.remove(book);
    }

    public boolean addToReadBook(Book book) {
        if (!toReadBooks.contains(book)) {
            toReadBooks.add(book);
            return true;
        } else {
            return false;
        }
    }

    public void deleteToReadBook(Book book) {
        toReadBooks.remove(book);
    }

    public List<Book> getReservedBooks() {
        return reservedBooks;
    }

    public double getFine() {
        return fine;
    }

    public void addFine(double fine) {
        this.fine += fine;
    }

    public void payFine(double payment) {
        this.fine -= payment;
    }

    public Map<Book, LocalDate> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public List<Book> getToReadBooks() {
        return toReadBooks;
    }

    public boolean postponeReturnDate(Book book) {
        if (!book.postponed) {
            LocalDate returnDate = checkedOutBooks.get(book);
            LocalDate newReturnDate = returnDate.plusDays(30);
            checkedOutBooks.put(book, newReturnDate);
            book.postponed = true;
            return true;
        } else {
            return false;
        }
    }
}