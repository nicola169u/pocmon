package main.java.com.exemple.Model;

public class MonstreAleatoire extends Monstre{

    protected Direction deplacement;

    protected int timer;



    public MonstreAleatoire(int x, int y) {
        super(x,y);
        deplacement = randomDir();
        timer = (int) (Math.random() * 100);

    }

    @Override
    public void comportement() {
        //timer--;
        avancer(deplacement, this.labyrinthe);
        deplacement = randomDir();
//        if (timer <= 0) {
//            deplacement = randomDir();
//            timer = (int) (Math.random() * 100);
//        }
        attaquer();

    }

    public Direction randomDir() {
        switch ((int) (Math.random() * 4)) {
            case 0:
                return Direction.HAUT;
            case 1:
                return Direction.DROITE;
            case 2:
                return Direction.BAS;
            case 3:
            default:
                return Direction.GAUCHE;
        }
    }

    public Direction getDeplacement(){
        return deplacement;
    }

    public int getTimer() {
        return timer;
    }
}
