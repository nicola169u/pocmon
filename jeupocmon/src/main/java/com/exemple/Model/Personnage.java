package main.java.com.exemple.Model;


import main.java.com.exemple.Model.Case.Case;
import main.java.com.exemple.Tools.Direction;

import javax.swing.*;

/**
 * Classe représentant un Personnage
 */
public abstract class Personnage {
    /**
     * Position en x du personnage dans le labyrinthe
     */
    protected int posX;
    /**
     * Position en y du personnage dans le labyrinthe
     */
    protected int posY;
    /**
     * Le labyrinthe
     */
    private Labyrinthe labyrinthe;
    /**
     * Variable qu indique si le personnage est vivant
     */
    protected boolean vivant;
    /**
     * Variable qui indique les points de vie restant du Personnage
     */
    private int pv;
    /**
     * Variable qui indique la vitesse de déplacement du Personnage
     */
    private int vitesse;
    /**
     * Variable qui indique le nombre max de points de vie du Personnage
     */
    private int pvMax;
    /**
     * Variable qui indique si le Personnage est invulnérable
     */
    private boolean invulnerable;
    /**
     * Derniere direction prise par le joueur
     */
    private Direction dernDirection;

    /**
     * Attribut qui sert comme compteur pour l'invulnérabilité du personnage
     */
    private int compteur;

    /**
     * Variable qui indique si le joueur est en train d'attaquer
     */
    protected boolean attaque;


    /**
     * Constructeur de Personnage en fonction de sa position initiale et de ses points de vie
     * @param x la position en x
     * @param y la position en y
     * @param pv le nombre de points de vie
     */
    public Personnage(int x, int y, int pv) {
        this.posX = x;
        this.posY = y;
        this.vivant = true;
        this.pv = pv;
        this.invulnerable = false;
        this.pvMax = pv;
        this.dernDirection = Direction.DROITE;
        this.compteur = 0;
    }


