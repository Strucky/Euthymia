package server.game.players.action;

public class Action {

    private boolean cancellable = true;
    private Runnable executetable = null;
    private ExitCondition exitCondition = null;

    public Action(Runnable action) {
        this.executetable = action;
    }

    public Action(boolean cancellable, ExitCondition exitCondition, Runnable executetable) {
        this.cancellable = cancellable;
        this.executetable = executetable;
        this.exitCondition = exitCondition;
    }

    public void makeCancellable() {
        cancellable = true;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public Runnable getExecutable() {
        return executetable;
    }

    public ExitCondition getExitCondition() {
        return exitCondition;
    }
}
