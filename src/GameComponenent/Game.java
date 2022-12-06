package GameComponenent;

import java.util.ArrayList;

public interface Game {
    public State getInitialState();
    public ArrayList<Action> getActions(State state);
    public int getDepth();
    public State getResult(State state,Action action);
    public boolean isTerminal(State state);
    public int getUtility(State state);


}
