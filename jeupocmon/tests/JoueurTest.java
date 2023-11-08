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

    @Test
    public void TestAcote(){
        Joueur j = new Joueur(5, 5, 10);
        Monstre m = new MonstreIntelligent(5, 6, 10, 2);
        Monstre m2 = new MonstreIntelligent(5, 4, 10, 2);
        Monstre m3 = new MonstreIntelligent(4, 5, 10, 2);
        Monstre m4 = new MonstreIntelligent(6, 5, 10, 2);
        assertEquals(true, j.monstreACote(m), "Le monstre est en dessous du joueur");
        assertEquals(true, j.monstreACote(m2), "Le monstre est au dessus du joueur");
        assertEquals(true, j.monstreACote(m3), "Le monstre est à gauche du joueur");
        assertEquals(true, j.monstreACote(m4), "Le monstre est à droite du joueur");
    }

    @Test
    public void invulnerable(){
        Joueur j = new Joueur(1, 1, 10);
        j.setInvulnerable(true);
        Monstre m = new Fantome(1, 1, 10, 10);
        m.setJoueurCible(j);
        boolean attaque = m.attaquer();
        assertEquals(true, attaque, "Le monstre attaque pas !!");
        assertEquals(10, j.getPv(), "Le joueur n'a pas perdu de pv comme il est invulnérable");


    }

    @Test
    public void soin(){
        Joueur j = new Joueur(1, 1, 10);
        Monstre m = new MonstreIntelligent(1,1, 10, 5);
        m.setJoueurCible(j);
        m.attaquer();
        assertEquals(5, j.getPv(), "Le joueur a bien perdu des pvs.");
        j.soigner(2);
        assertEquals(7, j.getPv(), "Le joueur s'est heal de 2 pv");
    }
}
