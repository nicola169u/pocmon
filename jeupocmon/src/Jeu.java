import java.util.Random;
import java.util.Scanner;

public class Jeu {
    public static void main(String[] args) {
        Joueur joueur = new Joueur();
        Labyrinthe lab = new Labyrinthe();

        Monstre monstre = new Monstre(lab, joueur);

        Scanner scanner = new Scanner(System.in);


        System.out.println("Bienvenue sur Pocmon !");
        System.out.println("Voulez-vous démarrer la partie (o pour continuer) ?");

        boolean jouer;

        String response = scanner.nextLine();
        if(response.equals("o")){
            jouer = true;
        } else {
            jouer = false;
        }

        while(jouer){
            lab.printLab(joueur, monstre);
            System.out.println("Quelle est votre prochaine action (zqsd) ? (Entrez quit pour quitter la partie)");
            response = scanner.nextLine();
            Random random = new Random();
            int dirM = random.nextInt(4);
            Direction directionMonstre = monstre.getOrientation(dirM);
            monstre.avancer(directionMonstre, lab);
            if(response.equals("z") || response.equals("q") || response.equals("s") || response.equals("d")){
                joueur.avancer(joueur.choisirDirection(response), lab);

            } else if (response.equals("quit")) {
                jouer = false;
            }else{
                System.out.println("Commande non reconnue !");
            }
        }

        System.out.println("Au revoir !");

    }
}