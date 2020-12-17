package server.game.players;

import server.util.Misc;

public class Home {
	public static void updates( Client player)  {
		player.sendMessage("Added brimhaven dungeon entrance at taverly island.");
		player.sendMessage("Added King in distress quest.");
		player.sendMessage("Added aubury at varrock, sells runes.");
		player.sendMessage("Most gems are cut-able now.");
		player.sendMessage("Added rune mysteries and essence mine.");
		player.sendMessage("Kalrags are found in stronghold.");
		player.sendMessage("Added sailing in rimigniton.");
		player.sendMessage("major bug fix with npcs and doors.");
		player.sendMessage("VPS purchased for one month.");
		player.sendMessage("Added edge dungeon.");
		player.sendMessage("added black demons at jogre dungeon.");
		player.sendMessage("Horrendous happenings quest.");
		player.sendMessage("Fixed Stronghold no clip.");
		player.sendMessage("Wave survival minigame under development.");

	}
	public static final int DESERt_BOOTS = 1837;
	public static final int DESeRT_SHIRT = 1833;
	public static final int DESER_ROBES = 1835;
	public static final int LAUNDRY_BASKET = 4039;
	public static void spawnObjects(Client c) {
		c.getPA().checkObjectSpawn(4156, 3418, 3488, 2, 10); //canuifis portal

		// TODO Auto-generated method stub
		c.getPA().checkObjectSpawn(1278, 2194, 3258, 2, 10);//stool
		c.getPA().checkObjectSpawn(5083, 2923, 3405, 2, 10);//stool
		c.getPA().checkObjectSpawn(-1, 2884, 3418, 0, 10);//stool
		c.getPA().checkObjectSpawn(-1, 2884, 3417, 0, 10);//drawers
		c.getPA().checkObjectSpawn(-1, 2887, 3417, 0, 10);//drawers
		c.getPA().checkObjectSpawn(-1, 2885, 3416, 0, 10);//drawers
		c.getPA().checkObjectSpawn(2213, 2885, 3417, 0, 10);//drawers
		c.getPA().checkObjectSpawn(2213, 2886, 3417, 0, 10);//drawers
		c.getPA().checkObjectSpawn(7303, 2885, 3419, 0, 10);//drawers
		c.getPA().checkObjectSpawn(12106, 2887, 3417, 0, 10);//drawers
		c.getPA().checkObjectSpawn(12106, 2884, 3417, 0, 10);//drawers
		c.getPA().checkObjectSpawn(1530, 2886, 3420, 0, 0);//bankdoor
		/*home stalls on taverly*/
		c.getPA().checkObjectSpawn(4874, 2893, 3426, 1, 10); // edge banana
		c.getPA().checkObjectSpawn(4878, 2893, 3430, 3, 10); // edge Scimitar
		c.getPA().checkObjectSpawn(4875, 2895, 3427, 3, 10); // edge Crafting
		c.getPA().checkObjectSpawn(4876, 2895, 3429, 3, 10); // edge general
		c.getPA().checkObjectSpawn(4877, 2893, 3417, 3, 10); // edge Runes
		c.getPA().checkObjectSpawn(409, 2898, 3425, 0, 10); //norm altar
		c.getPA().checkObjectSpawn(6552, 2906, 3443, 0, 10); //anc altar
		c.getPA().checkObjectSpawn(-1, 3319, 3467, 0, 0);//stool
		c.getPA().checkObjectSpawn(-1, 3319, 3468, 0, 0);//stool
		
	}
	static int scimmy [] = {1325,1321,1323,1327,1329};
	static int seeds[] = {5291,5292,5293,5294,5295,5296,5297,5298,5299,5300,5301,5302,5303,5304};
public static void secondObjectClick(Client c, int objectId, int objectx, int objectY) {
	switch(objectId){
	case 4874:
		c.getThieving().stealFromStall(1755, 1, 8, 1);
		break;
	case 4875:
		c.getThieving().stealFromStall(1963, 1, 30, 25);
		break;
	case 4876:
		c.getThieving().stealFromStall(1621, 1, 90, 50);
		break;
	case 4877:
		c.getThieving().stealFromStall(1437, Misc.random(4)+1, 220, 75);
		break;
	case 4878://scim
		c.getThieving().stealFromStall(scimmy[Misc.random(scimmy.length-1)], 1, 150, 60);
		break;
	case 14011:
		c.getThieving().stealFromStall(1993, 1, 150, 80);
		break;
	case 7053:
		c.getThieving().stealFromStall(seeds[Misc.random(seeds.length-1)], Misc.random(3)+1, 40, 30);//draynor stall
		break;
	}
}

