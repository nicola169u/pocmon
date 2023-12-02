package main.java.com.exemple.Controller;

import main.java.com.exemple.View.MenuView;
import main.java.com.exemple.View.ShopView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopController implements ActionListener {
    /**
     * La vue du shop
     */
    private ShopView shopView;

    /**
     * Le numéro de l'epee
     */
    private int swordIndex = 1;

    /**
     * La vue du menu
     */
    private MenuView menuView;

    /**
     * Constructeur de ShopController avec la vue du shop et la vue du menu
     * @param shopView la vue du shop
     * @param menuView la vue du menu
     */
    public ShopController(ShopView shopView,MenuView menuView){
        this.shopView = shopView;
        this.menuView = menuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == shopView.getExit()) {
            shopView.setVisible(false);
            menuView.setVisible(true);
        }
        else if(source == shopView.getSave())
        {
            System.out.println("Yes");
        }
    }
}
