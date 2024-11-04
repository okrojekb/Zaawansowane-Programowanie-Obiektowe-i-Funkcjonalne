package pl.edu.pw.mini.zpoif.zespol9.Book;

import java.util.Random;

public class Book {

    private Random random = new Random();

    public final int id;
    private static int count = 1;
    public String title;
    public String author;
    public String description;
    public BookFormat bookFormat;
    public double bookRating;
    public int bookRatingCount;
    public int bookReviewCount;
    public int numberOfFollowers;
    public Genre genre;
    public BookCondition bookCondition;
    public Status status;
    public boolean postponed;

    public Book(String title, String author, String description, double bookRating, int bookRatingCount,
                int bookReviewCount, int numberOfFollowers, Genre genre) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.bookRating = bookRating;
        this.bookRatingCount = bookRatingCount;
        this.bookReviewCount = bookReviewCount;
        this.numberOfFollowers = numberOfFollowers;
        this.genre = genre;
        id = count;
        count++;
        setBookFormat();
        setBookCondition();
        setStatus();

    }

    public Book(String title, String author, String description, double bookRating, Genre genre, BookFormat bookFormat) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.bookRating = bookRating;
        this.genre = genre;
        this.bookFormat = bookFormat;
        this.bookCondition = BookCondition.AsNew;
        this.status = Status.Available;
        id = count;
        count++;
    }


    private void setBookFormat() {
        double pstwo = random.nextDouble();
        if (pstwo < 0.4) {
            bookFormat = BookFormat.Softback;
        } else if (pstwo < 0.7) {
            bookFormat = BookFormat.Hardcover;
        } else if (pstwo < 0.9) {
            bookFormat = BookFormat.Ebook;
        } else {
            bookFormat = BookFormat.Clothbound;
        }
    }

    private void setBookCondition() {
        double pstwo = random.nextDouble();
        if (bookFormat == BookFormat.Ebook) {
            bookCondition = BookCondition.NotApplicable;
        } else if (pstwo < 0.1) {
            bookCondition = BookCondition.AsNew;
        } else if (pstwo < 0.7) {
            bookCondition = BookCondition.Good;
        } else if (pstwo < 0.95) {
            bookCondition = BookCondition.Poor;
        } else {
            bookCondition = BookCondition.Damaged;
        }
    }

    private void setStatus() {
        double pstwo = random.nextDouble();
        if (bookFormat == BookFormat.Ebook) {
            status = Status.Available;
        } else if (pstwo < 0.2) {
            status = Status.CheckedOut;
        } else if (pstwo < 0.4) {
            status = Status.Reserved;
        } else {
            status = Status.Available;
        }
    }

    public String toStringCatalogue() {
        return "<HTML>Title: '" + title + "',   author: " + author +
                ",   genre: " + genre + ",<br>format: " + bookFormat +
                ",   rating: " + bookRating +
                ",   status: " + status + "</HTML>";
    }

    public String toStringCatalogueForLibrarian() {
        return "<HTML>Title: '" + title + "', author: " + author +
                ", genre: " + genre + ", format: " + bookFormat +
                ",<br>rating: " + bookRating + ", status: " + status +
                ", rating count: " + bookRatingCount +
                ", id: " + id + ", condition: " + bookCondition + "</HTML>";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }
}