	public static void firstClickObject(Client c, int objectId, int objectX, int objectY) {
		switch(objectId) {
		case 4490:
			if(c.absX ==3429 & c.absY ==3535 ){
				c.getPA().movePlayer(c.absX,c.absY+1,0);
				break;
			} 
			if(c.absX ==3428 & c.absY ==3535 ){
				c.getPA().movePlayer(c.absX,c.absY+1,0);
				break;
			} 
			if(c.absX ==3429 & c.absY ==3536 ){
				c.getPA().movePlayer(c.absX,c.absY-1,0);
				break;
			} 
			if(c.absX ==3428 & c.absY ==3536 ){
				c.getPA().movePlayer(c.absX,c.absY-1,0);
				break;
			} 
		case 4156:
			c.getPA().movePlayer(3423,3485,0);
		break;
		case 3490:
			if(c.absX== 3408) {
				if(c.absY ==3487 || c.absY == 3488|| c.absY ==3489 ||  c.absY ==3490) {
					c.getPA().movePlayer(3409,3488,0);
					break;
				}
			}
			if(c.absX== 3409) {
				if(c.absY ==3487 || c.absY == 3488|| c.absY ==3489 ||  c.absY ==3490) {
					c.getPA().movePlayer(3408,3488,0);
					break;
				}
			}
			break;
		case 3489:
			if(c.absX== 3408) {
				if(c.absY ==3487 || c.absY == 3488|| c.absY ==3489 ||  c.absY ==3490) {
					c.getPA().movePlayer(3409,3489,0);
					break;
				}
			}
			if(c.absX== 3409) {
				if(c.absY ==3487 || c.absY == 3488|| c.absY ==3489 ||  c.absY ==3490) {
					c.getPA().movePlayer(3408,3489,0);
					break;
				}
			}
			break;
		case 1536:
			c.getPA().checkObjectSpawn(-1, objectX, objectY, 0, 10); //anc altar
			break;
		case LAUNDRY_BASKET:
			//c.pickingLaundry = true;
			c.getDH().sendItemChat1("Picking laundry", "You manage to steal the robes.", DESeRT_SHIRT, 350);
			c.getItems().addItem(Home.DESER_ROBES, 1);
			c.getItems().addItem(Home.DESeRT_SHIRT, 1);
			c.getItems().addItem(Home.DESERt_BOOTS, 1);
			break;
		case 1596:
			if(objectX ==2935 & objectY ==3451 )
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 1, 0);//drawers
			break;
		case 1597:
			if(objectX == 2935& objectY ==3450 )
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 3, 0);//drawers
			break;
		case 1530:
			
			if(c.absX == 2893 && c.absY == 3420||c.absX == 2893 && c.absY == 3421||c.absX == 2892 && c.absY == 3421) {
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 0, 0);//drawers
				break;
			}
			if(c.absX == 2896 && c.absY == 3451||c.absX == 2896 && c.absY == 3450||c.absX == 2896 && c.absY == 3449||c.absX == 2897 && c.absY == 3450) {
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 1, 0);//drawers
				break;
			}
			if(c.absX == 2884 && c.absY == 3422||c.absX == 2884 && c.absY == 3423||c.absX == 2884 && c.absY == 3424) {
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 1, 0);//drawers
				break;
			}
			if(c.absX == 2900 && c.absY == 3441||c.absX == 2899 && c.absY == 3441||c.absX == 2899 && c.absY == 3440) {
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 1, 0);//drawers
				break;
			}
			if(c.absX == 2885 && c.absY == 3444||c.absX == 2883 && c.absY == 3444||c.absX == 2884 && c.absY == 3444||c.absX == 2884 && c.absY == 3443) {
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 0, 0);//drawers
				break;
			}
			if(c.absX == 2884 && c.absY == 3441||c.absX == 2884 && c.absY == 3440) {
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 0, 0);//drawers
				break;
			}
			if(c.absX == 2884 && c.absY == 3438||c.absX == 2884 && c.absY == 3437) {
				c.getPA().makeGlobalObject(objectId, objectX, objectY, 0, 0);//drawers
				break;
			}
			break;
		}
	}

}
