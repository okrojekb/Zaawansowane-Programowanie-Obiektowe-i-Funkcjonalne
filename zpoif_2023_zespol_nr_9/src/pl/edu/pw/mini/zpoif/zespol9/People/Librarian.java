package pl.edu.pw.mini.zpoif.zespol9.People;


import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Book.BookCondition;
import pl.edu.pw.mini.zpoif.zespol9.Book.Status;
import pl.edu.pw.mini.zpoif.zespol9.Exceptions.NoReaderWithThatLoginException;
import pl.edu.pw.mini.zpoif.zespol9.System.CatalogueAccess;
import pl.edu.pw.mini.zpoif.zespol9.System.CheckOutDesk;
import pl.edu.pw.mini.zpoif.zespol9.System.SystemAccess;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Librarian extends Person implements CheckOutDesk {

    private final SystemAccess systemAccess;

    public Librarian(String name, String surname, SystemAccess systemAccess) {
        super(name, surname);
        this.signInData.setLogin("l" + this.signInData.getLogin());
        this.systemAccess = systemAccess;
    }

    @Override
    public void addBook(Book book) {
        systemAccess.getCatalogue().getCatalogue().add(book);
    }

    @Override
    public void deleteBook(Book book) {
        systemAccess.getCatalogue().getCatalogue().remove(book);
    }

    @Override
    public boolean checkOutBook(String login, Book book) throws NoReaderWithThatLoginException {
        Reader reader = systemAccess.getReader(login);
        if (book.status == Status.Available) {
            book.status = Status.CheckedOut;
            reader.getCheckedOutBooks().put(book, LocalDate.now().plusDays(30));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean acceptBookReturn(String login, Book book, BookCondition bookCondition) throws NoReaderWithThatLoginException {
        Reader reader = systemAccess.getReader(login);
        LocalDate returnDate = reader.getCheckedOutBooks().remove(book);
        if (returnDate == null) {
            return false;
        } else {
            if (LocalDate.now().isAfter(returnDate)) {
                long holdoverdays = Math.abs(ChronoUnit.DAYS.between(returnDate, LocalDate.now()));
                imposeFine(reader, 0.2 * holdoverdays);
            }
            book.bookCondition = bookCondition;
            book.status = Status.Available;
            return true;
        }
    }

    @Override
    public SignInData addUser(String name, String surname) {
        CatalogueAccess catalogueAccess = systemAccess.getCatalogueAccess();
        Reader reader = new Reader(name, surname, catalogueAccess);
        systemAccess.addReader(reader);
        return reader.getSignInData();
    }

    @Override
    public void imposeFine(Reader reader, double fine) {
        reader.addFine(fine);
    }

    @Override
    public boolean CheckOutBookFromReservedBooks(String login, Book book) throws NoReaderWithThatLoginException {
        Reader reader = systemAccess.getReader(login);
        if (reader.getReservedBooks().contains(book)) {
            book.status = Status.Available;
            checkOutBook(login, book);
            reader.getReservedBooks().remove(book);
            return true;
        } else {
            return false;
        }
    }
}
