package GameComponenent;

import java.util.ArrayList;

public interface Solveur {
    public Game game;

    public Solveur(Game game){
        this.game = game;
    }
    public Action makeDecision(State state);

}