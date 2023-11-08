import main.java.com.exemple.Model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonstreTest {

    @Test
    public void comportementMonstreAlea(){
        Joueur j = new Joueur(6,7, 10);
        MonstreAleatoire m = new MonstreAleatoire(5,5, 5, 1);
        m.setJoueurCible(j);
        Labyrinthe labyrinthe = new Labyrinthe(10);
        labyrinthe.lire_lab(1+"");
        m.setLabyrinthe(labyrinthe);
        while(!m.attaquer()){
            m.comportement();
//            System.out.println("Monstre : " + m.getPosX() + " " + m.getPosY());
//            System.out.println("Joueur : " + j.getPosX() + " " + j.getPosY());
        }



    }

    @Test
    public void attaquerMonstre(){
        Joueur j = new Joueur(5,5, 10);
        Monstre m = new MonstreAleatoire(5,5, 5, 1);
        m.setJoueurCible(j);
        assertEquals(m.getX(), j.getPosX(), "Coordonnées similaires");
        assertEquals(m.getY(), j.getPosY(), "Coordonnées similaires");
        boolean res = m.attaquer();
        assertEquals(true, res, "Le monstre n'a pas attaqué le type.");
        assertEquals(9, j.getPv(), "Le joueur devrait perdre 1pv." );
    }

    @Test
    public void comportementMonstreIntelligent(){
        Monstre m = new MonstreIntelligent(8, 8, 10, 1);
        Joueur j = new Joueur(1, 1, 10);
        m.setJoueurCible(j);
        Labyrinthe labyrinthe = new Labyrinthe(10);
        labyrinthe.lire_lab(1+"");
        m.setLabyrinthe(labyrinthe);
        j.setLabyrinthe(labyrinthe);

        m.comportement();
        System.out.println("Monstre : " + m.getPosX() + " " + m.getPosY());
        System.out.println("Joueur : " + j.getPosX() + " " + j.getPosY());
        m.comportement();
        System.out.println("Monstre : " + m.getPosX() + " " + m.getPosY());
        System.out.println("Joueur : " + j.getPosX() + " " + j.getPosY());
        m.comportement();
        System.out.println("Monstre : " + m.getPosX() + " " + m.getPosY());
        System.out.println("Joueur : " + j.getPosX() + " " + j.getPosY());



    }



}
