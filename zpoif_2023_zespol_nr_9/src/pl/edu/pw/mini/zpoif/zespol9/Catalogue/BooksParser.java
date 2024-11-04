package pl.edu.pw.mini.zpoif.zespol9.Catalogue;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Book.Genre;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BooksParser {

    public static List<Book> parseBooks() {
        List<Book> catalogue = new ArrayList<>();
        Path pathToFile = Paths.get("resources/BooksData.csv");

        try {
            Scanner scanner = new Scanner(pathToFile);
            scanner.useDelimiter(";");

            while (scanner.hasNextLine()) {
                catalogue.add(parseBook(scanner));
                scanner.nextLine();

            }
            scanner.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return catalogue;
    }


    private static Book parseBook(Scanner scanner) {
        String title = scanner.next();
        //double bookRating = scanner.nextDouble(); // zmieniłam bo wyrzucało wyjątek przy parsowaniu typu double!!!
        String book = scanner.next();
        double bookRating = Double.parseDouble(book.replaceAll(",", "."));
        String author = scanner.next();
        int bookRatingCount = scanner.nextInt();
        int bookReviewCount = scanner.nextInt();
        int numberOfFollowers = scanner.nextInt();
        String description = scanner.next();
        Genre genre = Genre.valueOf(scanner.next());

        return new Book(title, author, description, bookRating, bookRatingCount, bookReviewCount, numberOfFollowers, genre);
    }


}
