package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadDescriptionListener implements ActionListener {

    private Book book;

    public ReadDescriptionListener(Book book) {
        this.book = book;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Description");
        frame.setSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(238, 232, 223, 255));
        panel.setSize(new Dimension(600, 600));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        Font font = new Font("Serif", Font.PLAIN, 16);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Title: '" + book.getTitle() + "'");
        titleLabel.setFont(font);
        panel.add(titleLabel);

        JLabel descriptionLabel = new JLabel();
        String text = "<html><div style='width: 445px; text-align: justify;'>" + "Book's description: '" + book.getDescription() + "</div></html>";
        descriptionLabel.setText(text);
        descriptionLabel.setFont(font);
        panel.add(descriptionLabel);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
