package main.java.com.exemple.Tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe qui gère toutes les images du jeu
 */
public class ImageManager {

    /**
     * Instance de ImageManager
     */
    private static ImageManager instance = new ImageManager();
    /**
     * Map regroupant les types de cases/persos avec leur path
     */
    private Map<String, String> imagePaths = new HashMap<>();
    /**
     * Map regroupant le path des cases/persos avec leur image
     */
    private Map<String, BufferedImage> imageCache = new HashMap<>();


    /**
     * Constructeur privé de ImageManager
     */
    private ImageManager() {
        //On ajoute tous les types d'images

        //Les cases
        imagePaths.put("Mur", "/wall.png");
        imagePaths.put("Tresor", "/tresor.png");
        imagePaths.put("Teleporteur", "/porte.png");
        imagePaths.put("Mine", "/mine.png");
        imagePaths.put("Piege", "/piege.png");
        imagePaths.put("PotionForce", "/potionForce.png");
        imagePaths.put("PotionVie", "/potionVie.png");
        imagePaths.put("Etoile", "/etoile.png");
        imagePaths.put("MineDesac", "/mineDesac.png");

        //Les personnages
        imagePaths.put("MonstreIntelligent1", "/sa1.png");
        imagePaths.put("MonstreIntelligent2", "/sa2.png");
        imagePaths.put("MonstreAleatoire1", "/s1.png");
        imagePaths.put("MonstreAleatoire2", "/s2.png");
        imagePaths.put("Fantome", "/reaper/idle_g/frame_00_delay-0.08s.gif");
        imagePaths.put("JoueurD1", "/spriteperso/d1.png");
        imagePaths.put("JoueurD2", "/spriteperso/d2.png");
        imagePaths.put("JoueurD3", "/spriteperso/d3.png");
        imagePaths.put("JoueurD4", "/spriteperso/d4.png");
        imagePaths.put("JoueurG1", "/spriteperso/g1.png");
        imagePaths.put("JoueurG2", "/spriteperso/g2.png");
        imagePaths.put("JoueurG3", "/spriteperso/g3.png");
        imagePaths.put("JoueurG4", "/spriteperso/g4.png");
        imagePaths.put("JoueurH1", "/spriteperso/h1.png");
        imagePaths.put("JoueurH2", "/spriteperso/h2.png");
        imagePaths.put("JoueurH3", "/spriteperso/h3.png");
        imagePaths.put("JoueurH4", "/spriteperso/h4.png");
        imagePaths.put("JoueurB1", "/spriteperso/b1.png");
        imagePaths.put("JoueurB2", "/spriteperso/b2.png");
        imagePaths.put("JoueurB3", "/spriteperso/b3.png");
        imagePaths.put("JoueurB4", "/spriteperso/b4.png");
        imagePaths.put("Mort", "/spriteperso/mort.png");

        //Pour le menu
        imagePaths.put("MenuBackground", "/sunset_background.jpg");
        imagePaths.put("MenuIcon", "/icon.png");

        // Ajouter d'autres types d'objets au besoin
        imagePaths.put("Default", "/sol.png"); // Chemin par défaut


        //On charge ici toutes les images
        loadImage("/wall.png");
        loadImage("/tresor.png");
        loadImage("/porte.png");
        loadImage("/mine.png");
        loadImage("/piege.png");
        loadImage("/potionForce.png");
        loadImage("/potionVie.png");
        loadImage("/etoile.png");
        loadImage("/sol.png");
        loadImage("/mineDesac.png");
        loadImage("/sa1.png");
        loadImage("/s1.png");
        loadImage("/sa2.png");
        loadImage("/s2.png");
        loadImage("/reaper/idle_g/frame_00_delay-0.08s.gif");
        loadImage("/spriteperso/d1.png");
        loadImage("/spriteperso/d2.png");
        loadImage("/spriteperso/d3.png");
        loadImage("/spriteperso/d4.png");
        loadImage("/spriteperso/g1.png");
        loadImage("/spriteperso/g2.png");
        loadImage("/spriteperso/g3.png");
        loadImage("/spriteperso/g4.png");
        loadImage("/spriteperso/h1.png");
        loadImage("/spriteperso/h2.png");
        loadImage("/spriteperso/h3.png");
        loadImage("/spriteperso/h4.png");
        loadImage("/spriteperso/b1.png");
        loadImage("/spriteperso/b2.png");
        loadImage("/spriteperso/b3.png");
        loadImage("/spriteperso/b4.png");
        loadImage("/spriteperso/mort.png");
        loadImage("/sunset_background.jpg");
        loadImage("/icon.png");
    }

    /**
     * Recupere l'instance de ImageManager
     * @return l'instance de ImageManager
     */
    public static synchronized ImageManager getInstance() {
        return instance;
    }

    /**
     * Procédure privée qui charge une image avec son path
     * @param imagePath
     */
    private void loadImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            imageCache.put(imagePath, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Getter public permettant de recuperer une image en fonction du type
     * @param imageType
     * @return
     */
    public BufferedImage getImage(String imageType) {
        //On recupere d'abord le path puis l'image
        String imagePath = imagePaths.getOrDefault(imageType, imagePaths.get("Default"));
        return imageCache.get(imagePath);
    }
}
