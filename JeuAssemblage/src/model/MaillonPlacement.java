package model;

/**
 * Cette interface permet de definir des methodes pour les maillons qui vont nous permettre de placer les pieces sur le plateau en utilisant le design pattern Chain of Responsibility
 *
 * Le design pattern Chain of Responsibility permet de creer une chaine de maillons qui vont traiter une requete. Chaque maillon va traiter la requete et la passer au maillon suivant.
 *
 * Dans notre cas on aura deux maillons:
 * - Un maillon qui va verifier si une piece est en collision avec une autre piece sur le plateau
 * - Un maillon qui va verifier si une piece est en dehors du plateau
 */
public abstract class MaillonPlacement {

    private MaillonPlacement suivant;

    /**
     * Methode qui va permettre de savoir si un mailon est valide ou non
     *
     * @param plateau
     * @param piece
     * @param position
     *
     * @return true si le maillon est valide, false sinon
     */
    public abstract boolean estValide(PlateauPuzzle plateau, Piece piece, Position position);

    /**
     * Methode permettant de definir le maillon suivant
     *
     * @param maillon
     *
     */
    public void setNextMaillon(MaillonPlacement maillon) {
        this.suivant = maillon;
    }

    /**
     * Methode permettant de passer la requete au maillon suivant
     *
     * @param plateau
     * @param piece
     * @param position
     *
     * @return true si le maillon est valide sinon on passe la requete au maillon suivant
     */
    public boolean next(PlateauPuzzle plateau, Piece piece, Position position) {
        if (this.suivant == null) {
            return true;
        }
        return this.suivant.estValide(plateau, piece, position);
    }



}
