package main.java.com.exemple;

import main.java.com.exemple.Model.Case;

public class Mur extends Case {
    public Mur(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public boolean estMur() {
        return true;
    }
}
