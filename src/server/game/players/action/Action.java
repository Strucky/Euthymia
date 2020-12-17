package server.game.players.action;

import server.Server;
import server.event.Task;

import java.util.ArrayList;

public class Action {

    private boolean cancellable = true;
    private Runnable action = null;
    private ExitCondition exitCondition = null;

    public Action(Runnable action) {this.action=action; }

    public Action(boolean cancellable, ExitCondition exitCondition, Runnable action) {
        this.cancellable = cancellable;
        this.action=action;
        this.exitCondition = exitCondition;
    }

    public void makeCancellable() {
        cancellable = true;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public Runnable getAction() {
        return action;
    }

    public ExitCondition getExitCondition () {
        return exitCondition;
    }
}
