package main.java.com.exemple.Model.Case;

/**
 * Classe qui représente la case de cours de natation
 */
public class SwimmingLesson extends Case{
    /**
     * Constructeur de Case en fonction de ses coordonnées posX et posY
     *
     * @param posX la position en x
     * @param posY la position en y
     */
    public SwimmingLesson(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public String getType() {
        return "SwimmingLesson";
    }
}
