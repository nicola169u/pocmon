package main.java.com.exemple.Model;

public class MonstreIntelligent extends Monstre{

    private int nombrePas = 0;
    protected Direction deplacementEnCours;



    public MonstreIntelligent(int x, int y, int pv, int degat) {
        super(x,y, pv, degat);
    }

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

        avancer(deplacementEnCours);
        attaquer();
    }





}
