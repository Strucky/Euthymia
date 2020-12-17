package server.game.minigames;

import server.game.players.Client;

/**
 * @author Liberty
 * @category Minigame
 * @version 2.0
 */

public class Godwars {
	public static final int BOSS_NPCS[] = {6222,6226,6225,6227,6203,6204,6206,6208,6247,6248,6250,6252,6260,6261,6263,6265};
	private static final int KILLCOUNT_REQUIRED = 40;
	private static final int MINIGAME_INTERFACE = 16210;

	/*
	 * public static final int[] ZAMORAK_NPCS = { 6203, 6204, 6206, 6208, 6210,
	 * 6211, 6212, 6213, 6214, 6215, 6216, 6217, 6218, 6219, 6220, 6221 };
	 * 
	 * public static final int[] SARADOMIN_NPCS = { 6247, 6248, 6250, 6252, 6254,
	 * 6255, 6256, 6257, 6258, 6259 };
	 * 
	 * public static final int[] ARMADYL_NPCS = { 6222, 6223, 6225, 6227, 6229,
	 * 6230, 6231, 6232, 6233, 6234, 6235, 6236, 6237, 6238, 6239, 6240, 6241, 6242,
	 * 6243, 6244, 6245, 6246 };
	 * 
	 * public static final int[] BANDOS_NPCS = { 6260, 6261, 6263, 6265, 6267, 6268,
	 * 6269, 6270, 6271, 6272, 6273, 6274, 6275, 6276, 6277, 6278, 6279, 6280, 6281,
	 * 6282, 6283 };
	 */
	public static boolean inDungeon(Client c) {
		return c.absX >= 2817 && c.absY >= 5252 && c.absX <= 2961 && c.absY <= 5376;
	}

	public static void showInterface(Client c) {
		c.getPA().sendFrame126("" + c.BandKc, 16217);
		c.getPA().sendFrame126("" + c.ArmaKc, 16216);
		c.getPA().sendFrame126("" + c.SaraKc, 16218);
		c.getPA().sendFrame126("" + c.ZammyKc, 16219);
		c.getPA().walkableInterface(MINIGAME_INTERFACE);
	}

	/*
	 * public void updateKillCount(Client c, int npc) { if (!inDungeon(c)) return;
	 * for (int zamorak : ZAMORAK_NPCS) { if (zamorak == npc) c.zamorak++; } for
	 * (int saradomin: SARADOMIN_NPCS) { if (saradomin == npc) c.saradomin++; } for
	 * (int armadyl : ARMADYL_NPCS) { if (armadyl == npc) c.armadyl++; } for (int
	 * bandos : BANDOS_NPCS) { if (bandos == npc) c.bandos++; } }
	 */

	public static boolean canEnterRoom(Client c, int god) {
		switch (god) {
		case 1:
			if (c.BandKc >= KILLCOUNT_REQUIRED)
				return true;
		case 2:
			if (c.ZammyKc >= KILLCOUNT_REQUIRED)
				return true;
		case 3:
			if (c.ArmaKc >= KILLCOUNT_REQUIRED)
				return true;
		case 4:
			if (c.SaraKc >= KILLCOUNT_REQUIRED)
				return true;
		}
		return false;
	}

	public static boolean inBossRoom(Client c) {
		// TODO Auto-generated method stub
		return false;
	}
}
