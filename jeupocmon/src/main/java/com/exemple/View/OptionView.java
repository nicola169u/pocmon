package main.java.com.exemple.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionView extends JFrame implements ActionListener {

    MenuView menuView;
    JPanel panel = new JPanel();
    JPanel stages = new JPanel();
    JPanel difficulty = new JPanel();
    JButton exit = new JButton("Menu");
    LayoutManager layout = new FlowLayout();

    public OptionView(int width,int height) {
        panel.setLayout(layout);
        addStageLevels();
        addDifficulty();
        exit.addActionListener(this);
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
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem("1");
        group.add(menuItem);
        stages.add(menuItem);

        menuItem = new JRadioButtonMenuItem("2");
        group.add(menuItem);
        stages.add(menuItem);

        menuItem = new JRadioButtonMenuItem("3");
        group.add(menuItem);
        stages.add(menuItem);

        menuItem = new JRadioButtonMenuItem("4");
        group.add(menuItem);
        stages.add(menuItem);

        menuItem = new JRadioButtonMenuItem("5");
        group.add(menuItem);
        stages.add(menuItem);

        stages.setBorder(blackline);
        panel.add(stages);
        getContentPane().add(panel,BorderLayout.CENTER);

    }

    private void addDifficulty()
    {
        Border blackline = BorderFactory.createTitledBorder("Choix de la difficulte");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem("1");
        group.add(menuItem);
        difficulty.add(menuItem);

        menuItem = new JRadioButtonMenuItem("2");
        group.add(menuItem);
        difficulty.add(menuItem);

        menuItem = new JRadioButtonMenuItem("3");
        group.add(menuItem);
        difficulty.add(menuItem);

        menuItem = new JRadioButtonMenuItem("4");
        group.add(menuItem);
        difficulty.add(menuItem);

        menuItem = new JRadioButtonMenuItem("5");
        group.add(menuItem);
        difficulty.add(menuItem);

        difficulty.setBorder(blackline);
        panel.add(difficulty);
        getContentPane().add(panel,BorderLayout.CENTER);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO logique des options
        Object source = e.getSource();
        if (source == exit) {
            setVisible(false);
            menuView = new MenuView(300,80);
        }

    }
}
