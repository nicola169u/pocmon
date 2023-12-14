package main.java.com.exemple.Model;


import main.java.com.exemple.Model.Case.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Classe représentant le labyrinthe du jeu
 */
public class Labyrinthe {
    /**
     * La taille du labyrinthe (nombre de case en largeur et hauteur)
     */
    private int size;
    /**
     * Les cases du labyrinthe
     */
    private Case[][] cases;
    /**
     * Le trésor du labyrinthe
     */
    private Tresor tresor;
    /**
     * La liste des téléporteurs
     */
    private HashMap<Teleporteur, Teleporteur> teleporteurs;
    /**
     * La liste des pièges
     */
    private List<CasePiege> pieges;
    /**
     * La liste des mines
     */
    private List<CaseMine> mines;
    /**
     * La liste des potions de force
     */
    private List<PotionForce> forces;
    /**
     * La liste des potions de vie
     */
    private List<PotionVie> vies;
    /**
     * L'etoile de la map
     */
    private Etoile etoile;
    /**
     * La case de cours de natation
     */
    private SwimmingLesson swimmingLesson;
    /**
     * Variable qui sert à mémoriser le temps passé sur la case de cours de natation
     */
    private long tempsPasseSurSwimmingLesson;
    /**
     * Délai à passer sur la case de cours de natation
     */
    private static final long DELAI_APPRENDRE_NAGER = 3000; //Le joueur doit passer au min 3sec


    /**
     * Constructeur de Labyrinthe en fonction de sa taille
     * @param size la taille du labyrinthe (nombre de case en largeur et hauteur)
     */
    public Labyrinthe(int size) {
        this.size = size;
        cases = new Case[size][size];
        teleporteurs = new HashMap<>();
        this.pieges = new ArrayList<>();
        this.mines = new ArrayList<>();
        this.forces = new ArrayList<>();
        this.vies = new ArrayList<>();

    }


