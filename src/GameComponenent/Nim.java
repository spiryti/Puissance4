package GameComponenent;

import java.util.ArrayList;

public class Nim implements Game<Integer,Integer>{
    int depth=10;

    @Override
    public Integer getInitialState() {
        return 10;
    }

    @Override
    public ArrayList<Integer> getActions(Integer state) {
        ArrayList<Integer> actions=new ArrayList<>();
        if(state>=2){
            actions.add(1);
        }
        if(state>=3){
            actions.add(2);
        }
        if(state>=4){
            actions.add(3);
        }
        return actions;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public Integer getResult(Integer state, Integer action,boolean joueur) {
        depth-=1;
        return state-action;
    }

    @Override
    public boolean isTerminal(Integer state) {
        return state==1;
    }

    @Override
    public int getUtility(Integer state, boolean player) {
        if(isTerminal(state)){
            if(player){
                return 1;
            }
            else{
                return -1;
            }
        }
        return 0;
    }
}
