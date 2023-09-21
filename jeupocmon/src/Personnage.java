public abstract class Personnage {
    protected int posX;
    protected int posY;

    public Personnage() {
        posX = 0;
        posY = 0;
    }

    public abstract void avancer(Direction direction, Labyrinthe lab);
}
