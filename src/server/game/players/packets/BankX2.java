package server.game.players.packets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import server.Config;
import server.Server;
import server.game.players.Client;
import server.game.players.PacketType;
/**
 * Bank X Items
 **/
public class BankX2 implements PacketType {
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
			int Xamount = c.getInStream().readDWord();
		if (Xamount <= 0) {
			c.sendMessage("Amount must be greater than 0!");
			return;
		}
		 int yplus = c.absY +1;
		int yminus = c.absY -1;
		int xplus = c.absX +1;
		 int xminus = c.absX -1;

			if(c.dir == 5) {
				c.dir = Xamount ;
				 try {
					   BufferedWriter spawn = new BufferedWriter(new FileWriter("./Data/cfg/strdoor.cfg", true));
					   BufferedWriter spawn2 = new BufferedWriter(new FileWriter("./Data/cfg/strdoorl.cfg", true));
					   try {	
						   switch(c.dir) {
						   // out of mid doors
						   //x+
						   case 1:
							   spawn.newLine();
								 spawn.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+c.absX+" && c.absY == "+yplus+") { c.getPA().movePlayer("+xplus+" , "+c.absY+", 0); }");
								 spawn.newLine();
								 spawn2.newLine();
								 spawn2.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+c.absX+" && c.absY == "+yplus+") { c.getPA().movePlayer("+xplus+", "+yplus+", 0); }");
								 spawn2.newLine();
							   break;
							   // x-
						   case 2:
			 spawn.newLine();
			 spawn.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+c.absX+" && c.absY == "+yminus+") { c.getPA().movePlayer("+xminus+" , "+c.absY+", 0); }");
			 spawn.newLine();
			 spawn2.newLine();
			 spawn2.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+c.absX+" && c.absY == "+yminus+") { c.getPA().movePlayer("+xminus+", "+yminus+", 0); }");
			 spawn2.newLine();
			 break;
							   //y+
						   case 3:
								 spawn.newLine();
								 spawn.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+xminus+" && c.absY == "+c.absY+") { c.getPA().movePlayer("+c.absX+" , "+yplus+", 0); }");
								 spawn.newLine();
								 spawn2.newLine();
								 spawn2.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+xminus+" && c.absY == "+c.absY+") { c.getPA().movePlayer("+xminus+", "+yplus+", 0); }");
								 spawn2.newLine();
							   break;
							   
							   //y-
						   case 4:
								 spawn.newLine();
								 spawn.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+xplus+" && c.absY == "+c.absY+") { c.getPA().movePlayer("+c.absX+" , "+yminus+", 0); }");
								 spawn.newLine();
								 spawn2.newLine();
								 spawn2.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+xplus+" && c.absY == "+c.absY+") { c.getPA().movePlayer("+xplus+", "+yminus+", 0); }");
								 spawn2.newLine();
							   break;
							   //in mid doors
						   case 6://x+mid
							   spawn.newLine();
								 spawn.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+c.absX+" && c.absY == "+yminus+") { c.getPA().movePlayer("+xplus+" , "+c.absY+", 0); }");
								 spawn.newLine();
								 spawn2.newLine();
								 spawn2.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+c.absX+" && c.absY == "+yminus+") { c.getPA().movePlayer("+xplus+", "+yminus+", 0); }");
								 spawn2.newLine();
							   break;
						   case 7://x-mid
							   spawn.newLine();
								 spawn.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+c.absX+" && c.absY == "+yplus+") { c.getPA().movePlayer("+xminus+" , "+c.absY+", 0); }");
								 spawn.newLine();
								 spawn2.newLine();
								 spawn2.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+c.absX+" && c.absY == "+yplus+") { c.getPA().movePlayer("+xminus+", "+yplus+", 0); }");
								 spawn2.newLine();
							   break;
						   case 8:// y+mid
							   spawn.newLine();
								 spawn.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+xplus+" && c.absY == "+c.absY+") { c.getPA().movePlayer("+c.absX+" , "+yplus+", 0); }");
								 spawn.newLine();
								 spawn2.newLine();
								 spawn2.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+xplus+" && c.absY == "+c.absY+") { c.getPA().movePlayer("+xplus+", "+yplus+", 0); }");
								 spawn2.newLine();
							   break;
						   case 9://y-mid
							   spawn.newLine();
								 spawn.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+xminus+" && c.absY == "+c.absY+") { c.getPA().movePlayer("+c.absX+" , "+yminus+", 0); }");
								 spawn.newLine();
								 spawn2.newLine();
								 spawn2.write("if(c.absX == "+c.absX+" && c.absY == "+c.absY+" || c.absX == "+xminus+" && c.absY == "+c.absY+") { c.getPA().movePlayer("+xminus+", "+yminus+", 0); }");
								 spawn2.newLine();
							   break;
						   }
						   c.sendMessage("door has been added to strdoor"+c.dir);
						   c.dir = 0;
					
						
						} finally {
						spawn.close();
						spawn2.close();
						return;
						}
						} catch (IOException e) {
					                e.printStackTrace();
								}
				}
			
			if(c.spawningM) {
				if(c.playerRights != 3&&!c.playerName.equalsIgnoreCase("caboose"))
					return;
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
				Client c2 = (Client)Server.playerHandler.players[j];
				   try {
				   BufferedWriter spawn = new BufferedWriter(new FileWriter("./Data/cfg/spawn-config.cfg", true));
				   String Test123 = ""+Xamount+"";
				   int Test124 =Xamount;
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
				c.spawningM = false;
			}
			if(c.attackSkill) {
				if (c.inWild() || c.isInHighRiskPK()) 
					return;
			if (c.inDuelArena())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("@red@Please remove all your equipment before using this command.");
						return;
					}
				}
				try {	
				int skill = 0;
				int level = Xamount;
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
c.getPA().requestUpdates();
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
				c.attackSkill = false;
				c.sendMessage("@red@Attack level set to " +level+ ".");
				} catch (Exception e){}
		}
		
		if(c.defenceSkill) {
						if (c.inWild() || c.isInHighRiskPK()) 
					return;
			if (c.inDuelArena()) 
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("@red@Please remove all your equipment before using this command.");
						return;
					}
				}
				try {	
				int skill = 1;
				int level = Xamount;
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
c.getPA().requestUpdates();
				c.getPA().refreshSkill(skill);
				c.defenceSkill = false;
				c.sendMessage("@red@Defence level set to " +level+ ".");
				} catch (Exception e){}
		}
				if(c.strengthSkill) {
						if (c.inWild() || c.isInHighRiskPK()) 
					return;
			if (c.inDuelArena()) 
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("@red@Please remove all your equipment before using this command.");
						return;
					}
				}
				try {	
				int skill = 2;
				int level = Xamount;
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().requestUpdates();
				c.getPA().refreshSkill(skill);
				c.strengthSkill = false;
				c.sendMessage("@red@Strength level set to " +level+ ".");
				} catch (Exception e){}
		}
				if(c.healthSkill) {
						if (c.inWild() || c.isInHighRiskPK()) 
					return;
			if (c.inDuelArena()) 
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("@red@Please remove all your equipment before using this command.");
						return;
					}
				}
				try {	
				int skill = 3;
				int level = Xamount;
				if (level > 99)
					level = 99;
				else if (level < 50)
					level = 50;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
c.getPA().requestUpdates();
				c.getPA().refreshSkill(skill);
				c.healthSkill = false;
				c.sendMessage("@red@Hitpoints level set to " +level+ ".");
				} catch (Exception e){}
		}
				if(c.rangeSkill) {
						if (c.inWild() || c.isInHighRiskPK()) 
					return;
			if (c.inDuelArena()) 
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("@red@Please remove all your equipment before using this command.");
						return;
					}
				}
				try {	
				int skill = 4;
				int level = Xamount;
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
c.getPA().requestUpdates();
				c.getPA().refreshSkill(skill);
				c.rangeSkill = false;
				c.sendMessage("@red@Range level set to" +level+ ".");
				} catch (Exception e){}
		}
				if(c.prayerSkill) {
					if (c.inWild() || c.isInHighRiskPK()) 
					return;
			if (c.inDuelArena()) 
					return;
				if(c.prayerActive[25])
				return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("@red@Please remove all your equipment before using this command.");
						return;
					}
				}
				try {	
				int skill = 5;
				int level = Xamount;
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
c.getPA().requestUpdates();
				c.getPA().refreshSkill(skill);
				c.prayerSkill = false;
				c.sendMessage("@red@Prayer level set to " +level+ ".");
				} catch (Exception e){}
		}
				if(c.mageSkill) {
						if (c.inWild() || c.isInHighRiskPK()) 
					return;
			if (c.inDuelArena()) 
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("@red@Please remove all your equipment before using this command.");
						return;
					}
				}
				try {	
				int skill = 6;
				int level = Xamount;
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
c.getPA().requestUpdates();
				c.getPA().refreshSkill(skill);
				c.mageSkill = false;
				c.sendMessage("@red@Magic level set to " +level+ ".");
				} catch (Exception e){}
				
				
}
				if(c.exchange == 1) {
								if(Xamount > 10000)
					Xamount = 10000;
					try{
					if(Xamount <= c.pkPoints){
					c.pkPoints = c.pkPoints - Xamount;
					c.getItems().addItem(995,Xamount*250000);
					c.sendMessage("Successfully exchanged "+Xamount+" credits for "+Xamount*250000+" coins.");
					c.exchange = 0;
					} else {
					c.sendMessage("Successfully exchanged "+c.pkPoints+" credits for "+c.pkPoints*2500+" coins.");
					c.getItems().addItem(995,c.pkPoints*2500);
					c.pkPoints = 0;
					c.exchange = 0;
					}
					} catch (Exception e){}
				}
				
				if(c.exchange == 2) {//c.getItems().deleteItem(995,c.getItems().getItemSlot(995),1);
				if(Xamount > 10000)
					Xamount = 10000;
					try{
					if(c.getItems().playerHasItem(995,(Xamount*10000))){
					c.getItems().deleteItem(995,c.getItems().getItemSlot(995),(Xamount*10000));
					c.pkPoints = c.pkPoints + Xamount;
					c.sendMessage("Successfully exchanged "+Xamount*10000+" coins for "+Xamount+" credits.");
					c.exchange = 0;
					} else {
					c.sendMessage("Please enter the amount of times you want to exchange!");
					c.exchange = 0;
					c.getItems().deleteItem(995,c.getItems().getItemSlot(995),10000);
					}
					} catch (Exception e){}
				}
		switch(c.xInterfaceId) {
			case 5064:
			c.getItems().bankItem(c.playerItems[c.xRemoveSlot] , c.xRemoveSlot, Xamount);
			break;
				
			case 5382:
			c.getItems().fromBank(c.bankItems[c.xRemoveSlot] , c.xRemoveSlot, Xamount);
			break;
				
			case 3322:
			if(c.duelStatus <= 0) {
            	c.getTradeAndDuel().tradeItem(c.xRemoveId, c.xRemoveSlot, Xamount);
            } else {				
				c.getTradeAndDuel().stakeItem(c.xRemoveId, c.xRemoveSlot, Xamount);
			}  
			break;
				
			case 3415: 
			if(c.duelStatus <= 0) { 
            	c.getTradeAndDuel().fromTrade(c.xRemoveId, c.xRemoveSlot, Xamount);
			} 
			break;
				
			case 6669:
			c.getTradeAndDuel().fromDuel(c.xRemoveId, c.xRemoveSlot, Xamount);
			break;			
		}
	}
}