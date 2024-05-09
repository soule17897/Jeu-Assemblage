package model;


/**
 * Strategy pour la création de la configuration initiale d'une partie.
 */
public interface StrategieCreation {
    /**
     * Méthode pour créer la configuration initiale d'une partie.
     * @param plateau
     */
    void creerConfigurationInitiale(PlateauPuzzle plateau);
}
