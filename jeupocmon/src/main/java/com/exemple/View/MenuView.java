package main.java.com.exemple.View;

import main.java.com.exemple.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame implements ActionListener {

    int width, height;
    Jeu jeu;
    JButton play = new JButton("Jouer");
    JButton settings = new JButton("Option");
    JButton exit = new JButton("Quitter");
    CardLayout layout = new CardLayout();

    JPanel panel = new JPanel();
    JPanel menu = new JPanel();

    public MenuView(int width,int height)
    {
        this.width = width;
        this.height = height;

        panel.setLayout(layout);
        layout.addLayoutComponent(panel, "Menu");
        addButtons();

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Pocmon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();

    }

    private void addButtons() {

        play.addActionListener(this);
        settings.addActionListener(this);
        exit.addActionListener(this);
        menu.add(play);
        menu.add(settings);
        menu.add(exit);
        panel.add(menu,"Menu");
        add(panel);
        layout.show(panel,"Menu");

    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == exit) {
            System.exit(0);
        } else if (source == play) {
            setVisible(false);
            jeu = new Jeu();
            jeu.lancer();
            //TODO voir la logique
            setVisible(true);
        } else if (source == settings) {
            //TODO Choix niveau
            //TODO Choix difficulte
            //TODO Autres options
        }
    }
}
