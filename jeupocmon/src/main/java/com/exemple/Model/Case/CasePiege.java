package main.java.com.exemple.Model.Case;

import main.java.com.exemple.Model.Case.Case;

public class CasePiege extends Case {

        /**
         * constructeur
         * @param x pos x
         * @param y pos y
         */
	public CasePiege(int x, int y) {
        super(x,y);
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        this.posX = x;
        this.posY = y;
    }


    @Override
    public boolean estPiege() {
        return true;
    }

    @Override
    public String getType() {
        return "Piege";
    }
}
