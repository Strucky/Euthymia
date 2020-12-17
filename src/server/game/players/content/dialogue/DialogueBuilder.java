package server.game.players.content.dialogue;

import server.game.players.Client;

public class DialogueBuilder {

    private Client player;

    public DialogueBuilder(Client player) {
        this.player = player;
        dialogue.handleFinishAction = () -> {
            player.getDH().setInDialogue(false);
            player.getPA().closeAllWindows();
        };
    }

    private Dialogue dialogue = new Dialogue();

    //region builder
    public DialogueBuilder setDialogueFinishAction(Runnable runnable) {
        dialogue.handleFinishAction = runnable;
        return this;
    }

    public DialogueBuilder setFirstOptionAction(Runnable runnable) {
        dialogue.handleFirstOption = runnable;
        return this;
    }

    public DialogueBuilder setSecondOptionAction(Runnable runnable) {
        dialogue.handleSecondOption = runnable;
        return this;
    }

    public DialogueBuilder setThirdOptionAction(Runnable runnable) {
        dialogue.handleThirdOption = runnable;
        return this;
    }

    public DialogueBuilder setFourthOptionAction(Runnable runnable) {
        dialogue.handleFourthOption = runnable;
        return this;
    }

    public DialogueBuilder setFifthOptionAction(Runnable runnable) {
        dialogue.handleFifthOption = runnable;
        return this;
    }

    public DialogueBuilder sendItemChat1(String header, String one, int item, int zoom) {
        dialogue.dialogues.add(() -> {
            player.getDH().sendItemChat1(header, one, item, zoom);
        });
        return this;
    }

    public DialogueBuilder sendItemChat2(String header, String one, String two, int item,
                                         int zoom) {
        dialogue.dialogues.add(() ->
                player.getDH().sendItemChat2(header, one, two, item, zoom)
        );
        return this;
    }

    public DialogueBuilder sendItemChat3(String header, String one, String two,
                                         String three, int item, int zoom) {
        dialogue.dialogues.add(() ->
            player.getDH().sendItemChat3(header, one, two, three, item, zoom)
        );
        return this;
    }

    public DialogueBuilder sendItemChat4(String header, String one, String two,
                                         String three, String four, int item, int zoom) {
        dialogue.dialogues.add(() ->
            player.getDH().sendItemChat4(header, one, two, three, four, item, zoom)
        );
        return this;
    }

    public DialogueBuilder sendOption(String s) {
        dialogue.dialogues.add(() ->
            player.getDH().sendOption(s)
        );
        return this;
    }

    public DialogueBuilder sendOption2(String s, String s1) {
        dialogue.dialogues.add(() ->
            player.getDH().sendOption2(s, s1)
        );
        return this;
    }

    public DialogueBuilder sendOption3(String s, String s1, String s2) {
        dialogue.dialogues.add(() ->
            player.getDH().sendOption3(s, s1, s2)
        );
        return this;
    }

    public DialogueBuilder sendOption4(String s, String s1, String s2, String s3) {
        dialogue.dialogues.add(() ->
            player.getDH().sendOption4(s, s1, s2, s3)
        );
        return this;
    }

    public DialogueBuilder sendOption5(String s, String s1, String s2, String s3, String s4) {
        dialogue.dialogues.add(() ->
            player.getDH().sendOption5(s, s1, s2, s3, s4)
        );
        return this;
    }

    public DialogueBuilder sendNpcChat1(String s, int ChatNpc, String name, int animationId) {
        dialogue.dialogues.add(() ->
            player.getDH().sendNpcChat1(s, ChatNpc, name, animationId)
        );
        return this;
    }

    public DialogueBuilder sendNpcChat2(String s, String s1, int ChatNpc, String name, int animationId) {
        dialogue.dialogues.add(() ->
            player.getDH().sendNpcChat2(s, s1, ChatNpc, name, animationId)
        );
        return this;
    }

    public DialogueBuilder sendNpcChat3(String s, String s1, String s2, int ChatNpc,
                                        String name, int animationId) {
        dialogue.dialogues.add(() ->
            player.getDH().sendNpcChat3(s, s1, s2, ChatNpc, name, animationId)
        );
        return this;
    }

    public DialogueBuilder sendNpcChat4(String s, String s1, String s2, String s3,
                                        int ChatNpc, String name, int animationId) {
        dialogue.dialogues.add(() ->
            player.getDH().sendNpcChat4(s, s1, s2, s3, ChatNpc, name, animationId)
        );
        return this;
    }

    public DialogueBuilder sendPlayerChat1(String s, int animationId) {
        dialogue.dialogues.add(() ->
            player.getDH().sendPlayerChat1(s, animationId)
        );
        return this;
    }

    public DialogueBuilder sendPlayerChat2(String s, String s1, int animationId) {
        dialogue.dialogues.add(() ->
            player.getDH().sendPlayerChat2(s, s1, animationId)
        );
        return this;
    }

    public DialogueBuilder sendPlayerChat3(String s, String s1, String s2, int animationId) {
        dialogue.dialogues.add(() ->
            player.getDH().sendPlayerChat3(s, s1, s2, animationId)
        );
        return this;
    }

    public DialogueBuilder sendPlayerChat4(String s, String s1, String s2, String s3, int animationId) {
        dialogue.dialogues.add(() ->
            player.getDH().sendPlayerChat4(s, s1, s2, s3, animationId)
        );
        return this;
    }
    //endregion

    public Dialogue build() {
        return dialogue;
    }
}
