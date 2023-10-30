import main.java.com.exemple.Model.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    @Test
    void avancer() {
        /*
        Joueur joueur = new Joueur();
        Labyrinthe labyrinthe = new Labyrinthe();
        int x = joueur.getPosX();
        int y = joueur.getPosY();
        joueur.avancer(Direction.HAUT, labyrinthe);
        assertEquals(x-1, joueur.getPosX(), "Le joueur n'est pas monté..");
        assertEquals(y, joueur.getPosY(), "Le joueur n'est pas monté..");
        joueur.setPosX(5);
        joueur.setPosY(5);
        joueur.avancer(Direction.BAS, labyrinthe);
        assertEquals(x+1, joueur.getPosX(), "Le joueur n'est pas descendu..");
        assertEquals(y, joueur.getPosY(), "Le joueur n'est pas descendu..");
        joueur.setPosX(5);
        joueur.setPosY(5);
        joueur.avancer(Direction.GAUCHE, labyrinthe);
        assertEquals(x, joueur.getPosX(), "Le joueur n'est pas allé à gauche..");
        assertEquals(y-1, joueur.getPosY(), "Le joueur n'est pas allé à gauche..");
        joueur.setPosX(5);
        joueur.setPosY(5);
        joueur.avancer(Direction.DROITE, labyrinthe);
        assertEquals(x, joueur.getPosX(), "Le joueur n'est pas allé à droite..");
        assertEquals(y+1, joueur.getPosY(), "Le joueur n'est pas allé à droite..");
*/
    }

    @Test
    public void testSubirDegat(){
        Labyrinthe labyrinthe = new Labyrinthe(10);
        Joueur j = new Joueur(1, 1, 10);
        j.subirDegat(5);
        assertEquals(5, j.getPv(), "Le joueur devrait perdre 5pv.");
    }

    @Test
    public void testAttaquer(){
        Joueur j = new Joueur(1, 1, 10);
        Monstre monstre = new MonstreAleatoire(1,1, 5,1);
        Labyrinthe lab = new Labyrinthe(10);
        j.setLabyrinthe(lab);
        monstre.setLabyrinthe(lab);
        monstre.setJoueurCible(j);
        j.attaquer(monstre);
        assertEquals(4, monstre.getPv(), "Le monstre devrait perdre 1pv.");
    }
}
