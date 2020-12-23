package server.game.players.packets.impl;

import server.Server;
import server.game.players.Client;
//import server.game.players.DayNightSystem;
//import server.game.players.GlobalDrops;
import server.game.players.Music;
import server.game.players.packets.PacketType;
//import server.game.players.ZombieSurvival;
import server.world.ObjectManager;

/**
 * Change Regions
 */
public class ChangeRegions implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		// Server.objectHandler.updateObjects(c);
	
			//c.getPA().removeObjects();
			//Server.objectManager.loadObjects(c);
			//GlobalDrops.spawnGroundItemsOnStartup(c);
			Server.itemHandler.reloadItems(c);
			//c.sendMessage("Height"+c.heightLevel);
			ObjectManager.loadNeitiSpawns(c);
		//c.getPA().castleWarsObjects();
		Music.getMusic(c);
		
	
		c.saveFile = true;

		if (c.skullTimer > 0) {
			c.isSkulled = true;
			c.headIconPk = 0;
			c.getPA().requestUpdates();
		}

	}

}
