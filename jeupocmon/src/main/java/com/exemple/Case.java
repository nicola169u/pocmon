package main.java.com.exemple;

public abstract class Case {
    protected int posX;
    protected int posY;

    public Case(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean estMur(){
        return false;
    }
}
