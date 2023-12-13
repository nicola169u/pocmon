package main.java.com.exemple.Model.Case;

import main.java.com.exemple.Model.Case.Case;

public class PotionForce extends Case {

    /**
     * attribut pour savoir si la potion force a été ramassée
     */
    private boolean ramasse;
    public PotionForce(int x, int y) {
        super(x, y);
        this.ramasse = false;
    }

    @Override
    public boolean estPotionForce() {
        return true;
    }

    @Override
    public String getType() {
        if(ramasse){
            return "Default";
        }
        return "PotionForce";
    }

    public boolean isRamasse() {
        return ramasse;
    }

    public void setRamasse(boolean ramasse) {
        this.ramasse = ramasse;
    }
}
