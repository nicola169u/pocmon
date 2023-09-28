package main.java.com.exemple;

import java.io.*;

public class Labyrinthe {
    protected int size;
    protected Case[][] cases;

    public Labyrinthe() {
        size = 10;
        cases = new Case[size][size];
        //créer le plateau
        /*
        for (int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(i==0 || i==size-1){
                    cases[i][j] = new Mur(i, j); //Première ou dernière ligne
                }else if(j==0 || j==size-1){
                    cases[i][j] = new Mur(i, j); //Première ou dernière colonne
                }else{
                    cases[i][j] = new CaseVide(i, j); //Le reste est vide
                }
            }
        }

         */
        lire_lab();
    }

    public void lire_lab(){

        //On charge le fichier
        File file = new File("jeupocmon/src/main/resources/labyrinthe.txt");

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
                    } else {
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
    }


    public Case getCase(int i, int j){
        return cases[i][j];
    }

    public void printLab(Joueur joueur, Monstre monstre){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(getCase(j, i).estMur()){
                    System.out.print(" # ");
                } else if (joueur.getPosX()==j && joueur.getPosY()==i) {
                    System.out.print(" & ");
                }else if(monstre.getX() == j && monstre.getY() == i){
                    System.out.print(" M ");

                }else{
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
    }

    public int getSize(){
        return size;
    }
}