    /**
     * Procédure qui gère le déplacement du Personnage dans le labyrinthe en fonction de la direction prise
     * @param direction la direction
     */
    public void avancer(Direction direction){
        dernDirection = direction;
        Case caseToGo = null;
        switch (direction){
            case GAUCHE:
                caseToGo = labyrinthe.getCase(this.posX - 1, this.posY);
                if(!caseToGo.estMur() && vivant){
                    //On teste si c'est de l'eau et que le joueur peut nager
                    if(!caseToGo.estEau() || this.canSwim()) {
                        this.posX--;
                    }else{
                        JOptionPane.showMessageDialog(null, "Vous devez d'abord apprendre à nager !", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else if(!this.estMonstre()){
                    //JOptionPane.showMessageDialog(null, "On fonce droit dans un mur inconscient !!", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case DROITE:
                caseToGo = labyrinthe.getCase(this.posX + 1, this.posY);
                if(!caseToGo.estMur() && vivant) {
                    //On teste si c'est de l'eau et que le joueur peut nager
                    if (!caseToGo.estEau() || this.canSwim()) {
                        this.posX++;
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous devez d'abord apprendre à nager !", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else if(!this.estMonstre()){
                    //JOptionPane.showMessageDialog(null, "On fonce droit dans un mur inconscient !!", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case BAS:
                caseToGo = labyrinthe.getCase(this.posX, this.posY + 1);
                if(!caseToGo.estMur() && vivant) {
                    //On teste si c'est de l'eau et que le joueur peut nager
                    if (!caseToGo.estEau() || this.canSwim()) {
                        this.posY++;
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous devez d'abord apprendre à nager !", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else if(!this.estMonstre()){
                    //JOptionPane.showMessageDialog(null, "On fonce droit dans un mur inconscient !!", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case HAUT:
                caseToGo = labyrinthe.getCase(this.posX, this.posY - 1);
                if(!caseToGo.estMur() && vivant) {
                    //On teste si c'est de l'eau et que le joueur peut nager
                    if (!caseToGo.estEau() || this.canSwim()) {
                        this.posY--;
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous devez d'abord apprendre à nager !", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else if(!this.estMonstre()){
                    //JOptionPane.showMessageDialog(null, "On fonce droit dans un mur inconscient !!", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
        }
        if(this.isInvulnerable()){
            this.compteur++;
        }
    }

    /**
     * Fonction qui indique si le Personnage est vivant ou non
     * @return true si le Personnage est vivant, false sinon
     */
    public boolean isVivant(){
        return this.vivant;
    }


    /**
     * Fonction qui indique si le Personnage est mort
     * @return true si le Personnage est vivant, false sinon
     */
    public boolean isMort(){
        return !vivant;
    }


    /**
     * Setter du labyrinthe
     * @param labyrinthe1 le nouveau labyrinthe
     */
    public void setLabyrinthe(Labyrinthe labyrinthe1){
        if(labyrinthe1 != null){
            this.labyrinthe = labyrinthe1;
        }
    }

    /**
     * Getter du labyrinthe
     * @return le labyrinthe
     */
    public Labyrinthe getLabyrinthe(){
        return this.labyrinthe;
    }


    /**
     * Fonction qui indique si le Personnage est un monstre
     * @return false
     */
    public boolean estMonstre(){
        return false;
    }

    /**
     * Fonction qui indique si le Personnage est un Fantome
     * @return false
     */
    public boolean estFantome(){
        return false;
    }

    /**
     * Fonction qui indique si le Personnage est un MonstreAleatoire
     * @return false
     */
    public boolean isMonstreAleatoire(){return false;}


    /**
     * Fonction qui indique si le Personnage est un MonstreIntelligent
     * @return false
     */
    public boolean isMonstreIntelligent(){return false;}

    /**
     * Getter de la position x dans le labyrinthe du Personnage
     * @return la position x du Personnage dans le labyrinthe
     */
    public int getPosX() {
        return posX;
    }


    /**
     * Setter de la position x dans le labyrinthe du Personnage
     * @param posX la position en x
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }


    /**
     * Getter de la position y dans le labyrinthe du Personnage
     * @return la position y du Personnage dans le labyrinthe
     */
    public int getPosY() {
        return posY;
    }


    /**
     * Setter de la position y dans le labyrinthe du Personnage
     * @param posY la position en y
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }


    /**
     * Fonction qui retourne la distance entre 2 Personnage
     * @param perso le personnage
     * @return la distance entre 2 Personnage
     */
    public double distancePerso(Personnage perso) {
        return Math.sqrt(Math.pow((this.posX-perso.posX),2)+Math.pow((this.posY-perso.posY),2));

    }


    /**
     * Fonction qui retourne la distance entre le Personnage et une entité à des coordonnées (px, py) et de la taille (dx, dy)
     * @param px la positione en x
     * @param py la position en y
     * @param dx la largeur
     * @param dy la hauteur
     * @return
     */
    public double distanceEntite(int px, int py, int dx, int dy) {
        return Math.sqrt(Math.pow((px - dx),2)+Math.pow((py - dy),2));

    }


    /**
     * Procédure qui gère la prise de dégâts
     * @param degat le nombre de dégât
     */
    public void subirDegat(int degat){
        if(!invulnerable){
            pv -= degat;
            if(pv < 0){
                pv = 0;
            }
            mort();
        }
    }


    /**
     * Getter du nombre de points de vie du Personnage
     * @return le nombre de points de vie du Personnage
     */
    public int getPv() {
        return pv;
    }


    /**
     * Setter du nombre de points de vie
     * @param pv le nombre de points de vie
     */
    public void setPv(int pv){
        this.pv = pv;
        if(pv > pvMax){
            this.pvMax = pv;
        }
    }


    /**
     * Met à jour la mort du Personnage en fonction du nombre de points de vie restant
     */
    public void mort(){
        if(pv==0){
            vivant = false;
        }
    }

    /**
     * Setter de la vitesse du Personnage
     * @param vitesse la vitesse du Personnage
     */
    public void setVitesse(int vitesse){
        this.vitesse = vitesse;
    }


    /**
     * Getter de la vitesse du Personnage
     * @return la vitesse du Personnage
     */
    public int getVitesse(){
        return vitesse;
    }


    /**
     * Procédure qui soigne un Personnage en lui redonnant soin points de vie
     * @param soin le nombre de points de vie à récupérer
     */
    public void soigner(int soin){
        pv += soin;
        if(this.pv > this.pvMax) {
            this.pv = pvMax;
        }
        mort();

    }

    /**
     * Getter du nombre maximal de points de vie du Personnage
     * @return le nombre maximal de points de vie du Personnage
     */
    public int getPvMax() {
        return this.pvMax;
    }


    /**
     * Setter de l'invulnerabilité du Personnage
     * @param a l'invulnérabilité, de type booléen
     */
    public void setInvulnerable(boolean a)
    {
        this.invulnerable = a;
    }

    public boolean isInvulnerable() {
        if(this.compteur > 10){
            this.invulnerable = false;
        }
        return invulnerable;
    }

    /**
     * Getter de la dernière direction prise par le perso
     * @return la dernière direction prise par le perso
     */
    public Direction getDernDirection() {
        return dernDirection;
    }

    public abstract String getType();

    public int getCompteur(){
        return compteur;
    }

    public boolean getAttaque(){
        return attaque;
    }

    public boolean canSwim(){
        return true;
    }


}