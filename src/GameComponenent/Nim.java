package GameComponenent;

import java.util.ArrayList;

public class Nim implements Game<Integer,Integer>{
    int depth=11;

    @Override
    public Integer getInitialState() {
        return 11;
    }

    @Override
    public ArrayList<Integer> getActions(Integer state) {
        ArrayList<Integer> actions=new ArrayList<>();
        if(state>1){
            actions.add(1);
        }
        if(state>2){
            actions.add(2);
        }
        if(state>3){
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
        //System.out.println("is terminal"+ state);
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
