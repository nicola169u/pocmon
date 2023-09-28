package main.java.com.exemple;

public class Tresor extends Case{
    public Tresor(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public boolean estTresor() {
        return true;
    }
}