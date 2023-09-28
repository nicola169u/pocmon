import java.util.Random;
import java.util.Scanner;

public class Jeu {
    public static void main(String[] args) {
        Joueur joueur = new Joueur();
        Labyrinthe lab = new Labyrinthe();
        Random rand = new Random();
        int x = rand.nextInt(lab.size);
        int y = rand.nextInt(lab.size);
        while(lab.getCase(x,y).estMur() || (joueur.getPosX() == x && joueur.getPosY() == y)){
            x = rand.nextInt(lab.size);
            y = rand.nextInt(lab.size);
        }
        Monstre monstre = new Monstre(x,y);

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
            Direction directionMonstre = monstre.getOrientation(random.nextInt(4));
            if(response.equals("z") || response.equals("q") || response.equals("s") || response.equals("d")){
                joueur.avancer(joueur.choisirDirection(response), lab);
                monstre.avancer(directionMonstre, lab);
            } else if (response.equals("quit")) {
                jouer = false;
            }else{
                System.out.println("Commande non reconnue !");
            }
        }

        System.out.println("Au revoir !");

    }
}