package main.java.com.exemple.View;

import main.java.com.exemple.Controller.MenuController;
import main.java.com.exemple.Controller.OptionController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class OptionView extends JFrame {

    private MenuView menuView;
    private OptionController optionController;

    private JPanel panel = new JPanel();
    private JPanel stages = new JPanel();
    private JPanel difficulty = new JPanel();

    private ButtonGroup groupLevels = new ButtonGroup();
    private ButtonGroup groupDifficulty = new ButtonGroup();



    private JButton exit = new JButton("Menu");
    private JButton save = new JButton("Sauvegarder");

    private LayoutManager layout = new FlowLayout();

    public OptionView(int width,int height) {
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

    public void launchMenuView(int niveau) {
        menuView = new MenuView(300,80,niveau);
    }

    public JButton getExit() {
        return exit;
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public ButtonGroup getGroupLevels() {
        return groupLevels;
    }

    public ButtonGroup getGroupDifficulty() {
        return groupDifficulty;
    }

    public JButton getSave() {
        return save;
    }
}
