public abstract class Personnage {
    protected int posX;
    protected int posY;

    public Personnage() {
        posX = 5;
        posY = 5;
    }

    public abstract void avancer(Direction direction, Labyrinthe lab);
}
