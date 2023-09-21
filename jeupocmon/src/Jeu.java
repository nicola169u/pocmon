public class Jeu {

    public static void main(String[] args) {
        Joueur joueur = new Joueur();
        Labyrinthe lab = new Labyrinthe();
        lab.printLab(joueur);
    }
}
