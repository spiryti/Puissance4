import java.util.ArrayList;
import java.util.List;

public class Nim implements Game<Integer, Integer> {

    Integer[] hashTable;

    int _matchLeft;
    int _initialState;
    int _depthTree;

    public Nim(int nbrMatch, int depthTree) {
        hashTable = new Integer[nbrMatch];
        _matchLeft = nbrMatch;
        _initialState = nbrMatch;
        _depthTree = depthTree;
        for(int i = 0; i < nbrMatch; i++) {
            hashTable[i] = i;
        }
    }


    @Override
    public Integer getInitialState() {
        return _initialState;
    }

    @Override
    public List<Integer> getActions(Integer state) {
        List<Integer> actions = new ArrayList<>();
        if(state > 3) {
            actions.add(3);
        }
        if(state > 2) {
            actions.add(2);
        }
        if(state > 1) {
            actions.add(1);
        }
        else {
            return null;
        }
        return actions;
    }

    @Override
    public Integer getResult(Integer state, Integer action) {
        return state - action;
    }

    @Override
    public boolean isTerminal(Integer state) {

        return state == 1;
    }

    @Override
    public double getUtility(Integer state, boolean player) {
        if(player && state == 1) {
            return -1;
        }
        if(player && state > 1 && state < 4) {
            return 1;
        }
        if(!player && state > 1 && state < 4) {
            return -1;
        }
        if(!player && state == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getDepth() {
        return _depthTree;
    }

    @Override
    public void Print(Integer s) {
        System.out.println("Matches left " + s);
    }

    @Override
    public int hash(Integer s) {
        return hashTable[s-1];
    }

    @Override
    public Integer ChangePlayer(Integer s) {
        return s;
    }
}
