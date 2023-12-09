package main.java.com.exemple.View;

import main.java.com.exemple.Controller.MenuController;
import main.java.com.exemple.Tools.ImageManager;

import javax.swing.*;
import java.awt.*;


/**
 * Classe représentant la vue du Menu
 */
public class MenuView extends JFrame {
    /**
     * Le niveau choisi
     */
    private int niveau = 1;
    /**
     * La difficulte choisie
     */
    private int difficulte = 1;
    /**
     * Le score du joueur
     */
    private int score = 0;
    /**
     * L'epee du joueur
     */
    private int sword = 0;
    /**
     * La vue du menu des options
     */
    private OptionView optionView;
    /**
     * La vue du shop
     */
    private ShopView shopView;
    /**
     * Le bouton "jouer"
     */
    private JButton play = new JButton("Jouer");
    /**
     * Le bouton "option"
     */
    private JButton settings = new JButton("Option");
    /**
     * Le bouton "Shop"
     */
    private JButton shop = new JButton("Shop");
    /**
     * Le bouton "quitter"
     */
    private JButton exit = new JButton("Quitter");
    /**
     * Le controller du menu
     */
    private MenuController menuController;


    /**
     * Constructeur de MenuView en fonction de la taille et du niveau
     *
     * @param width
     * @param height
     * @param lvl
     */
    public MenuView(int width, int height, int lvl) {
        this.niveau = lvl;

        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ImageManager.getInstance().getImage("MenuBackground"), 0, 0, getWidth(), getHeight(), null);
            }
        });
        setIconImage(new ImageIcon(ImageManager.getInstance().getImage("MenuIcon")).getImage());
        menuController = new MenuController(this);
        addToPane(getContentPane());
        pack();
        setSize(width, height);
    }

    public void startMenu() {
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Pocmon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();
    }

    /**
     * Procédure privée qui ajoute les boutons au panneau
     */
    private void addToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(Box.createVerticalStrut(150));
        addButtons(play, pane, menuController);
        addButtons(shop,pane,menuController);
        addButtons(settings, pane, menuController);
        addButtons(exit, pane, menuController);

    }

    /**
     * Procédure privée pour ajoute des boutons a un conteneur
     */
    private void addButtons(JButton button, Container container, MenuController menu) {
        button.setForeground(Color.WHITE);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setHorizontalAlignment(SwingConstants.RIGHT);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);

        if (button.getText() == "Jouer") {
            setButtonIcon(button,"/icon_s.png");
        } else if (button.getText() == "Option") {
            setButtonIcon(button,"/settings.png");
        } else if (button.getText() == "Quitter") {
            button.setForeground(Color.BLACK);
            setButtonIcon(button,"/exit.png");
        }else if(button.getText() == "Shop") {
            setButtonIcon(button,"/shop_s.png");
        }

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(menu);
        container.add(button);
        container.add(Box.createVerticalStrut(10));
    }

    private void setButtonIcon(JButton button,String text){
        button.setIcon(new ImageIcon(getClass().getResource(text)));
        button.setRolloverIcon(new ImageIcon(getClass().getResource("/money_s.png")));
    }


    /**
     * Getter du bouton "quitter"
     * @return le bouton "quitter"
     */
    public JButton getExit() {
        return exit;
    }

    /**
     * Getter du bouton "jouer"
     * @return le bouton "jouer"
     */
    public JButton getPlay() {
        return play;
    }

    /**
     * Getter le bouton "option"
     * @return le bouton "option"
     */
    public JButton getSettings() {
        return settings;
    }

    /**
     * Procédure qui lance le menu des options
     */
    public void launchOptionView() {
        optionView = new OptionView(750,350,this);
    }

    /**
     * Procédure qui lance le shop
     */
    public void launchShopView() {
        shopView = new ShopView(750,350,this);
    }

    /**
     * Getter du niveau
     * @return le niveau
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Getter de la difficulte
     * @return la difficulte
     */
    public int getDifficulte() {
        return difficulte;
    }

    /**
     * Getter du shop
     * @return le shop
     */
    public JButton getShop() {
        return shop;
    }

    /**
     * Getter du score
     * @return le score
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter du niveau
     * @param niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * Setter de la difficulte
     * @param difficulte
     */
    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    /**
     * Setter du score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Setter de l'epee
     * @param sword
     */
    public void setSword(int sword) {
        this.sword = sword;
    }
    /**
     * Getter de l'epee
     */
    public int getSword() {
        return sword;
    }
}
