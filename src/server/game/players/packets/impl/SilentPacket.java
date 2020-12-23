package server.game.players.packets.impl;

import server.game.players.Client;
import server.game.players.packets.PacketType;

/**
 * Slient Packet
 **/
public class SilentPacket implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {

	}
}
