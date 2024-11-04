package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.*;
import pl.edu.pw.mini.zpoif.zespol9.Catalogue.Catalogue;
import pl.edu.pw.mini.zpoif.zespol9.Exceptions.AlreadySeenThePasswordException;
import pl.edu.pw.mini.zpoif.zespol9.Exceptions.NoReaderWithThatLoginException;
import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;
import pl.edu.pw.mini.zpoif.zespol9.People.SignInData;
import pl.edu.pw.mini.zpoif.zespol9.System.LibrarySystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibrarianWindow extends JFrame {

    private LibrarySystem librarySystem;
    private Librarian librarian;

    public LibrarianWindow(LibrarySystem librarySystem, Librarian librarian) {
        this.librarian = librarian;
        this.librarySystem = librarySystem;

        setTitle("Librarian Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 800));

        //dodajemy panel górny z obrazkiem:
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(1000, 100));
        try {
            BufferedImage image = ImageIO.read(new File("resources/WelcomeBack.png"));

            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);
            imagePanel.add(imageLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // dodajemy lewy panel z zakładkami:
        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanel.setBackground(new Color(206, 190, 170, 255));
        leftPanel.setPreferredSize(new Dimension(180, 0));

        Font fontTab = new Font("Serif", Font.BOLD, 18);
        JButton jButton1 = new JButton("Catalogue");
        jButton1.setFont(fontTab);
        jButton1.setForeground(new Color(239, 221, 191, 255));
        JButton jButton2 = new JButton("Add Book");
        jButton2.setFont(fontTab);
        jButton2.setForeground(new Color(239, 221, 191, 255));
        JButton jButton3 = new JButton("Add User");
        jButton3.setFont(fontTab);
        jButton3.setForeground(new Color(239, 221, 191, 255));
        JButton jButton4 = new JButton("Library Management");
        jButton4.setFont(fontTab);
        jButton4.setForeground(new Color(239, 221, 191, 255));

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setFont(fontTab);
        logOutButton.setForeground(new Color(239, 221, 191, 255));

        JPanel rightPanel = new JPanel();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementCatalogue(librarySystem);

            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementAddBook(rightPanel);

            }
        });

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementAddUser(rightPanel);

            }
        });

        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementLibraryManagement(rightPanel);

            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LokigGui lokigGui = new LokigGui(librarySystem);
                dispose();
            }
        });

        jButton2.setPreferredSize(new Dimension(160, 50));
        jButton1.setPreferredSize(new Dimension(160, 50));
        jButton3.setPreferredSize(new Dimension(160, 50));
        jButton4.setPreferredSize(new Dimension(160, 50));
        logOutButton.setPreferredSize(new Dimension(160, 50));
        jButton1.setBackground(new Color(107, 79, 51));
        jButton2.setBackground(new Color(107, 79, 51));
        jButton3.setBackground(new Color(107, 79, 51));
        jButton4.setBackground(new Color(107, 79, 51));
        logOutButton.setBackground(new Color(107, 79, 51));

        leftPanel.add(jButton1);
        leftPanel.add(jButton2);
        leftPanel.add(jButton3);
        leftPanel.add(jButton4);
        leftPanel.add(logOutButton);
        rightPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    private void implementCatalogue(LibrarySystem librarySystem) {
        JPanel rightPanel = (JPanel) getContentPane().getComponent(2);
        rightPanel.setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBackground(new Color(206, 190, 170, 255));
        JPanel upperPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();

        upperPanel.setSize(new Dimension(920, 200));
        upperPanel.setBackground(new Color(238, 232, 223, 255));
        upperPanel.setLayout(null);

        scrollPane.setSize(new Dimension(920, 600));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Scroll Panel - Layout
        JPanel borderlaoutpanel = new JPanel();
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));

        JPanel columnpanel = new JPanel();
        borderlaoutpanel.add(columnpanel, BorderLayout.NORTH);
        columnpanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnpanel.setBackground(Color.gray);

        //upper panel
        JLabel labelTextCatalogue = new JLabel();
        labelTextCatalogue.setSize(new Dimension(910, 50));
        labelTextCatalogue.setText("<HTML>Search</HTML>");
        Font font = new Font("Serif", Font.BOLD, 15);
        Font font1 = new Font("Serif", Font.PLAIN, 15);

        labelTextCatalogue.setFont(font);
        labelTextCatalogue.setBounds(5, 5, 910, 20);
        upperPanel.add(labelTextCatalogue);

        // search by title
        JLabel titleSearchLabel = new JLabel("Search by title: ");
        JTextField titleSearchField = new JTextField("");
        JButton titleSearchButton = new JButton("Search");

        titleSearchLabel.setBounds(5, 50, 120, 25);
        titleSearchField.setBounds(135, 50, 230, 25);
        titleSearchButton.setBounds(375, 50, 80, 25);

        titleSearchLabel.setFont(font);
        titleSearchField.setFont(font1);
        titleSearchButton.setFont(font);

        upperPanel.add(titleSearchLabel);
        upperPanel.add(titleSearchField);
        upperPanel.add(titleSearchButton);


        titleSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleText = titleSearchField.getText();
                titleSearchField.setText("");
                java.util.List<Book> booksList = librarySystem.getCatalogue().searchByTitle(titleText);

                columnpanel.removeAll();
                columnpanel.revalidate();
                if (booksList.isEmpty()) {
                    JOptionPane.showMessageDialog(columnpanel, "We're sorry, but there is no book with the title '" + titleText
                            + "' in our collection", "No such title", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    printCatalogue(columnpanel, booksList);
                }

            }
        });
        // end search by title


        // search by author
        JLabel authorSearchLabel = new JLabel("Search by author: ");
        JTextField authorSearchField = new JTextField("");
        JButton authorSearchButton = new JButton("Search");

        authorSearchLabel.setBounds(5, 80, 120, 25);
        authorSearchField.setBounds(135, 80, 230, 25);
        authorSearchButton.setBounds(375, 80, 80, 25);

        authorSearchLabel.setFont(font);
        authorSearchField.setFont(font1);
        authorSearchButton.setFont(font);

        upperPanel.add(authorSearchLabel);
        upperPanel.add(authorSearchField);
        upperPanel.add(authorSearchButton);

        authorSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String authorText = authorSearchField.getText();
                authorSearchField.setText("");
                List<Book> booksList = librarySystem.getCatalogue().searchByAuthor(authorText);
                booksList.sort(Comparator.comparing(book -> book.title.toLowerCase()));

                columnpanel.removeAll();
                columnpanel.revalidate();
                if (booksList.isEmpty()) {
                    JOptionPane.showMessageDialog(columnpanel, "We're sorry, but there are no books by '" + authorText
                            + "' in our collection", "No such author", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    printCatalogue(columnpanel, booksList);
                }
            }
        });
        // end search by author

        // search by id
        JLabel idSearchLabel = new JLabel("Search by id: ");
        JTextField idSearchField = new JTextField("");
        JButton idSearchButton = new JButton("Search");

        idSearchLabel.setBounds(5, 110, 120, 25);
        idSearchField.setBounds(135, 110, 230, 25);
        idSearchButton.setBounds(375, 110, 80, 25);

        idSearchLabel.setFont(font);
        idSearchField.setFont(font1);
        idSearchButton.setFont(font);

        upperPanel.add(idSearchLabel);
        upperPanel.add(idSearchField);
        upperPanel.add(idSearchButton);

        idSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idSearchField.getText());
                    idSearchField.setText("");
                    List<Book> booksList = librarySystem.getCatalogue().searchById(id);
                    booksList.sort(Comparator.comparing(book -> book.title.toLowerCase()));

                    columnpanel.removeAll();
                    columnpanel.revalidate();
                    if (booksList.isEmpty()) {
                        JOptionPane.showMessageDialog(columnpanel, "We're sorry, but there is no book with " + id
                                + " id number in our collection", "No such id", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        printCatalogue(columnpanel, booksList);
                    }
                } catch (NumberFormatException ea) {
                    JOptionPane.showMessageDialog(columnpanel, "Id must be a number!", "id must be number",
                            JOptionPane.WARNING_MESSAGE);
                    columnpanel.removeAll();
                    columnpanel.revalidate();
                }


            }
        });
        // end search by id

        // show damaged books
        JLabel demagedBooks = new JLabel("Display list of damaged books");
        JButton demagedBooksButton = new JButton("Display");

        demagedBooks.setBounds(5, 140, 250, 25);
        demagedBooksButton.setBounds(375, 140, 80, 25);

        demagedBooks.setFont(font);
        demagedBooksButton.setFont(font);

        upperPanel.add(demagedBooks);
        upperPanel.add(demagedBooksButton);

        demagedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Book> booksList = librarySystem.getCatalogue().getCatalogue();
                booksList.sort(Comparator.comparing(book -> book.title.toLowerCase()));

                List<Book> listOfDamagedBooks = booksList.stream()
                        .filter(book -> book.bookCondition.equals(BookCondition.Damaged))
                        .sorted(Comparator.comparing(book -> book.title.toLowerCase()))
                        .collect(Collectors.toList());
                columnpanel.removeAll();
                if (listOfDamagedBooks.isEmpty()) {
                    JOptionPane.showMessageDialog(columnpanel, "There are no demaged books", "No such id",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    printCatalogue(columnpanel, listOfDamagedBooks);
                }

            }
        });
        // end show damaged books


        splitPane.setTopComponent(upperPanel);
        splitPane.setBottomComponent(scrollPane);

        splitPane.getTopComponent().setMinimumSize(new Dimension(0, 200));
        splitPane.getTopComponent().setMaximumSize(new Dimension(920, 200));

        splitPane.setEnabled(false);
        splitPane.setResizeWeight(0.0);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerSize(5);

        rightPanel.add(splitPane, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void printCatalogue(JPanel columnpanel, List<Book> booksList) {
        Font font = new Font("Serif", Font.BOLD, 15);

        int i = 0;
        for (Book book : booksList) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(1100, 100));
            columnpanel.add(rowPanel);
            rowPanel.setLayout(null);

            JLabel bookLabel = new JLabel(book.toStringCatalogueForLibrarian());
            bookLabel.setFont(font);
            bookLabel.setBounds(5, 5, 1100, 50);
            rowPanel.add(bookLabel);

            addCatalogueButton(rowPanel, book);

            i++;
            if (i % 2 == 0)
                rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
        }
        columnpanel.revalidate();
    }

    private void addCatalogueButton(JPanel rowPanel, Book book) {
        Font font = new Font("Serif", Font.BOLD, 15);

        // description button
        JButton readDesriptionButton = new JButton("Read Description");
        readDesriptionButton.setBounds(5, 60, 150, 30);
        readDesriptionButton.setFont(font);
        readDesriptionButton.setBackground(new Color(161, 148, 137));
        readDesriptionButton.addActionListener(new ReadDescriptionListener(book));
        rowPanel.add(readDesriptionButton);
        //end descriptionbutton

        //delete button
        JButton addDeleteButton = new JButton("Delete");
        addDeleteButton.setBounds(170, 60, 150, 30);
        addDeleteButton.setFont(font);
        addDeleteButton.setBackground(new Color(128, 125, 123));
        addDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(rowPanel, "Are you sure you want to delete this book?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_NO_OPTION) {
                    librarian.deleteBook(book);

                    rowPanel.removeAll();
                    rowPanel.setLayout(null);

                    JLabel label = new JLabel("<html>This book has been successfully deleted from catalogue</html>");
                    label.setFont(font);
                    label.setBounds(15, 25, 500, 40);
                    label.setVisible(true);
                    rowPanel.add(label);

                    rowPanel.revalidate();
                    rowPanel.repaint();
                }
            }
        });
        rowPanel.add(addDeleteButton);
        //end delete read

    }


    private void implementAddBook(JPanel jPanel) {
        jPanel.setLayout(new FlowLayout());
        jPanel.setBackground(new Color(238, 232, 223, 255));

        JPanel jPanelMain = new JPanel();
        JPanel jPanelTitle = new JPanel();
        JPanel jPanelAuthor = new JPanel();
        JPanel jPanelDescription = new JPanel();
        JPanel jPanelBookRating = new JPanel();
        JPanel jPanelGenre = new JPanel();
        JPanel jPanelBookFormat = new JPanel();
        JPanel jPanelAddBook = new JPanel();

        jPanelMain.setBackground(new Color(238, 232, 223, 255));
        jPanelTitle.setBackground(new Color(238, 232, 223, 255));
        jPanelAuthor.setBackground(new Color(238, 232, 223, 255));
        jPanelDescription.setBackground(new Color(238, 232, 223, 255));
        jPanelBookRating.setBackground(new Color(238, 232, 223, 255));
        jPanelGenre.setBackground(new Color(238, 232, 223, 255));
        jPanelBookFormat.setBackground(new Color(238, 232, 223, 255));
        jPanelAddBook.setBackground(new Color(238, 232, 223, 255));

        jPanelMain.setPreferredSize(new Dimension(jPanel.getWidth(), 100));
        jPanelTitle.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelAuthor.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelDescription.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelBookRating.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelGenre.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelBookFormat.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelAddBook.setPreferredSize(new Dimension(jPanel.getWidth(), 80));

        Font font = new Font("Serif", Font.BOLD, 18);
        Font font1 = new Font("Serif", Font.BOLD, 22);
        Font font2 = new Font("Serif", Font.PLAIN, 14);

        JLabel main = new JLabel("Add Book:");
        main.setFont(font1);
        jPanelMain.add(main);

        jPanelTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst1 = new JLabel("Title:");
        tekst1.setFont(font);
        JTextField field1 = new JTextField();
        field1.setFont(font2);
        field1.setBackground(new Color(239, 221, 191, 255));

        tekst1.setPreferredSize(new Dimension(150, 30));
        field1.setPreferredSize(new Dimension(150, 30));
        jPanelTitle.add(tekst1);
        jPanelTitle.add(field1);

        jPanelAuthor.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst2 = new JLabel("Author:");
        tekst2.setFont(font);
        JTextField field2 = new JTextField();
        field2.setFont(font2);
        field2.setBackground(new Color(239, 221, 191, 255));

        tekst2.setPreferredSize(new Dimension(150, 30));
        field2.setPreferredSize(new Dimension(150, 30));
        jPanelAuthor.add(tekst2);
        jPanelAuthor.add(field2);

        jPanelDescription.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst3 = new JLabel("Description:");
        tekst3.setFont(font);
        JTextField field3 = new JTextField();
        field3.setFont(font2);
        field3.setBackground(new Color(239, 221, 191, 255));

        tekst3.setPreferredSize(new Dimension(150, 30));
        field3.setPreferredSize(new Dimension(350, 30));
        jPanelDescription.add(tekst3);
        jPanelDescription.add(field3);

        jPanelBookRating.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst4 = new JLabel("Book Rating:");
        tekst4.setFont(font);
        JTextField field4 = new JTextField();
        field4.setFont(font2);
        field4.setBackground(new Color(239, 221, 191, 255));

        tekst4.setPreferredSize(new Dimension(150, 30));
        field4.setPreferredSize(new Dimension(150, 30));
        jPanelBookRating.add(tekst4);
        jPanelBookRating.add(field4);

        jPanelGenre.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst5 = new JLabel("Genre");
        tekst5.setFont(font);

        Genre[] genres = Genre.values();
        JComboBox<Genre> comboBox = new JComboBox<>(genres);

        tekst5.setPreferredSize(new Dimension(150, 30));
        comboBox.setPreferredSize(new Dimension(150, 30));
        comboBox.setBackground(new Color(239, 221, 191, 255));
        comboBox.setFont(font2);
        jPanelGenre.add(tekst5);
        jPanelGenre.add(comboBox);


        jPanelBookFormat.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst6 = new JLabel("Book Format:");
        tekst6.setFont(font);
        BookFormat[] bookFormats = BookFormat.values();
        JComboBox<BookFormat> comboBoxBookFormat = new JComboBox<>(bookFormats);

        tekst6.setPreferredSize(new Dimension(150, 30));
        comboBoxBookFormat.setPreferredSize(new Dimension(150, 30));
        comboBoxBookFormat.setBackground(new Color(239, 221, 191, 255));
        comboBoxBookFormat.setFont(font2);
        jPanelBookFormat.add(tekst6);
        jPanelBookFormat.add(comboBoxBookFormat);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = field1.getText();
                String author = field2.getText();
                String description = field3.getText();
                String rating = field4.getText();
                //double rating = Double.parseDouble(field4.getText());
                Genre genre = (Genre) comboBox.getSelectedItem();
                BookFormat bookFormat = (BookFormat) comboBoxBookFormat.getSelectedItem();

                if (title.equals("") | author.equals("") | description.equals("") | String.valueOf(rating).equals("")) {
                    JOptionPane.showMessageDialog(LibrarianWindow.this, "Please fill in all the fields!");
                } else {
                    double ratingDouble;
                    try {
                        ratingDouble = Double.parseDouble(rating);
                        if (ratingDouble >= 0 & ratingDouble <= 5) {
                            Book book = new Book(title, author, description, ratingDouble, genre, bookFormat);
                            librarian.addBook(book);
                            JOptionPane.showMessageDialog(LibrarianWindow.this, "You have successfully added a book!");
                            field1.setText("");
                            field2.setText("");
                            field3.setText("");
                            field4.setText("");
                        } else {
                            JOptionPane.showMessageDialog(LibrarianWindow.this, "Book rating should be less or equal 5 and bigger or equal 0!");
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(LibrarianWindow.this, "Book Rating must be a number!");
                    }
                }
            }
        });

        jPanelAddBook.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanelAddBook.add(addButton);
        addButton.setPreferredSize(new Dimension(150, 50));

        jPanel.add(jPanelMain);
        jPanel.add(jPanelTitle);
        jPanel.add(jPanelAuthor);
        jPanel.add(jPanelDescription);
        jPanel.add(jPanelBookRating);
        jPanel.add(jPanelGenre);
        jPanel.add(jPanelBookFormat);
        jPanel.add(jPanelAddBook);

        jPanel.revalidate();
        jPanel.repaint();
    }

    private void implementAddUser(JPanel jPanel) {

        jPanel.setLayout(new FlowLayout());
        jPanel.setBackground(new Color(238, 232, 223, 255));

        JPanel jPanelMain = new JPanel();
        JPanel jPanelName = new JPanel();
        JPanel jPanelSurname = new JPanel();
        jPanelMain.setBackground(new Color(238, 232, 223, 255));
        jPanelName.setBackground(new Color(238, 232, 223, 255));
        jPanelSurname.setBackground(new Color(238, 232, 223, 255));
        jPanelMain.setPreferredSize(new Dimension(jPanel.getWidth(), 100));
        jPanelName.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelSurname.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        Font font = new Font("Serif", Font.BOLD, 18);
        Font font1 = new Font("Serif", Font.BOLD, 22);
        Font font2 = new Font("Serif", Font.PLAIN, 14);

        JLabel main = new JLabel("Add User:");
        main.setFont(font1);
        jPanelMain.add(main);

        jPanelName.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst1 = new JLabel("Name:");
        tekst1.setFont(font1);
        JTextField field1 = new JTextField();
        field1.setBackground(new Color(239, 221, 191, 255));
        field1.setFont(font2);

        tekst1.setPreferredSize(new Dimension(150, 30));
        field1.setPreferredSize(new Dimension(150, 30));
        jPanelName.add(tekst1);
        jPanelName.add(field1);

        jPanelSurname.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst2 = new JLabel("Surname:");
        tekst2.setFont(font1);
        JTextField field2 = new JTextField();
        field2.setBackground(new Color(239, 221, 191, 255));
        field2.setFont(font2);

        tekst2.setPreferredSize(new Dimension(150, 30));
        field2.setPreferredSize(new Dimension(150, 30));
        jPanelSurname.add(tekst2);
        jPanelSurname.add(field2);

        JPanel addUser = new JPanel();
        JButton addButton = new JButton("Add User");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = field1.getText();
                String surname = field2.getText();

                if (name.equals("") | surname.equals("")) {
                    JOptionPane.showMessageDialog(LibrarianWindow.this, "Please fill in all the fields");
                } else {
                    SignInData signInData = librarian.addUser(name, surname);
                    String login = signInData.getLogin();
                    String password;
                    try {
                        password = signInData.getNewPassword();
                    } catch (AlreadySeenThePasswordException ex) {
                        throw new RuntimeException(ex);
                    }

                    String message = "New User has been added!\n"
                            + "Login: " + login + "\n"
                            + "Password:  " + password;

                    JOptionPane.showMessageDialog(LibrarianWindow.this, message);
                    field1.setText("");
                    field2.setText("");
                }
            }
        });

        addUser.setLayout(new FlowLayout(FlowLayout.CENTER));
        addUser.add(addButton);
        addButton.setPreferredSize(new Dimension(150, 30));


        jPanel.add(jPanelMain);
        jPanel.add(jPanelName);
        jPanel.add(jPanelSurname);
        jPanel.add(addUser);

        jPanel.revalidate();
        jPanel.repaint();

    }

    private void implementLibraryManagement(JPanel jPanel) {
        jPanel.setLayout(new GridLayout(3, 1));

        Font font = new Font("Serif", Font.BOLD, 18);
        Font font1 = new Font("Serif", Font.PLAIN, 14);

        JPanel searchLoginPanel = new JPanel();
        searchLoginPanel.setBackground(new Color(238, 232, 223, 255));
        jPanel.add(searchLoginPanel);
        JPanel reservedBooksPanel = new JPanel();
        reservedBooksPanel.setBackground(new Color(238, 232, 223, 255));
        jPanel.add(reservedBooksPanel);
        JPanel checkedOutBooksPanel = new JPanel();
        checkedOutBooksPanel.setBackground(new Color(238, 232, 223, 255));
        jPanel.add(checkedOutBooksPanel);

        // search login panel:

        JLabel jLabelSearch = new JLabel("Search User (by login): ");
        JTextField jTextSearch = new JTextField();
        JButton jButtonSearch = new JButton("Search");
        searchLoginPanel.add(jLabelSearch);
        searchLoginPanel.add(jTextSearch);
        searchLoginPanel.add(jButtonSearch);
        jTextSearch.setPreferredSize(new Dimension(200, 30));
        jButtonSearch.setPreferredSize(new Dimension(200, 30));
        jLabelSearch.setFont(font);
        jTextSearch.setFont(font1);
        jButtonSearch.setFont(font);

        jButtonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservedBooksPanel.removeAll();
                checkedOutBooksPanel.removeAll();

                String readerLogin = jTextSearch.getText();
                Reader myReader = null;
                try {
                    myReader = librarySystem.getReader(readerLogin);
                    PanelWithScrollPane panelWithScrollPane = new PanelWithScrollPane("Librarian", reservedBooksPanel, checkedOutBooksPanel, myReader, librarian, "Reserved Books:", "Checked Out Books", "Check Out", "Return");
                    panelWithScrollPane.createPanel();

                } catch (NoReaderWithThatLoginException ex) {
                    JOptionPane.showMessageDialog(searchLoginPanel, "There is no such user in our database.");
                }
                reservedBooksPanel.repaint();
                reservedBooksPanel.revalidate();
                checkedOutBooksPanel.repaint();
                checkedOutBooksPanel.revalidate();

            }
        });

        jPanel.revalidate();
        jPanel.repaint();

    }

}
