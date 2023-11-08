package main.java.com.exemple.Model;


import main.java.com.exemple.Model.Mur;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Labyrinthe {
    private int size;
    private Case[][] cases;
    private Tresor tresor;
    private HashMap<Teleporteur, Teleporteur> teleporteurs;

    public Labyrinthe(int size) {
        this.size = size;
        cases = new Case[size][size];
        teleporteurs = new HashMap<>();
    }

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
                        }else {
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
                    }else {
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
                }else if(getCase(j, i).estTresor()){
                    System.out.print(" € ");
                }
                else if(monstre.getX() == j && monstre.getY() == i){
                    System.out.print(" M ");

                }else{
                    System.out.print("   ");
                }
            }
            System.out.println("");
        }
    }

    public boolean aGagne(Joueur joueur){
        return joueur.getPosX() == tresor.getPosX() && joueur.getPosY() == tresor.getPosY();
    }


    public int getSize(){
        return size;
    }

    private void clearLab(){
        cases = new Case[size][size];
        teleporteurs.clear();
    }

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

}
