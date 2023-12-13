package main.java.com.exemple.Model;

import main.java.com.exemple.Model.Case.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateurDeNivAlt {
    private int colonnes , lignes , nbMurs; //dimension de notre niveau
    private Case[][] niv; //notre niveau qui va être generé
    private boolean[][] visite; //tableau nous permettant de vérifier que le niveau est faisable

    private boolean faisable;

    public GenerateurDeNivAlt(int murs){
        //pour nous facilité la tâche, on va limiter le nombre de murs max à 100
        this.nbMurs = (murs > 100) ? 100 : murs;
        this.colonnes = 20;
        this.lignes = 20;
        this.niv = new Case[colonnes][lignes];
        this.visite = new boolean[colonnes][lignes];
        this.faisable = false;
    }

    public void init(){
        //nous allons déjà placer les murs extérieus
        for(int colonne = 0 ; colonne < colonnes ; colonne++){
            niv[colonne][0] = new Mur(colonne,0);
            niv[colonne][lignes-1] = new Mur(colonne,lignes-1);
            visite[colonne][0] = true;
            visite[colonne][lignes-1] = true;
        }
        for(int ligne = 1 ; ligne < lignes-1 ; ligne++){
            niv[0][ligne] = new Mur(0,ligne);
            niv[colonnes-1][ligne] = new Mur(colonnes-1,ligne);
            visite[0][ligne] = true;
            visite[colonnes - 1][ligne] = true;
        }

        //et maintenant on rempli de case vide au cas ou notre niveau est faissable dés le début
        for(int j = 1 ; j < colonnes - 1 ; j++){
            for(int h = 1 ; h < lignes - 1 ; h++){
                niv[j][h] = new CaseVide(j,h);
            }
        }

        //enfin on met le tresor (pour l'instant) en (colonnes - 2,lignes - 2)
        niv[colonnes-2][lignes-2] = new Tresor(colonnes - 2 , lignes - 2);
    }

    //Fonction qui permet de savoir si notre niv est faisable (si on peut aller au trésor)
    public void solveur(int col , int ligne){
        if(faisable) return;
        if(visite[col][ligne]) return;
        visite[col][ligne] = true;

        if(niv[col][ligne].estTresor()){
            faisable = true;
            return;
        }
        solveur(col+1,ligne);
        solveur(col , ligne + 1);
        solveur(col - 1 , ligne);
        solveur(col , ligne - 1);
    }

    public void solveur(){
        for(int i = 1 ; i < colonnes - 1 ; i++){
            for(int j = 1 ; j < lignes - 1 ; j++){
                visite[i][j] = false;
            }
        }
        solveur(1,1);
    }

    public void nivCorrecte(){
        init();
        //On va maintenant, faire créer un niveau faisable
        Random random = new Random();
        int randCol, randLigne;
        Mur randmur;
        while(nbMurs !=0){
            randCol = random.nextInt(colonnes-2) + 1;
            randLigne = random.nextInt(lignes-2) + 1;
            if(!niv[randCol][randLigne].estMur() && !(randCol == 1 && randLigne == 1) && !(randCol == colonnes-2 && randLigne == lignes - 2)) {
                randmur = new Mur(randCol, randLigne);
                niv[randCol][randLigne] = randmur;
                solveur();
                if (faisable) {
                    nbMurs--;
                } else {
                    niv[randCol][randLigne] = new CaseVide(randCol, randLigne);
                }
            }
        }
        ajouterEtoile(1,random);
        ajouterPotionVie(1,random);
        ajouterPiege(1,random);
        ajouterTeleporteur(random);
        ajouterPotionForce(1,random);
        //Une fois qu'on a notre niveau correcte, on va l'écrire dans un fichier
        //Déjà on compte combien on a de niveau dispo pour les ajouter en plus
        int nbNivAct = getNbNiveau() + 1;
        try {
            String pathname = "jeupocmon//src//main//resources//niv" + nbNivAct + ".txt";
            System.out.println(pathname);
            File file = new File(pathname);
            if (!file.exists()) {
                System.out.println("le fichier n'existe pas");
                file.createNewFile();
                System.out.println("le fichier est créer");
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0 ; i < colonnes ; i++){
                for(int j = 0 ; j < lignes ; j++){
                    if(niv[i][j].estMur()){
                        bw.write(35);
                    }
                    else if(niv[i][j].estTresor()){
                        bw.write(84);
                    }
                    else if(niv[i][j].estTeleporteur()){
                        bw.write(88);
                    }
                    else if(niv[i][j].estEtoile()){
                        bw.write(69);
                    }
                    else if(niv[i][j].estPiege()){
                        bw.write(80);
                    }
                    else if(niv[i][j].estMine()){
                        bw.write(77);
                    }
                    else if(niv[i][j].estPotionForce()){
                        bw.write(85);
                    }
                    else if(niv[i][j].estPotionVie()){
                        bw.write(86);
                    }
                }
                bw.newLine();
            }
            bw.close();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    //Fonction qui retourne le nombre de niveau dans notre dossier ressources
    public int getNbNiveau() {
        File dossier = new File("jeupocmon/src/main/resources");

        FilenameFilter filtre = new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.startsWith("niv");
            }
        };

        File[] fichiers = dossier.listFiles(filtre);
        return fichiers.length;
    }

    public void printLab(){
        for(int i=0; i<colonnes; i++){
            for(int j=0; j<lignes; j++){
                if(niv[i][j].estMur()) {
                    System.out.print(" # ");
                }else if(niv[i][j].estTresor()){
                    System.out.print(" € ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
    }

    public void ajouterMines(int nbMine , Random rand){
        int xMines = rand.nextInt(colonnes-2) + 1;
        int yMines = rand.nextInt(lignes-2) + 1;
        while(nbMine != 0){
            if(niv[xMines][yMines].estCaseVide() && !(xMines == 1 && yMines == 1) && !(xMines == colonnes-2 && yMines == lignes - 2)){
                CaseMine mine = new CaseMine(xMines,yMines);
                niv[xMines][yMines] = mine;
                ajouterMines(nbMine-1 , rand);
            }
        }
    }

    public void ajouterPiege(int nbPiege , Random rand){
        int xPiege = rand.nextInt(colonnes-2) + 1;
        int yPiege = rand.nextInt(lignes-2) + 1;
        while(nbPiege != 0){
            if(niv[xPiege][yPiege].estCaseVide() && !(xPiege == 1 && yPiege == 1) && !(xPiege == colonnes-2 && yPiege == lignes - 2)){
                CasePiege piege = new CasePiege(xPiege,yPiege);
                niv[xPiege][yPiege] = piege;
                ajouterPiege(nbPiege-1 , rand);
            }
        }
    }

    public void ajouterPotionForce(int nbPotionForce , Random rand){
        int xPotionForce = rand.nextInt(colonnes-2) + 1;
        int yPotionForce = rand.nextInt(lignes-2) + 1;
        while(nbPotionForce != 0){
            if(niv[xPotionForce][yPotionForce].estCaseVide() && !(xPotionForce == 1 && yPotionForce == 1) && !(xPotionForce == colonnes-2 && yPotionForce == lignes - 2)){
                PotionForce potionForce = new PotionForce(xPotionForce,yPotionForce);
                niv[xPotionForce][yPotionForce] = potionForce;
                ajouterPotionForce(nbPotionForce-1 , rand);
            }
        }
    }

    public void ajouterPotionVie(int nbPotionVie , Random rand){
        int xPotionVie = rand.nextInt(colonnes-2) + 1;
        int yPotionVie = rand.nextInt(lignes-2) + 1;
        while(nbPotionVie != 0){
            if(niv[xPotionVie][yPotionVie].estCaseVide() && !(xPotionVie == 1 && yPotionVie == 1) && !(xPotionVie == colonnes-2 && yPotionVie == lignes - 2)){
                PotionVie potionVie = new PotionVie(xPotionVie,yPotionVie);
                niv[xPotionVie][yPotionVie] = potionVie;
                ajouterPotionVie(nbPotionVie-1 , rand);
            }
        }
    }

    public void ajouterTeleporteur(Random rand){
        int xTp1 = rand.nextInt(colonnes-2) + 1;
        int yTp1 = rand.nextInt(lignes-2) + 1;
        Boolean Tp1 = false;
        Boolean Tp2 = false;
        while(Tp1 == false){
            if(niv[xTp1][yTp1].estCaseVide() && !(xTp1 == 1 && yTp1 == 1) && !(xTp1 == colonnes-2 && yTp1 == lignes - 2)){
                Teleporteur tp1 = new Teleporteur(xTp1,yTp1);
                niv[xTp1][yTp1] = tp1;
                Tp1 = true;
            }
        }
        int xTp2 = rand.nextInt(colonnes-2) + 1;
        int yTp2 = rand.nextInt(lignes-2) + 1;
        while(Tp2 == false){
            if(niv[xTp2][yTp2].estCaseVide() && !(xTp2 == 1 && yTp2 == 1) && !(xTp2 == colonnes-2 && yTp2 == lignes - 2)){
                Teleporteur tp2 = new Teleporteur(xTp2,yTp2);
                niv[xTp2][yTp2] = tp2;
                Tp2 = true;
            }
        }
    }

    public void ajouterEtoile(int nbEtoile , Random rand){
        int xEtoile = rand.nextInt(colonnes-2) + 1;
        int yEtoile = rand.nextInt(lignes-2) + 1;
        while(nbEtoile != 0){
            if(niv[xEtoile][yEtoile].estCaseVide() && !(xEtoile == 1 && yEtoile == 1) && !(xEtoile == colonnes-2 && yEtoile == lignes - 2)){
                Etoile Etoile = new Etoile(xEtoile,yEtoile);
                niv[xEtoile][yEtoile] = Etoile;
                ajouterEtoile(nbEtoile-1 , rand);
            }
        }
    }

    public boolean getFaisable(){
        return faisable;
    }

    public int getColonnes() {
        return colonnes;
    }

    public int getLignes() {
        return lignes;
    }

    public boolean[][] getVisite() {
        return visite;
    }
}
