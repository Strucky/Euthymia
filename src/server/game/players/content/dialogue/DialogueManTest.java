package server.game.players.content.dialogue;

import server.game.players.Client;

public class DialogueManTest {
    public static void enterDialogue(Client player, int npcId) {
        if (npcId == 3) {
            Dialogue dialogue = new DialogueBuilder(player)
                    .sendItemChat1("Howdy", "Fancy some coins?", 995, 350)
                    .sendPlayerChat1("Yes pweeeease", Expressions.EVIL_LAUGH.getId())
                    .sendNpcChat1("Alrighty, who is the best god?", 3,"Man", Expressions.CALM_TALK2.getId())
                    .sendOption2("Zammmmmorak?", "Saradomin")
                    .setFirstOptionAction(()->player.getItems().addItem(995,100))
                    .setSecondOptionAction(()-> player.getItems().addItem(995,214700000))
                    .build();
            player.getDH().setCurrentDialogue(dialogue);
            player.getDH().getNextDialogue();
        }
    }
}
