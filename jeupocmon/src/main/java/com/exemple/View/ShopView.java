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
     * Le panneau des items
     */
    private JPanel items = new JPanel();

    /**
     * Le groupe de boutons pour les niveaux
     */
    private ButtonGroup groupItems = new ButtonGroup();

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
        shopController = new ShopController(this,menuView);
        addToPane(groupItems,items,getContentPane());
        addButtons(buttons,getContentPane());
        shopViewSettings(width,height);


    }

    /**
     * Procédure privée qui ajoute les boutons au panneau
     */
    private void addToPane(ButtonGroup group, JPanel panel, Container pane){
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        addItems(group,items,pane);
    }

    /**
     * Procédure privée qui construit les boutons radios pour une option.
     */
    private void addItems(ButtonGroup buttonGroup, JPanel panel, Container pane)
    {
        JRadioButtonMenuItem menuItem;
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        for (int i = 1; i < 4 ; i++) {
            menuItem = new JRadioButtonMenuItem(new ImageIcon(getClass().getResource("/sword" + i + "_icon.png")));
            menuItem.setText(""+i*10+" GC.");
            menuItem.setVerticalTextPosition(SwingConstants.BOTTOM);
            menuItem.setOpaque(false);
            menuItem.setBorderPainted(false);
            menuItem.setBorder(null);
            buttonGroup.add(menuItem);
            panel.add(menuItem);
        }
        pane.add(Box.createVerticalGlue());
        panel.setBorder(BorderFactory.createEmptyBorder(100,325,0,0));
        pane.add(panel,BorderLayout.CENTER);

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
        panel.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        panel.setOpaque(false);
        panel.add(save,BorderLayout.CENTER);
        panel.add(exit,BorderLayout.CENTER);
        exit.addActionListener(shopController);
        save.addActionListener(shopController);
        pane.add(panel);
    }

    public JButton getSave() {
        return save;
    }

    public JButton getExit() {
        return exit;
    }
}
