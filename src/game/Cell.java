package game;

/**
 * Created by Mateusz on 14.01.2017.
 */
public class Cell implements Cloneable{

    private boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public Cell clone() {
        return new Cell(alive);
    }

    public void changeState(int neighboursCount){
        if(alive)
            commitConditionForDie(neighboursCount);
        else
           commitConditionForRevive(neighboursCount);
    }

    private void commitConditionForDie(int neighboursCount){
        if(neighboursCount<2||neighboursCount>3)
            alive = false;
    }

    private void commitConditionForRevive(int neighboursCount){
        if(neighboursCount==3)
            alive = true;
    }
}
