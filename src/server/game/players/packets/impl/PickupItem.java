package server.game.players.packets.impl;

import server.Server;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;
import server.game.players.Client;
import server.game.players.packets.PacketType;

/**
 * Pickup Item
 **/
public class PickupItem implements PacketType {

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		c.walkingToItem = false;
		c.pItemY = c.getInStream().readSignedWordBigEndian();
		c.pItemId = c.getInStream().readUnsignedWord();
		c.pItemX = c.getInStream().readSignedWordBigEndian();
		if (Math.abs(c.getX() - c.pItemX) > 25
				|| Math.abs(c.getY() - c.pItemY) > 25) {
			c.resetWalkingQueue();
			return;
		}
		c.getCombat().resetPlayerAttack();
		if (c.goodDistance(c.getX(), c.getY(), c.pItemX, c.pItemY, 1)) {
			Server.itemHandler.removeGroundItem(c, c.pItemId, c.pItemX,
					c.pItemY, true);
		
		} else {
			c.walkingToItem = true;
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (!c.walkingToItem)
						container.stop();
					if (c.goodDistance(c.getX(), c.getY(), c.pItemX, c.pItemY, 1)) {
						Server.itemHandler.removeGroundItem(c, c.pItemId,
								c.pItemX, c.pItemY, true);
						container.stop();
					}
				}

				@Override
				public void stop() {
					c.walkingToItem = false;
				}
			}, 1);
		}

	}

}
