package main.java.com.exemple.Model;

public class PotionVie extends Case{

    private boolean ramasse;

    public PotionVie(int x, int y) {
        super(x, y);
        this.ramasse = false;
    }


    @Override
    public boolean estPotionVie() {
        return true;
    }


    public boolean isRamasse() {
        return ramasse;
    }

    public void setRamasse(boolean ramasse) {
        this.ramasse = ramasse;
    }
}
