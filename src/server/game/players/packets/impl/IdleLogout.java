package server.game.players.packets.impl;

import server.game.players.Client;
import server.game.players.packets.PacketType;

public class IdleLogout implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		// if (!c.playerName.equalsIgnoreCase("Aguilar"))
		// c.logout();
        if(c.underAttackBy > 0 || c.underAttackBy2 > 0)
            return;
       // if(!c.disableIdle)
        //	c.logout();
	}
}