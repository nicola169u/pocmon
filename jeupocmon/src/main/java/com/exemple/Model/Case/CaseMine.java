package main.java.com.exemple.Model.Case;

import main.java.com.exemple.Model.Case.Case;

public class CaseMine extends Case {


    private boolean exploded;
    /**
     * Constructeur de Case en fonction de ses coordonnées posX et posY
     *
     * @param x la position en x
     * @param y la position en y
     */
    public CaseMine(int x, int y) {
        super(x, y);
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        this.posX = x;
        this.posY = y;
        this.exploded = false;
    }


    @Override
    public boolean estMine() {
        return true;
    }

    @Override
    public String getType() {
        if(exploded){
            return "MineDesac";
        }
        return "Mine";
    }

    public boolean exploded(){
        return exploded;
    }


    public void setExplosion(boolean explosion){
        this.exploded = explosion;
    }
}
