package server.game.players.content.skills;

import server.Server;
import server.util.Misc;
import server.game.players.Client;
import server.game.players.PlayerHandler;
import server.event.*;

/**
 * Class Fishing Handles: Fishing
 * 
 * @author: PapaDoc START: 22:07 23/12/2010 FINISH: 22:28 23/12/2010
 */

public class Fishing extends SkillHandler {



	public enum FishData {
		SHRIMP_ANCHOVIES(316, 303, -1, 1, 317, 5, 321, 7, 621, 1),
		SARDINE_HERRING(316,309,313,5,327,10,345,10,622,2),
		SARDINE_HERRING1(323,309,313,5,327,10,345,10,622,2),
		MACKEREL(313, 305,-1,16,353,20,-1,-1,620,1),
		TROUT(309,309,314,20,335,25,331,30,622,1),
		BASS_COD(322,305,-1,23,341,30,363,35,621,1),
		PIKE(309,309,314,25,349,50,-1,-1,622,2),
		PIKE1(320,309,314,25,349,50,-1,-1,622,2),
		PIKE2(319,309,314,25,349,50,-1,-1,622,2),
		TUNA_SWORD(312,311,-1,35,359,55,371,70,618,2),
		LOBSTER(312,301,-1,40,377,70,-1,-1,619,1),
		MONKFISH(320,305,-1,62,7944,120,-1,-1,620,1),
		SHARK(313,311,-1,76,383,175,-1,-1,618,2),
		SHARK2(322,311,-1,76,383,175,-1,-1,618,2),
		MANTA(334,311,-1,95,389,225,-1,-1,621,1),
		MANTA2(323,311,-1,95,389,225,-1,-1,621,1),
		SEATURTLE(319,305,-1,79,395,200,-1,-1,619,1),
		ROCKTAIL(334,311,-1,95,12019,300,-1,-1,618,2);
		int spotId, itemReq, itemReq2, levelReq, rawFish, experience, rawFish2,
				experience2, animation, click;

		private FishData(int spotId, int itemReq, int itemReq2, int levelReq,
				int rawFish, int experience, int rawFish2, int experience2,
				int animation, int click) {
			this.spotId = spotId;
			this.itemReq = itemReq;
			this.itemReq2 = itemReq2;
			this.levelReq = levelReq;
			this.rawFish = rawFish;
			this.experience = experience;
			this.rawFish2 = rawFish2;
			this.experience2 = experience2;
			this.animation = animation;
			this.click = click;
		}
	}

	

	public static void fish(Client player, int spotId, int click) {
		if (player.isFishingTrune)
			return;
		
	
		if (player.getPA().freeSlots() == 0) {
			player.sendMessage("There is not enough space in your inventory");
			return;
		}
		for (FishData e : FishData.values()) {
			if (click == e.click) {
				if (spotId == e.spotId) {
					player.isFishingTrune = true;
					if (player.playerLevel[player.playerFishing] < e.levelReq) {
						player.sendMessage("You need a level of " + e.levelReq
								+ " to fish here.");
						return;
					}
					if (!player.getItems().playerHasItem(e.itemReq)) {
						player.sendMessage("You need a "
								+ player.getItems().getItemName(e.itemReq)
								+ " to fish here.");
						return;
					}
					if (e.itemReq2 != -1) {
						if (!player.getItems().playerHasItem(e.itemReq2)) {
							player.sendMessage("You need a "
									+ player.getItems().getItemName(e.itemReq2)
									+ " to fish here.");
							return;
						}
					}
					if (player.playerLevel[player.playerFishing] >= e.levelReq) {
						if (player.getItems().playerHasItem(e.itemReq)) {
							if (e.itemReq2 != -1) {
								;
								fishCycle(player, e.animation, e.experience,
										e.rawFish, e.rawFish2, true, e.itemReq,
										e.itemReq2, e.experience);

								return;

							} else if (e.itemReq2 == -1) {
								//player.sendMessage("You start fishing");
								fishCycle(player, e.animation, e.experience2,
										e.rawFish, e.rawFish2, false,
										e.itemReq, e.itemReq2, e.experience);
								return;

							}
						}
					}
				}
			}
		}
	}

	private static void fishCycle(final Client player, int animation,
			int experience2, int rawFish, int rawFish2, boolean secondReq,
			int itemReq, int itemReq2, int experience) {
	
		if (secondReq) {
			Server.getTaskScheduler().schedule(new Task(2, true) {
				private int count = 5;

				@Override
				protected void execute() {
					if (count > 0) {
						if (player.getPA().freeSlots() == 0
								|| !player.isFishingTrune
								|| !player.getItems().playerHasItem(itemReq2)
								|| !player.getItems().playerHasItem(itemReq)) {
							player.isFishingTrune = false;
							player.sendMessage("You've stopped fishing.");
							player.startAnimation(65535);
							stop();
							return;
						}
						player.startAnimation(animation);
						count--;
					} else {
						player.getItems().deleteItem(itemReq2, 1);
						if(rawFish2 != -1) {
						if (Misc.random(1) == 1) {
							player.getItems().addItem(rawFish, 1);
							player.getPA().addSkillXP(experience * FISHING_XP,
									player.playerFishing);
						} else {
							player.getItems().addItem(rawFish2, 1);
							player.getPA().addSkillXP(experience2 * FISHING_XP,
									player.playerFishing);
						}
						} else {
							player.getItems().addItem(rawFish, 1);
							player.getPA().addSkillXP(experience * FISHING_XP,
									player.playerFishing);
						}
						fishCycle(player, animation, experience2, rawFish,
								rawFish2, secondReq, itemReq, itemReq2,
								experience);
						stop();
					}
				}
			});
		}
		if (!secondReq) {
			Server.getTaskScheduler().schedule(new Task(2, true) {
				private int count = 5;

				@Override
				protected void execute() {
					if (count > 0) {
						if (player.getPA().freeSlots() == 0
								|| !player.isFishingTrune
								|| !player.getItems().playerHasItem(itemReq)) {
							player.isFishingTrune = false;
							player.sendMessage("You've stopped fishing.");
							player.startAnimation(65535);
							stop();
							return;
						}
						player.startAnimation(animation);
						count--;
					} else {
						if(rawFish2 != -1) {
							if (Misc.random(1) == 1) {
								player.getItems().addItem(rawFish, 1);
								player.getPA().addSkillXP(experience * FISHING_XP,
										player.playerFishing);
							} else {
								player.getItems().addItem(rawFish2, 1);
								player.getPA().addSkillXP(experience2 * FISHING_XP,
										player.playerFishing);
							}
							} else {
								player.getItems().addItem(rawFish, 1);
								player.getPA().addSkillXP(experience * FISHING_XP,
										player.playerFishing);
							}
						fishCycle(player, animation, experience2, rawFish,
								rawFish2, secondReq, itemReq, itemReq2,
								experience);
						stop();
					}
				}
			});
		}
	}

}
