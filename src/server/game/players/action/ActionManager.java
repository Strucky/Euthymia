package server.game.players.action;

public class ActionManager {

    private Action currentAction = null;

    public void executeAction(Action action) {
        checkExitCondition();
        if (canRunAction()) {
            //System.out.println("Executing");
            currentAction = action;
            currentAction.getExecutable().run();
        }
    }

    private void checkExitCondition() {
        if (currentAction != null && currentAction.getExitCondition() != null && currentAction.getExitCondition().shouldExit()) {
            currentAction.makeCancellable();
        }
    }

    private boolean canRunAction() {
        if ((currentAction != null && currentAction.isCancellable()) ||currentAction == null){
            return true;
        }
        return false;
    }

}