    /**
     * Procédure qui lit le fichier dans lequel est représenté le labyrinthe de niveau niveau et qui créé les case du labyrinthe
     * @param niveau le niveau à lancer
     */
    public void lire_lab(String niveau){
        //On nettoie le labyrinthe
        clearLab();


        //On charge le fichier
        File file = new File("jeupocmon/src/main/resources/niv"+niveau+".txt");

        //On test si on recupere le fichier (lancement via intellij ou si on est en jar (et donc file null))

        if(file.exists()){
            lire_lab_local(file);
        }else{
            Teleporteur tp1 = null;
            Teleporteur tp2 = null;
            try (InputStream in = getClass().getResourceAsStream("/niv"+niveau+".txt");
                 BufferedReader br = new BufferedReader(new InputStreamReader(in))){
                String line;
                int ligne = 0;
                // Lire ligne par ligne
                while ((line = br.readLine()) != null) {
                    char[] l = line.toCharArray();
                    for (int i = 0; i < line.length(); i++) {
                        char c = l[i];
                        if (c == 35) {
                            cases[i][ligne] = new Mur(i, ligne);
                        } else if (c == 84) {
                            tresor = new Tresor(i, ligne);
                            cases[i][ligne] = tresor;
                        } else if (c == 88) {
                            Teleporteur teleporteur = new Teleporteur(i, ligne);
                            cases[i][ligne] = teleporteur;
                            if (tp1 == null) {
                                tp1 = teleporteur;
                            } else {
                                tp2 = teleporteur;
                            }
                        } else if (c == 77) {
                            CaseMine cm = new CaseMine(i, ligne);
                            this.mines.add(cm);
                            cases[i][ligne] = cm;
                        } else if (c == 80) {
                            CasePiege cp = new CasePiege(i, ligne);
                            this.pieges.add(cp);
                            cases[i][ligne] = cp;
                        } else if (c == 85){
                            PotionForce pf = new PotionForce(i, ligne);
                            this.forces.add(pf);
                            cases[i][ligne] = pf;
                        }else if (c == 86){
                            PotionVie pv = new PotionVie(i, ligne);
                            this.vies.add(pv);
                            cases[i][ligne] = pv;
                        }else if(c == 69){
                            this.etoile = new Etoile(i, ligne);
                            cases[i][ligne] = this.etoile;
                        }else if(c == 87){
                            cases[i][ligne] = new Eau(i, ligne);
                        }else if(c == 83){
                            swimmingLesson = new SwimmingLesson(i, ligne);
                            cases[i][ligne] = swimmingLesson;
                        }else{
                            cases[i][ligne] = new CaseVide(i, ligne);
                        }
                    }
                    ligne++;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            teleporteurs.put(tp1, tp2);
            teleporteurs.put(tp2, tp1);
        }
    }


    /**
     * Procédure appelée dans le cas où on exécute le programme avec les .java et .class et pas en lancant le Maven
     * @param file le fichier de type File
     */
    private void lire_lab_local(File file){
        Teleporteur tp1 = null;
        Teleporteur tp2 = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int ligne = 0;
            // Lire ligne par ligne
            while ((line = br.readLine()) != null) {
                char[] l = line.toCharArray();
                for (int i = 0; i < line.length(); i++) {
                    char c = l[i];
                    if (c == 35) {
                        cases[i][ligne] = new Mur(i, ligne);
                    } else if(c == 84){
                        tresor = new Tresor(i, ligne);
                        cases[i][ligne] = tresor;
                    } else if(c == 88){
                        Teleporteur teleporteur = new Teleporteur(i, ligne);
                        cases[i][ligne] = teleporteur;
                        if(tp1 == null){
                            tp1 = teleporteur;
                        }else{
                            tp2 = teleporteur;
                        }
                    }else if(c == 77){
                        CaseMine cm = new CaseMine(i, ligne);
                        this.mines.add(cm);
                        cases[i][ligne] = cm;
                    }else if(c == 80){
                        CasePiege cp = new CasePiege(i, ligne);
                        this.pieges.add(cp);

                        cases[i][ligne] = cp;
                    }else if (c == 85){
                        PotionForce pf = new PotionForce(i, ligne);
                        this.forces.add(pf);
                        cases[i][ligne] = pf;

                    }else if (c == 86){
                        PotionVie pv = new PotionVie(i, ligne);
                        this.vies.add(pv);
                        cases[i][ligne] = pv;
                    }else if(c == 69){
                        this.etoile = new Etoile(i, ligne);
                        cases[i][ligne] = this.etoile;
                    }else if(c == 87){
                        cases[i][ligne] = new Eau(i, ligne);
                    }else if(c == 83){
                        swimmingLesson = new SwimmingLesson(i, ligne);
                        cases[i][ligne] = swimmingLesson;
                    }else{
                        cases[i][ligne] = new CaseVide(i, ligne);

                    }
                }
                ligne++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        teleporteurs.put(tp1, tp2);
        teleporteurs.put(tp2, tp1);
    }


    /**
     * Fonction qui renvoie la case située à la postion (i, j) dans le labyrinthe
     * @param i la position en x
     * @param j la position en y
     * @return la case à la position (i, j) du labyrinthe
     */
    public Case getCase(int i, int j){
        return cases[i][j];
    }


    /**
     * Procédure permettant d'afficher le labyrinthe sur un terminal
     * @param joueur le joueur
     * @param monstre le monstre
     */
    public void printLab(Joueur joueur, Monstre monstre){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(getCase(j, i).estMur()){
                    System.out.print(" # ");
                } else if (joueur.getPosX()==j && joueur.getPosY()==i) {
                    System.out.print(" & ");
                }else if(getCase(j, i).estTresor()){
                    System.out.print(" € ");
                }
                else if(monstre.getPosX() == j && monstre.getPosY() == i){
                    System.out.print(" M ");

                }else{
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
    }


    /**
     * Fonction qui indique si le joueur a gagné
     * @param joueur le joueur
     * @return true si le joueua gagné, false sinon
     */
    public boolean aGagne(Joueur joueur){
        return joueur.getPosX() == tresor.getPosX() && joueur.getPosY() == tresor.getPosY();
    }


    /**
     * Procédure qui permet de reset le labyrinthe, c'est-à-dire vider les cases
     */
    private void clearLab(){
        cases = new Case[size][size];
        teleporteurs.clear();
    }


    /**
     * Procédure qui gère le cas où le joueur est sur un téléporteur
     * @param joueur le joueur
     */
    public void isOnTp(Joueur joueur){
        for(Teleporteur tp : teleporteurs.keySet()){
            if(tp.getPosX() == joueur.getPosX() && tp.getPosY() == joueur.getPosY()){
                int x = teleporteurs.get(tp).getPosX();
                int y = teleporteurs.get(tp).getPosY();
                joueur.setPosX(x);
                joueur.setPosY(y);
                break;
            }
        }
    }

    /**
     * Procédure qui gère le cas où le joueur est sur un piège
     * @param joueur le joueur
     */
    public void trap(Joueur joueur){
        for(CasePiege piege : this.pieges){
            if(joueur.getPosX() == piege.getPosX() && joueur.getPosY() == piege.getPosY()){
                joueur.subirDegat(5);
            }
        }
    }

    /**
     * Procédure qui gère le cas où le joueur est sur une mine
     * @param joueur le joueur
     */
    public void isOnMine(Joueur joueur){
        for(CaseMine mine : this.mines){
            if(joueur.getPosX() == mine.getPosX() && joueur.getPosY() == mine.getPosY() && !mine.exploded()){
                joueur.subirDegat(10);
                mine.setExplosion(true);
            }
        }
    }

    /**
     * Procédure qui gère le cas où le joueur est sur une potion de force
     * @param joueur le joueur
     */
    public void isOnPotionForce(Joueur joueur){
        ArrayList<PotionForce> del = new ArrayList<>();
        for(PotionForce pf : this.forces){
            if(pf.getPosX() == joueur.getPosX() && joueur.getPosY() == pf.getPosY() && !pf.isRamasse()){
                joueur.setDegat(10);
                del.add(pf);
                pf.setRamasse(true);
            }
        }

        for(PotionForce pf : del){
            this.forces.remove(pf);
        }
    }

    /**
     * Procédure qui gère le cas où le joueur est sur une potion de vie
     * @param joueur le joueur
     */
    public void isOnPotionVie(Joueur joueur){
        ArrayList<PotionVie> del = new ArrayList<>();
        for(PotionVie pv : this.vies){
            if(pv.getPosX() == joueur.getPosX() && joueur.getPosY() == pv.getPosY() && !pv.isRamasse()){
                joueur.soigner(10);
                del.add(pv);
                pv.setRamasse(true);
            }
        }

        for(PotionVie pv : del){
            this.vies.remove(pv);
        }
    }

    /**
     * Procédure qui gère le cas où le joueur est sur une etoile
     * @param joueur le joueur
     */
    public void isOnEtoile(Joueur joueur){
        if(this.etoile != null){
            if(this.etoile.getPosX() == joueur.getPosX() && joueur.getPosY() == this.etoile.getPosY()){
                this.etoile.setRamasse(true);
                joueur.setInvulnerable(true);
            }
        }

    }


    /**
     * Procédure qui gère le cas où le joueur est sur le cours de natation
     * @param joueur le joueur
     */
    public void isOnSwimmingLesson(Joueur joueur) {
        if (this.swimmingLesson != null) {
            if (this.swimmingLesson.getPosX() == joueur.getPosX() && joueur.getPosY() == this.swimmingLesson.getPosY()) {
                if (System.currentTimeMillis() - tempsPasseSurSwimmingLesson >= DELAI_APPRENDRE_NAGER) {
                    joueur.setCanSwim(true);
                    JOptionPane.showMessageDialog(null, "Vous pouvez à présent nager !", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                tempsPasseSurSwimmingLesson = System.currentTimeMillis(); // Le joueur a quitté la case, réinitialiser le temps
            }
        }
    }


    /**
     * ajouter la mine à la liste des Mines
     * @param c
     */
    public void addMine(CaseMine c){
        cases[c.getPosX()][c.getPosY()] = c;
        this.mines.add(c);
    }

    /**
     * ajoute le piège  à la liste des pièges
     * @param c
     */
    public void addPiege(CasePiege c){
        cases[c.getPosX()][c.getPosY()] = c;
        this.pieges.add(c);
    }

    /**
     * ajoute la potion de vie à la liste des potions de vie
     * @param c
     */
    public void addPotionVie(PotionVie c){
        cases[c.getPosX()][c.getPosY()] = c;
        this.vies.add(c);
    }

    /**
     * ajoute la potion de force à la liste des potions de force
     * @param c
     */
    public void addPotionForce(PotionForce c){
        cases[c.getPosX()][c.getPosY()] = c;
        this.forces.add(c);
    }

    /**
     * ajoute les deux téléporteurs et les relie dans la map
     * @param c
     * @param a
     */
    public void addTp(Teleporteur c, Teleporteur a){
        cases[c.getPosX()][c.getPosY()] = c;
        cases[a.getPosX()][a.getPosY()] = a;
        this.teleporteurs.put(c, a);

    }

    /**
     * ajoute l'étoile au labyrinthe
     * @param c
     */
    public void addEtoile(Etoile c){
        cases[c.getPosX()][c.getPosY()] = c;
        this.etoile = c;
    }

}
