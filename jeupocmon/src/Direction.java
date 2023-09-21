public enum Direction {
    HAUT, BAS, GAUCHE, DROITE;

    @Override
    public String toString() {
        switch (this) {
            case HAUT:
                return "Haut";
            case BAS:
                return "Bas";
            case GAUCHE:
                return "Gauche";
            case DROITE:
                return "Droite";
            default:
                throw new IllegalArgumentException();
        }
    }
}


