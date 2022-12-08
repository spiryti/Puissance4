package GameComponenent;

public interface Solveur<State,Action> {
        Action makeDecision(State state);

}