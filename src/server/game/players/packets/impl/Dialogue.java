package server.game.players.packets.impl;

import server.game.players.Client;
import server.game.players.packets.PacketType;

/**
 * Dialogue
 **/
public class Dialogue implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {

		c.getDH().getNextDialogue();

		if (c.nextChat > 0) {
			c.getDH().sendDialogues(c.nextChat, c.talkingNpc);
		} else {
			c.getDH().sendDialogues(0, -1);
		}
		if (c.nextItem > 0) {
			c.getDH().sendItems(c.nextItem, c.itemShown);
		} else {
			c.getDH().sendItems(0, -1);
		}
	}
}
