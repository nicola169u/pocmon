import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jeu {
    public static void main(String[] args) {
        Joueur joueur = new Joueur();
        Labyrinthe lab = new Labyrinthe();
        System.out.println("Bienvenu sur pocmon/nAppuyer sur entree pour démarer le jeu ");
        InputStreamReader inputStream = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        System.out.println("Voulez-vous demarer la partie");
        String test = null;
        try {
            test = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (test.equals("o"))
        {
            lab.printLab(joueur);
            inputStream = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStream);
            System.out.println("Quelle est votre prochaine action (zqsd)");
            try {
                test = bufferedReader.readLine();
                joueur.avancer(joueur.choisirDirection(test),lab);
            } catch (IOException e) {
                e.printStackTrace();
            }inputStream = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStream);
            System.out.println("Voulez vous continuer la partie ?");
            try {
                test = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
