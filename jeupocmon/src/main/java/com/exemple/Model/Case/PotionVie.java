package main.java.com.exemple.Model.Case;

import main.java.com.exemple.Model.Case.Case;

public class PotionVie extends Case {

    private boolean ramasse;

    public PotionVie(int x, int y) {
        super(x, y);
        this.ramasse = false;
    }


    @Override
    public boolean estPotionVie() {
        return true;
    }

    @Override
    public String getType() {
        if(ramasse){
            return "Default";
        }
        return "PotionVie";
    }


    public boolean isRamasse() {
        return ramasse;
    }

    public void setRamasse(boolean ramasse) {
        this.ramasse = ramasse;
    }
}
