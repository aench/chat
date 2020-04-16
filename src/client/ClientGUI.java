package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ActionListener {

    Client client;
    JLabel[] screen;
    JPanel center;
    JPanel south;
    JLabel scrittaText;
    JTextField input;
    JButton button;

    public ClientGUI(Client c) {

        client = c;

        setTitle("Chat Client");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BorderLayout layout = new BorderLayout();

        GridLayout lowerLayout = new GridLayout(1,3);
        south = new JPanel();
        south.setLayout(lowerLayout);
        scrittaText = new JLabel("Text:");
        input = new JTextField();
        button = new JButton("Invia");
        button.addActionListener(this);
        south.add(scrittaText);
        south.add(input);
        south.add(button);

        this.setLayout(layout);
        add(south, BorderLayout.SOUTH);

        center = new JPanel();
        GridLayout centerLayout = new GridLayout(10,1);
        center.setLayout(centerLayout);
        screen = new JLabel[10];
        screen[0]= new JLabel("Welcome!");
        screen[1] = new JLabel("");
        screen[2] = new JLabel("+---+");
        for (int i = 3; i<10; i++) {
            screen[i] = new JLabel();
        }
        for (int j = 0;j<10;j++){
            center.add(screen[j]);
        }
        add(center,BorderLayout.CENTER);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel scritta = new JLabel("Prova");
        this.add(scritta, BorderLayout.CENTER);
        String tmp = this.input.getText();
        System.out.println(tmp);
        client.setText(tmp);
        this.input.setText("");
    }

    public void refreshScreen(String[] s) {
        for (int j = 0;j<s.length-1;j++){
            System.out.println(s[j]);
        }
        if (s.length < 11) {
            for (int i = s.length - 1; i >= 0; i--) {
                this.screen[i].setText(s[i]);
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                this.screen[i].setText(s[s.length-10+i]);
            }
        }
    }
}
