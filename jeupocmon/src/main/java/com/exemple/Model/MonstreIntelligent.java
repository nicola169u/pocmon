package main.java.com.exemple.Model;

public class MonstreIntelligent extends Monstre{

    protected int nombrePas = 0;
    protected Direction deplacementEnCours;



    public MonstreIntelligent(int x, int y) {
        super(x,y);
    }

    @Override
    public void comportement() {

    }
}
