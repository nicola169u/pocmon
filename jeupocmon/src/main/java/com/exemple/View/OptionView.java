package main.java.com.exemple.View;

import main.java.com.exemple.Controller.MenuController;
import main.java.com.exemple.Controller.OptionController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;


/**
 * Classe représentant la vue du menu des options
 */
public class OptionView extends JFrame {
    /**
     * La vue du menu
     */
    private MenuView menuView;
    /**
     * Le controller de la vue des options
     */
    private OptionController optionController;
    /**
     * Le panneau principal
     */
    private JPanel panel = new JPanel();
    /**
     * Le panneau des niveaux
     */
    private JPanel stages = new JPanel();
    /**
     * Le panneau des difficultés
     */
    private JPanel difficulty = new JPanel();
    /**
     * Le groupe de boutons pour les niveaux
     */
    private ButtonGroup groupLevels = new ButtonGroup();
    /**
     * Le groupe de boutons pour le niveau de difficultés
     */
    private ButtonGroup groupDifficulty = new ButtonGroup();
    /**
     * Le bouton "quitter"
     */
    private JButton exit = new JButton("Menu");
    /**
     * Le bouton "sauvegarder"
     */
    private JButton save = new JButton("Sauvegarder");
    /**
     * Le conteneur
     */
    private LayoutManager layout = new FlowLayout();


    /**
     * Constructeur de OptionView en fonction de sa taille
     * @param width
     * @param height
     */
    public OptionView(int width,int height , MenuView m) {
        menuView = m;
        panel.setLayout(layout);
        addStageLevels();
        addDifficulty();
        optionController = new OptionController(this,menuView);
        exit.addActionListener(optionController);
        save.addActionListener(optionController);
        panel.add(save,BorderLayout.CENTER);
        panel.add(exit,BorderLayout.CENTER);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Option");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();
    }


    /**
     * Procédure privée qui ajoute les boutons pour choisir le niveau
     */
    private void addStageLevels()
    {
        Border blackline = BorderFactory.createTitledBorder("Choix du niveau");
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem("1");
        groupLevels.add(menuItem);
        stages.add(menuItem);

        menuItem = new JRadioButtonMenuItem("2");
        groupLevels.add(menuItem);
        stages.add(menuItem);

        menuItem = new JRadioButtonMenuItem("3");
        groupLevels.add(menuItem);
        stages.add(menuItem);

        menuItem = new JRadioButtonMenuItem("4");
        groupLevels.add(menuItem);
        stages.add(menuItem);

        menuItem = new JRadioButtonMenuItem("5");
        groupLevels.add(menuItem);
        stages.add(menuItem);

        stages.setBorder(blackline);
        panel.add(stages);
        getContentPane().add(panel,BorderLayout.CENTER);

    }


    /**
     * Procédure privée qui ajoute les boutons pour choisir le niveau de difficulté
     */
    private void addDifficulty()
    {
        Border blackline = BorderFactory.createTitledBorder("Choix de la difficulte");
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem("1");
        groupDifficulty.add(menuItem);
        difficulty.add(menuItem);

        menuItem = new JRadioButtonMenuItem("2");
        groupDifficulty.add(menuItem);
        difficulty.add(menuItem);

        menuItem = new JRadioButtonMenuItem("3");
        groupDifficulty.add(menuItem);
        difficulty.add(menuItem);

        menuItem = new JRadioButtonMenuItem("4");
        groupDifficulty.add(menuItem);
        difficulty.add(menuItem);

        menuItem = new JRadioButtonMenuItem("5");
        groupDifficulty.add(menuItem);
        difficulty.add(menuItem);

        difficulty.setBorder(blackline);
        panel.add(difficulty);
        getContentPane().add(panel,BorderLayout.CENTER);

    }


    /**
     * Procédure qui lance la vue du menu en fonction du niveau choisi
     * @param niveau
     * @param difficulte
     */
    public void launchMenuView(int niveau, int difficulte) {

        this.menuView.setNiveau(niveau);
        this.menuView.setDifficulte(difficulte);
        this.menuView.setVisible(true);
    }


    /**
     * Fonction qui retourne le bouton "quitter"
     * @return le bouton "quitter"
     */
    public JButton getExit() {
        return exit;
    }


    /**
     * Fonction qui retourne le texte du bouton selectionné dans le groupe de boutons buttonGroup
     * @param buttonGroup
     * @return le texte du bouton selectionné dans le groupe de boutons buttonGroup
     */
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }


    /**
     * Fonction qui retourne le groupe de boutons pour le choix des niveaux
     * @return le groupe de boutons pour le choix des niveaux
     */
    public ButtonGroup getGroupLevels() {
        return groupLevels;
    }

    /**
     * Fonction qui retourne le groupe de boutons pour le choix de la difficulté
     * @return le groupe de boutons pour le choix de la difficulté
     */
    public ButtonGroup getGroupDifficulty() {
        return groupDifficulty;
    }


    /**
     * Fonction qui retourne le bouton "sauvegarder"
     * @return
     */
    public JButton getSave() {
        return save;
    }
}
