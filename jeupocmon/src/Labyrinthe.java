public class Labyrinthe {
    protected int size;
    protected Case[][] cases;

    public Labyrinthe() {
        size = 10;
        cases = new Case[size][size];
        //créer le plateau
        for (int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(i==0 || i==size-1){
                    cases[i][j] = new Mur(i, j); //Première ou dernière ligne
                }else if(j==0 || j==size-1){
                    cases[i][j] = new Mur(i, j); //Première ou dernière colonne
                }else{
                    cases[i][j] = new CaseVide(i, j); //Le reste est vide
                }
            }
        }
    }

    public Case getCase(int i, int j){
        return cases[i][j];
    }

    public void printLab(Joueur joueur){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(getCase(i, j).estMur()){
                    System.out.print("#");
                } else if (joueur.getPosX()==i && joueur.getPosY()==j) {
                    System.out.print("&");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public int getSize(){
        return this.size;
    }
}
