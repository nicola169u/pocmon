import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Model.Labyrinthe;
import main.java.com.exemple.Model.Personnage;
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
        Labyrinthe labyrinthe = new Labyrinthe();
        Joueur j = new Joueur(1, 1, 10);
        j.subirDegat(5);
        assertEquals(5, j.getPv(), "Le joueur devrait perdre 5pv.");
    }
}