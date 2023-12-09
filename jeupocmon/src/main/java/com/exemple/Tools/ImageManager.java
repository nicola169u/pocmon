package main.java.com.exemple.Tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe qui gère toutes les images du jeu
 * Pour ajouter une nouvelle image :
 *      ajouter dans le constructeur de ImageManager votre image en faisant addToManager(typeImage, pathImage)
 *      avec typeImage le type de l'image (ex: Fantome pour un fantome) et pathImage le chemin vers l'image (on part du module resources)
 * Pour trouver le type d'image à afficher, il suffit d'appeler la fonction getType() sur une case ou un personnage
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
        addToManager("Mur", "/wall.png");
        addToManager("Tresor", "/tresor.png");
        addToManager("Teleporteur", "/porte.png");
        addToManager("Mine", "/mine.png");
        addToManager("Piege", "/piege.png");
        addToManager("PotionForce", "/potionForce.png");
        addToManager("PotionVie", "/potionVie.png");
        addToManager("Etoile", "/etoile.png");
        addToManager("MineDesac", "/mineDesac.png");
        addToManager("Eau", "/water.jpg");
        addToManager("SwimmingLesson", "/piscine.png");

        //Les personnages
        addToManager("MonstreIntelligent1", "/sa1.png");
        addToManager("MonstreIntelligent2", "/sa2.png");
        addToManager("MonstreAleatoire1", "/s1.png");
        addToManager("MonstreAleatoire2", "/s2.png");
        addToManager("Fantome", "/reaper/idle_g/frame_00_delay-0.08s.gif");
        addToManager("JoueurD1", "/spriteperso/d1.png");
        addToManager("JoueurD2", "/spriteperso/d2.png");
        addToManager("JoueurD3", "/spriteperso/d3.png");
        addToManager("JoueurD4", "/spriteperso/d4.png");
        addToManager("JoueurG1", "/spriteperso/g1.png");
        addToManager("JoueurG2", "/spriteperso/g2.png");
        addToManager("JoueurG3", "/spriteperso/g3.png");
        addToManager("JoueurG4", "/spriteperso/g4.png");
        addToManager("JoueurH1", "/spriteperso/h1.png");
        addToManager("JoueurH2", "/spriteperso/h2.png");
        addToManager("JoueurH3", "/spriteperso/h3.png");
        addToManager("JoueurH4", "/spriteperso/h4.png");
        addToManager("JoueurB1", "/spriteperso/b1.png");
        addToManager("JoueurB2", "/spriteperso/b2.png");
        addToManager("JoueurB3", "/spriteperso/b3.png");
        addToManager("JoueurB4", "/spriteperso/b4.png");
        addToManager("Mort", "/spriteperso/mort.png");
        //Pour les eppee
        addToManager("Epee_0G", "/spriteperso/epee0g.png");
        addToManager("Epee_0D", "/spriteperso/epee0d.png");
        addToManager("Epee_0B", "/spriteperso/epee0b.png");
        addToManager("Epee_0H", "/spriteperso/epee0h.png");
        addToManager("Epee_1G", "/spriteperso/epee1g.png");
        addToManager("Epee_1D", "/spriteperso/epee1d.png");
        addToManager("Epee_1B", "/spriteperso/epee1b.png");
        addToManager("Epee_1H", "/spriteperso/epee1h.png");
        addToManager("Epee_2G", "/spriteperso/epee2g.png");
        addToManager("Epee_2D", "/spriteperso/epee2d.png");
        addToManager("Epee_2B", "/spriteperso/epee2b.png");
        addToManager("Epee_2H", "/spriteperso/epee2h.png");
        addToManager("Epee_3G", "/spriteperso/epee3g.png");
        addToManager("Epee_3D", "/spriteperso/epee3d.png");
        addToManager("Epee_3B", "/spriteperso/epee3b.png");
        addToManager("Epee_3H", "/spriteperso/epee3h.png");

        //Pour le menu
        addToManager("MenuBackground", "/sunset_background.jpg");
        addToManager("MenuIcon", "/icon.png");
        addToManager("ShopIcon","/shop_s.png");
        addToManager("SettingsIcon", "/settings.png");
        addToManager("ShopBackground","/shop_background.png");

        // Ajouter d'autres types d'objets au besoin
        addToManager("Default", "/sol.png"); // Chemin par défaut
    }

    private void addToManager(String type, String path){
        imagePaths.put(type, path);
        loadImage(path);
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
