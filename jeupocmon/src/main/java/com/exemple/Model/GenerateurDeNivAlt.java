package main.java.com.exemple.Model;

import main.java.com.exemple.Model.Case.Case;
import main.java.com.exemple.Model.Case.CaseVide;
import main.java.com.exemple.Model.Case.Mur;
import main.java.com.exemple.Model.Case.Tresor;

import java.io.*;
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
            System.out.println("rand Col = "+randCol+" et randLigne = "+randLigne);
            if(!niv[randCol][randLigne].estMur() && !(randCol == 1 && randLigne == 1) && !(randCol == colonnes-2 && randLigne == lignes - 2)){
                randmur = new Mur(randCol,randLigne);
                niv[randCol][randLigne] = randmur;
                solveur();
                if(faisable) {
                    nbMurs--;
                }else{
                    niv[randCol][randLigne] = new CaseVide(randCol,randLigne);
                }
            }
            System.out.println("On a "+nbMurs+" murs à mettre pour l'instant");
        }
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
