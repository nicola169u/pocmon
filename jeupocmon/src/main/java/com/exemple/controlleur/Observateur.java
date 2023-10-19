package controlleur;

import main.java.com.exemple.Jeu;

abstract public class Observateur {
    protected Jeu jeu;

    public Observateur(){

    }
    public abstract void reagir();

    public void setModel(Jeu jeu){
        this.jeu = jeu;
    }
}
