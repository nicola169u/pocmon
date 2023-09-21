public class Mur extends Case{
    public Mur(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public boolean estMur() {
        return true;
    }
}
