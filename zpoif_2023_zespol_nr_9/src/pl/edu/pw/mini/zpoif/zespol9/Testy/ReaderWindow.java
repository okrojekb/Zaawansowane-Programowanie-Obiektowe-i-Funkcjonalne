package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.*;

import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

import pl.edu.pw.mini.zpoif.zespol9.System.LibrarySystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ReaderWindow extends JFrame {

    private Reader myReader;

    public ReaderWindow(LibrarySystem librarySystem, Reader reader) {
        this.myReader = reader;

        setTitle("Reader Window");
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
        JButton jButton0 = new JButton("Catalogue");
        jButton0.setFont(fontTab);
        jButton0.setForeground(new Color(239, 221, 191, 255));
        JButton jButton1 = new JButton("Search");
        jButton1.setFont(fontTab);
        jButton1.setForeground(new Color(239, 221, 191, 255));
        JButton jButton2 = new JButton("My Account");
        jButton2.setFont(fontTab);
        jButton2.setForeground(new Color(239, 221, 191, 255));

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setFont(fontTab);
        logOutButton.setForeground(new Color(239, 221, 191, 255));

        JPanel rightPanel = new JPanel();

        jButton0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementCatalogue(librarySystem);
            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementSearch(librarySystem);
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementAccount();

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
        jButton0.setPreferredSize(new Dimension(160, 50));
        logOutButton.setPreferredSize(new Dimension(160, 50));
        jButton0.setBackground(new Color(107, 79, 51));
        jButton1.setBackground(new Color(107, 79, 51));
        jButton2.setBackground(new Color(107, 79, 51));
        logOutButton.setBackground(new Color(107, 79, 51));

        leftPanel.add(jButton0);
        leftPanel.add(jButton1);
        leftPanel.add(jButton2);
        leftPanel.add(logOutButton);
        rightPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void implementCatalogue(LibrarySystem librarySystem) {
        List<Book> booksList = librarySystem.getCatalogue().getCatalogue();

        JPanel rightPanel = (JPanel) getContentPane().getComponent(2);

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

        // upperPanel with sorting option
        JLabel labelTextCatalogue = new JLabel();
        labelTextCatalogue.setSize(new Dimension(910, 50));
        labelTextCatalogue.setText("<HTML>Immerse yourself in our comprehensive catalog with versatile sorting options.<br>" +
                "Choose to sort by alphabetical order or rating marks to uncover the ideal book for you!</HTML>");
        Font font = new Font("Serif", Font.BOLD, 15);
        labelTextCatalogue.setFont(font);
        labelTextCatalogue.setBounds(5, 5, 910, 50);
        upperPanel.add(labelTextCatalogue);

        // sorting buttons
        JLabel labelTextSorting = new JLabel("<html>Choose your catalogue sorting preference:</html>");
        labelTextSorting.setFont(font);
        labelTextSorting.setBounds(5, 70, 350, 25);
        upperPanel.add(labelTextSorting);

        JRadioButton authorSortButton = new JRadioButton("Sort by Author");
        JRadioButton titleSortButton = new JRadioButton("Sort by Title");
        JRadioButton ratingSortButton = new JRadioButton("Sort by Rating");
        JRadioButton top10booksButton = new JRadioButton("Show TOP 10 from: ");
        JRadioButton ebooksButton = new JRadioButton("Show e-books");

        authorSortButton.setBounds(20, 100, 200, 25);
        titleSortButton.setBounds(20, 130, 200, 25);
        ratingSortButton.setBounds(20, 160, 200, 25);
        top10booksButton.setBounds(300, 100, 200, 25);
        ebooksButton.setBounds(300, 130, 200, 25);

        authorSortButton.setFont(font);
        titleSortButton.setFont(font);
        ratingSortButton.setFont(font);
        top10booksButton.setFont(font);
        ebooksButton.setFont(font);

        authorSortButton.setBackground(new Color(190, 172, 150, 255));
        titleSortButton.setBackground(new Color(190, 172, 150, 255));
        ratingSortButton.setBackground(new Color(190, 172, 150, 255));
        top10booksButton.setBackground(new Color(190, 172, 150, 255));
        ebooksButton.setBackground(new Color(190, 172, 150, 255));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(authorSortButton);
        buttonGroup.add(titleSortButton);
        buttonGroup.add(ratingSortButton);
        buttonGroup.add(top10booksButton);
        buttonGroup.add(ebooksButton);

        authorSortButton.setSelected(true);

        authorSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnpanel.removeAll();
                columnpanel.revalidate();
            }
        });

        titleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnpanel.removeAll();
                columnpanel.revalidate();
            }
        });

        ratingSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnpanel.removeAll();
                columnpanel.revalidate();
            }
        });

        top10booksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnpanel.removeAll();
                columnpanel.revalidate();
            }
        });

        ebooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnpanel.removeAll();
                columnpanel.revalidate();
            }
        });

        Genre[] options = new Genre[Genre.values().length + 1];
        options[0] = null;
        System.arraycopy(Genre.values(), 0, options, 1, Genre.values().length);
        JComboBox<Genre> genreJComboBox = new JComboBox<>(options);
        genreJComboBox.setBounds(520, 100, 230, 25);

        upperPanel.add(authorSortButton);
        upperPanel.add(titleSortButton);
        upperPanel.add(ratingSortButton);
        upperPanel.add(top10booksButton);
        upperPanel.add(genreJComboBox);
        upperPanel.add(ebooksButton);


        //display button
        JButton displayButton = new JButton("Display");
        displayButton.setBounds(700, 170, 100, 25);
        displayButton.setFont(font);
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnpanel.removeAll();

                if (authorSortButton.isSelected()) {
                    booksList.sort(Comparator.comparing(book -> book.author.toLowerCase()));
                    printCatalogue(columnpanel, booksList);

                } else if (titleSortButton.isSelected()) {
                    booksList.sort(Comparator.comparing(book -> book.title.toLowerCase()));
                    printCatalogue(columnpanel, booksList);

                } else if (ratingSortButton.isSelected()) {
                    booksList.sort((book1, book2) -> Double.compare(book2.bookRating, book1.bookRating));
                    printCatalogue(columnpanel, booksList);

                } else if (top10booksButton.isSelected()) {
                    Genre selectedGenre = (Genre) genreJComboBox.getSelectedItem();
                    genreJComboBox.setSelectedIndex(0);

                    List<Book> TOP10booksList = booksList.stream()
                            .filter(book -> book.genre.equals(selectedGenre))
                            .sorted((book1, book2) -> Double.compare(book2.bookRating, book1.bookRating))
                            .limit(10).collect(Collectors.toList());
                    printCatalogue(columnpanel, TOP10booksList);

                } else if (ebooksButton.isSelected()) {
                    List<Book> ebooksList = booksList.stream()
                            .filter(book -> book.bookFormat.equals(BookFormat.Ebook))
                            .sorted(Comparator.comparing(book -> book.title.toLowerCase()))
                            .collect(Collectors.toList());
                    printCatalogue(columnpanel, ebooksList);
                }
            }
        });
        upperPanel.add(displayButton);


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

    private void implementSearch(LibrarySystem librarySystem) {
        JPanel rightPanel = (JPanel) getContentPane().getComponent(2);

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


        // upperPanel with search option
        JLabel labelTextCatalogue = new JLabel();
        labelTextCatalogue.setSize(new Dimension(910, 50));
        labelTextCatalogue.setText("<HTML>Find your next literary adventure! Search for books by author, title, or genre.<br>" +
                " Type your query and let the exploration begin!</HTML>");
        Font font = new Font("Serif", Font.BOLD, 15);
        labelTextCatalogue.setFont(font);
        labelTextCatalogue.setBounds(5, 4, 910, 50);
        upperPanel.add(labelTextCatalogue);


        // search by title
        JLabel titleSearchLabel = new JLabel("Search by title: ");
        JTextField titleSearchField = new JTextField("");
        JButton titleSearchButton = new JButton("Search");

        titleSearchLabel.setBounds(5, 70, 120, 25);
        titleSearchField.setBounds(135, 70, 230, 25);
        titleSearchButton.setBounds(375, 70, 80, 25);

        titleSearchLabel.setFont(font);
        titleSearchButton.setFont(font);

        upperPanel.add(titleSearchLabel);
        upperPanel.add(titleSearchField);
        upperPanel.add(titleSearchButton);


        titleSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleText = titleSearchField.getText();
                titleSearchField.setText("");
                List<Book> booksList = librarySystem.getCatalogue().searchByTitle(titleText);

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

        authorSearchLabel.setBounds(5, 100, 120, 25);
        authorSearchField.setBounds(135, 100, 230, 25);
        authorSearchButton.setBounds(375, 100, 80, 25);

        authorSearchLabel.setFont(font);
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


        // search by genre
        Genre[] options = new Genre[Genre.values().length + 1];
        options[0] = null;
        System.arraycopy(Genre.values(), 0, options, 1, Genre.values().length);

        JLabel genreSearchLabel = new JLabel("Search by genre: ");
        JComboBox<Genre> genreJComboBox = new JComboBox<>(options);
        JButton genreSearchButton = new JButton("Search");

        genreSearchLabel.setBounds(5, 130, 120, 25);
        genreJComboBox.setBounds(135, 130, 230, 25);
        genreSearchButton.setBounds(375, 130, 80, 25);

        genreSearchLabel.setFont(font);
        genreSearchButton.setFont(font);

        upperPanel.add(genreSearchLabel);
        upperPanel.add(genreJComboBox);
        upperPanel.add(genreSearchButton);

        genreSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnpanel.removeAll();

                Genre selectedGenre = (Genre) genreJComboBox.getSelectedItem();
                genreJComboBox.setSelectedIndex(0);
                List<Book> booksList = librarySystem.getCatalogue().searchByGenre(selectedGenre);
                booksList.sort(Comparator.comparing(book -> book.title.toLowerCase()));

                printCatalogue(columnpanel, booksList);
            }
        });
        // end search by genre


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

            JLabel bookLabel = new JLabel(book.toStringCatalogue());
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

        //add to read button
        JButton addToReadButton = new JButton("Add to read");
        addToReadButton.setBounds(170, 60, 150, 30);
        addToReadButton.setFont(font);
        addToReadButton.setBackground(new Color(204, 131, 141));
        addToReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean wasItAdd = myReader.addToReadBook(book);
                if (wasItAdd) {
                    rowPanel.removeAll();
                    rowPanel.setLayout(null);

                    JLabel label = new JLabel("<html>This book has been successfully added to your list.<br> " +
                            "We hope that you'll come back to read this book soon</html>");
                    label.setFont(font);
                    label.setBounds(15, 25, 500, 40);
                    label.setVisible(true);
                    rowPanel.add(label);

                    rowPanel.revalidate();
                    rowPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(rowPanel, "You already have this book on your list!", "Unable To Add", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        rowPanel.add(addToReadButton);
        //end add to read


        if (book.bookFormat == BookFormat.Ebook) {
            JButton downloadButton = new JButton("Download");
            downloadButton.setBounds(335, 60, 150, 30);
            downloadButton.setFont(font);
            downloadButton.setBackground(new Color(157, 154, 151));

            downloadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(rowPanel, "Thank you from downloading our book ;)", "Downloading", JOptionPane.PLAIN_MESSAGE);
                }
            });
            rowPanel.add(downloadButton);

        } else {
            JButton reserveButton = new JButton("Reserve");
            reserveButton.setBounds(335, 60, 150, 30);
            if (book.status == Status.Available) {
                reserveButton.setBackground(new Color(130, 164, 114));
                reserveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myReader.reserveBook(book);
                        rowPanel.removeAll();
                        rowPanel.setLayout(null);

                        JLabel label = new JLabel("This book has been successfully reserved for you. Happy reading!");
                        label.setFont(font);
                        label.setBounds(15, 25, 500, 40);
                        label.setVisible(true);
                        rowPanel.add(label);

                        rowPanel.revalidate();
                        rowPanel.repaint();

                    }
                });
            } else {
                reserveButton.setBackground(new Color(215, 99, 83));
                reserveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(rowPanel, "<html><div style='text-align: center;'>The book " +
                                        "is not available for reservation at the moment. It is either reserved or on loan.<br>Please " +
                                        "choose another book or check back later.<br>Thank you for your understanding.</html>",
                                "Currently unavailable for reservation", JOptionPane.PLAIN_MESSAGE);
                    }
                });
            }
            rowPanel.add(reserveButton);
        }
    }

    private void implementAccount() {
        JPanel rightPanel = (JPanel) getContentPane().getComponent(2);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setBackground(new Color(206, 190, 170, 255));
        upperPanel.setSize(new Dimension(620, 200));
        lowerPanel.setLayout(new GridLayout(3, 1));

        JPanel reservedBooksPanel = new JPanel();
        reservedBooksPanel.setBackground(new Color(238, 232, 223, 255));
        lowerPanel.add(reservedBooksPanel);
        JPanel checkedOutBooksPanel = new JPanel();
        checkedOutBooksPanel.setBackground(new Color(238, 232, 223, 255));
        lowerPanel.add(checkedOutBooksPanel);
        JPanel toReadBooksPanel = new JPanel();
        toReadBooksPanel.setBackground(new Color(238, 232, 223, 255));
        lowerPanel.add(toReadBooksPanel);

        Font font = new Font("MV Boli", Font.BOLD, 18);
        Font font1 = new Font("Serif", Font.BOLD, 16);

        // panel na gorze:
        upperPanel.setLayout(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        upperPanel.add(nameLabel);
        JLabel j1 = new JLabel(this.myReader.getName());
        j1.setFont(font1);
        upperPanel.add(j1);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setFont(font);
        upperPanel.add(surnameLabel);
        JLabel j2 = new JLabel(this.myReader.getSurname());
        j2.setFont(font1);
        upperPanel.add(j2);

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(font);
        upperPanel.add(loginLabel);
        JLabel j3 = new JLabel(this.myReader.getSignInData().getLogin());
        j3.setFont(font1);
        upperPanel.add(j3);

        JLabel fineLabel = new JLabel("Fine:");
        fineLabel.setFont(font);
        upperPanel.add(fineLabel);
        JLabel j4 = new JLabel(String.valueOf(this.myReader.getFine()));
        j4.setFont(font1);
        upperPanel.add(j4);
        reservedBooksPanel.setLayout(new FlowLayout());

        PanelWithScrollPane panelWithScrollPaneReserved = new PanelWithScrollPane("reader", reservedBooksPanel, myReader, "Reserved Books:", "Delete From Reserved");
        panelWithScrollPaneReserved.createSinglePanel();

        PanelWithScrollPane panelWithScrollPane = new PanelWithScrollPane("reader", toReadBooksPanel, checkedOutBooksPanel, myReader, null, "To Read Books:", "Checked Out Books:", "Delete", "Postpone Return Date");
        panelWithScrollPane.createPanel();

        splitPane.setTopComponent(upperPanel);
        splitPane.setBottomComponent(lowerPanel);

        splitPane.getTopComponent().setMinimumSize(new Dimension(0, 200));
        splitPane.getTopComponent().setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        splitPane.setResizeWeight(0.0);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerSize(0);

        rightPanel.add(splitPane, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();

    }


}
