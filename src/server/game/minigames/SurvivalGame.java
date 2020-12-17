package server.game.minigames;

import server.Server;
import server.clip.region.Region;
import server.event.Task;
import server.game.npcs.NPCHandler;
import server.game.players.Client;
import server.util.Misc;

public class SurvivalGame {
	public static int slot = 4;

	public static int giveSlot() {
		//if (slot == 268435456) {
		if (slot == 80) {
			slot = 8;
			return slot - 4;
		}
		slot = slot + 4;
		return slot - 4;
	}

	public static boolean inGame(Client player) {
		if (player.absX > 3084 && player.absY > 3330 && player.absX < 3120
				&& player.absY < 3384 && player.heightLevel > 3)
			return true;
		return false;

	}

	public static void resetGameVariables(Client player, boolean inGame) {
		player.sgWaveCounter = 0;
		player.sgInGame = inGame;
		player.sgNpcAmount = 0;
		player.sgCurrentNpc = 0;
	}
//https://www.rune-server.ee/runescape-development/rs2-server/configuration/20312-all-construction-object-ids-examine-info.html
	public static void joinGame(Client player) {
		if (player.sgInGame == false) {
			player.getPA().showInterface(3281);
			player.getPA().sendFrame36(75, 5);
			player.getPA().movePlayer(1, 1, 0);

			Server.getTaskScheduler().schedule(new Task(4, false) {
				@Override
				protected void execute() {
					stop();
					player.getPA().movePlayer(3112, 3371, giveSlot());
					player.sgInGame = true;
					Server.getTaskScheduler().schedule(new Task(1, false) {
						@Override
						protected void execute() {
							player.getPA().closeAllWindows();
						//	player.sendMessage("height"+player.heightLevel);
							cycle(player);
							stop();
						}});

				}
			});
		}
	}

	public static int waves[][] = { { 1, 3, 1, 10, 40 }, { 175, 3, 1, 10, 60 },{ 182, 3, 1, 15, 70 },
			{ 1614, 4, 1, 20, 80 }, { 1220, 3, 1, 35, 90 },{ 1605, 4, 1, 30, 110 }, { 1914, 2, 3, 30, 120 },{ -1, 4, 1, 20, 40 } };
	private static final int NPC_SLOT = 0;
	private static final int AMOUNT_SLOT = 1;
	private static final int POINTS_SLOT = 2;
	private static final int MAX_HIT_SLOT = 3;
	private static final int STATS_SLOT = 4;

	/**
	 * Setting the interfaces for the waiting lobby
	 */
	private static void setGameInterface(Client c) {
		if (c != null) {
			if(c.count > 0) {
				c.getPA().sendFrame126("Next Wave in: " + c.count, 21120);
				c.getPA()
						.sendFrame126(
								"Current Target: "
										+ NPCHandler
												.getNpcListName(waves[c.sgWaveCounter][NPC_SLOT]),
								21121);
				c.getPA().sendFrame126("Missing targets: " + c.sgNpcAmount, 21122);
				c.getPA().sendFrame126("Points: " + c.sgPoints + "", 21123);
		
				c.getPA().walkableInterface(21119);
				return;
			}
			c.getPA().sendFrame126("Current Wave: " + c.sgWaveCounter, 21120);
			c.getPA()
					.sendFrame126(
							"Current Target: "
									+ NPCHandler
											.getNpcListName(waves[c.sgWaveCounter][NPC_SLOT]),
							21121);
			c.getPA().sendFrame126("Missing targets: " + c.sgNpcAmount, 21122);
			c.getPA().sendFrame126("Points: " + c.sgPoints + "", 21123);
			c.getPA().walkableInterface(21119);
			c.sendMessage("intface");
			
		}
	}

	public static void deleteKeys(Client player) {
		if (player.getItems().playerHasItem(9722)) {
			player.getItems().deleteItem(9722, 1);
			if (player.getItems().playerHasItem(9722)) {
				deleteKeys(player);
			}
		}
	}

	public static void cycle(Client player) {
		Server.getTaskScheduler().schedule(new Task(1, true) {
			@Override
			protected void execute() {
				if (!SurvivalGame.inGame(player)) {
					SurvivalGame.resetGameVariables(player, false);
					deleteKeys(player);
//					player.sendMessage("Not in g");
					player.survivalGrabs =0;
					stop();
					return;
				}

				if (player.sgCurrentNpc == 0) {
					player.sgCurrentNpc = waves[player.sgWaveCounter][NPC_SLOT];
					// player.sgNpcAmount =
					// waves[player.sgWaveCounter][AMOUNT_SLOT];
				}
				if (player.sgNpcAmount == 0 && player.sgCurrentNpc != 0) {
					if (player.sgWaveCounter != 0)
						player.sgPoints = player.sgPoints
								+ waves[player.sgWaveCounter][POINTS_SLOT];

					player.sgWaveCounter++;
					player.sgCurrentNpc = waves[player.sgWaveCounter][NPC_SLOT];
					if (player.sgCurrentNpc == -1) {
						player.getPA().startTeleport(2886, 3419, 0,
								"mo" + "dern");
						SurvivalGame.resetGameVariables(player, false);
						deleteKeys(player);
						player.survivalGrabs =0;
						stop();
						return;
					}
					player.sgNpcAmount = waves[player.sgWaveCounter][AMOUNT_SLOT];
					// player.sendMessage("b4For");
					Server.getTaskScheduler().schedule(new Task(2, true) {
						private int count = 6;

						@Override
						protected void execute() {
							if (count > 0) {
								count--;
								player.count = count;
								if(count == 0)
									 player.forcedChat("Go!");
								
								else
									 player.forcedChat(""+count+"");
							} else {
								stop();
							
					for (int i = 0; i < player.sgNpcAmount; i++) {
						// player.sendMessage("For");
						int rand = Misc.random(3);
						int rand2 = Misc.random(1);
						if (Region.getClipping(player.getX() - rand,
								player.getY() + rand2, player.heightLevel, -1,
								0)) {
				//			player.sendMessage("Spawning npc if");
							Server.npcHandler.spawnNpc(player,
									waves[player.sgWaveCounter][NPC_SLOT],
									player.absX - rand, player.absY + rand2,
									player.heightLevel, 1, player.combatLevel,
									waves[player.sgWaveCounter][MAX_HIT_SLOT],
									waves[player.sgWaveCounter][STATS_SLOT],
									waves[player.sgWaveCounter][STATS_SLOT],
									false, false, false);
						} else {
					//		player.sendMessage("Spawning npc else");
							Server.npcHandler.spawnNpc(player,
									waves[player.sgWaveCounter][NPC_SLOT],
									player.absX, player.absY,
									player.heightLevel, 1, player.combatLevel,
									waves[player.sgWaveCounter][MAX_HIT_SLOT],
									waves[player.sgWaveCounter][STATS_SLOT],
									waves[player.sgWaveCounter][STATS_SLOT],
									false, false, false);
						}	}
					
					}
						}});
				}
				setGameInterface(player);
			}
		});
	}
}
