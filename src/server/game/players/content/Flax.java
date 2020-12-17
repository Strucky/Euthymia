package server.game.players.content;

import java.util.ArrayList;

import server.Server;
import server.game.players.Client;
import server.util.Misc;

/**
 * Flaxpicking. OUTDATED
 * 
 * @author Derive
 * 
 */
public class Flax {

	public static ArrayList<int[]> flaxRemoved = new ArrayList<int[]>();
	public static ArrayList<int[]> cabbageRemoved = new ArrayList<int[]>();
	public static void pickFlax(final Client c, final int x, final int y) {
		if (c.getItems().freeSlots() != 0) {
			c.getItems().addItem(1779, 1);
			c.startAnimation(827);
			c.sendMessage("You pick some flax.");
			if (Misc.random(3) == 1) {
				flaxRemoved.add(new int[] { x, y });
				Server.objectManager.removeObject(x, y);
			}
		} else {
			c.sendMessage("Not enough space in your inventory.");
			return;
		}

	}

	public static void pickCabbage(Client c, int x, int y) {
		if (c.getItems().freeSlots() != 0) {
			c.getItems().addItem(1965, 1);
			c.startAnimation(827);
			c.sendMessage("You pick a cabbage.");
			if (Misc.random(1) == 1) {
				
				cabbageRemoved.add(new int[] { x, y });
				Server.objectManager.removeObject(x, y);
			}
		} else {
			c.sendMessage("Not enough space in your inventory.");
			return;
		}
		
	}
	
	public static void pickGrain(Client c, int x, int y) {
		if (c.getItems().freeSlots() != 0) {
			c.getItems().addItem(1947, 1);
			c.startAnimation(827);
			c.sendMessage("You pick some grain.");
			if (Misc.random(1) == 1) {
				
				cabbageRemoved.add(new int[] { x, y });
				Server.objectManager.removeObject(x, y);
			}
		} else {
			c.sendMessage("Not enough space in your inventory.");
			return;
		}
		
	}
}