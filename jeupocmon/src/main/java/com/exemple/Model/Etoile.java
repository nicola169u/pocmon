package main.java.com.exemple.Model;

public class Etoile extends Case{

    private boolean ramasse;

    /**
     * Constructeur de Case en fonction de ses coordonnées posX et posY
     *
     * @param posX la position en x
     * @param posY la position en y
     */
    public Etoile(int posX, int posY) {
        super(posX, posY);
    }

    public boolean estEtoile(){
        return true;
    }

    public boolean isRamasse() {
        return ramasse;
    }

    public void setRamasse(boolean ramasse) {
        this.ramasse = ramasse;
    }
}
