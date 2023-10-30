package main.java.com.exemple.Model;

public class MonstreIntelligent extends Monstre{

    protected int nombrePas = 0;
    protected Direction deplacementEnCours;



    public MonstreIntelligent(int x, int y, int pv, int degat) {
        super(x,y, pv, degat);
    }

    @Override
    public void comportement() {

        Labyrinthe lab = this.getLabyrinthe();
        int pxc = joueurCible.getPosX();
        int pyc = joueurCible.getPosY();

        int dx = pxc - posX;
        int dy = pyc - posY;

        if(Math.abs(dx) > Math.abs(dy)){
            if(dx > 0 && !lab.getCase(posX + 1, posY).estMur()){
                deplacementEnCours = Direction.DROITE;
            }else if(dx < 0 && !lab.getCase(posX - 1 , posY).estMur()){
                deplacementEnCours = Direction.GAUCHE;
            }
        }else{
            if(dy > 0 && !lab.getCase(posX, posY + 1).estMur()){
                deplacementEnCours = Direction.BAS;
            }else if (dy < 0 && !lab.getCase(posX, posY - 1).estMur()){
                deplacementEnCours = Direction.HAUT;
            }

        }

        avancer(deplacementEnCours);
        attaquer();

    }



}
