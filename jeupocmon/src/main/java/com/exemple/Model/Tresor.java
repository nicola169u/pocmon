package main.java.com.exemple;

import main.java.com.exemple.Model.Case;

public class Tresor extends Case {
    public Tresor(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public boolean estTresor() {
        return true;
    }
}