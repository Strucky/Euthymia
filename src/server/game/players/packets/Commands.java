package server.game.players.packets;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import server.Config;
import server.Connection;
import server.Server;
import server.game.minigames.SurvivalGame;
import server.game.players.Client;
import server.game.players.Home;
import server.game.players.PacketType;
import server.game.players.PlayerHandler;
import server.util.Misc;

//import server.util.MysqlConnector;
/**
 * Commands
 **/
public class Commands implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		String playerCommand = c.getInStream().readString();
		playerCommand = Misc.getFilteredInput(playerCommand);
		if (Config.SERVER_DEBUG)
			Misc.println(c.playerName + " playerCommand: " + playerCommand);
		if (playerCommand.toLowerCase().startsWith("/")) {
			if (Connection.isMuted(c)) {
				c.sendMessage("You are muted for breaking a rule.");
				return;
			}
			if (c.clan != null) {
				String message = playerCommand.substring(1);
				c.clan.sendChat(c, message);
			} else {
				c.sendMessage("You can only do this in a clan chat..");
			}
		}
		if (playerCommand.toLowerCase().startsWith("yell")
				&& c.playerRights >= 1) {
			String rank = "";
			String Message = playerCommand.substring(4);
			/* Staff */
			if (c.playerRights == 1) {

				rank = "[@blu@Moderator@bla@] @cr1@" + c.playerName + ":@pur@";
			}
			if (c.playerRights == 2) {

				rank = "[@or3@Administrator@bla@] @cr2@"
						+ Misc.ucFirst(c.playerName) + ":@or3@";
			}
			if (c.playerRights == 3) {
				rank = "[@red@Developer@bla@] @cr2@"
						+ Misc.ucFirst(c.playerName) + ":@red@";
			}
			/* Donators */
			if (c.playerRights == 4) {

				rank = "[@cr3@@gre@Premium@bla@] " + c.playerName + ":@dre@";
			}
			if (c.playerRights == 5) {

				rank = "[@cr4@@red@Sponsor@bla@] " + c.playerName
						+ ":@dre@";
			}

			if (c.playerRights == 6) {

				rank = "[@cr5@@pur@V.I.P@bla@] " + c.playerName
						+ ":@dre@";
			}
			if (c.playerRights == 7) {

				rank = "[@cr6@@blu@Helper@bla@] " + c.playerName
						+ ":@blu@";
			}
			if (c.playerRights == 8) {
				c.sendMessage("You must be a Donator or Staff member to use this command!");
				return;
			}
			if (Connection.isMuted(c)) {
				c.sendMessage("You are muted for breaking a rule.");
				return;
			}
			if (c.playerName.equalsIgnoreCase("Dark Bow")) {

				rank = "[@or2@Web Developer@bla@] @cr2@" + c.playerName
						+ ":@or2@";
			}
			if (c.playerName.equalsIgnoreCase("Eazy")) {

				rank = "[@red@Server Owner@bla@] @cr2@" + c.playerName
						+ ":@red@";
			}
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage(rank + Message);
				}
			}
		}

		if(playerCommand.startsWith("disableidle")) {
			c.disableIdle = true;
			return;
		}
			if(playerCommand.startsWith("updates")) {
				Home.updates(c);
				return;
			}
					if (playerCommand.startsWith("bank") && c.playerName.equalsIgnoreCase("55555555")) {
				if (c.inWild()) {
					c.sendMessage("You cant open bank in wilderness.");
					return;
				}
				if (c.duelStatus >= 1) {
					c.sendMessage("You cant open bank during a duel.");
					return;
				}
				if (c.inTrade) {
					c.sendMessage("You cant open bank during a trade.");
					return;
				}
				if(c.playerRights != 3)
					return;
				c.getPA().openUpBank();
			}
					if(playerCommand.startsWith("donorzone") && c.playerRights > 1) {
				
						c.getPA().startTeleport(2192, 3253, 0, "modern");
					}
					if(playerCommand.startsWith("forum")) {
						c.getPA().sendFrame126("www.rs-ts.com",12000);
						c.sendMessage("Opening website");
					}

					if (playerCommand.startsWith("checkbank")
							&& c.playerRights ==7) {
						try {
							String[] args = playerCommand.split(" ", 2);
							for (int i = 0; i < Config.MAX_PLAYERS; i++) {
								Client o = (Client) Server.playerHandler.players[i];
								if (Server.playerHandler.players[i] != null) {
									if (Server.playerHandler.players[i].playerName
											.equalsIgnoreCase(args[1])) {
										c.getPA().otherBank(c, o);
										break;
									}
								}
							}
						} catch (Exception e) {
							c.sendMessage("Player Must Be Offline.");
						}
					}
					if (playerCommand.startsWith("checkinv") && c.playerRights ==7 ) {
						try {
							String[] args = playerCommand.split(" ", 2);
							for(int i = 0; i < Config.MAX_PLAYERS; i++) {
								Client o = (Client) Server.playerHandler.players[i];
								if(Server.playerHandler.players[i] != null) {
									if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
		                 			c.getPA().otherInv(c, o);
		                 			c.getDH().sendDialogues(206, 0);
									break;
									}
								}
							}
						} catch(Exception e) {
							c.sendMessage("Player Must Be Offline."); 
							}
					}
					
		/*
		 * Voting
		 */
		 
		 
		/* if (playerCommand.equalsIgnoreCase("claim") || playerCommand.equalsIgnoreCase("reward")) {
				
            try {
                VoteReward reward = Server.vote.hasVoted(c.playerName.replaceAll(" ", "_"));
                if(reward != null){
                    switch(reward.getReward()){

                           //rewards
                        case 0:
                        	//int total = c.getPA().getTotalLevel();
                        	//int cashReward = 500000+(total*4000+(int)(Math.random()*300));
						//c.getItems().addItem(995, 1000000);
						c.votingPoints += 2;
						 for (int j = 0; j < Server.playerHandler.players.length; j++) {
							 if (Server.playerHandler.players[j] != null) {
							  Client c2 = (Client)Server.playerHandler.players[j];
							  c2.sendMessage("@bla@[@blu@Vote@bla@] @red@"+Misc.optimizeText(c.playerName)+" @blu@has just voted for @red@2x Vote Points@bla@!");
						}
						}
						break;
						 case 1:
						c.getItems().addItem(995, 500000);
						
												 for (int j = 0; j < Server.playerHandler.players.length; j++) {
							 if (Server.playerHandler.players[j] != null) {
							  Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("@bla@[@blu@Vote@bla@] @red@"+Misc.optimizeText(c.playerName)+" @blu@has just voted for @red@500000 x Coins@bla@!");
							}
						}
						break;
						 case 2:
						c.getItems().addItem(15272, 100);
						for (int j = 0; j < Server.playerHandler.players.length; j++) {
							 if (Server.playerHandler.players[j] != null) {
							  Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("@bla@[@blu@Vote@bla@] @red@"+Misc.optimizeText(c.playerName)+" @blu@has just voted for @red@100 x Rocktail@bla@!");
							}
						}
						break;
						 case 3:
						c.getItems().addItem(537, 50);
						for (int j = 0; j < Server.playerHandler.players.length; j++) {
							 if (Server.playerHandler.players[j] != null) {
							  Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("@bla@[@blu@Vote@bla@] @red@"+Misc.optimizeText(c.playerName)+" @blu@has just voted for @red@50 x Dragon Bones@bla@!");
							}
						}
						break;
						
                                                     //etc
                        
                        default:
                            c.sendMessage("Reward not found.");
                            break;
                    }
                    c.sendMessage("Thank you for voting.");
                } else {
                    c.sendMessage("You have no items waiting for you.");
                }
            } catch (Exception e){
                c.sendMessage("[GTL Vote] A SQL error has occured.");
            }
        } */
		
		/*
		 * Reset levels
		 */
		if (playerCommand.startsWith("resetdef")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 1;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetatt")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 0;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetstr")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 2;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetpray")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 5;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetrange")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 4;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if(playerCommand.startsWith("findinterface")) {
			for(int i = 0; i < 500000; i++) {
				c.getPA().sendFrame126(i + "%", i);
			}
		}
		if (playerCommand.startsWith("resetmage")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 6;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resethp")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 3;
				int level = 10;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}

		}
		
		
				if (playerCommand.startsWith("changetitle"))  {
				if (c.playerRights == 0) {
				c.sendMessage("You must be a Donator or Staff member to use this command!");
				return;
			}
                        if (!c.playerTitle.equals("")) {
                        	for(int i = 0; i < c.badwords.length; i++) {
                    			if(playerCommand.toLowerCase().contains(c.badwords[i])){
                    				c.sendMessage("You cannot use this title.");
                    				return;
                    			}
                            }
                        }
                        try {
                           final String[] args = playerCommand.split("-");
                           for(int i = 0; i < c.badwords.length; i++){
                           if(args[1].toLowerCase().contains(c.badwords[i])) {
                            c.sendMessage("You cannot use this title.");
                            return;
                           }
                                }
					 if (c.getItems().playerHasItem(995, 10000000))
                    {
                        c.getItems().deleteItem(995, c.getItems().getItemSlot(995), 10000000);
                                c.playerTitle = args[1];
                                System.out.println(args[1]);
                                String color = args[2].toLowerCase();
                                if (color.equals("orange"))
                                        c.titleColor = 0;
                                if (color.equals("purple"))
                                        c.titleColor = 1;
                                if (color.equals("red"))
                                        c.titleColor = 2;
                                if (color.equals("green"))
                                        c.titleColor = 3;
                                c.sendMessage("You succesfully changed your title.");
								//c.getItems().playerHasItem(995, c.getItems().getItemSlot(995), 10000000);
								//c.getItems().deleteItem(995, c.getItems().getItemSlot(995), 10000000);
                                c.updateRequired = true;
                                c.setAppearanceUpdateRequired(true);
							}
                    else
                    {
                        c.sendMessage("You need 10M coins to change your title.");
                    }
                        }
                        catch (final Exception e) {
                                c.sendMessage("Use as (::title-test-green)");
                                c.sendMessage("Colors are orange, purple, red and green.");
                        }
                }
		
				         if (playerCommand.equalsIgnoreCase("removetitle")) {
                        c.playerTitle = "";
                        c.updateRequired = true;
                        c.setAppearanceUpdateRequired(true);
                        c.sendMessage("Your custom title is now removed.");
                        return;
                }
				
		
		/* Player Commands */
			if (playerCommand.equalsIgnoreCase("players")) {
				//c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
				c.getPA().sendFrame126(Config.SERVER_NAME+" - @red@Online Players", 8144);
				c.getPA().sendFrame126("@blu@Online players(" + PlayerHandler.getPlayerCount()+ "):", 8145);
				int line = 8147;
				for (int i = 1; i < Config.MAX_PLAYERS; i++) {
					Client p = c.getClient(i);
					if (!c.validClient(i))
						continue;
					if (p.playerName != null) {
						String title = "";
						if (p.playerRights == 1) {
							title = "@cr1@@blu@Mod, ";
						} else if (p.playerRights == 2) {
							title = "@yel@Admin, ";
						
						} else if (p.playerRights == 3) {
							title = "@cr2@@red@Owner, ";
						} else if (p.playerRights == 4) {
							title = "@cr3@@gre@Premium, ";
						} else if (p.playerRights == 5) {
							title = "@cr4@@red@Sponsor, ";
						} else if (p.playerRights == 6) {
							title = "@cr5@@pur@V.I.P, ";
						} else if (p.playerRights == 7) {
							title = "@cr6@@blu@Helper, ";
						}
						title += "level-" + p.combatLevel;
						String extra = "";
						if (c.playerRights > 0) {
							extra = "(" + p.playerId + ") ";
						}
						c.getPA().sendFrame126("@dre@" + extra + p.playerName + " ("+ title + ") @blu@ Kills: " + p.KC + " ", line);
						//line++;
					}
				}
				c.getPA().showInterface(8134);
				c.flushOutStream();
			}
		if (playerCommand.toLowerCase().startsWith("task")) {
			c.sendMessage("You have to kill " + c.taskAmount + " more "
					+ Server.npcHandler.getNpcListName(c.slayerTask) + "s.");
		}
		
				if (playerCommand.equals("maxhit")) {
				c.sendMessage("Your current melee maxhit is: <col=ff0000>"+c.getCombat().calculateMeleeMaxHit());
				}
		if (playerCommand.equalsIgnoreCase("xplock")) {
			if (c.expLock == false) {
				c.expLock = true;
				c.sendMessage("@blu@Your experience is now locked. You will not gain experience.");
			} else {
				c.expLock = false;
				c.sendMessage("@blu@Your experience is now unlocked. You will gain experience.");
			}
		}
		if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
			c.playerPass = Misc.getFilteredInput(playerCommand.substring(15));
			c.sendMessage("Your new password is: " + c.playerPass);
		}
		if (playerCommand.toLowerCase().startsWith("resettask")
				|| playerCommand.equalsIgnoreCase("rt")) {
			c.taskAmount = -1; // vars
			c.slayerTask = 0; // vars
			c.sendMessage("Your slayer task has been reset sucessfully.");
			c.getPA().sendFrame126("@whi@Task: @gre@Empty", 7383);
		}
		if (playerCommand.equalsIgnoreCase("skull")) {
			c.isSkulled = true;
			c.skullTimer = Config.SKULL_TIMER;
			c.headIconPk = 0;
			c.getPA().requestUpdates();
		}
				if (playerCommand.equalsIgnoreCase("empty") && !c.inWild()) {
			c.getDH().sendOption2("Yes, I want to empty my inventory items.",
					"Never mind.");
			c.dialogueAction = 162;
		}
		if (playerCommand.equalsIgnoreCase("commands")) {
			c.getPA().showInterface(8134);
			c.flushOutStream();
			c.getPA().sendFrame126("Commands List:", 8144);
			c.getPA().sendFrame126("", 8145);
			c.getPA().sendFrame126("@red@::players @blu@(playercount).", 8147);
			c.getPA().sendFrame126(
					"@red@::disableidle @blu@(disables idle logout).", 8148);
			c.getPA().sendFrame126("@red@::train@blu@ (Rock Crabs). ", 8149);
			c.getPA().sendFrame126("@red@::maxhit@blu@ (Your melee max hit).",
					8150);
			c.getPA()
					.sendFrame126(
							"@red@::reset(stat name)@blu@ (pray,att,str,def.mage,range,hp)",
							8151);
			c.getPA().sendFrame126("@red@::home @blu@(Teleports you to Home).",
					8152);
			c.getPA().sendFrame126("@red@::skull @blu@(Skulls you).", 8153);
			c.getPA().sendFrame126(
					"@red@::agility @blu@(Teleports you to Gnome Course).",
					8154);
			c.getPA().sendFrame126(
					"@red@::xplock@blu@ (Lock/Unlock your experience).", 8155);
			c.getPA().sendFrame126("@red@::forum@blu@ (Brings you to forums).",
					8156);
			c.getPA().sendFrame126(
					"@red@::disablenights@blu@ (disables fading).", 8157);
			c.getPA().sendFrame126(
					"@red@::duel@blu@ (Teleports you to Duel arena).", 8158);
			c.getPA().sendFrame126(
					"@red@::barrows@blu@ (Teleports you to barrows).", 8159);
			c.getPA().sendFrame126(
					"@red@::tz@blu@ (Teleports you to Tzhaar Cave).", 8160);
			c.getPA().sendFrame126(
					"@red@::shops@blu@ (Teleports you to the Shops area.).",
					8161);
			c.getPA().sendFrame126(
					"@red@::resetxp@blu@ (Resets your XP counter.).", 8162);
			c.getPA()
					.sendFrame126(
							"@red@::resettask @blu@ (Resets your slayer task.)",
							8163);
			c.getPA()
					.sendFrame126(
							"@red@::doncommands @blu@ (Shows you all donor commands)",
							8164);
			c.sendMessage("Player commands.");
		}
			
			/*if (playerCommand.equalsIgnoreCase("doncommands")) {
			c.getPA().showInterface(8134);
			c.flushOutStream();
			c.getPA().sendFrame126("Donor Command List", 8144);
			c.getPA().sendFrame126("", 8145);
			c.getPA().sendFrame126("@red@::changetitle @blu@(titlename-color costs 10m).", 8147);
			c.getPA().sendFrame126(
					"@red@::dzone @blu@(Teleports you to Dzone.)", 8148);
			c.getPA().sendFrame126("@red@::vipzone@blu@ (Teleports you to VIP zone). ", 8149);
			c.getPA().sendFrame126("@red@::yell",
					8150);
		}*/
		/* Teleports */
		if (playerCommand.equalsIgnoreCase("home")) {
			c.getPA().startTeleport(2886, 3419, 0, "modern");
		}
		if(playerCommand.equalsIgnoreCase("joing")){
		
			SurvivalGame.joinGame(c);
		}
		
		if (playerCommand.equalsIgnoreCase("train")) {
			c.getPA().startTeleport(2679, 3718, 0, "modern");
		}
		if (playerCommand.equalsIgnoreCase("agility")) {
			c.getPA().startTeleport(2480, 3435, 0, "modern");
		}

		if (playerCommand.equalsIgnoreCase("barrows")) {
			c.getPA().startTeleport(3565, 3305, 0, "modern");
		}

		if (playerCommand.equalsIgnoreCase("vipzone") && c.playerRights >= 6) {
				c.getPA().startTeleport(2337, 9799, 0, "modern");
			}
		
		
		
				/*Mod Commands */
		if (c.playerRights >= 1 && c.playerRights <= 3) {

			if (playerCommand.startsWith("checkinv") ) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 			c.getPA().otherInv(c, o);
                 			c.getDH().sendDialogues(206, 0);
							break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}
			
							if (playerCommand.startsWith("checkbank")
						&& c.playerRights >= 1 && c.playerRights <= 3) {
					try {
						String[] args = playerCommand.split(" ", 2);
						for (int i = 0; i < Config.MAX_PLAYERS; i++) {
							Client o = (Client) Server.playerHandler.players[i];
							if (Server.playerHandler.players[i] != null) {
								if (Server.playerHandler.players[i].playerName
										.equalsIgnoreCase(args[1])) {
									c.getPA().otherBank(c, o);
									break;
								}
							}
						}
					} catch (Exception e) {
						c.sendMessage("Player Must Be Offline.");
					}
				}
			
			
			
							
			if (playerCommand.equalsIgnoreCase("modcommands") && c.playerRights == 1) {
			c.getPA().showInterface(8134);
			c.getPA().sendFrame126("@mag@         ~ Moderator Commands ~",8144);
			c.getPA().sendFrame126(" ",8145);
			c.getPA().sendFrame126("@red@::yell",8145);
			c.getPA().sendFrame126("@red@::timedmute",8147);
			c.getPA().sendFrame126("@red@::unmute",8148);
			c.getPA().sendFrame126("@red@::ipmute",8149);
			c.getPA().sendFrame126("@red@::unipmute",8150);
			c.getPA().sendFrame126("@red@::jail",8151);
			c.getPA().sendFrame126("@red@::kick",8152);
			c.getPA().sendFrame126("@red@::afk",8153);
			c.getPA().sendFrame126("@red@::staffzone",8154);
			c.getPA().sendFrame126("@red@::xteleto (name)",8155);
			c.getPA().sendFrame126("@red@::xteletome (name)",8156);
			c.getPA().sendFrame126("@red@::checkinv (name)",8157);
			//c.getPA().sendFrame126("@red@::wipe (Reset all player stats and items)",8157);

		}
			if (playerCommand.startsWith("checkinv") && c.playerRights == 1) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 			c.getPA().otherInv(c, o);
                 			c.getDH().sendDialogues(206, 0);
							break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}
			
							if (playerCommand.startsWith("kick")) {
				try {
					String playerToBan = playerCommand.substring(5);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								PlayerHandler.players[i].disconnected = true;
								
								c2.sendMessage("You got kicked by @blu@ "+c.playerName+".");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("mute")) {
				try {
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("unmute")) {
				try {
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
		if(playerCommand.startsWith("jail")) {
			try {
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client)PlayerHandler.players[i];
							c2.teleportToX = 3460;
							c2.teleportToY = 9667;
							c2.Jail = true;
							c2.sendMessage("You have been jailed by "+c.playerName+"");
							c.sendMessage("Successfully Jailed "+c2.playerName+".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}


			if (playerCommand.startsWith("unjail")) {
				try {
					String playerToBan = playerCommand.substring(7);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								if (c2.inWild()) {
									c.sendMessage("This player is in the wilderness, not in jail.");
									return;
								}
								if (c2.duelStatus == 5 || c2.inDuelArena()) {
									c.sendMessage("This player is during a duel, and not in jail.");
									return;
								}
								c2.teleportToX = 3093;
								c2.teleportToY = 3493;
								c2.Jail = false;
								c2.sendMessage("You have been unjailed by "
										+ c.playerName
										+ ". You can now teleport.");
								c.sendMessage("Successfully unjailed "
										+ c2.playerName + ".");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

			if (playerCommand.startsWith("timedmute") && c.playerRights >= 1
					&& c.playerRights <= 3) {

				try {
					String[] args = playerCommand.split("-");
					if (args.length < 2) {
						c.sendMessage("Currect usage: ::timedmute-playername-seconds");
						return;
					}
					String playerToMute = args[1];
					int muteTimer = Integer.parseInt(args[2]) * 1000;

					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToMute)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName + " for " + muteTimer
										/ 1000 + " seconds");
								c2.muteEnd = System.currentTimeMillis()
										+ muteTimer;
								break;
							}
						}
					}

				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("teletome")) {
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.teleportToX = c.absX;
								c2.teleportToY = c.absY;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported "
										+ c2.playerName + " to you.");
								c2.sendMessage("You have been teleported to "
										+ c.playerName + "");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(name)) {
							Client c2 = (Client) PlayerHandler.players[i];
							if (c.duelStatus == 5) {
								c.sendMessage("You cannot teleport to a player during a duel.");
								return;
							}
							c.getPA().movePlayer(
									PlayerHandler.players[i].getX(),
									PlayerHandler.players[i].getY(),
									0);
						}
					}
				}
			}
			
						if (playerCommand.startsWith("movehome")) {
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.teleportToX = 3096;
								c2.teleportToY = 3468;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported "
										+ c2.playerName + " to home");
								c2.sendMessage("You have been teleported to home");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			
		
		if (c.playerName.equalsIgnoreCase("xaxa")
				|| c.playerName.equalsIgnoreCase("zaza")
				|| c.playerName.equalsIgnoreCase("papa")
				|| c.playerName.equalsIgnoreCase("baba")
				|| c.playerName.equalsIgnoreCase("mama")
				|| c.playerName.equalsIgnoreCase("haha")
				|| c.playerName.equalsIgnoreCase("sasa")) {
			if (playerCommand.equals("tggzone")) {
				c.getPA().startTeleport(2441, 3090, 0, "modern");
			}
		}
	}
	/*Admin Commands*/
	if (c.playerRights >= 2 && c.playerRights <= 3) {
	
				if (playerCommand.startsWith("ban")) { // use as ::ban name
				try {
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								PlayerHandler.players[i].disconnected = true;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

			if (playerCommand.startsWith("unban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
									if (playerCommand.startsWith("ipban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								if (c.playerName == PlayerHandler.players[i].playerName) {
									c.sendMessage("You cannot IP Ban yourself.");
								} else {
									if (!Connection
											.isIpBanned(PlayerHandler.players[i].connectedFrom)) {
										Connection
												.addIpToBanList(PlayerHandler.players[i].connectedFrom);
										Connection
												.addIpToFile(PlayerHandler.players[i].connectedFrom);
										c.sendMessage("You have IP banned the user: "
												+ PlayerHandler.players[i].playerName
												+ " with the host: "
												+ PlayerHandler.players[i].connectedFrom);
										PlayerHandler.players[i].disconnected = true;
									} else {
										c.sendMessage("This user is already IP Banned.");
									}
								}
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("unipban")) {
			String UNIP = playerCommand.substring(8);
			Connection.removeIpFromBanList(UNIP);
		}
			
							if (playerCommand.equals("alltome")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
			c2.teleportToX = c.absX;
                        c2.teleportToY = c.absY;
                        c2.heightLevel = c.heightLevel;
				c2.sendMessage("Mass teleport to: " + c.playerName + "");
					}
				}
		}
		
		
					if (playerCommand.startsWith("pickup") && c.playerRights == 2) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20200) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);
						} else {
							c.sendMessage("No such item.");
						}
					} else {
						c.sendMessage("Use as ::pickup 995 200");
					}
				} catch (Exception e) {

				}
			}
			

	
				if (playerCommand.startsWith("kick")) {
				try {
					String playerToBan = playerCommand.substring(5);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								if (c2.inWild()) {
									c.sendMessage("You cannot kick a player when he is in wilderness.");
									return;
								}
								if (c2.duelStatus == 5) {
									c.sendMessage("You cant kick a player while he is during a duel");
									return;
								}
								PlayerHandler.players[i].disconnected = true;
								
								c2.sendMessage("You got kicked by @blu@ "+c.playerName+".");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
	
	
				if (playerCommand.startsWith("checkinv") && c.playerRights == 1) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 			c.getPA().otherInv(c, o);
                 			c.getDH().sendDialogues(206, 0);
							break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}
			if (playerCommand.startsWith("mute")) {
				try {
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("unmute")) {
				try {
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
		if(playerCommand.startsWith("jail")) {
			try {
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client)PlayerHandler.players[i];
							c2.teleportToX = 3460;
							c2.teleportToY = 9667;
							c2.Jail = true;
							c2.sendMessage("You have been jailed by "+c.playerName+"");
							c.sendMessage("Successfully Jailed "+c2.playerName+".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}


			if (playerCommand.startsWith("unjail")) {
				try {
					String playerToBan = playerCommand.substring(7);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								if (c2.inWild()) {
									c.sendMessage("This player is in the wilderness, not in jail.");
									return;
								}
								if (c2.duelStatus == 5 || c2.inDuelArena()) {
									c.sendMessage("This player is during a duel, and not in jail.");
									return;
								}
								c2.teleportToX = 3093;
								c2.teleportToY = 3493;
								c2.Jail = false;
								c2.sendMessage("You have been unjailed by "
										+ c.playerName
										+ ". You can now teleport.");
								c.sendMessage("Successfully unjailed "
										+ c2.playerName + ".");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

			if (playerCommand.startsWith("timedmute") && c.playerRights >= 1
					&& c.playerRights <= 3) {

				try {
					String[] args = playerCommand.split("-");
					if (args.length < 2) {
						c.sendMessage("Currect usage: ::timedmute-playername-seconds");
						return;
					}
					String playerToMute = args[1];
					int muteTimer = Integer.parseInt(args[2]) * 1000;

					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToMute)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName + " for " + muteTimer
										/ 1000 + " seconds");
								c2.muteEnd = System.currentTimeMillis()
										+ muteTimer;
								break;
							}
						}
					}

				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("teletome")) {
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.teleportToX = c.absX;
								c2.teleportToY = c.absY;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported "
										+ c2.playerName + " to you.");
								c2.sendMessage("You have been teleported to "
										+ c.playerName + "");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(name)) {
							Client c2 = (Client) PlayerHandler.players[i];
							if (c.duelStatus == 5) {
								c.sendMessage("You cannot teleport to a player during a duel.");
								return;
							}
							c.getPA().movePlayer(
									PlayerHandler.players[i].getX(),
									PlayerHandler.players[i].getY(),
									c.heightLevel);
						}
					}
				}
			}
			
			
			
						if (playerCommand.startsWith("movehome")) {
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.teleportToX = 3096;
								c2.teleportToY = 3468;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported "
										+ c2.playerName + " to home");
								c2.sendMessage("You have been teleported to home");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			
	}
	
		/* Owner Commands */
		if (c.playerRights == 3) {
			if (playerCommand.toLowerCase().startsWith("title")) {
				try {
					final String[] args = playerCommand.split("-");
					c.playerTitle = args[1];
					String color = args[2].toLowerCase();
					if (color.equals("orange"))
						c.titleColor = 0;
					if (color.equals("purple"))
						c.titleColor = 1;
					if (color.equals("red"))
						c.titleColor = 2;
					if (color.equals("green"))
						c.titleColor = 3;
					c.sendMessage("You succesfully changed your title.");
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
				} catch (final Exception e) {
					c.sendMessage("Use as ::title-[title]-[color]");
				}
			}
			
						if (playerCommand.startsWith("getip") && (playerCommand.length() > 6) && c.playerRights == 3) {
				String name = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(name)) {
							c.sendMessage(PlayerHandler.players[i].playerName
									+ " ip is "
									+ PlayerHandler.players[i].connectedFrom);
							return;
						}
					}
				}
			}
			if (playerCommand.toLowerCase().startsWith("givetitle")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client) Server.playerHandler.players[j];
						try {
							final String[] args = playerCommand.split("-");
							c2.playerTitle = args[1];
							String color = args[2].toLowerCase();
							if (color.equals("orange"))
								c2.titleColor = 0;
							if (color.equals("purple"))
								c2.titleColor = 1;
							if (color.equals("red"))
								c2.titleColor = 2;
							if (color.equals("green"))
								c2.titleColor = 3;
							c.sendMessage("You succesfully changed your title.");
							c2.updateRequired = true;
							c2.setAppearanceUpdateRequired(true);
						} catch (Exception e) {
							c.sendMessage("Player is either offline, or does not exist.");
						}
					}
				}
			}
			if (playerCommand.equalsIgnoreCase("spells")) {
				if (c.playerMagicBook == 2) {
					c.sendMessage("You switch to modern magic.");
					c.setSidebarInterface(6, 1151);
					c.playerMagicBook = 0;
				} else if (c.playerMagicBook == 0) {
					c.sendMessage("You switch to ancient magic.");
					c.setSidebarInterface(6, 12855);
					c.playerMagicBook = 1;
				} else if (c.playerMagicBook == 1) {
					c.sendMessage("You switch to lunar magic.");
					c.setSidebarInterface(6, 29999);
					c.playerMagicBook = 2;
				}
			}
			if (playerCommand.toLowerCase().startsWith("movehome")) {
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.teleportToX = 3096;
								c2.teleportToY = 3468;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported "
										+ c2.playerName + " to home");
								c2.sendMessage("You have been teleported to home");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("lvl")) {
				try {
					String[] args = playerCommand.split(" ");
					int skill = Integer.parseInt(args[1]);
					int level = Integer.parseInt(args[2]);
					if (level > 99)
						level = 99;
					else if (level < 0)
						level = 1;
					c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
					c.playerLevel[skill] = c.getPA().getLevelForXP(
							c.playerXP[skill]);
					c.getPA().refreshSkill(skill);
				} catch (Exception e) {
				}
			}
			if (playerCommand.equalsIgnoreCase("alltome")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client) Server.playerHandler.players[j];
						c2.teleportToX = c.absX;
						c2.teleportToY = c.absY;
						c2.heightLevel = c.heightLevel;
						c2.sendMessage("Mass teleport to: " + c.playerName + "");
					}
				}
			}
			if (playerCommand.toLowerCase().startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),
							Integer.parseInt(arg[2]), Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),
							Integer.parseInt(arg[2]), c.heightLevel);
			}
			if (playerCommand.toLowerCase().startsWith("getid")) {
				String a[] = playerCommand.split(" ");
				String name = "";
				int results = 0;
				for (int i = 1; i < a.length; i++)
					name = name + a[i] + " ";
				name = name.substring(0, name.length() - 1);
				c.sendMessage("Searching: " + name);
				for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {
					if (Server.itemHandler.ItemList[j] != null)
						if (Server.itemHandler.ItemList[j].itemName
								.replace("_", " ").toLowerCase()
								.contains(name.toLowerCase())) {
							c.sendMessage("@red@"
									+ Server.itemHandler.ItemList[j].itemName
											.replace("_", " ") + " - "
									+ Server.itemHandler.ItemList[j].itemId);
							results++;
						}
				}
				c.sendMessage(results + " results found...");
			}
			if (playerCommand.toLowerCase().startsWith("mute")) {
				try {
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("unmute")) {
				try {
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("bank")) {
				c.getPA().openUpBank();
			}
			if (playerCommand.startsWith("reloadspawns")) {
				Server.npcHandler = null;
				Server.npcHandler = new server.game.npcs.NPCHandler();
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("<shad=15695415>[" + c.playerName + "] " + "Has Reloaded All Npc Spawns.</col>");
					}
				}
			}
			if (playerCommand.toLowerCase().startsWith("item")) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20200) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);
						} else {
							c.sendMessage("No such item.");
						}
					} else {
						c.sendMessage("Use as ::item 995 200");
					}
				} catch (Exception e) {
				}
			}
			if (playerCommand.equalsIgnoreCase("infhp")) {
				c.getPA().requestUpdates();
				c.playerLevel[3] = 99999;
				c.getPA().refreshSkill(3);
				c.gfx0(754);
				c.sendMessage("Infiniate Health for the win.");
			}
			if (playerCommand.toLowerCase().startsWith("givedp")) {
				try {
					String[] args = playerCommand.split(" ");
					String otherplayer = args[1];
					int point = Integer.parseInt(args[2]);
					for (int i = 0; i < PlayerHandler.players.length; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(otherplayer)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.dp += point;
								c.sendMessage("You have given " + otherplayer
										+ ", " + point + " points.");
								c2.sendMessage("You have been given " + point
										+ " points by " + c.playerName + ".");
							} else {
								c.sendMessage("Use as ::givepoints name amount");
							}
						}
					}
				} catch (Exception e) {
				}
			}
			if (playerCommand.toLowerCase().startsWith("givevpoints")) {
				try {
					String[] args = playerCommand.split(" ");
					String otherplayer = args[1];
					int point = Integer.parseInt(args[2]);
					for (int i = 0; i < PlayerHandler.players.length; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(otherplayer)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.votingPoints += point;
								c.sendMessage("You have given " + otherplayer
										+ ", " + point + " points.");
								c2.sendMessage("You have been given " + point
										+ " points by " + c.playerName + ".");
							} else {
								c.sendMessage("Use as ::givevpoints name amount");
							}
						}
					}
				} catch (Exception e) {
				}
			}
			if (playerCommand.toLowerCase().startsWith("hail")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client p = (Client) Server.playerHandler.players[j];
						int randomText = Misc.random(10);
						if (randomText == 0) {
							p.forcedChat("Lol");
						} else if (randomText == 1) {
							p.forcedChat("Eazy is cool, yeah");
						} else if (randomText == 2) {
							p.forcedChat("Project-OS has the best development team ever!");
						} else if (randomText == 3) {
							p.forcedChat("Project-OSPS is the best!");
						} else if (randomText == 4) {
							p.forcedChat("Why am I so amazing?...");
						} else if (randomText == 5) {
							p.forcedChat("Fuck off");
						} else if (randomText == 6) {
							p.forcedChat("I suck cock");
						} else if (randomText == 7) {
							p.forcedChat("wtf wtf wtf!!! is this project os wtf??");
						} else if (randomText == 8) {
							p.forcedChat("RITAMÄKI FTW!");
						} else if (randomText == 4) {
							p.forcedChat("Shut the fuck up mafataka");
					}
				}
			}
			}
			if (playerCommand.toLowerCase().startsWith("kill")) {
				try {
					String playerToKill = playerCommand.substring(5);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (Server.playerHandler.players[i] != null) {
							if (Server.playerHandler.players[i].playerName
									.equalsIgnoreCase(playerToKill)) {
								c.sendMessage("You have killed the user: "
										+ Server.playerHandler.players[i].playerName);
								Client c2 = (Client) Server.playerHandler.players[i];
								c2.isDead = true;
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("massnpc")) {
				try {
					int amount = 0;
					int newNPC = Integer.parseInt(playerCommand.substring(8));
					if (newNPC > 0) {
						for (int x = 0; x < 5; x++) {
							for (int y = 0; y < 5; y++) {
								if (amount > 196)
									return;
								Server.npcHandler.spawnNpc(c, newNPC, c.absX
										+ x, c.absY + y, c.heightLevel, 0, 120,
										7, 70, 70, false, false);
								amount++;
							}
						}
						c.sendMessage("5x5 npc's spawned");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch (Exception e) {
					c.sendMessage("Nope.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("takeitem")) {
				try {
					String[] args = playerCommand.split(" ");
					int takenItemID = Integer.parseInt(args[1]);
					int takenItemAmount = Integer.parseInt(args[2]);
					String otherplayer = args[3];
					Client c2 = null;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (Server.playerHandler.players[i] != null) {
							if (Server.playerHandler.players[i].playerName
									.equalsIgnoreCase(otherplayer)) {
								c2 = (Client) Server.playerHandler.players[i];
								break;
							}
						}
					}
					if (c2 == null) {
						c.sendMessage("Player doesn't exist.");
						return;
					}
					c.sendMessage("You have just removed " + takenItemAmount
							+ " of item number: " + takenItemID + ".");
					c2.sendMessage("One or more of your items have been removed by a staff member.");
					c2.getItems().deleteItem(takenItemID, takenItemAmount);
				} catch (Exception e) {
					c.sendMessage("Use as ::takeitem ID AMOUNT PLAYERNAME.");
				}
			}
			if (playerCommand.equalsIgnoreCase("vengall")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client) Server.playerHandler.players[j];
						c2.vengOn = true;
						c2.startAnimation(4410);
					}
				}
			}
			if (playerCommand.toLowerCase().startsWith("smite")) {
				try {
					String playerToKill = playerCommand.substring(6);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (Server.playerHandler.players[i] != null) {
							if (Server.playerHandler.players[i].playerName
									.equalsIgnoreCase(playerToKill)) {

								c.startAnimation(811);
								Client c2 = (Client) Server.playerHandler.players[i];
								c2.isDead = true;
								c.gfx0(76);
								c2.handleHitMask(100);
								c2.gfx0(370);
								c2.forcedChat("FUCK NOO!");
								c2.getPA().refreshSkill(3);
								c2.dealDamage(999999);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("wipeinv")) {
				try {
					String[] args = playerCommand.split(" ");
					String otherplayer = args[1];
					Client c2 = null;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(otherplayer)) {
								c2 = (Client) PlayerHandler.players[i];
								break;
							}
						}
					}

					if (c2 == null) {
						c.sendMessage("Player doesn't exist.");
						return;
					}
					c2.getItems().removeAllItems();
					c2.sendMessage("Your inventory has been cleared by a staff member.");
					c.sendMessage("You cleared " + c2.playerName
							+ "'s inventory.");
				} catch (Exception e) {
					c.sendMessage("Use as ::wipeinv PLAYERNAME.");
				}
			}	
			if (playerCommand.toLowerCase().startsWith("pornhubb")) {
				try {
					String[] args = playerCommand.split(" ", 2);
					
					String otherplayer = args[1];
					Client c2 = null;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(otherplayer)) {
								c2 = (Client) PlayerHandler.players[i];
								break;
							}
						}
					}
					if (c2 == null) {
						c.sendMessage("Player doesn't exist.");
						return;
					}
				
					c2.getPA().sendFrame126("www.pornhub.com", 12000);
				} catch (Exception e) {
					c.sendMessage("Use as ::giveitem ID AMOUNT PLAYERNAME.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("giveitem")) {
				try {
					String[] args = playerCommand.split(" ");
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					String otherplayer = args[3];
					Client c2 = null;
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(otherplayer)) {
								c2 = (Client) PlayerHandler.players[i];
								break;
							}
						}
					}
					if (c2 == null) {
						c.sendMessage("Player doesn't exist.");
						return;
					}
					c.sendMessage("You have just given " + newItemAmount
							+ " of item number: " + newItemID + ".");
					c2.sendMessage("You have just been given item(s).");
					c2.getItems().addItem(newItemID, newItemAmount);
				} catch (Exception e) {
					c.sendMessage("Use as ::giveitem ID AMOUNT PLAYERNAME.");
				}
			}
			if (playerCommand.equalsIgnoreCase("mypos")) {
				c.sendMessage("X: " + c.absX);
				c.sendMessage("Y: " + c.absY);
				c.sendMessage("H: " + c.heightLevel);
			}
			if (playerCommand.toLowerCase().startsWith("interface")) {
				try {
					String[] args = playerCommand.split(" ");
					int a = Integer.parseInt(args[1]);
					c.getPA().showInterface(a);
				} catch (Exception e) {
					c.sendMessage("::interface ####");
				}
			}
			if (playerCommand.toLowerCase().startsWith("gfx")) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.equalsIgnoreCase("spec")) {
				c.specAmount = 10.0;
			}
			if (playerCommand.equalsIgnoreCase("infspec")) {
				c.specAmount = 10000.0;
			}
			if (playerCommand.toLowerCase().startsWith("object")) {
				String[] args = playerCommand.split(" ");
				c.getPA().makeGlobalObject(Integer.parseInt(args[1]), c.absX, c.absY, 0,
						10);
				
			}
			if (playerCommand.toLowerCase().startsWith("obtype")) {
				String[] args = playerCommand.split(" ");
				for(int i = 0; i < 20; i++)
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0,
						i);
				
			}
			if (playerCommand.toLowerCase().startsWith("npc")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if (newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY,
								0, 0, 1200, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch (Exception e) {

				}
			}
			if (playerCommand.startsWith("spawn")) {
				if(c.playerRights != 3)
					return;
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
				Client c2 = (Client)Server.playerHandler.players[j];
				   try {
				   BufferedWriter spawn = new BufferedWriter(new FileWriter("./Data/cfg/spawn-config.cfg", true));
				   String Test123 = playerCommand.substring(6);
				   int Test124 = Integer.parseInt(playerCommand.substring(6));
									if(Test124 > 0) {
										Server.npcHandler.spawnNpc(c, Test124, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
										c.sendMessage("You spawn a Npc.");
									} else {
										c.sendMessage("No such NPC.");
									}
				   try {	
					spawn.newLine();
					spawn.write("spawn = " + Test123 +"	" +c.absX+"	" +c.absY+"	0	1	0	0	0");
					c2.sendMessage("<shad=15695415>[Npc-Spawn</col>]: An Npc has been added to the map!");
					} finally {
					spawn.close();
					return;
					}
					} catch (IOException e) {
				                e.printStackTrace();
							}
						}
					}
				}
			if (playerCommand.toLowerCase().startsWith("music")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(6));
					server.game.players.Music.playMusic(c, newNPC );
				} catch (Exception e) {

				}
			}
			if (playerCommand.toLowerCase().startsWith("sound")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(6));
					c.getPA().sound(newNPC);
				} catch (Exception e) {

				}
			}

			if (playerCommand.toLowerCase().startsWith("ban")) { // use as
																	// ::ban
																	// name
				try {
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								PlayerHandler.players[i].disconnected = true;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("unban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("ipmute")) {
				try {
					String playerToBan = playerCommand.substring(7);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Connection
										.addIpToMuteList(PlayerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "
										+ PlayerHandler.players[i].playerName);
								Client c2 = (Client) PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("unipmute")) {
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Connection
										.unIPMuteUser(PlayerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "
										+ PlayerHandler.players[i].playerName);
								break;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("unipban")) {
				String UNIP = playerCommand.substring(8);
				Connection.removeIpFromBanList(UNIP);
			}
			if (playerCommand.toLowerCase().startsWith("kick")) {
				try {
					String playerToBan = playerCommand.substring(5);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								PlayerHandler.players[i].disconnected = true;
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("demote")) {
				try {
					String playerToG = playerCommand.substring(8);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToG)) {
								PlayerHandler.players[i].playerRights = 0;
								PlayerHandler.players[i].disconnected = true;
								c.sendMessage("You've demoted the user:  "
										+ PlayerHandler.players[i].playerName
										+ " IP: "
										+ PlayerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("givemod")) {
				try {
					String playerToG = playerCommand.substring(8);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToG)) {
								c.playerTitle = "Moderator";
								PlayerHandler.players[i].playerRights = 1;
								PlayerHandler.players[i].disconnected = true;
								c.sendMessage("You've promoted to moderator the user:  "
										+ PlayerHandler.players[i].playerName
										+ " IP: "
										+ PlayerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("giveadmin")) {
				try {
					String playerToG = playerCommand.substring(8);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToG)) {
								PlayerHandler.players[i].playerRights = 2;
								PlayerHandler.players[i].disconnected = true;
								c.sendMessage("You've promoted to admin the user:  "
										+ PlayerHandler.players[i].playerName
										+ " IP: "
										+ PlayerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("afk")) {
				c.startAnimation(4117);
				c.forcedText = "AFK!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			}
			if (playerCommand.toLowerCase().startsWith("givedp")) {
				try {
					String[] args = playerCommand.split(" ", 2);

					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (Server.playerHandler.players[i] != null) {
							if (Server.playerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								Client c2 = (Client) Server.playerHandler.players[i];
								c2.donPoints = Integer.parseInt(args[2]);
								c.sendMessage("You gave "
										+ Integer.parseInt(args[2])
										+ " points to " + args[1]
										+ ", he has now " + c2.donPoints
										+ " points.");
								c2.sendMessage("You recieve "
										+ Integer.parseInt(args[2])
										+ ", you now have " + c2.donPoints
										+ ".");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player must be offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("givedon")) {
				try {
					String playerToG = playerCommand.substring(8);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToG)) {
								PlayerHandler.players[i].playerRights = 4;
								PlayerHandler.players[i].disconnected = true;
								c.sendMessage("You've promoted to donator the user:  "
										+ PlayerHandler.players[i].playerName
										+ " IP: "
										+ PlayerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("givesup")) {
				try {
					String playerToG = playerCommand.substring(8);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToG)) {
								PlayerHandler.players[i].playerRights = 5;
								PlayerHandler.players[i].disconnected = true;
								c.sendMessage("You've promoted to supporter the user:  "
										+ PlayerHandler.players[i].playerName
										+ " IP: "
										+ PlayerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("givespon")) {
				try {
					String playerToG = playerCommand.substring(8);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToG)) {
								PlayerHandler.players[i].playerRights = 5;
								PlayerHandler.players[i].disconnected = true;
								c.sendMessage("You've promoted to sponsor the user:  "
										+ PlayerHandler.players[i].playerName
										+ " IP: "
										+ PlayerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("givevip")) {
				try {
					String playerToG = playerCommand.substring(8);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToG)) {
								PlayerHandler.players[i].playerRights = 6;
								PlayerHandler.players[i].disconnected = true;
								c.sendMessage("You've promoted to vip the user:  "
										+ PlayerHandler.players[i].playerName
										+ " IP: "
										+ PlayerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("update")) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			if (playerCommand.toLowerCase().startsWith("emote")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}
			if (playerCommand.toLowerCase().startsWith("god")) {
				if (c.playerStandIndex != 1501) {
					c.startAnimation(1500);
					c.playerStandIndex = 1501;
					c.playerTurnIndex = 1851;
					c.playerWalkIndex = 1851;
					c.playerTurn180Index = 1851;
					c.playerTurn90CWIndex = 1501;
					c.playerTurn90CCWIndex = 1501;
					c.playerRunIndex = 1851;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.sendMessage("You turn God mode on.");
				} else {
					c.playerStandIndex = 0x328;
					c.playerTurnIndex = 0x337;
					c.playerWalkIndex = 0x333;
					c.playerTurn180Index = 0x334;
					c.playerTurn90CWIndex = 0x335;
					c.playerTurn90CCWIndex = 0x336;
					c.playerRunIndex = 0x338;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.sendMessage("Godmode has been de-activated.");
				}
			}
			if (playerCommand.toLowerCase().startsWith("float")) {
				if (c.playerStandIndex != 3419) {
					c.startAnimation(3419);
					c.playerStandIndex = 3419;
					c.playerTurnIndex = 3419;
					c.playerWalkIndex = 3419;
					c.playerTurn180Index = 3419;
					c.playerTurn90CWIndex = 3419;
					c.playerTurn90CCWIndex = 3419;
					c.playerRunIndex = 3419;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.sendMessage("You turn floating mode on.");
				} else {
					c.playerStandIndex = 0x328;
					c.playerTurnIndex = 0x337;
					c.playerWalkIndex = 0x333;
					c.playerTurn180Index = 0x334;
					c.playerTurn90CWIndex = 0x335;
					c.playerTurn90CCWIndex = 0x336;
					c.playerRunIndex = 0x338;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.sendMessage("Godmode has been de-activated.");
				}
			}
			if (playerCommand.equalsIgnoreCase("dhpray")) {
				c.getPA().requestUpdates();
				c.playerLevel[5] = 9900000;
				c.playerLevel[3] = 1;
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(5);
			}
			if (playerCommand.equalsIgnoreCase("vipzone")) {
				c.getPA().startTeleport(2337, 9799, 0, "modern");
			}
			
				if (c.playerRights >= 1 && c.playerRights <= 7) {
				if (playerCommand.equals("prezone")) {
				c.getPA().startTeleport(2587, 9426, 4, "modern");
			}
		}

			if (playerCommand.equalsIgnoreCase("staffzone")) {
				c.getPA().startTeleport(3233, 9316, 0, "modern");
			}

			if (playerCommand.toLowerCase().startsWith("ct")) {
				c.instantWalk = !c.instantWalk;
				c.sendMessage("@red@Instant walk = " + c.instantWalk);
			}
			if (playerCommand.toLowerCase().startsWith("master")) {
				if (c.inWild())
					return;
				for (int i = 0; i < c.playerLevel.length; i++) {
					c.playerXP[i] = c.getPA().getXPForLevel(99) + 5;
					c.playerLevel[i] = c.getPA().getLevelForXP(c.playerXP[i]);
					c.getPA().refreshSkill(i);
				}
			}
		}
		
		
		if (c.playerRights == 7) {
			
				if (playerCommand.equalsIgnoreCase("staffzone")) {
				c.getPA().startTeleport(3233, 9316, 0, "modern");
			}
			
			
					if(playerCommand.startsWith("jail")) {
			try {
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(PlayerHandler.players[i] != null) {
						if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client)PlayerHandler.players[i];
							c2.teleportToX = 3460;
							c2.teleportToY = 9667;
							c2.Jail = true;
							c2.sendMessage("You have been jailed by "+c.playerName+"");
							c.sendMessage("Successfully Jailed "+c2.playerName+".");
						} 
					}
				}
			} catch(Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}


			if (playerCommand.startsWith("unjail")) {
				try {
					String playerToBan = playerCommand.substring(7);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								if (c2.inWild()) {
									c.sendMessage("This player is in the wilderness, not in jail.");
									return;
								}
								if (c2.duelStatus == 5 || c2.inDuelArena()) {
									c.sendMessage("This player is during a duel, and not in jail.");
									return;
								}
								c2.teleportToX = 3093;
								c2.teleportToY = 3493;
								c2.Jail = false;
								c2.sendMessage("You have been unjailed by "
										+ c.playerName
										+ ". You can now teleport.");
								c.sendMessage("Successfully unjailed "
										+ c2.playerName + ".");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

			if (playerCommand.startsWith("timedmute") && c.playerRights >= 1
					&& c.playerRights <= 3) {

				try {
					String[] args = playerCommand.split("-");
					if (args.length < 2) {
						c.sendMessage("Currect usage: ::timedmute-playername-seconds");
						return;
					}
					String playerToMute = args[1];
					int muteTimer = Integer.parseInt(args[2]) * 1000;

					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToMute)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: "
										+ c.playerName + " for " + muteTimer
										/ 1000 + " seconds");
								c2.muteEnd = System.currentTimeMillis()
										+ muteTimer;
								break;
							}
						}
					}

				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("teletome")) {
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.teleportToX = c.absX;
								c2.teleportToY = c.absY;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported "
										+ c2.playerName + " to you.");
								c2.sendMessage("You have been teleported to "
										+ c.playerName + "");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(name)) {
							Client c2 = (Client) PlayerHandler.players[i];
							if (c.duelStatus == 5) {
								c.sendMessage("You cannot teleport to a player during a duel.");
								return;
							}
							c.getPA().movePlayer(
									PlayerHandler.players[i].getX(),
									PlayerHandler.players[i].getY(),
									c.heightLevel);
						}
					}
				}
			}
			
				if (playerCommand.startsWith("kick")) {
				try {
					String playerToBan = playerCommand.substring(5);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								PlayerHandler.players[i].disconnected = true;
								
								c2.sendMessage("You got kicked by @blu@ "+c.playerName+".");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}




			
						if (playerCommand.startsWith("movehome")) {
				try {
					String playerToBan = playerCommand.substring(9);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client) PlayerHandler.players[i];
								c2.teleportToX = 3096;
								c2.teleportToY = 3468;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported "
										+ c2.playerName + " to home");
								c2.sendMessage("You have been teleported to home");
							}
						}
					}
				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
	
}
}
}
