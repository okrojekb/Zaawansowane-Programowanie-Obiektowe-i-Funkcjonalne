package pl.edu.pw.mini.zpoif.zespol9.System;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Book.BookCondition;
import pl.edu.pw.mini.zpoif.zespol9.Exceptions.NoReaderWithThatLoginException;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;
import pl.edu.pw.mini.zpoif.zespol9.People.SignInData;

public interface CheckOutDesk {

    void addBook(Book book);

    void deleteBook(Book book);

    boolean checkOutBook(String login, Book book) throws NoReaderWithThatLoginException;

    boolean acceptBookReturn(String login, Book book, BookCondition bookCondition) throws NoReaderWithThatLoginException;

    SignInData addUser(String name, String surname);

    void imposeFine(Reader reader, double fine);

    boolean CheckOutBookFromReservedBooks(String login, Book book) throws NoReaderWithThatLoginException;
}
