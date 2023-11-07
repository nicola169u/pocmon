package main.java.com.exemple.View;

import main.java.com.exemple.Model.Jeu;
import main.java.com.exemple.Model.Monstre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame implements ActionListener {

    private Jeu jeu;
    private OptionView optionView;
    private JButton play = new JButton("Jouer");
    private JButton settings = new JButton("Option");
    private JButton exit = new JButton("Quitter");
    private CardLayout layout = new CardLayout();

    private JPanel panel = new JPanel();
    private JPanel menu = new JPanel();

    public MenuView(int width,int height)
    {
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
        } else if (source == settings) {
            setVisible(false);
            optionView = new OptionView(300,200);
            //TODO Autres options
        }
    }
}
