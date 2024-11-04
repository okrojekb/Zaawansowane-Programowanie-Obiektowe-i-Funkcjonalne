package pl.edu.pw.mini.zpoif.zespol9.Catalogue;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Book.Genre;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Catalogue {

    private static List<Book> catalogue = BooksParser.parseBooks();

    public static List<Book> searchByAuthor(String author) {
        List<Book> listByAuthor = new LinkedList<>();
        catalogue.forEach(book -> {
            if (book.author.equalsIgnoreCase(author)) {
                listByAuthor.add(book);
            } else {
                String[] namesSurnames = book.author.split(" ");
                for (String el : namesSurnames) {
                    if (el.equalsIgnoreCase(author)) {
                        listByAuthor.add(book);
                        break;
                    }
                }
            }
        });
        return listByAuthor;

    }

    public static List<Book> searchByGenre(Genre genre) {
        List<Book> listByGenre = new LinkedList<>();
        catalogue.forEach(book -> {
            if (book.genre == genre) {
                listByGenre.add(book);
            }
        });
        return listByGenre;
    }

    public static List<Book> searchByTitle(String title) {
        List<Book> listByTitle = new LinkedList<>();
        catalogue.forEach(book -> {
            if (book.title.equalsIgnoreCase(title)) {
                listByTitle.add(book);
            }
        });
        return listByTitle;
    }

    public static List<Book> searchById(int id) {
        List<Book> listById = new LinkedList<>();
        catalogue.forEach(book -> {
            if (id == book.id) {
                listById.add(book);
            }
        });
        return listById;
    }

    public static List<Book> getCatalogue() {
        return catalogue;
    }
}
