package server.game.players.content.dialogue;

import java.util.ArrayDeque;
import java.util.Queue;

public class Dialogue {

    public Queue<Runnable> dialogues = new ArrayDeque<>();
    public Runnable handleFirstOption = () -> {};
    public Runnable handleSecondOption = () -> {};
    public Runnable handleThirdOption = () -> {};
    public Runnable handleFourthOption = () -> {};
    public Runnable handleFifthOption = () -> {};
    public Runnable handleFinishAction= () -> {};

}
