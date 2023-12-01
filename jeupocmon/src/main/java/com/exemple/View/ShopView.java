package main.java.com.exemple.View;

import main.java.com.exemple.Controller.ShopController;
import main.java.com.exemple.Tools.ImageManager;

import javax.swing.*;
import java.awt.*;

public class ShopView extends JFrame {

    /**
     * La vue du menu
     */
    private MenuView menuView;

    /**
     * Le controller de la vue du Shop
     */
    private ShopController shopController;

    /**
     * Le panneau des boutons
     */
    private JPanel buttons = new JPanel();

    /**
     * Le bouton "quitter"
     */
    private JButton exit = new JButton("Menu");
    /**
     * Le bouton "sauvegarder"
     */
    private JButton save = new JButton("Sauvegarder");

    /**
     * Constructeur de ShopView en fonction de sa taille
     *
     * @param width
     * @param height
     */
    public ShopView(int width, int height, MenuView m){
        menuView = m;
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ImageManager.getInstance().getImage("ShopBackground"), 0, 0, getWidth(), getHeight(), this);
            }
        });
        shopController = new ShopController();
        addButtons(buttons,getContentPane());
        shopViewSettings(width,height);


    }

    private void shopViewSettings(int width, int height)
    {
        setIconImage(new ImageIcon(ImageManager.getInstance().getImage("ShopIcon")).getImage());
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Option");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();
    }

    /**
     * Procédure privée qui ajoute les boutons pour revenir au menu
     */
    private void addButtons(JPanel panel, Container pane){
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);
        panel.add(save,BorderLayout.CENTER);
        panel.add(exit,BorderLayout.CENTER);
        exit.addActionListener(shopController);
        save.addActionListener(shopController);
        pane.add(panel);
    }

}
