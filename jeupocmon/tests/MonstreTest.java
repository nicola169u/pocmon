import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Model.Labyrinthe;
import main.java.com.exemple.Model.Monstre;
import main.java.com.exemple.Model.MonstreAleatoire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonstreTest {

    @Test
    public void comportementMonstreAlea(){
        Joueur j = new Joueur(6,7);
        MonstreAleatoire m = new MonstreAleatoire(5,5);
        m.setJoueurCible(j);
        Labyrinthe labyrinthe = new Labyrinthe();
        labyrinthe.lire_lab(1+"");
        m.setLabyrinthe(labyrinthe);
        while(!m.attaquer()){
            m.comportement();
            System.out.println("Monstre : " + m.getPosX() + " " + m.getPosY());
            System.out.println("Joueur : " + j.getPosX() + " " + j.getPosY());
        }



    }

    @Test
    public void attaquerMonstre(){
        Joueur j = new Joueur(5,5);
        Monstre m = new MonstreAleatoire(5,5);
        m.setJoueurCible(j);
        assertEquals(m.getX(), j.getPosX(), "Coordonnées similaires");
        assertEquals(m.getY(), j.getPosY(), "Coordonnées similaires");
        assertEquals(true, m.attaquer(), "Le monstre n'a pas attaqué le type.");
    }



}
