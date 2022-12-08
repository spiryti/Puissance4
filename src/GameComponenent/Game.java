package GameComponenent;

import java.util.ArrayList;

public interface Game <State,Action> {

    /**
     *
     * @return l'état initial du jeu
     */
    public State getInitialState();
    /**
     * @param state l'état actuel
     * @return List d'actions possible en partant d'un état donné
     */
    public ArrayList<Action> getActions(State state);

    /**
     *
     * @return profondeur total du jeu
     */
    public int getDepth();

    /**
     *
     * @param state etat actuel
     * @param action action effectué
     * @return nouvel état après que l'action ait été effectué
     */
    public State getResult(State state,Action action);

    /**
     *
     * @param state état actuel
     * @return true si l'état actuel est un état final: la fin du jeu (victoire, defaite ou match nul)
     */
    public boolean isTerminal(State state);

    /**
     * Renvoie la valeur d'un état 0 si il d'agit d'un défaite, maxInt si c'est une victoire et une valeurs positive dans le reste des cas
     * @param state
     * @param player true: 1er joueur, false: Deuxième joueur
     * @return
     */
    public int getUtility(State state,boolean player);


}
