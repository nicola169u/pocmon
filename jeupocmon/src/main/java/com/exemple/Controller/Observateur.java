package main.java.com.exemple.Controller;

import main.java.com.exemple.Model.Jeu;

public abstract class Observateur {
    protected Jeu jeu;

    public Observateur(){
    }

    public abstract void reagir();

    public void setJeu(Jeu jeu){
        this.jeu = jeu;
    }
}
