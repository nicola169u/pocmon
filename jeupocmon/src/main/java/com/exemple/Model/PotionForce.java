package main.java.com.exemple.Model;

public class PotionForce extends Case{

    private boolean ramasse;
    public PotionForce(int x, int y) {
        super(x, y);
        this.ramasse = false;
    }

    @Override
    public boolean estPotionForce() {
        return true;
    }

    public boolean isRamasse() {
        return ramasse;
    }

    public void setRamasse(boolean ramasse) {
        this.ramasse = ramasse;
    }
}
