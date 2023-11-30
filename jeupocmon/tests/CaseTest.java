import main.java.com.exemple.Model.Case.*;
import main.java.com.exemple.Model.Joueur;
import main.java.com.exemple.Model.Labyrinthe;
import main.java.com.exemple.Tools.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    private Joueur joueur;

    private CaseMine mine;

    private CasePiege piege;

    private PotionForce force;
    private PotionVie vie;

    private Etoile etoile;

    private Teleporteur c;

    private Teleporteur a;

    private Labyrinthe lab;


    @BeforeEach
    void setUp() {
        this.lab = new Labyrinthe(20);
        this.joueur = new Joueur(4,5, 10);
        this.mine = new CaseMine(5,5);
        this.force = new PotionForce(5, 5);
        this.vie = new PotionVie(5,5);
        this.piege = new CasePiege(5,5);
        this.c = new Teleporteur(5, 5);
        this.a = new Teleporteur(15, 15);
        this.etoile = new Etoile(5,5);
        this.joueur.setLabyrinthe(lab);


    }

    @Test
    public void testSurMine(){
        this.lab.addMine(mine);
        this.joueur.avancer(Direction.DROITE);
        this.lab.isOnMine(joueur);
        assertEquals(0, this.joueur.getPv(), "Le joueur perd pas de pv.");



    }

    @Test
    public void testSurPiege(){
        this.lab.addPiege(piege);
        this.joueur.avancer(Direction.DROITE);
        this.lab.trap(joueur);
        assertEquals(5, this.joueur.getPv(), "Le joueur perd pas de pv");
    }

    @Test
    public void testTp(){
        this.lab.addTp(c, a);
        this.joueur.avancer(Direction.DROITE);
        this.lab.isOnTp(joueur);
        assertEquals(a.getPosX(), joueur.getPosX(), "Le joueur ne s'est pas téléporté.");
        assertEquals(a.getPosY(), joueur.getPosY(), "Le joueur ne s'est pas téléporté.");

    }

    @Test
    public void testPotionForce(){
        this.lab.addPotionForce(force);
        this.joueur.avancer(Direction.DROITE);
        this.lab.isOnPotionForce(joueur);
        assertEquals(10, this.joueur.getDegat(), "Le joueur n'a pas été buff.");

    }

    @Test
    public void testEtoile(){
        this.lab.addEtoile(etoile);
        this.joueur.avancer(Direction.DROITE);
        this.lab.isOnEtoile(joueur);
        assertTrue(this.joueur.isInvulnerable());

    }

    @Test
    public void testPotionVieSuperieurPvMax(){
        this.lab.addPotionVie(vie);
        this.joueur.avancer(Direction.DROITE);
        this.lab.isOnPotionVie(joueur);
        assertEquals(this.joueur.getPvMax(), this.joueur.getPv(), "Le  joueur ne s'est pas heal.");
    }

    @Test
    public void testPotionVie(){
        this.joueur.setPv(20);
        this.joueur.subirDegat(11);
        int pvBase = this.joueur.getPv();
        System.out.println(pvBase);
        this.lab.addPotionVie(vie);
        this.joueur.avancer(Direction.DROITE);
        this.lab.isOnPotionVie(joueur);
        assertEquals(pvBase + 10, this.joueur.getPv(), "Le joueur ne s'est pas heal");

    }



}