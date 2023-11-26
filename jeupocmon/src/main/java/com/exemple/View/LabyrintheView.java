package main.java.com.exemple.View;

/**
 * Classe qui représente la vue du labyrinthe
 * Plus précisement, elle regroupe les vues de toutes les cases du labyrinthe
 */
public class LabyrintheView{
    /**
     * Liste des vues des cases du labyrinthe
     */
    private CaseView[][] casesView;

    /**
     * Constructeur de LabyrintheView en fonction de la taille du labyrinthe
     * @param size
     */
    public LabyrintheView(int size) {
        casesView = new CaseView[size][size];
    }


    /**
     * Setter de la case c à la position (i, j) du labyrinthe
     * @param i
     * @param j
     * @param c
     */
    public void setCase(int i, int j, CaseView c){
        casesView[i][j] = c;
    }


    /**
     * Getter de la case à la position (i, j)
     * @param i
     * @param j
     * @return la case à la position (i, j) du labyrinthe
     */
    public CaseView getCase(int i, int j){
        return casesView[i][j];
    }
}
