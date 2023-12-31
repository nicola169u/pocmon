package main.java.com.exemple.Model;


import main.java.com.exemple.Tools.Direction;

/**
 * Classe représentant un MonstreIntelligent c'est-à-dire qui cherche à atteindre le joueur
 */
public class MonstreIntelligent extends Monstre{

    /**
     * Le nombre de pas
     */
    private int nombrePas = 0;
    /**
     * La direction du MonstreIntelligent
     */
    protected Direction deplacementEnCours;


    /**
     * Constructeur de MonstreIntelligent en fonction de sa position initiale dans le labyrinthe, de ses points de vie et du nombre de dégât à infliger par attaque
     * @param x la position en x
     * @param y la position en y
     * @param pv le nombre de points de vie
     * @param degat le nombre dégât
     */
    public MonstreIntelligent(int x, int y, int pv, int degat) {
        super(x,y, pv, degat);
    }


    /**
     * Procédure qui gère le comportement d'un MonstreIntelligent
     */
    @Override
    public void comportement() {
        Labyrinthe lab = this.getLabyrinthe();
        int pxc = joueurCible.getPosX();
        int pyc = joueurCible.getPosY();

        int dx = pxc - posX;
        int dy = pyc - posY;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0 && !lab.getCase(posX + 1, posY).estMur()) {
                deplacementEnCours = Direction.DROITE;
            } else if (dx < 0 && !lab.getCase(posX - 1, posY).estMur()) {
                deplacementEnCours = Direction.GAUCHE;
            }
        } else {
            if (dy > 0 && !lab.getCase(posX, posY + 1).estMur()) {
                deplacementEnCours = Direction.BAS;
            } else if (dy < 0 && !lab.getCase(posX, posY - 1).estMur()) {
                deplacementEnCours = Direction.HAUT;
            }
        }

        // Vérifie si la prochaine case est un mur
        int nextX = posX;
        int nextY = posY;
        switch (deplacementEnCours) {
            case DROITE:
                nextX++;
                break;
            case GAUCHE:
                nextX--;
                break;
            case BAS:
                nextY++;
                break;
            case HAUT:
                nextY--;
                break;
        }

        // Si la prochaine case est un mur, trouvez un chemin alternatif
        if (lab.getCase(nextX, nextY).estMur()) {
            Direction ancienneDirection = deplacementEnCours;
            if (deplacementEnCours == Direction.DROITE || deplacementEnCours == Direction.GAUCHE) {
                deplacementEnCours = Direction.HAUT;
            } else {
                deplacementEnCours = Direction.DROITE;
            }
            if (lab.getCase(posX, posY).estMur()) {
                // Si la nouvelle direction est également bloquée, essayez une autre.
                deplacementEnCours = ancienneDirection;
            }
        }

        this.currentNbSprite = (this.currentNbSprite % this.NB_SPRITE) + 1;


        avancer(deplacementEnCours);
        attaquer();
    }

    @Override
    public String getType() {
        if(isMort()){
            return "Mort";
        }
        return "MonstreIntelligent" + currentNbSprite;
    }


    /**
     * Fonction qui indique si le MonstreIntelligent est un MonstreIntelligent
     * @return true
     */
    @Override
    public boolean isMonstreIntelligent() {
        return true;
    }
}
