package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.People.Person;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;
import pl.edu.pw.mini.zpoif.zespol9.System.LibrarySystem;
import pl.edu.pw.mini.zpoif.zespol9.System.PasswordCheckInterfejs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LokigGui extends JFrame {

    private JTextField loginField;
    private JPasswordField passwordField;
    private LibrarySystem librarySystem;

    public LokigGui(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;

        setTitle("Sign In Panel");
        setVisible(true);
        setSize(new Dimension(500, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        JLabel background = new JLabel(new ImageIcon("resources/LogingPanelBackground.png"));
        background.setBounds(0, 0, getWidth(), getHeight());
        add(background);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(3, 3));

        Font font = new Font("Serif", Font.BOLD, 17);
        Font font1 = new Font("Serif", Font.PLAIN, 14);

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(font);
        //loginField = new JTextField("");
        //passwordField = new JPasswordField("");
        loginField = new JTextField("lBilboBaggins10");
        passwordField = new JPasswordField("wEcdS");
        panel.add(loginLabel);
        panel.add(loginField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton loginButton = new JButton("Sign In");
        loginButton.setFont(font1);
        loginButton.setBackground(new Color(238, 232, 223));
        //loginButton.setBounds(200, 20, 10, 2);
        //panel.add(loginButton, GroupLayout.DEFAULT_SIZE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginUsedForSigning = loginField.getText();
                String passwordUsedForSigning = String.valueOf(passwordField.getPassword());
                boolean exist = false;

                if (loginUsedForSigning.startsWith("r")) {
                    List<Reader> tmpList = librarySystem.getReaderList();

                    for (Reader el : tmpList) {
                        if (el.getSignInData().getLogin().equals(loginUsedForSigning)) {

                            if (el.getSignInData().isPasswordCorrect(passwordUsedForSigning)) {
                                exist = true;
                                addNewWindow("reader", el);
                                dispose();

                            }
                            break;
                        }
                    }

                    if (!exist) JOptionPane.showMessageDialog(LokigGui.this, "Wrong Login or Password!");

                } else if (loginUsedForSigning.startsWith("l")) {

                    Librarian librarian = librarySystem.getLibrarian();

                    if (librarian.getSignInData().getLogin().equals(loginUsedForSigning)) {
                        if (librarian.getSignInData().isPasswordCorrect(passwordUsedForSigning)) {
                            exist = true;
                            addNewWindow("librarian", librarian);
                            dispose();

                        }
                    }

                    if (!exist) JOptionPane.showMessageDialog(LokigGui.this, "Wrong Login or Password!");
                } else {
                    JOptionPane.showMessageDialog(LokigGui.this, "Wrong Login or Password!");
                }
            }
        });
        panel.add(loginButton);
        panel.setBounds(120, 42, 250, 100);
        background.add(panel);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    private void addNewWindow(String user, Person person) {
        JFrame window;
        if (user.equalsIgnoreCase("librarian")) {
            window = new LibrarianWindow(librarySystem, (Librarian) person);
        } else {
            window = new ReaderWindow(librarySystem, (Reader) person);
        }

    }

}

