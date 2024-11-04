package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Exceptions.NoReaderWithThatLoginException;
import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PanelWithScrollPane extends JPanel {

    private Reader myReader;
    private Librarian librarian;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private String title1;
    private String title2;
    private String panel;
    private String button1;
    private String button2;

    public PanelWithScrollPane(String panel, JPanel jPanel1, JPanel jPanel2, Reader myReader, Librarian librarian, String title1, String title2, String button1, String button2) {

        this.myReader = myReader;
        this.jPanel2 = jPanel2;
        this.jPanel1 = jPanel1;
        this.librarian = librarian;
        this.title1 = title1;
        this.title2 = title2;
        this.panel = panel;
        this.button1 = button1;
        this.button2 = button2;
    }

    public PanelWithScrollPane(String panel, JPanel jPanel, Reader myReader, String title1, String button1) {
        this.panel = panel;
        this.jPanel1 = jPanel;
        this.myReader = myReader;
        this.title1 = title1;
        this.button1 = button1;
    }


    public void createSinglePanel() {

        Font font1 = new Font("Serif", Font.BOLD, 16);
        DefaultListModel<PanelWithScrollPane.ClassWithComponentsToBePrinted> defaultListModel = new DefaultListModel<>();

        jPanel1.setBackground(new Color(238, 232, 223, 255));
        JLabel jReserved = new JLabel(title1);
        jReserved.setFont(font1);
        jPanel1.add(jReserved);
        jPanel1.setLayout(new FlowLayout());
        List<Book> listReader = this.myReader.getReservedBooks();

        List<PanelWithScrollPane.ClassWithComponentsToBePrinted> toBePrinted = new ArrayList<>();
        for (Book book : listReader) {
            toBePrinted.add(new PanelWithScrollPane.ClassWithComponentsToBePrinted(book));
        }

        for (int i = 0; i < listReader.size(); i++) {
            defaultListModel.addElement(toBePrinted.get(i));
        }

        JButton jButton = new JButton(button1);
        JList<PanelWithScrollPane.ClassWithComponentsToBePrinted> elementList = new JList<>(defaultListModel);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = elementList.getSelectedIndex();
                if (selectedIndex != -1) {
                    PanelWithScrollPane.ClassWithComponentsToBePrinted selectedBook = defaultListModel.getElementAt(selectedIndex);
                    Book book = selectedBook.book;

                    if (panel.equals("reader")) {

                        int choice = JOptionPane.showConfirmDialog(jPanel1, "Are you sure you want to delete this book?", "Confirmation", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_NO_OPTION) {
                            myReader.deleteReservedBook(book);
                            defaultListModel.remove(selectedIndex);
                            jPanel1.revalidate();
                            jPanel1.repaint();

                            JOptionPane.showMessageDialog(jPanel1, "<html>This book has been successfully deleted from reserved books!</html>");

                        }
                    }

                }
            }
        });

        jButton.setEnabled(false);
        jPanel1.add(jButton);
        elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        elementList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isSelectionEmpty = elementList.isSelectionEmpty();
                jButton.setEnabled(!isSelectionEmpty);
            }
        });

        elementList.setCellRenderer(new PanelWithScrollPane.PrintInFourRows());
        JScrollPane scrollPane = new JScrollPane(elementList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 100));
        scrollPane.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList.setBackground(new Color(238, 232, 223, 255));
        jPanel1.add(scrollPane);
    }


    public void createPanel() {
        DefaultListModel<PanelWithScrollPane.ClassWithComponentsToBePrinted> defaultListModel = new DefaultListModel<>();
        DefaultListModel<PanelWithScrollPane.ClassWithComponentsToBePrinted> defaultListModel1 = new DefaultListModel<>();

        jPanel1.setBackground(new Color(238, 232, 223, 255));
        jPanel2.setBackground(new Color(238, 232, 223, 255));

        JLabel jReserved = new JLabel(title1);
        jPanel1.add(jReserved);
        jPanel1.setLayout(new FlowLayout());

        List<Book> listReader;
        if (panel.equals("reader")) {
            listReader = myReader.getToReadBooks();
        } else {
            listReader = myReader.getReservedBooks();
        }

        List<PanelWithScrollPane.ClassWithComponentsToBePrinted> toBePrinted = new ArrayList<>();
        for (Book book : listReader) {
            toBePrinted.add(new PanelWithScrollPane.ClassWithComponentsToBePrinted(book));
        }

        for (int i = 0; i < listReader.size(); i++) {
            defaultListModel.addElement(toBePrinted.get(i));
        }

        JButton jButton = new JButton(button1);
        JList<PanelWithScrollPane.ClassWithComponentsToBePrinted> elementList = new JList<>(defaultListModel);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = elementList.getSelectedIndex();
                if (selectedIndex != -1) {
                    PanelWithScrollPane.ClassWithComponentsToBePrinted selectedBook = defaultListModel.getElementAt(selectedIndex);
                    Book book = selectedBook.book;

                    if (panel.equals("reader")) {
                        int choice = JOptionPane.showConfirmDialog(jPanel1, "Are you sure you want to delete this book?", "Confirmation", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_NO_OPTION) {
                            myReader.deleteToReadBook(book);
                            defaultListModel.remove(selectedIndex);
                            jPanel1.revalidate();
                            jPanel1.repaint();

                            JOptionPane.showMessageDialog(jPanel1, "<html>This book has been successfully deleted from to read books!</html>");
                        }
                    } else {

                        try {
                            librarian.CheckOutBookFromReservedBooks(myReader.getSignInData().getLogin(), book);
                            JOptionPane.showMessageDialog(PanelWithScrollPane.this, "The book has been checked out");
                            defaultListModel.remove(selectedIndex);
                            PanelWithScrollPane.ClassWithComponentsToBePrinted x = new PanelWithScrollPane.ClassWithComponentsToBePrinted(book, myReader.getCheckedOutBooks().get(book));
                            defaultListModel1.addElement(x);
                            jPanel2.revalidate();
                            jPanel2.repaint();
                            jPanel1.revalidate();
                            jPanel1.repaint();

                        } catch (NoReaderWithThatLoginException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

            }
        });
        jButton.setEnabled(false);
        jPanel1.add(jButton);
        elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        elementList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isSelectionEmpty = elementList.isSelectionEmpty();
                jButton.setEnabled(!isSelectionEmpty);
            }
        });

        elementList.setCellRenderer(new PanelWithScrollPane.PrintInFourRows());
        JScrollPane scrollPane = new JScrollPane(elementList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 100));
        scrollPane.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList.setBackground(new Color(238, 232, 223, 255));
        jPanel1.add(scrollPane);

        JLabel jCheckedOut = new JLabel(title2);
        jPanel2.add(jCheckedOut);
        jPanel2.setLayout(new FlowLayout());

        Map<Book, LocalDate> mapReader = myReader.getCheckedOutBooks();
        List<PanelWithScrollPane.ClassWithComponentsToBePrinted> toBePrinted1 = new ArrayList<>();
        for (Map.Entry<Book, LocalDate> entry : mapReader.entrySet()) {
            toBePrinted1.add(new PanelWithScrollPane.ClassWithComponentsToBePrinted(entry.getKey(), entry.getValue()));
        }

        for (int i = 0; i < mapReader.size(); i++) {
            defaultListModel1.addElement(toBePrinted1.get(i));
        }


        JButton jButton1 = new JButton(button2);
        JList<PanelWithScrollPane.ClassWithComponentsToBePrinted> elementList1 = new JList<>(defaultListModel1);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = elementList1.getSelectedIndex();
                if (selectedIndex != -1) {
                    PanelWithScrollPane.ClassWithComponentsToBePrinted selectedBook = defaultListModel1.getElementAt(selectedIndex);
                    Book book = selectedBook.book;

                    if (panel.equals("reader")) {
                        boolean postpone = myReader.postponeReturnDate(book);
                        if (postpone) {
                            PanelWithScrollPane.ClassWithComponentsToBePrinted x = new PanelWithScrollPane.ClassWithComponentsToBePrinted(book, myReader.getCheckedOutBooks().get(book));
                            defaultListModel1.setElementAt(x, selectedIndex);
                            JOptionPane.showMessageDialog(jPanel1, "<html>You have successfully postponed the return date!");
                        } else {
                            JOptionPane.showMessageDialog(jPanel1, "<html>You cannot postpone a book twice!");
                        }

                    } else {
                        try {
                            librarian.acceptBookReturn(myReader.getSignInData().getLogin(), book, book.bookCondition);
                            defaultListModel1.remove(selectedIndex);
                            JOptionPane.showMessageDialog(PanelWithScrollPane.this, "The book has been returned");
                            revalidate();
                            repaint();
                        } catch (NoReaderWithThatLoginException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

            }
        });
        jButton1.setEnabled(false);
        jPanel2.add(jButton1);

        elementList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        elementList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isSelectionEmpty = elementList1.isSelectionEmpty();
                jButton1.setEnabled(!isSelectionEmpty);
            }
        });

        elementList1.setCellRenderer(new PanelWithScrollPane.PrintInFourRows());
        JScrollPane scrollPane1 = new JScrollPane(elementList1);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(800, 100));
        scrollPane1.setBackground(new Color(238, 232, 223, 255));
        scrollPane1.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane1.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList1.setBackground(new Color(238, 232, 223, 255));
        jPanel2.add(scrollPane1);
    }

    public class ClassWithComponentsToBePrinted {

        public Book book;

        public String tytul;
        public String autor;
        public String data;
        public String pusty;

        public ClassWithComponentsToBePrinted(Book book, LocalDate localDate) {
            this.book = book;
            this.tytul = book.title;
            this.autor = book.author;
            this.data = String.valueOf(localDate);
            this.pusty = " ";
        }

        public ClassWithComponentsToBePrinted(Book book) {
            this.book = book;
            this.tytul = book.title;
            this.autor = book.author;
            this.data = " ";
            this.pusty = " ";
        }

    }

    class PrintInFourRows extends JPanel implements ListCellRenderer<PanelWithScrollPane.ClassWithComponentsToBePrinted> {
        private JLabel titleLabel;
        private JLabel authorLabel;
        private JLabel dataLabel;
        private JLabel emptuLabel;

        public PrintInFourRows() {
            setLayout(new GridLayout(4, 1));
            titleLabel = new JLabel();
            authorLabel = new JLabel();
            dataLabel = new JLabel();
            emptuLabel = new JLabel();

            add(titleLabel);
            add(authorLabel);
            add(dataLabel);
            add(emptuLabel);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends PanelWithScrollPane.ClassWithComponentsToBePrinted> list, PanelWithScrollPane.ClassWithComponentsToBePrinted value, int index, boolean isSelected, boolean cellHasFocus) {
            titleLabel.setText(value.tytul);
            authorLabel.setText(value.autor);
            dataLabel.setText(value.data);
            emptuLabel.setText(value.pusty);

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }

}
