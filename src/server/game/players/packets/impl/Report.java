package server.game.players.packets.impl;

import server.game.players.Client;
import server.game.players.packets.PacketType;

public class Report implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		try {
			ReportHandler.handleReport(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}