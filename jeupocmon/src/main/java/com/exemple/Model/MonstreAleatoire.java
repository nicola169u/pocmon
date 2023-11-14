package main.java.com.exemple.Model;

public class MonstreAleatoire extends Monstre{

    private Direction deplacement;

    private int timer;



    public MonstreAleatoire(int x, int y, int pv, int degat) {
        super(x,y, pv, degat);
        deplacement = randomDir();
        timer = (int) (Math.random() * 100);

    }

    @Override
    public void comportement() {
        //timer--;
        avancer(deplacement);
        deplacement = randomDir();
//        if (timer <= 0) {
//            deplacement = randomDir();
//            timer = (int) (Math.random() * 100);
//        }
        attaquer();

    }

    @Override
    public boolean isMonstreAleatoire() {
        return true;
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


    @Override
    public void run(){

    }
}
