package server.game.players.packets.impl;

import server.game.players.Client;
import server.game.players.Music;
import server.game.players.packets.PacketType;

public class ChangeRegion implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.getPA().removeObjects();
		// Server.objectManager.loadObjects(c);
		Music.getMusic(c);
	}

}
