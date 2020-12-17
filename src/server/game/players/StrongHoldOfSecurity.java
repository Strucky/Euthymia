package server.game.players;

import server.Server;
import server.event.Task;

public class StrongHoldOfSecurity {
	public static final int CRADLE_OF_LIFE = 16047;

	public static void spawnObjects(Client c) {
		c.getPA().checkObjectSpawn(2605, 3081, 3421, 0, 10);// entrance ladder
		c.getPA().checkObjectSpawn(CRADLE_OF_LIFE, 2486, 4974, 0, 10);// entrance
																		// ladder
	}

	public static final int SECOND_FLOOR_RIGHT_DOOR = 16065;
	public static final int SECOND_FLOOR_LEFT_DOOR = 16066;

	public static final int THIRD_FLOOR_RIGHT_DOOR = 16090;
	public static final int THIRD_FLOOR_LEFT_DOOR = 16089;

	public static final int BOX_OF_HEALTH = 16118;
	public static final int GRAIN_OF_PLENTY = 16077;
	public static void setObjectUsingFalse(Client c, int ticks) {

				if(c.objectAction5 > 0)
				c.objectAction5 = 0;
		

	}
	public static void clickingConfirm(Client c, int actionButton) {
		switch (actionButton) {
		case 9157:
			if (c.objectAction5 == 1) {
				setObjectUsingFalse(c,1);
				c.getPA().movePlayer(1860, 5245, 0);
				c.sendMessage("You enter the Stronghold security");
				c.inStronghold = true;
			}
			if (c.objectAction5 == 2) {
				setObjectUsingFalse(c,1);
				c.getPA().movePlayer(3080, 3421, 0);
				c.sendMessage("You left the Stronghold security");
				c.inStronghold = false;
			}
			if (c.objectAction5 == 3) {
				setObjectUsingFalse(c,1);
				c.getPA().movePlayer(2145, 5283, 0);
			}
			if (c.objectAction5 == 4) {
				setObjectUsingFalse(c,1);
				c.getPA().movePlayer(1904, 5221, 0);
			}
			if (c.objectAction5 == 5) {
				setObjectUsingFalse(c,1);
				c.getPA().movePlayer(2023, 5217, 0);
			}
			if (c.objectAction5 == 6) {
				setObjectUsingFalse(c,1);
				c.getPA().movePlayer(2486, 4970, 0);
				c.sendMessage("You are now on the last floor.");
			}
			if(c.objectAction5==7){
				c.getPA().movePlayer(2044, 5244, 0);
				setObjectUsingFalse(c,1);
				c.sendMessage("You are now on the second floor.");
			}
			if(c.objectAction5==8){
				c.getPA().movePlayer(2128, 5252, 0);
				setObjectUsingFalse(c,1);
				c.sendMessage("You are now on the third floor.");
				c.getDH().sendPlayerChat1(
						"This is getting scary, I must be careful.");
			}
			break;
		case 9158:
			if (c.objectAction5 > 0) {
				setObjectUsingFalse(c,1);
				c.getPA().closeAllWindows();
			}

			break;
		}
	}

	public static void firstClickObject(Client c, int x, int y, int objectId) {
		strongHoldDoor(c, x, y, objectId);
		switch (objectId) {

		case 16116:
			if (x == 2120 && y == 5258) {
				if (c.sStage >= 3) {
					c.getDH().sendOption2("Skip Floor.",
							"Nevermind, I want to say here.");
					c.objectAction5 = 3;
				}
			}
			break;
		case 16150:
			if (x == 1863 && y == 5238) {
				if (c.sStage >= 1) {
					c.getDH().sendOption2("Skip Floor.",
							"Nevermind, I want to say here.");
					c.objectAction5 = 4;

				}
			}
			break;
		case 16082:
			if (x == 2039 && y == 5240) {
				if (c.sStage >= 2) {
					c.getDH().sendOption2("Skip Floor.",
							"Nevermind, I want to say here.");
					c.objectAction5 = 5;

				}

			}
			break;
		case 7272:
			if (x == 2490 && y == 4972) {
				c.getDH().sendOption2("Leave the stronghold.",
						"Nevermind, I want to say here.");
				c.objectAction5 = 2;
			}
			break;
		case 16115:
			if (x == 2148 && y == 5284) {
				c.getDH().sendOption2("Climb down",
						"I dont want to enter, its scary.");
				c.objectAction5 = 6;

			}
			break;
		/*
		 * takes you to stronghold first level.
		 */
		case 2605:
			if (x == 3081 && y == 3421) {
				c.getDH().sendOption2("Enter the stronghold",
						"I dont want to enter, its scary.");
				c.objectAction5 = 1;
			}
			break;
		/*
		 * climb from first ladder to surface
		 */
		case 16148:
		case 16080:
		case 16146:
		case 16078:
		case 16114:
		case 16112:
			c.getDH().sendOption2("Leave the stronghold.",
					"Nevermind, I want to say here.");
			c.objectAction5 = 2;
			break;
		case 16149: // Stairs to 2nd floor
			if (x == 1902 && y == 5222) {
				c.getDH().sendOption2("Climb donw the stairs.",
						"Nevermind, I want to say here.");
			c.objectAction5 = 7;
			}
			break;
		case 16081:// 3rd floor stairs on second floor
			if (x == 2026 && y == 5218) {
				c.getDH().sendOption2("Climb donw the stairs.",
						"Nevermind, I want to say here.");
		c.objectAction5 = 8;
			}
			break;
		}
	}

	public static void firstClickObjectRewards(Client c, int x, int y,
			int objectId) {
		switch (objectId) {
		case CRADLE_OF_LIFE:
			if (c.sStage == 3) {
				c.sStage = 4;
				if (c.getItems().playerHasEquipped(9005)
						|| c.getItems().playerHasItem(9005)) {
					c.getDH()
							.sendStatement(
									"I should drop my boots before choosing another pair");
					return;
				}
				if (c.getItems().playerHasEquipped(9006)
						|| c.getItems().playerHasItem(9006)) {
					c.getDH()
							.sendStatement(
									"I should drop my boots before choosing another pair");
					return;
				}
				for (int i = 0; i < c.bankItems.length; i++) {
					if (c.bankItems[i] == 9005 || c.bankItems[i] == 9006) {
						c.bankItems[i] = -1;
					}
				}
				c.getDH().sendOption2("Give me the colorful ones",
						"Give me the fighter looking pair");
			}
			// c.sStage = 3;
			break;
		case GRAIN_OF_PLENTY:
			if (c.sStage == 1) {
				c.getItems().addItem(10400, 1);
				c.getItems().addItem(2365, 1);
				c.getItems().addItem(995, 17069);
				c.getDH().sendItemChat1("Money is the root of all evil",
						"Don't be doomed by wealth.", 2365, 350);
				c.sStage = 2;
			}
			break;
		case BOX_OF_HEALTH:
			if (c.sStage == 2) {
				c.getItems().addItem(385, 10);
				c.getItems().addItem(995, 23069);
				c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.getDH().sendItemChat1("You've been restored",
						"be careful...", 2448, 150);
				c.sStage = 3;
			}
			break;
		/*
		 * first chest
		 */
		case 16135:
			if (c.sStage < 1) {
				c.getItems().addItem(10402, 1);
				c.getItems().addItem(995, 15699);
				c.getDH().sendItemChat1("Money is the root of all evil",
						"Although, here's a mighty reward.", 995, 350);
				c.sStage = 1;
			}
			break;
		}
	}

	private static void strongHoldDoor(Client c, int x, int y, int objectId) {
		// TODO Auto-generated method stub
		switch (objectId) {
		case 16124:
			// 1st flor
			if (c.absX == 1859 && c.absY == 5238 || c.absX == 1858
					&& c.absY == 5238) {
				c.getPA().movePlayer(1859, 5239, 0);
			} else if (c.absX == 1859 && c.absY == 5239 || c.absX == 1858
					&& c.absY == 5239) {
				c.getPA().movePlayer(1859, 5238, 0);
			}

			if (c.absX == 1859 && c.absY == 5236 || c.absX == 1858
					&& c.absY == 5236) {
				c.getPA().movePlayer(1859, 5235, 0);
			} else if (c.absX == 1859 && c.absY == 5235 || c.absX == 1858
					&& c.absY == 5235) {
				c.getPA().movePlayer(1859, 5236, 0);
			}

			if (c.absX == 1864 && c.absY == 5227 || c.absX == 1864
					&& c.absY == 5226) {
				c.getPA().movePlayer(1865, 5227, 0);
			} else if (c.absX == 1865 && c.absY == 5227 || c.absX == 1865
					&& c.absY == 5226) {
				c.getPA().movePlayer(1864, 5227, 0);
			}

			if (c.absX == 1867 && c.absY == 5227 || c.absX == 1867
					&& c.absY == 5226) {
				c.getPA().movePlayer(1868, 5227, 0);
			} else if (c.absX == 1868 && c.absY == 5227 || c.absX == 1868
					&& c.absY == 5226) {
				c.getPA().movePlayer(1867, 5227, 0);
			}

			if (c.absX == 1866 && c.absY == 5218 || c.absX == 1866
					&& c.absY == 5217) {
				c.getPA().movePlayer(1867, 5218, 0);
			} else if (c.absX == 1867 && c.absY == 5218 || c.absX == 1867
					&& c.absY == 5217) {
				c.getPA().movePlayer(1866, 5218, 0);
			}

			if (c.absX == 1869 && c.absY == 5218 || c.absX == 1869
					&& c.absY == 5217) {
				c.getPA().movePlayer(1870, 5218, 0);
			} else if (c.absX == 1870 && c.absY == 5218 || c.absX == 1870
					&& c.absY == 5217) {
				c.getPA().movePlayer(1869, 5218, 0);
			}

			if (c.absX == 1861 && c.absY == 5213 || c.absX == 1860
					&& c.absY == 5213) {
				c.getPA().movePlayer(1861, 5212, 0);
			} else if (c.absX == 1861 && c.absY == 5212 || c.absX == 1860
					&& c.absY == 5212) {
				c.getPA().movePlayer(1861, 5213, 0);
			}

			if (c.absX == 1861 && c.absY == 5210 || c.absX == 1860
					&& c.absY == 5210) {
				c.getPA().movePlayer(1861, 5209, 0);
			} else if (c.absX == 1861 && c.absY == 5209 || c.absX == 1860
					&& c.absY == 5209) {
				c.getPA().movePlayer(1861, 5210, 0);
			}

			if (c.absX == 1875 && c.absY == 5240 || c.absX == 1875
					&& c.absY == 5239) {
				c.getPA().movePlayer(1876, 5240, 0);
			} else if (c.absX == 1876 && c.absY == 5240 || c.absX == 1876
					&& c.absY == 5239) {
				c.getPA().movePlayer(1875, 5240, 0);
			}

			if (c.absX == 1878 && c.absY == 5240 || c.absX == 1878
					&& c.absY == 5239) {
				c.getPA().movePlayer(1879, 5240, 0);
			} else if (c.absX == 1879 && c.absY == 5240 || c.absX == 1879
					&& c.absY == 5239) {
				c.getPA().movePlayer(1878, 5240, 0);
			}

			if (c.absX == 1883 && c.absY == 5244 || c.absX == 1883
					&& c.absY == 5243) {
				c.getPA().movePlayer(1884, 5244, 0);
			} else if (c.absX == 1884 && c.absY == 5244 || c.absX == 1884
					&& c.absY == 5243) {
				c.getPA().movePlayer(1883, 5244, 0);
			}

			if (c.absX == 1886 && c.absY == 5244 || c.absX == 1886
					&& c.absY == 5243) {
				c.getPA().movePlayer(1887, 5244, 0);
			} else if (c.absX == 1887 && c.absY == 5244 || c.absX == 1887
					&& c.absY == 5243) {
				c.getPA().movePlayer(1886, 5244, 0);
			}

			if (c.absX == 1903 && c.absY == 5243 || c.absX == 1903
					&& c.absY == 5242) {
				c.getPA().movePlayer(1904, 5243, 0);
			} else if (c.absX == 1904 && c.absY == 5243 || c.absX == 1904
					&& c.absY == 5242) {
				c.getPA().movePlayer(1903, 5243, 0);
			}

			if (c.absX == 1907 && c.absY == 5243 || c.absX == 1907
					&& c.absY == 5242) {
				c.getPA().movePlayer(1908, 5243, 0);
			} else if (c.absX == 1908 && c.absY == 5243 || c.absX == 1908
					&& c.absY == 5242) {
				c.getPA().movePlayer(1907, 5243, 0);
			}

			if (c.absX == 1905 && c.absY == 5234 || c.absX == 1904
					&& c.absY == 5234) {
				c.getPA().movePlayer(1905, 5233, 0);
			} else if (c.absX == 1905 && c.absY == 5233 || c.absX == 1904
					&& c.absY == 5233) {
				c.getPA().movePlayer(1905, 5234, 0);
			}

			if (c.absX == 1905 && c.absY == 5231 || c.absX == 1904
					&& c.absY == 5231) {
				c.getPA().movePlayer(1905, 5230, 0);
			} else if (c.absX == 1905 && c.absY == 5230 || c.absX == 1904
					&& c.absY == 5230) {
				c.getPA().movePlayer(1905, 5231, 0);
			}

			if (c.absX == 1889 && c.absY == 5236 || c.absX == 1889
					&& c.absY == 5235) {
				c.getPA().movePlayer(1888, 5236, 0);
			} else if (c.absX == 1888 && c.absY == 5236 || c.absX == 1888
					&& c.absY == 5235) {
				c.getPA().movePlayer(1889, 5236, 0);
			}

			if (c.absX == 1886 && c.absY == 5236 || c.absX == 1886
					&& c.absY == 5235) {
				c.getPA().movePlayer(1885, 5236, 0);
			} else if (c.absX == 1885 && c.absY == 5236 || c.absX == 1885
					&& c.absY == 5235) {
				c.getPA().movePlayer(1886, 5236, 0);
			}

			if (c.absX == 1861 && c.absY == 5199 || c.absX == 1860
					&& c.absY == 5199) {
				c.getPA().movePlayer(1861, 5198, 0);
			} else if (c.absX == 1861 && c.absY == 5198 || c.absX == 1860
					&& c.absY == 5198) {
				c.getPA().movePlayer(1861, 5199, 0);
			}

			if (c.absX == 1861 && c.absY == 5196 || c.absX == 1860
					&& c.absY == 5196) {
				c.getPA().movePlayer(1861, 5195, 0);
			} else if (c.absX == 1861 && c.absY == 5195 || c.absX == 1860
					&& c.absY == 5195) {
				c.getPA().movePlayer(1861, 5196, 0);
			}

			if (c.absX == 1876 && c.absY == 5191 || c.absX == 1877
					&& c.absY == 5191) {
				c.getPA().movePlayer(1876, 5192, 0);
			} else if (c.absX == 1876 && c.absY == 5192 || c.absX == 1877
					&& c.absY == 5192) {
				c.getPA().movePlayer(1876, 5191, 0);
			}

			if (c.absX == 1876 && c.absY == 5194 || c.absX == 1877
					&& c.absY == 5194) {
				c.getPA().movePlayer(1876, 5195, 0);
			} else if (c.absX == 1876 && c.absY == 5195 || c.absX == 1877
					&& c.absY == 5195) {
				c.getPA().movePlayer(1876, 5194, 0);
			}

			if (c.absX == 1875 && c.absY == 5204 || c.absX == 1874
					&& c.absY == 5204) {
				c.getPA().movePlayer(1875, 5205, 0);
			} else if (c.absX == 1875 && c.absY == 5205 || c.absX == 1874
					&& c.absY == 5205) {
				c.getPA().movePlayer(1875, 5204, 0);
			}

			if (c.absX == 1875 && c.absY == 5207 || c.absX == 1874
					&& c.absY == 5207) {
				c.getPA().movePlayer(1875, 5208, 0);
			} else if (c.absX == 1875 && c.absY == 5208 || c.absX == 1874
					&& c.absY == 5208) {
				c.getPA().movePlayer(1875, 5207, 0);
			}

			if (c.absX == 1879 && c.absY == 5222 || c.absX == 1878
					&& c.absY == 5222) {
				c.getPA().movePlayer(1879, 5223, 0);
			} else if (c.absX == 1879 && c.absY == 5223 || c.absX == 1878
					&& c.absY == 5223) {
				c.getPA().movePlayer(1879, 5222, 0);
			}

			if (c.absX == 1878 && c.absY == 5225 || c.absX == 1879
					&& c.absY == 5225) {
				c.getPA().movePlayer(1878, 5226, 0);
			} else if (c.absX == 1878 && c.absY == 5226 || c.absX == 1879
					&& c.absY == 5226) {
				c.getPA().movePlayer(1878, 5225, 0);
			}

			if (c.absX == 1878 && c.absY == 5225 || c.absX == 1879
					&& c.absY == 5225) {
				c.getPA().movePlayer(1878, 5226, 0);
			} else if (c.absX == 1878 && c.absY == 5226 || c.absX == 1879
					&& c.absY == 5226) {
				c.getPA().movePlayer(1878, 5225, 0);
			}

			if (c.absX == 1890 && c.absY == 5212 || c.absX == 1889
					&& c.absY == 5212) {
				c.getPA().movePlayer(1890, 5211, 0);
			} else if (c.absX == 1890 && c.absY == 5211 || c.absX == 1889
					&& c.absY == 5211) {
				c.getPA().movePlayer(1890, 5212, 0);
			}

			if (c.absX == 1890 && c.absY == 5208 || c.absX == 1889
					&& c.absY == 5208) {
				c.getPA().movePlayer(1889, 5207, 0);
			} else if (c.absX == 1890 && c.absY == 5207 || c.absX == 1889
					&& c.absY == 5207) {
				c.getPA().movePlayer(1889, 5208, 0);
			}

			if (c.absX == 1893 && c.absY == 5212 || c.absX == 1893
					&& c.absY == 5213) {
				c.getPA().movePlayer(1894, 5213, 0);
			} else if (c.absX == 1894 && c.absY == 5212 || c.absX == 1894
					&& c.absY == 5213) {
				c.getPA().movePlayer(1893, 5213, 0);
			}

			if (c.absX == 1896 && c.absY == 5212 || c.absX == 1896
					&& c.absY == 5213) {
				c.getPA().movePlayer(1897, 5213, 0);
			} else if (c.absX == 1897 && c.absY == 5212 || c.absX == 1897
					&& c.absY == 5213) {
				c.getPA().movePlayer(1896, 5213, 0);
			}

			if (c.absX == 1896 && c.absY == 5212 || c.absX == 1896
					&& c.absY == 5213) {
				c.getPA().movePlayer(1897, 5213, 0);
			} else if (c.absX == 1897 && c.absY == 5212 || c.absX == 1897
					&& c.absY == 5213) {
				c.getPA().movePlayer(1896, 5213, 0);
			}

			if (c.absX == 1903 && c.absY == 5204 || c.absX == 1903
					&& c.absY == 5203) {
				c.getPA().movePlayer(1904, 5204, 0);
			} else if (c.absX == 1904 && c.absY == 5204 || c.absX == 1904
					&& c.absY == 5203) {
				c.getPA().movePlayer(1903, 5204, 0);
			}

			if (c.absX == 1906 && c.absY == 5204 || c.absX == 1906
					&& c.absY == 5203) {
				c.getPA().movePlayer(1907, 5204, 0);
			} else if (c.absX == 1907 && c.absY == 5204 || c.absX == 1907
					&& c.absY == 5203) {
				c.getPA().movePlayer(1906, 5204, 0);
			}

			if (c.absX == 1878 && c.absY == 5189 || c.absX == 1878
					&& c.absY == 5188) {
				c.getPA().movePlayer(1879, 5189, 0);
			} else if (c.absX == 1879 && c.absY == 5189 || c.absX == 1879
					&& c.absY == 5188) {
				c.getPA().movePlayer(1878, 5189, 0);
			}

			if (c.absX == 1881 && c.absY == 5189 || c.absX == 1881
					&& c.absY == 5188) {
				c.getPA().movePlayer(1882, 5189, 0);
			} else if (c.absX == 1882 && c.absY == 5189 || c.absX == 1882
					&& c.absY == 5188) {
				c.getPA().movePlayer(1881, 5189, 0);
			}

			if (c.absX == 1912 && c.absY == 5206 || c.absX == 1911
					&& c.absY == 5206) {
				c.getPA().movePlayer(1912, 5207, 0);
			} else if (c.absX == 1911 && c.absY == 5207 || c.absX == 1912
					&& c.absY == 5207) {
				c.getPA().movePlayer(1912, 5206, 0);
			}

			if (c.absX == 1912 && c.absY == 5209 || c.absX == 1911
					&& c.absY == 5209) {
				c.getPA().movePlayer(1912, 5210, 0);
			} else if (c.absX == 1911 && c.absY == 5210 || c.absX == 1912
					&& c.absY == 5210) {
				c.getPA().movePlayer(1912, 5209, 0);
			}

			break;

		case 16123:
			// 1st Floor
			if (c.absX == 1858 && c.absY == 5239 || c.absX == 1859
					&& c.absY == 5239) {
				c.getPA().movePlayer(1858, 5238, 0);
			} else if (c.absX == 1858 && c.absY == 5238 || c.absX == 1859
					&& c.absY == 5238) {
				c.getPA().movePlayer(1858, 5239, 0);
			}

			if (c.absX == 1858 && c.absY == 5236 || c.absX == 1859
					&& c.absY == 5236) {
				c.getPA().movePlayer(1858, 5235, 0);
			} else if (c.absX == 1858 && c.absY == 5235 || c.absX == 1859
					&& c.absY == 5235) {
				c.getPA().movePlayer(1858, 5236, 0);
			}

			if (c.absX == 1864 && c.absY == 5227 || c.absX == 1864
					&& c.absY == 5226) {
				c.getPA().movePlayer(1865, 5226, 0);
			} else if (c.absX == 1865 && c.absY == 5227 || c.absX == 1865
					&& c.absY == 5226) {
				c.getPA().movePlayer(1864, 5226, 0);
			}

			if (c.absX == 1867 && c.absY == 5226 || c.absX == 1867
					&& c.absY == 5227) {
				c.getPA().movePlayer(1868, 5226, 0);
			} else if (c.absX == 1868 && c.absY == 5226 || c.absX == 1868
					&& c.absY == 5227) {
				c.getPA().movePlayer(1867, 5226, 0);
			}

			if (c.absX == 1866 && c.absY == 5217 || c.absX == 1866
					&& c.absY == 5218) {
				c.getPA().movePlayer(1867, 5217, 0);
			} else if (c.absX == 1867 && c.absY == 5217 || c.absX == 1867
					&& c.absY == 5218) {
				c.getPA().movePlayer(1866, 5217, 0);
			}

			if (c.absX == 1869 && c.absY == 5217 || c.absX == 1869
					&& c.absY == 5218) {
				c.getPA().movePlayer(1870, 5217, 0);
			} else if (c.absX == 1870 && c.absY == 5217 || c.absX == 1870
					&& c.absY == 5218) {
				c.getPA().movePlayer(1869, 5217, 0);
			}

			if (c.absX == 1860 && c.absY == 5213 || c.absX == 1861
					&& c.absY == 5213) {
				c.getPA().movePlayer(1860, 5212, 0);
			} else if (c.absX == 1860 && c.absY == 5212 || c.absX == 1861
					&& c.absY == 5212) {
				c.getPA().movePlayer(1860, 5213, 0);
			}

			if (c.absX == 1860 && c.absY == 5210 || c.absX == 1861
					&& c.absY == 5210) {
				c.getPA().movePlayer(1860, 5209, 0);
			} else if (c.absX == 1860 && c.absY == 5209 || c.absX == 1861
					&& c.absY == 5209) {
				c.getPA().movePlayer(1860, 5210, 0);
			}

			if (c.absX == 1875 && c.absY == 5239 || c.absX == 1875
					&& c.absY == 5240) {
				c.getPA().movePlayer(1876, 5239, 0);
			} else if (c.absX == 1876 && c.absY == 5239 || c.absX == 1876
					&& c.absY == 5240) {
				c.getPA().movePlayer(1875, 5239, 0);
			}

			if (c.absX == 1878 && c.absY == 5239 || c.absX == 1878
					&& c.absY == 5240) {
				c.getPA().movePlayer(1879, 5239, 0);
			} else if (c.absX == 1879 && c.absY == 5239 || c.absX == 1879
					&& c.absY == 5240) {
				c.getPA().movePlayer(1878, 5239, 0);
			}

			if (c.absX == 1883 && c.absY == 5243 || c.absX == 1883
					&& c.absY == 5244) {
				c.getPA().movePlayer(1884, 5243, 0);
			} else if (c.absX == 1884 && c.absY == 5243 || c.absX == 1884
					&& c.absY == 5244) {
				c.getPA().movePlayer(1883, 5243, 0);
			}

			if (c.absX == 1886 && c.absY == 5243 || c.absX == 1886
					&& c.absY == 5244) {
				c.getPA().movePlayer(1887, 5243, 0);
			} else if (c.absX == 1887 && c.absY == 5243 || c.absX == 1887
					&& c.absY == 5244) {
				c.getPA().movePlayer(1886, 5243, 0);
			}

			if (c.absX == 1903 && c.absY == 5242 || c.absX == 1903
					&& c.absY == 5243) {
				c.getPA().movePlayer(1904, 5242, 0);
			} else if (c.absX == 1904 && c.absY == 5242 || c.absX == 1904
					&& c.absY == 5243) {
				c.getPA().movePlayer(1903, 5242, 0);
			}

			if (c.absX == 1907 && c.absY == 5242 || c.absX == 1907
					&& c.absY == 5243) {
				c.getPA().movePlayer(1908, 5242, 0);
			} else if (c.absX == 1908 && c.absY == 5242 || c.absX == 1908
					&& c.absY == 5243) {
				c.getPA().movePlayer(1907, 5242, 0);
			}

			if (c.absX == 1904 && c.absY == 5234 || c.absX == 1905
					&& c.absY == 5234) {
				c.getPA().movePlayer(1904, 5233, 0);
			} else if (c.absX == 1904 && c.absY == 5233 || c.absX == 1905
					&& c.absY == 5233) {
				c.getPA().movePlayer(1904, 5234, 0);
			}

			if (c.absX == 1904 && c.absY == 5231 || c.absX == 1905
					&& c.absY == 5231) {
				c.getPA().movePlayer(1904, 5230, 0);
			} else if (c.absX == 1904 && c.absY == 5230 || c.absX == 1905
					&& c.absY == 5230) {
				c.getPA().movePlayer(1904, 5231, 0);
			}

			if (c.absX == 1889 && c.absY == 5235 || c.absX == 1889
					&& c.absY == 5236) {
				c.getPA().movePlayer(1888, 5235, 0);
			} else if (c.absX == 1888 && c.absY == 5235 || c.absX == 1888
					&& c.absY == 5236) {
				c.getPA().movePlayer(1889, 5235, 0);
			}

			if (c.absX == 1886 && c.absY == 5235 || c.absX == 1886
					&& c.absY == 5236) {
				c.getPA().movePlayer(1885, 5235, 0);
			} else if (c.absX == 1885 && c.absY == 5235 || c.absX == 1885
					&& c.absY == 5236) {
				c.getPA().movePlayer(1886, 5235, 0);
			}

			if (c.absX == 1860 && c.absY == 5199 || c.absX == 1861
					&& c.absY == 5199) {
				c.getPA().movePlayer(1860, 5198, 0);
			} else if (c.absX == 1860 && c.absY == 5198 || c.absX == 1861
					&& c.absY == 5198) {
				c.getPA().movePlayer(1860, 5199, 0);
			}

			if (c.absX == 1860 && c.absY == 5196 || c.absX == 1861
					&& c.absY == 5196) {
				c.getPA().movePlayer(1860, 5195, 0);
			} else if (c.absX == 1860 && c.absY == 5195 || c.absX == 1861
					&& c.absY == 5195) {
				c.getPA().movePlayer(1860, 5196, 0);
			}

			if (c.absX == 1877 && c.absY == 5191 || c.absX == 1876
					&& c.absY == 5191) {
				c.getPA().movePlayer(1877, 5192, 0);
			} else if (c.absX == 1877 && c.absY == 5192 || c.absX == 1876
					&& c.absY == 5192) {
				c.getPA().movePlayer(1877, 5191, 0);
			}

			if (c.absX == 1877 && c.absY == 5194 || c.absX == 1876
					&& c.absY == 5194) {
				c.getPA().movePlayer(1877, 5195, 0);
			} else if (c.absX == 1877 && c.absY == 5195 || c.absX == 1876
					&& c.absY == 5195) {
				c.getPA().movePlayer(1877, 5194, 0);
			}

			if (c.absX == 1874 && c.absY == 5204 || c.absX == 1875
					&& c.absY == 5204) {
				c.getPA().movePlayer(1874, 5205, 0);
			} else if (c.absX == 1874 && c.absY == 5205 || c.absX == 1875
					&& c.absY == 5205) {
				c.getPA().movePlayer(1874, 5204, 0);
			}

			if (c.absX == 1874 && c.absY == 5207 || c.absX == 1875
					&& c.absY == 5207) {
				c.getPA().movePlayer(1874, 5208, 0);
			} else if (c.absX == 1874 && c.absY == 5208 || c.absX == 1875
					&& c.absY == 5208) {
				c.getPA().movePlayer(1874, 5207, 0);
			}

			if (c.absX == 1878 && c.absY == 5222 || c.absX == 1879
					&& c.absY == 5222) {
				c.getPA().movePlayer(1878, 5223, 0);
			} else if (c.absX == 1878 && c.absY == 5223 || c.absX == 1879
					&& c.absY == 5223) {
				c.getPA().movePlayer(1878, 5222, 0);
			}

			if (c.absX == 1879 && c.absY == 5225 || c.absX == 1878
					&& c.absY == 5225) {
				c.getPA().movePlayer(1879, 5226, 0);
			} else if (c.absX == 1879 && c.absY == 5226 || c.absX == 1878
					&& c.absY == 5226) {
				c.getPA().movePlayer(1879, 5225, 0);
			}

			if (c.absX == 1889 && c.absY == 5212 || c.absX == 1890
					&& c.absY == 5212) {
				c.getPA().movePlayer(1889, 5211, 0);
			} else if (c.absX == 1889 && c.absY == 5211 || c.absX == 1890
					&& c.absY == 5211) {
				c.getPA().movePlayer(1889, 5212, 0);
			}

			if (c.absX == 1889 && c.absY == 5208 || c.absX == 1890
					&& c.absY == 5208) {
				c.getPA().movePlayer(1890, 5207, 0);
			} else if (c.absX == 1889 && c.absY == 5207 || c.absX == 1890
					&& c.absY == 5207) {
				c.getPA().movePlayer(1890, 5208, 0);
			}

			if (c.absX == 1893 && c.absY == 5213 || c.absX == 1893
					&& c.absY == 5212) {
				c.getPA().movePlayer(1894, 5212, 0);
			} else if (c.absX == 1894 && c.absY == 5213 || c.absX == 1894
					&& c.absY == 5212) {
				c.getPA().movePlayer(1893, 5212, 0);
			}

			if (c.absX == 1896 && c.absY == 5213 || c.absX == 1896
					&& c.absY == 5212) {
				c.getPA().movePlayer(1897, 5212, 0);
			} else if (c.absX == 1897 && c.absY == 5213 || c.absX == 1897
					&& c.absY == 5212) {
				c.getPA().movePlayer(1896, 5212, 0);
			}

			if (c.absX == 1903 && c.absY == 5204 || c.absX == 1903
					&& c.absY == 5203) {
				c.getPA().movePlayer(1904, 5203, 0);
			} else if (c.absX == 1904 && c.absY == 5204 || c.absX == 1904
					&& c.absY == 5203) {
				c.getPA().movePlayer(1903, 5203, 0);
			}

			if (c.absX == 1906 && c.absY == 5204 || c.absX == 1906
					&& c.absY == 5203) {
				c.getPA().movePlayer(1907, 5203, 0);
			} else if (c.absX == 1907 && c.absY == 5204 || c.absX == 1907
					&& c.absY == 5203) {
				c.getPA().movePlayer(1906, 5203, 0);
			}

			if (c.absX == 1878 && c.absY == 5188 || c.absX == 1878
					&& c.absY == 5189) {
				c.getPA().movePlayer(1879, 5188, 0);
			} else if (c.absX == 1879 && c.absY == 5188 || c.absX == 1879
					&& c.absY == 5189) {
				c.getPA().movePlayer(1878, 5188, 0);
			}

			if (c.absX == 1881 && c.absY == 5188 || c.absX == 1881
					&& c.absY == 5189) {
				c.getPA().movePlayer(1882, 5188, 0);
			} else if (c.absX == 1882 && c.absY == 5188 || c.absX == 1882
					&& c.absY == 5189) {
				c.getPA().movePlayer(1881, 5188, 0);
			}

			if (c.absX == 1912 && c.absY == 5206 || c.absX == 1911
					&& c.absY == 5206) {
				c.getPA().movePlayer(1911, 5207, 0);
			} else if (c.absX == 1911 && c.absY == 5207 || c.absX == 1912
					&& c.absY == 5207) {
				c.getPA().movePlayer(1911, 5206, 0);
			}

			if (c.absX == 1912 && c.absY == 5209 || c.absX == 1911
					&& c.absY == 5209) {
				c.getPA().movePlayer(1911, 5210, 0);
			} else if (c.absX == 1911 && c.absY == 5210 || c.absX == 1912
					&& c.absY == 5210) {
				c.getPA().movePlayer(1911, 5209, 0);
			}
			break;
		// 2nd Floor 2044/5244
		case StrongHoldOfSecurity.SECOND_FLOOR_RIGHT_DOOR:

			if (c.absX == 2044 && c.absY == 5239 || c.absX == 2045
					&& c.absY == 5239) {
				c.getPA().movePlayer(2044, 5240, 0);
			}
			if (c.absX == 2045 && c.absY == 5237 || c.absX == 2044
					&& c.absY == 5237) {
				c.getPA().movePlayer(2045, 5236, 0);
			}
			if (c.absX == 2045 && c.absY == 5236 || c.absX == 2044
					&& c.absY == 5236) {
				c.getPA().movePlayer(2045, 5237, 0);
			}
			if (c.absX == 2044 && c.absY == 5240 || c.absX == 2045
					&& c.absY == 5240) {
				c.getPA().movePlayer(2044, 5239, 0);
			}

			if (c.absX == 2040 && c.absY == 5245 || c.absX == 2040
					&& c.absY == 5244) {
				c.getPA().movePlayer(2039, 5245, 0);
			}
			if (c.absX == 2039 && c.absY == 5245 || c.absX == 2039
					&& c.absY == 5244) {
				c.getPA().movePlayer(2040, 5245, 0);
			}
			if (c.absX == 2037 && c.absY == 5244 || c.absX == 2037
					&& c.absY == 5245) {
				c.getPA().movePlayer(2036, 5244, 0);
			}
			if (c.absX == 2036 && c.absY == 5244 || c.absX == 2036
					&& c.absY == 5245) {
				c.getPA().movePlayer(2037, 5244, 0);
			}

			if (c.absX == 2031 && c.absY == 5228 || c.absX == 2032
					&& c.absY == 5228) {
				c.getPA().movePlayer(2031, 5227, 0);
			}
			if (c.absX == 2031 && c.absY == 5227 || c.absX == 2032
					&& c.absY == 5227) {
				c.getPA().movePlayer(2031, 5228, 0);
			}
			if (c.absX == 2032 && c.absY == 5225 || c.absX == 2031
					&& c.absY == 5225) {
				c.getPA().movePlayer(2032, 5224, 0);
			}
			if (c.absX == 2032 && c.absY == 5224 || c.absX == 2031
					&& c.absY == 5224) {
				c.getPA().movePlayer(2032, 5225, 0);
			}

			if (c.absX == 2039 && c.absY == 5222 || c.absX == 2039
					&& c.absY == 5223) {
				c.getPA().movePlayer(2040, 5222, 0);
			}
			if (c.absX == 2040 && c.absY == 5222 || c.absX == 2040
					&& c.absY == 5223) {
				c.getPA().movePlayer(2039, 5222, 0);
			}
			if (c.absX == 2042 && c.absY == 5223 || c.absX == 2042
					&& c.absY == 5222) {
				c.getPA().movePlayer(2043, 5223, 0);
			}
			if (c.absX == 2043 && c.absY == 5223 || c.absX == 2043
					&& c.absY == 5222) {
				c.getPA().movePlayer(2042, 5223, 0);
			}

			if (c.absX == 2027 && c.absY == 5238 || c.absX == 2026
					&& c.absY == 5238) {
				c.getPA().movePlayer(2027, 5239, 0);
			}

			if (c.absX == 2027 && c.absY == 5239 || c.absX == 2026
					&& c.absY == 5239) {
				c.getPA().movePlayer(2027, 5238, 0);
			}

			if (c.absX == 2026 && c.absY == 5241 || c.absX == 2027
					&& c.absY == 5241) {
				c.getPA().movePlayer(2026, 5242, 0);
			}

			if (c.absX == 2026 && c.absY == 5242 || c.absX == 2027
					&& c.absY == 5242) {
				c.getPA().movePlayer(2026, 5241, 0);
			}

			if (c.absX == 2019 && c.absY == 5243 || c.absX == 2020
					&& c.absY == 5243) {
				c.getPA().movePlayer(2019, 5242, 0);
			}

			if (c.absX == 2019 && c.absY == 5243 || c.absX == 2020
					&& c.absY == 5243) {
				c.getPA().movePlayer(2020, 5242, 0);
			}

			if (c.absX == 2020 && c.absY == 5240 || c.absX == 2019
					&& c.absY == 5240) {
				c.getPA().movePlayer(2020, 5239, 0);
			}

			if (c.absX == 2020 && c.absY == 5239 || c.absX == 2019
					&& c.absY == 5239) {
				c.getPA().movePlayer(2020, 5240, 0);
			}

			if (c.absX == 2014 && c.absY == 5239 || c.absX == 2013
					&& c.absY == 5239) {
				c.getPA().movePlayer(2014, 5240, 0);
			}

			if (c.absX == 2014 && c.absY == 5240 || c.absX == 2013
					&& c.absY == 5240) {
				c.getPA().movePlayer(2014, 5239, 0);
			}

			if (c.absX == 2013 && c.absY == 5242 || c.absX == 2014
					&& c.absY == 5242) {
				c.getPA().movePlayer(2013, 5243, 0);
			}

			if (c.absX == 2013 && c.absY == 5243 || c.absX == 2014
					&& c.absY == 5243) {
				c.getPA().movePlayer(2013, 5242, 0);
			}

			if (c.absX == 2005 && c.absY == 5238 || c.absX == 2006
					&& c.absY == 5238) {
				c.getPA().movePlayer(2005, 5237, 0);
			}

			if (c.absX == 2005 && c.absY == 5237 || c.absX == 2006
					&& c.absY == 5237) {
				c.getPA().movePlayer(2005, 5238, 0);
			}

			if (c.absX == 2006 && c.absY == 5235 || c.absX == 2005
					&& c.absY == 5235) {
				c.getPA().movePlayer(2006, 5234, 0);
			}

			if (c.absX == 2006 && c.absY == 5234 || c.absX == 2005
					&& c.absY == 5234) {
				c.getPA().movePlayer(2006, 5235, 0);
			}

			if (c.absX == 2015 && c.absY == 5227 || c.absX == 2015
					&& c.absY == 5228) {
				c.getPA().movePlayer(2016, 5227, 0);
			}

			if (c.absX == 2016 && c.absY == 5227 || c.absX == 2016
					&& c.absY == 5228) {
				c.getPA().movePlayer(2015, 5227, 0);
			}

			if (c.absX == 2018 && c.absY == 5228 || c.absX == 2018
					&& c.absY == 5227) {
				c.getPA().movePlayer(2019, 5228, 0);
			}

			if (c.absX == 2019 && c.absY == 5228 || c.absX == 2019
					&& c.absY == 5227) {
				c.getPA().movePlayer(2018, 5228, 0);
			}

			if (c.absX == 2009 && c.absY == 5216 || c.absX == 2009
					&& c.absY == 5215) {
				c.getPA().movePlayer(2008, 5216, 0);
			}

			if (c.absX == 2008 && c.absY == 5216 || c.absX == 2008
					&& c.absY == 5215) {
				c.getPA().movePlayer(2009, 5216, 0);
			}

			if (c.absX == 2006 && c.absY == 5215 || c.absX == 2006
					&& c.absY == 5216) {
				c.getPA().movePlayer(2005, 5215, 0);
			}

			if (c.absX == 2005 && c.absY == 5215 || c.absX == 2005
					&& c.absY == 5216) {
				c.getPA().movePlayer(2006, 5215, 0);
			}

			if (c.absX == 2004 && c.absY == 5195 || c.absX == 2005
					&& c.absY == 5195) {
				c.getPA().movePlayer(2004, 5194, 0);
			}

			if (c.absX == 2004 && c.absY == 5194 || c.absX == 2005
					&& c.absY == 5194) {
				c.getPA().movePlayer(2004, 5195, 0);
			}

			if (c.absX == 2005 && c.absY == 5192 || c.absX == 2004
					&& c.absY == 5192) {
				c.getPA().movePlayer(2005, 5191, 0);
			}

			if (c.absX == 2005 && c.absY == 5191 || c.absX == 2004
					&& c.absY == 5191) {
				c.getPA().movePlayer(2005, 5192, 0);
			}

			if (c.absX == 2000 && c.absY == 5216 || c.absX == 2000
					&& c.absY == 5215) {
				c.getPA().movePlayer(1999, 5216, 0);
			}

			if (c.absX == 1999 && c.absY == 5216 || c.absX == 1999
					&& c.absY == 5215) {
				c.getPA().movePlayer(2000, 5216, 0);
			}

			if (c.absX == 1997 && c.absY == 5215 || c.absX == 1997
					&& c.absY == 5216) {
				c.getPA().movePlayer(1996, 5215, 0);
			}

			if (c.absX == 1996 && c.absY == 5215 || c.absX == 1996
					&& c.absY == 5216) {
				c.getPA().movePlayer(1997, 5215, 0);
			}

			if (c.absX == 1994 && c.absY == 5197 || c.absX == 1995
					&& c.absY == 5197) {
				c.getPA().movePlayer(1994, 5196, 0);
			}

			if (c.absX == 1994 && c.absY == 5196 || c.absX == 1995
					&& c.absY == 5196) {
				c.getPA().movePlayer(1994, 5197, 0);
			}

			if (c.absX == 1995 && c.absY == 5194 || c.absX == 1994
					&& c.absY == 5194) {
				c.getPA().movePlayer(1995, 5193, 0);
			}

			if (c.absX == 1995 && c.absY == 5193 || c.absX == 1994
					&& c.absY == 5193) {
				c.getPA().movePlayer(1995, 5194, 0);
			}

			if (c.absX == 2020 && c.absY == 5203 || c.absX == 2021
					&& c.absY == 5203) {
				c.getPA().movePlayer(2020, 5202, 0);
			}

			if (c.absX == 2020 && c.absY == 5202 || c.absX == 2021
					&& c.absY == 5202) {
				c.getPA().movePlayer(2020, 5203, 0);
			}

			if (c.absX == 2021 && c.absY == 5200 || c.absX == 2020
					&& c.absY == 5200) {
				c.getPA().movePlayer(2021, 5199, 0);
			}

			if (c.absX == 2021 && c.absY == 5199 || c.absX == 2020
					&& c.absY == 5199) {
				c.getPA().movePlayer(2021, 5200, 0);
			}

			if (c.absX == 2032 && c.absY == 5195 || c.absX == 2031
					&& c.absY == 5195) {
				c.getPA().movePlayer(2032, 5196, 0);
			}

			if (c.absX == 2032 && c.absY == 5196 || c.absX == 2031
					&& c.absY == 5196) {
				c.getPA().movePlayer(2032, 5195, 0);
			}

			if (c.absX == 2031 && c.absY == 5198 || c.absX == 2032
					&& c.absY == 5198) {
				c.getPA().movePlayer(2031, 5199, 0);
			}

			if (c.absX == 2031 && c.absY == 5199 || c.absX == 2032
					&& c.absY == 5199) {
				c.getPA().movePlayer(2031, 5198, 0);
			}

			if (c.absX == 2034 && c.absY == 5207 || c.absX == 2033
					&& c.absY == 5207) {
				c.getPA().movePlayer(2034, 5208, 0);
			}

			if (c.absX == 2034 && c.absY == 5208 || c.absX == 2033
					&& c.absY == 5208) {
				c.getPA().movePlayer(2034, 5207, 0);
			}

			if (c.absX == 2033 && c.absY == 5210 || c.absX == 2034
					&& c.absY == 5210) {
				c.getPA().movePlayer(2033, 5211, 0);
			}

			if (c.absX == 2033 && c.absY == 5211 || c.absX == 2034
					&& c.absY == 5211) {
				c.getPA().movePlayer(2033, 5210, 0);
			}

			if (c.absX == 2033 && c.absY == 5185 || c.absX == 2033
					&& c.absY == 5186) {
				c.getPA().movePlayer(2034, 5185, 0);
			}

			if (c.absX == 2034 && c.absY == 5185 || c.absX == 2034
					&& c.absY == 5186) {
				c.getPA().movePlayer(2033, 5185, 0);
			}

			if (c.absX == 2036 && c.absY == 5186 || c.absX == 2036
					&& c.absY == 5185) {
				c.getPA().movePlayer(2037, 5186, 0);
			}

			if (c.absX == 2037 && c.absY == 5186 || c.absX == 2037
					&& c.absY == 5185) {
				c.getPA().movePlayer(2036, 5186, 0);
			}

			if (c.absX == 2046 && c.absY == 5194 || c.absX == 2045
					&& c.absY == 5194) {
				c.getPA().movePlayer(2046, 5195, 0);
			}

			if (c.absX == 2046 && c.absY == 5195 || c.absX == 2045
					&& c.absY == 5195) {
				c.getPA().movePlayer(2046, 5194, 0);
			}

			if (c.absX == 2045 && c.absY == 5197 || c.absX == 2046
					&& c.absY == 5197) {
				c.getPA().movePlayer(2045, 5198, 0);
			}

			if (c.absX == 2045 && c.absY == 5198 || c.absX == 2044
					&& c.absY == 5198) {
				c.getPA().movePlayer(2045, 5197, 0);
			}

			if (c.absX == 2037 && c.absY == 5200 || c.absX == 2036
					&& c.absY == 5200) {
				c.getPA().movePlayer(2037, 5201, 0);
			}

			if (c.absX == 2037 && c.absY == 5201 || c.absX == 2036
					&& c.absY == 5201) {
				c.getPA().movePlayer(2037, 5200, 0);
			}

			if (c.absX == 2036 && c.absY == 5203 || c.absX == 2037
					&& c.absY == 5203) {
				c.getPA().movePlayer(2036, 5204, 0);
			}

			if (c.absX == 2036 && c.absY == 5204 || c.absX == 2037
					&& c.absY == 5204) {
				c.getPA().movePlayer(2036, 5203, 0);
			}

			break;
		case StrongHoldOfSecurity.SECOND_FLOOR_LEFT_DOOR:

			if (c.absX == 2044 && c.absY == 5239 || c.absX == 2045
					&& c.absY == 5239) {
				c.getPA().movePlayer(2045, 5240, 0);
			}
			if (c.absX == 2045 && c.absY == 5237 || c.absX == 2044
					&& c.absY == 5237) {
				c.getPA().movePlayer(2044, 5236, 0);
			}
			if (c.absX == 2045 && c.absY == 5236 || c.absX == 2044
					&& c.absY == 5236) {
				c.getPA().movePlayer(2044, 5237, 0);
			}
			if (c.absX == 2044 && c.absY == 5240 || c.absX == 2045
					&& c.absY == 5240) {
				c.getPA().movePlayer(2045, 5239, 0);
			}

			if (c.absX == 2040 && c.absY == 5245 || c.absX == 2040
					&& c.absY == 5244) {
				c.getPA().movePlayer(2039, 5244, 0);
			}
			if (c.absX == 2039 && c.absY == 5245 || c.absX == 2039
					&& c.absY == 5244) {
				c.getPA().movePlayer(2040, 5244, 0);
			}
			if (c.absX == 2037 && c.absY == 5244 || c.absX == 2037
					&& c.absY == 5245) {
				c.getPA().movePlayer(2036, 5245, 0);
			}
			if (c.absX == 2036 && c.absY == 5244 || c.absX == 2036
					&& c.absY == 5245) {
				c.getPA().movePlayer(2037, 5245, 0);
			}

			if (c.absX == 2031 && c.absY == 5228 || c.absX == 2032
					&& c.absY == 5228) {
				c.getPA().movePlayer(2032, 5227, 0);
			}
			if (c.absX == 2031 && c.absY == 5227 || c.absX == 2032
					&& c.absY == 5227) {
				c.getPA().movePlayer(2032, 5228, 0);
			}
			if (c.absX == 2032 && c.absY == 5225 || c.absX == 2031
					&& c.absY == 5225) {
				c.getPA().movePlayer(2031, 5224, 0);
			}
			if (c.absX == 2032 && c.absY == 5224 || c.absX == 2031
					&& c.absY == 5224) {
				c.getPA().movePlayer(2031, 5225, 0);
			}

			if (c.absX == 2039 && c.absY == 5222 || c.absX == 2039
					&& c.absY == 5223) {
				c.getPA().movePlayer(2040, 5223, 0);
			}
			if (c.absX == 2040 && c.absY == 5222 || c.absX == 2040
					&& c.absY == 5223) {
				c.getPA().movePlayer(2039, 5223, 0);
			}
			if (c.absX == 2042 && c.absY == 5223 || c.absX == 2042
					&& c.absY == 5222) {
				c.getPA().movePlayer(2043, 5222, 0);
			}
			if (c.absX == 2043 && c.absY == 5223 || c.absX == 2043
					&& c.absY == 5222) {
				c.getPA().movePlayer(2042, 5222, 0);
			}

			if (c.absX == 2027 && c.absY == 5238 || c.absX == 2026
					&& c.absY == 5238) {
				c.getPA().movePlayer(2026, 5239, 0);
			}

			if (c.absX == 2027 && c.absY == 5239 || c.absX == 2026
					&& c.absY == 5239) {
				c.getPA().movePlayer(2026, 5238, 0);
			}

			if (c.absX == 2026 && c.absY == 5241 || c.absX == 2027
					&& c.absY == 5241) {
				c.getPA().movePlayer(2027, 5242, 0);
			}

			if (c.absX == 2026 && c.absY == 5242 || c.absX == 2027
					&& c.absY == 5242) {
				c.getPA().movePlayer(2027, 5241, 0);
			}

			if (c.absX == 2019 && c.absY == 5243 || c.absX == 2018
					&& c.absY == 5243) {
				c.getPA().movePlayer(2018, 5244, 0);
			}

			if (c.absX == 2019 && c.absY == 5242 || c.absX == 2020
					&& c.absY == 5242) {
				c.getPA().movePlayer(2020, 5243, 0);
			}

			if (c.absX == 2020 && c.absY == 5240 || c.absX == 2019
					&& c.absY == 5240) {
				c.getPA().movePlayer(2019, 5239, 0);
			}

			if (c.absX == 2020 && c.absY == 5239 || c.absX == 2019
					&& c.absY == 5239) {
				c.getPA().movePlayer(2019, 5240, 0);
			}

			if (c.absX == 2014 && c.absY == 5239 || c.absX == 2013
					&& c.absY == 5239) {
				c.getPA().movePlayer(2013, 5240, 0);
			}

			if (c.absX == 2014 && c.absY == 5240 || c.absX == 2013
					&& c.absY == 5240) {
				c.getPA().movePlayer(2013, 5239, 0);
			}

			if (c.absX == 2013 && c.absY == 5242 || c.absX == 2014
					&& c.absY == 5242) {
				c.getPA().movePlayer(2014, 5243, 0);
			}

			if (c.absX == 2013 && c.absY == 5243 || c.absX == 2014
					&& c.absY == 5243) {
				c.getPA().movePlayer(2014, 5242, 0);
			}

			if (c.absX == 2005 && c.absY == 5238 || c.absX == 2006
					&& c.absY == 5238) {
				c.getPA().movePlayer(2006, 5237, 0);
			}

			if (c.absX == 2005 && c.absY == 5237 || c.absX == 2006
					&& c.absY == 5237) {
				c.getPA().movePlayer(2006, 5238, 0);
			}

			if (c.absX == 2006 && c.absY == 5235 || c.absX == 2005
					&& c.absY == 5235) {
				c.getPA().movePlayer(2005, 5234, 0);
			}

			if (c.absX == 2006 && c.absY == 5234 || c.absX == 2005
					&& c.absY == 5234) {
				c.getPA().movePlayer(2005, 5235, 0);
			}

			if (c.absX == 2015 && c.absY == 5227 || c.absX == 2015
					&& c.absY == 5228) {
				c.getPA().movePlayer(2016, 5228, 0);
			}

			if (c.absX == 2016 && c.absY == 5227 || c.absX == 2016
					&& c.absY == 5228) {
				c.getPA().movePlayer(2015, 5228, 0);
			}

			if (c.absX == 2018 && c.absY == 5228 || c.absX == 2018
					&& c.absY == 5227) {
				c.getPA().movePlayer(2019, 5227, 0);
			}

			if (c.absX == 2019 && c.absY == 5228 || c.absX == 2019
					&& c.absY == 5227) {
				c.getPA().movePlayer(2018, 5227, 0);
			}

			if (c.absX == 2009 && c.absY == 5216 || c.absX == 2009
					&& c.absY == 5215) {
				c.getPA().movePlayer(2008, 5215, 0);
			}

			if (c.absX == 2008 && c.absY == 5216 || c.absX == 2008
					&& c.absY == 5215) {
				c.getPA().movePlayer(2009, 5215, 0);
			}

			if (c.absX == 2006 && c.absY == 5215 || c.absX == 2006
					&& c.absY == 5216) {
				c.getPA().movePlayer(2005, 5216, 0);
			}

			if (c.absX == 2005 && c.absY == 5215 || c.absX == 2005
					&& c.absY == 5216) {
				c.getPA().movePlayer(2006, 5216, 0);
			}

			if (c.absX == 2004 && c.absY == 5195 || c.absX == 2005
					&& c.absY == 5195) {
				c.getPA().movePlayer(2005, 5194, 0);
			}

			if (c.absX == 2004 && c.absY == 5194 || c.absX == 2005
					&& c.absY == 5194) {
				c.getPA().movePlayer(2005, 5195, 0);
			}

			if (c.absX == 2005 && c.absY == 5192 || c.absX == 2004
					&& c.absY == 5192) {
				c.getPA().movePlayer(2004, 5191, 0);
			}

			if (c.absX == 2005 && c.absY == 5191 || c.absX == 2004
					&& c.absY == 5191) {
				c.getPA().movePlayer(2004, 5192, 0);
			}

			if (c.absX == 2000 && c.absY == 5216 || c.absX == 2000
					&& c.absY == 5215) {
				c.getPA().movePlayer(1999, 5215, 0);
			}

			if (c.absX == 1999 && c.absY == 5216 || c.absX == 1999
					&& c.absY == 5215) {
				c.getPA().movePlayer(2000, 5215, 0);
			}

			if (c.absX == 1997 && c.absY == 5215 || c.absX == 1997
					&& c.absY == 5216) {
				c.getPA().movePlayer(1996, 5216, 0);
			}

			if (c.absX == 1996 && c.absY == 5215 || c.absX == 1996
					&& c.absY == 5216) {
				c.getPA().movePlayer(1997, 5216, 0);
			}

			if (c.absX == 1994 && c.absY == 5197 || c.absX == 1995
					&& c.absY == 5197) {
				c.getPA().movePlayer(1995, 5196, 0);
			}

			if (c.absX == 1994 && c.absY == 5196 || c.absX == 1995
					&& c.absY == 5196) {
				c.getPA().movePlayer(1995, 5197, 0);
			}

			if (c.absX == 1995 && c.absY == 5194 || c.absX == 1994
					&& c.absY == 5194) {
				c.getPA().movePlayer(1994, 5193, 0);
			}

			if (c.absX == 1995 && c.absY == 5193 || c.absX == 1994
					&& c.absY == 5193) {
				c.getPA().movePlayer(1994, 5194, 0);
			}

			if (c.absX == 2020 && c.absY == 5203 || c.absX == 2021
					&& c.absY == 5203) {
				c.getPA().movePlayer(2021, 5202, 0);
			}

			if (c.absX == 2020 && c.absY == 5202 || c.absX == 2021
					&& c.absY == 5202) {
				c.getPA().movePlayer(2021, 5203, 0);
			}

			if (c.absX == 2021 && c.absY == 5200 || c.absX == 2020
					&& c.absY == 5200) {
				c.getPA().movePlayer(2020, 5199, 0);
			}

			if (c.absX == 2021 && c.absY == 5199 || c.absX == 2020
					&& c.absY == 5199) {
				c.getPA().movePlayer(2020, 5200, 0);
			}

			if (c.absX == 2032 && c.absY == 5195 || c.absX == 2031
					&& c.absY == 5195) {
				c.getPA().movePlayer(2031, 5196, 0);
			}

			if (c.absX == 2032 && c.absY == 5196 || c.absX == 2031
					&& c.absY == 5196) {
				c.getPA().movePlayer(2031, 5195, 0);
			}

			if (c.absX == 2031 && c.absY == 5198 || c.absX == 2032
					&& c.absY == 5198) {
				c.getPA().movePlayer(2032, 5199, 0);
			}

			if (c.absX == 2031 && c.absY == 5199 || c.absX == 2032
					&& c.absY == 5199) {
				c.getPA().movePlayer(2032, 5198, 0);
			}

			if (c.absX == 2034 && c.absY == 5207 || c.absX == 2033
					&& c.absY == 5207) {
				c.getPA().movePlayer(2033, 5208, 0);
			}

			if (c.absX == 2034 && c.absY == 5208 || c.absX == 2033
					&& c.absY == 5208) {
				c.getPA().movePlayer(2033, 5207, 0);
			}

			if (c.absX == 2033 && c.absY == 5210 || c.absX == 2034
					&& c.absY == 5210) {
				c.getPA().movePlayer(2034, 5211, 0);
			}

			if (c.absX == 2033 && c.absY == 5211 || c.absX == 2034
					&& c.absY == 5211) {
				c.getPA().movePlayer(2034, 5210, 0);
			}
			if (c.absX == 2033 && c.absY == 5185 || c.absX == 2033
					&& c.absY == 5186) {
				c.getPA().movePlayer(2034, 5186, 0);
			}

			if (c.absX == 2034 && c.absY == 5185 || c.absX == 2034
					&& c.absY == 5186) {
				c.getPA().movePlayer(2033, 5186, 0);
			}

			if (c.absX == 2036 && c.absY == 5186 || c.absX == 2036
					&& c.absY == 5185) {
				c.getPA().movePlayer(2037, 5185, 0);
			}

			if (c.absX == 2037 && c.absY == 5186 || c.absX == 2037
					&& c.absY == 5185) {
				c.getPA().movePlayer(2036, 5185, 0);
			}

			if (c.absX == 2046 && c.absY == 5194 || c.absX == 2045
					&& c.absY == 5194) {
				c.getPA().movePlayer(2045, 5195, 0);
			}

			if (c.absX == 2046 && c.absY == 5195 || c.absX == 2045
					&& c.absY == 5195) {
				c.getPA().movePlayer(2045, 5194, 0);
			}

			if (c.absX == 2045 && c.absY == 5197 || c.absX == 2046
					&& c.absY == 5197) {
				c.getPA().movePlayer(2046, 5198, 0);
			}

			if (c.absX == 2045 && c.absY == 5198 || c.absX == 2044
					&& c.absY == 5198) {
				c.getPA().movePlayer(2044, 5197, 0);
			}

			if (c.absX == 2037 && c.absY == 5200 || c.absX == 2036
					&& c.absY == 5200) {
				c.getPA().movePlayer(2036, 5201, 0);
			}

			if (c.absX == 2037 && c.absY == 5201 || c.absX == 2036
					&& c.absY == 5201) {
				c.getPA().movePlayer(2036, 5200, 0);
			}

			if (c.absX == 2036 && c.absY == 5203 || c.absX == 2037
					&& c.absY == 5203) {
				c.getPA().movePlayer(2037, 5204, 0);
			}

			if (c.absX == 2036 && c.absY == 5204 || c.absX == 2037
					&& c.absY == 5204) {
				c.getPA().movePlayer(2037, 5203, 0);
			}

			break;
		case THIRD_FLOOR_RIGHT_DOOR:

			if (c.absX == 2154 && c.absY == 5264 || c.absX == 2155
					&& c.absY == 5264) {
				c.getPA().movePlayer(2154, 5263, 0);
			}

			if (c.absX == 2164 && c.absY == 5274 || c.absX == 2163
					&& c.absY == 5274) {
				c.getPA().movePlayer(2164, 5275, 0);
			}

			if (c.absX == 2167 && c.absY == 5272 || c.absX == 2167
					&& c.absY == 5271) {
				c.getPA().movePlayer(2168, 5272, 0);
			}

			if (c.absX == 2167 && c.absY == 5262 || c.absX == 2166
					&& c.absY == 5262) {
				c.getPA().movePlayer(2167, 5261, 0);
			}

			if (c.absX == 2157 && c.absY == 5264 || c.absX == 2157
					&& c.absY == 5265) {
				c.getPA().movePlayer(2156, 5264, 0);
			}

			if (c.absX == 2164 && c.absY == 5275 || c.absX == 2163
					&& c.absY == 5275) {
				c.getPA().movePlayer(2164, 5274, 0);
			}

			if (c.absX == 2164 && c.absY == 5277 || c.absX == 2163
					&& c.absY == 5277) {
				c.getPA().movePlayer(2164, 5278, 0);
			}

			if (c.absX == 2164 && c.absY == 5278 || c.absX == 2163
					&& c.absY == 5278) {
				c.getPA().movePlayer(2164, 5277, 0);
			}

			if (c.absX == 2152 && c.absY == 5291 || c.absX == 2152
					&& c.absY == 5292) {
				c.getPA().movePlayer(2153, 5292, 0);
			}

			if (c.absX == 2152 && c.absY == 5291 || c.absX == 2152
					&& c.absY == 5292) {
				c.getPA().movePlayer(2153, 5291, 0);
			}

			if (c.absX == 2149 && c.absY == 5291 || c.absX == 2149
					&& c.absY == 5292) {
				c.getPA().movePlayer(2148, 5291, 0);
			}

			if (c.absX == 2148 && c.absY == 5291 || c.absX == 2148
					&& c.absY == 5292) {
				c.getPA().movePlayer(2149, 5291, 0);
			}

			if (c.absX == 2133 && c.absY == 5256 || c.absX == 2132
					&& c.absY == 5256) {
				c.getPA().movePlayer(2133, 5257, 0);
			}

			if (c.absX == 2133 && c.absY == 5257 || c.absX == 2132
					&& c.absY == 5257) {
				c.getPA().movePlayer(2133, 5256, 0);
			}

			if (c.absX == 2133 && c.absY == 5259 || c.absX == 2132
					&& c.absY == 5259) {
				c.getPA().movePlayer(2133, 5260, 0);
			}

			if (c.absX == 2133 && c.absY == 5260 || c.absX == 2132
					&& c.absY == 5260) {
				c.getPA().movePlayer(2133, 5259, 0);
			}

			if (c.absX == 2137 && c.absY == 5263 || c.absX == 2137
					&& c.absY == 5262) {
				c.getPA().movePlayer(2138, 5263, 0);
			}

			if (c.absX == 2138 && c.absY == 5263 || c.absX == 2138
					&& c.absY == 5262) {
				c.getPA().movePlayer(2137, 5263, 0);
			}

			if (c.absX == 2140 && c.absY == 5263 || c.absX == 2140
					&& c.absY == 5262) {
				c.getPA().movePlayer(2141, 5263, 0);
			}

			if (c.absX == 2141 && c.absY == 5263 || c.absX == 2141
					&& c.absY == 5262) {
				c.getPA().movePlayer(2140, 5263, 0);
			}

			if (c.absX == 2132 && c.absY == 5278 || c.absX == 2133
					&& c.absY == 5278) {
				c.getPA().movePlayer(2132, 5279, 0);
			}

			if (c.absX == 2132 && c.absY == 5279 || c.absX == 2133
					&& c.absY == 5279) {
				c.getPA().movePlayer(2132, 5278, 0);
			}

			if (c.absX == 2132 && c.absY == 5281 || c.absX == 2133
					&& c.absY == 5281) {
				c.getPA().movePlayer(2132, 5282, 0);
			}

			if (c.absX == 2132 && c.absY == 5282 || c.absX == 2133
					&& c.absY == 5282) {
				c.getPA().movePlayer(2132, 5281, 0);
			}

			if (c.absX == 2127 && c.absY == 5287 || c.absX == 2127
					&& c.absY == 5288) {
				c.getPA().movePlayer(2126, 5287, 0);
			}

			if (c.absX == 2126 && c.absY == 5287 || c.absX == 2126
					&& c.absY == 5288) {
				c.getPA().movePlayer(2127, 5287, 0);
			}

			if (c.absX == 2124 && c.absY == 5287 || c.absX == 2124
					&& c.absY == 5288) {
				c.getPA().movePlayer(2123, 5287, 0);
			}

			if (c.absX == 2123 && c.absY == 5287 || c.absX == 2123
					&& c.absY == 5288) {
				c.getPA().movePlayer(2124, 5287, 0);
			}

			if (c.absX == 2131 && c.absY == 5296 || c.absX == 2130
					&& c.absY == 5296) {
				c.getPA().movePlayer(2131, 5295, 0);
			}

			if (c.absX == 2131 && c.absY == 5295 || c.absX == 2130
					&& c.absY == 5295) {
				c.getPA().movePlayer(2131, 5296, 0);
			}

			if (c.absX == 2131 && c.absY == 5293 || c.absX == 2130
					&& c.absY == 5293) {
				c.getPA().movePlayer(2131, 5292, 0);
			}

			if (c.absX == 2131 && c.absY == 5292 || c.absX == 2130
					&& c.absY == 5292) {
				c.getPA().movePlayer(2131, 5293, 0);
			}

			if (c.absX == 2137 && c.absY == 5294 || c.absX == 2137
					&& c.absY == 5295) {
				c.getPA().movePlayer(2138, 5294, 0);
			}

			if (c.absX == 2138 && c.absY == 5294 || c.absX == 2138
					&& c.absY == 5295) {
				c.getPA().movePlayer(2137, 5294, 0);
			}

			if (c.absX == 2140 && c.absY == 5294 || c.absX == 2140
					&& c.absY == 5295) {
				c.getPA().movePlayer(2141, 5294, 0);
			}

			if (c.absX == 2141 && c.absY == 5294 || c.absX == 2141
					&& c.absY == 5295) {
				c.getPA().movePlayer(2140, 5294, 0);
			}

			if (c.absX == 2149 && c.absY == 5298 || c.absX == 2148
					&& c.absY == 5298) {
				c.getPA().movePlayer(2149, 5299, 0);
			}

			if (c.absX == 2149 && c.absY == 5299 || c.absX == 2148
					&& c.absY == 5299) {
				c.getPA().movePlayer(2149, 5298, 0);
			}

			if (c.absX == 2149 && c.absY == 5301 || c.absX == 2148
					&& c.absY == 5301) {
				c.getPA().movePlayer(2149, 5302, 0);
			}

			if (c.absX == 2149 && c.absY == 5302 || c.absX == 2148
					&& c.absY == 5302) {
				c.getPA().movePlayer(2149, 5301, 0);
			}

			if (c.absX == 2163 && c.absY == 5290 || c.absX == 2162
					&& c.absY == 5290) {
				c.getPA().movePlayer(2163, 5289, 0);
			}

			if (c.absX == 2163 && c.absY == 5289 || c.absX == 2162
					&& c.absY == 5289) {
				c.getPA().movePlayer(2163, 5290, 0);
			}

			if (c.absX == 2163 && c.absY == 5287 || c.absX == 2162
					&& c.absY == 5287) {
				c.getPA().movePlayer(2163, 5286, 0);
			}

			if (c.absX == 2163 && c.absY == 5286 || c.absX == 2162
					&& c.absY == 5286) {
				c.getPA().movePlayer(2163, 5287, 0);
			}

			if (c.absX == 2168 && c.absY == 5297 || c.absX == 2167
					&& c.absY == 5297) {
				c.getPA().movePlayer(2168, 5296, 0);
			}

			if (c.absX == 2168 && c.absY == 5296 || c.absX == 2169
					&& c.absY == 5296) {
				c.getPA().movePlayer(2168, 5297, 0);
			}

			if (c.absX == 2168 && c.absY == 5294 || c.absX == 2167
					&& c.absY == 5294) {
				c.getPA().movePlayer(2168, 5293, 0);
			}

			if (c.absX == 2168 && c.absY == 5293 || c.absX == 2169
					&& c.absY == 5293) {
				c.getPA().movePlayer(2168, 5294, 0);
			}

			if (c.absX == 2168 && c.absY == 5272 || c.absX == 2168
					&& c.absY == 5273) {
				c.getPA().movePlayer(2167, 5272, 0);
			}

			if (c.absX == 2170 && c.absY == 5272 || c.absX == 2170
					&& c.absY == 5273) {
				c.getPA().movePlayer(2171, 5272, 0);
			}

			if (c.absX == 2171 && c.absY == 5272 || c.absX == 2171
					&& c.absY == 5271) {
				c.getPA().movePlayer(2170, 5272, 0);
			}

			if (c.absX == 2167 && c.absY == 5261 || c.absX == 2166
					&& c.absY == 5261) {
				c.getPA().movePlayer(2167, 5262, 0);
			}

			if (c.absX == 2166 && c.absY == 5259 || c.absX == 2167
					&& c.absY == 5259) {
				c.getPA().movePlayer(2166, 5260, 0);
			}

			if (c.absX == 2166 && c.absY == 5260 || c.absX == 2167
					&& c.absY == 5260) {
				c.getPA().movePlayer(2166, 5259, 0);
			}

			if (c.absX == 2156 && c.absY == 5264 || c.absX == 2156
					&& c.absY == 5263) {
				c.getPA().movePlayer(2157, 5264, 0);
			}

			if (c.absX == 2154 && c.absY == 5264 || c.absX == 2154
					&& c.absY == 5263) {
				c.getPA().movePlayer(2153, 5264, 0);
			}

			if (c.absX == 2153 && c.absY == 5264 || c.absX == 2153
					&& c.absY == 5263) {
				c.getPA().movePlayer(2154, 5264, 0);
			}

			if (c.absX == 2156 && c.absY == 5285 || c.absX == 2155
					&& c.absY == 5285) {
				c.getPA().movePlayer(2156, 5286, 0);
			}

			if (c.absX == 2156 && c.absY == 5286 || c.absX == 2155
					&& c.absY == 5286) {
				c.getPA().movePlayer(2156, 5285, 0);
			}

			if (c.absX == 2156 && c.absY == 5288 || c.absX == 2155
					&& c.absY == 5288) {
				c.getPA().movePlayer(2156, 5289, 0);
			}

			if (c.absX == 2156 && c.absY == 5289 || c.absX == 2155
					&& c.absY == 5289) {
				c.getPA().movePlayer(2156, 5288, 0);
			}

			if (c.absX == 2153 && c.absY == 5292 || c.absX == 2153
					&& c.absY == 5291) {
				c.getPA().movePlayer(2152, 5292, 0);
			}

			break;
		case THIRD_FLOOR_LEFT_DOOR:

			if (c.absX == 2156 && c.absY == 5289 || c.absX == 2155
					&& c.absY == 5289) {
				c.getPA().movePlayer(2155, 5288, 0);
			}

			if (c.absX == 2153 && c.absY == 5292 || c.absX == 2153
					&& c.absY == 5291) {
				c.getPA().movePlayer(2152, 5291, 0);
			}

			if (c.absX == 2156 && c.absY == 5285 || c.absX == 2155
					&& c.absY == 5285) {
				c.getPA().movePlayer(2155, 5286, 0);
			}

			if (c.absX == 2156 && c.absY == 5286 || c.absX == 2155
					&& c.absY == 5286) {
				c.getPA().movePlayer(2155, 5285, 0);
			}

			if (c.absX == 2156 && c.absY == 5288 || c.absX == 2155
					&& c.absY == 5288) {
				c.getPA().movePlayer(2155, 5289, 0);
			}

			if (c.absX == 2153 && c.absY == 5264 || c.absX == 2153
					&& c.absY == 5263) {
				c.getPA().movePlayer(2154, 5263, 0);
			}

			if (c.absX == 2156 && c.absY == 5264 || c.absX == 2156
					&& c.absY == 5263) {
				c.getPA().movePlayer(2157, 5263, 0);
			}

			if (c.absX == 2154 && c.absY == 5264 || c.absX == 2154
					&& c.absY == 5263) {
				c.getPA().movePlayer(2153, 5263, 0);
			}

			if (c.absX == 2167 && c.absY == 5261 || c.absX == 2166
					&& c.absY == 5261) {
				c.getPA().movePlayer(2166, 5262, 0);
			}

			if (c.absX == 2166 && c.absY == 5260 || c.absX == 2167
					&& c.absY == 5260) {
				c.getPA().movePlayer(2167, 5259, 0);
			}

			if (c.absX == 2166 && c.absY == 5259 || c.absX == 2167
					&& c.absY == 5259) {
				c.getPA().movePlayer(2167, 5260, 0);
			}

			if (c.absX == 2164 && c.absY == 5274 || c.absX == 2163
					&& c.absY == 5274) {
				c.getPA().movePlayer(2163, 5275, 0);
			}

			if (c.absX == 2167 && c.absY == 5272 || c.absX == 2167
					&& c.absY == 5271) {
				c.getPA().movePlayer(2168, 5271, 0);
			}

			if (c.absX == 2167 && c.absY == 5262 || c.absX == 2166
					&& c.absY == 5262) {
				c.getPA().movePlayer(2166, 5261, 0);
			}

			if (c.absX == 2157 && c.absY == 5264 || c.absX == 2157
					&& c.absY == 5265) {
				c.getPA().movePlayer(2156, 5265, 0);
			}

			if (c.absX == 2164 && c.absY == 5275 || c.absX == 2163
					&& c.absY == 5275) {
				c.getPA().movePlayer(2163, 5274, 0);
			}

			if (c.absX == 2164 && c.absY == 5277 || c.absX == 2163
					&& c.absY == 5277) {
				c.getPA().movePlayer(2163, 5278, 0);
			}

			if (c.absX == 2164 && c.absY == 5278 || c.absX == 2163
					&& c.absY == 5278) {
				c.getPA().movePlayer(2163, 5277, 0);
			}

			if (c.absX == 2154 && c.absY == 5264 || c.absX == 2155
					&& c.absY == 5264) {
				c.getPA().movePlayer(2155, 5263, 0);
			}

			if (c.absX == 2153 && c.absY == 5292 || c.absX == 2153
					&& c.absY == 5293) {
				c.getPA().movePlayer(2154, 5293, 0);
			}

			if (c.absX == 2152 && c.absY == 5292 || c.absX == 2153
					&& c.absY == 5292) {
				c.getPA().movePlayer(2153, 5293, 0);
			}

			if (c.absX == 2149 && c.absY == 5291 || c.absX == 2149
					&& c.absY == 5292) {
				c.getPA().movePlayer(2148, 5292, 0);
			}

			if (c.absX == 2148 && c.absY == 5291 || c.absX == 2148
					&& c.absY == 5292) {
				c.getPA().movePlayer(2149, 5292, 0);
			}

			if (c.absX == 2133 && c.absY == 5256 || c.absX == 2132
					&& c.absY == 5256) {
				c.getPA().movePlayer(2132, 5257, 0);
			}

			if (c.absX == 2133 && c.absY == 5257 || c.absX == 2132
					&& c.absY == 5257) {
				c.getPA().movePlayer(2132, 5256, 0);
			}

			if (c.absX == 2133 && c.absY == 5259 || c.absX == 2132
					&& c.absY == 5259) {
				c.getPA().movePlayer(2132, 5260, 0);
			}

			if (c.absX == 2133 && c.absY == 5260 || c.absX == 2132
					&& c.absY == 5260) {
				c.getPA().movePlayer(2132, 5259, 0);
			}

			if (c.absX == 2137 && c.absY == 5263 || c.absX == 2137
					&& c.absY == 5262) {
				c.getPA().movePlayer(2138, 5262, 0);
			}

			if (c.absX == 2138 && c.absY == 5263 || c.absX == 2138
					&& c.absY == 5262) {
				c.getPA().movePlayer(2137, 5262, 0);
			}

			if (c.absX == 2140 && c.absY == 5263 || c.absX == 2140
					&& c.absY == 5262) {
				c.getPA().movePlayer(2141, 5262, 0);
			}

			if (c.absX == 2141 && c.absY == 5263 || c.absX == 2141
					&& c.absY == 5262) {
				c.getPA().movePlayer(2140, 5262, 0);
			}

			if (c.absX == 2132 && c.absY == 5278 || c.absX == 2133
					&& c.absY == 5278) {
				c.getPA().movePlayer(2133, 5279, 0);
			}

			if (c.absX == 2132 && c.absY == 5279 || c.absX == 2133
					&& c.absY == 5279) {
				c.getPA().movePlayer(2133, 5278, 0);
			}

			if (c.absX == 2132 && c.absY == 5281 || c.absX == 2133
					&& c.absY == 5281) {
				c.getPA().movePlayer(2133, 5282, 0);
			}

			if (c.absX == 2132 && c.absY == 5282 || c.absX == 2133
					&& c.absY == 5282) {
				c.getPA().movePlayer(2133, 5281, 0);
			}

			if (c.absX == 2127 && c.absY == 5287 || c.absX == 2127
					&& c.absY == 5288) {
				c.getPA().movePlayer(2126, 5288, 0);
			}

			if (c.absX == 2126 && c.absY == 5287 || c.absX == 2126
					&& c.absY == 5288) {
				c.getPA().movePlayer(2127, 5288, 0);
			}

			if (c.absX == 2124 && c.absY == 5287 || c.absX == 2124
					&& c.absY == 5288) {
				c.getPA().movePlayer(2123, 5288, 0);
			}

			if (c.absX == 2123 && c.absY == 5287 || c.absX == 2123
					&& c.absY == 5288) {
				c.getPA().movePlayer(2124, 5288, 0);
			}

			if (c.absX == 2131 && c.absY == 5296 || c.absX == 2130
					&& c.absY == 5296) {
				c.getPA().movePlayer(2130, 5295, 0);
			}

			if (c.absX == 2131 && c.absY == 5295 || c.absX == 2130
					&& c.absY == 5295) {
				c.getPA().movePlayer(2130, 5296, 0);
			}

			if (c.absX == 2131 && c.absY == 5293 || c.absX == 2130
					&& c.absY == 5293) {
				c.getPA().movePlayer(2130, 5292, 0);
			}

			if (c.absX == 2131 && c.absY == 5292 || c.absX == 2130
					&& c.absY == 5292) {
				c.getPA().movePlayer(2130, 5293, 0);
			}

			if (c.absX == 2137 && c.absY == 5294 || c.absX == 2137
					&& c.absY == 5295) {
				c.getPA().movePlayer(2138, 5295, 0);
			}

			if (c.absX == 2138 && c.absY == 5294 || c.absX == 2138
					&& c.absY == 5295) {
				c.getPA().movePlayer(2137, 5295, 0);
			}

			if (c.absX == 2140 && c.absY == 5294 || c.absX == 2140
					&& c.absY == 5295) {
				c.getPA().movePlayer(2141, 5295, 0);
			}

			if (c.absX == 2141 && c.absY == 5294 || c.absX == 2141
					&& c.absY == 5295) {
				c.getPA().movePlayer(2140, 5295, 0);
			}

			if (c.absX == 2149 && c.absY == 5298 || c.absX == 2148
					&& c.absY == 5298) {
				c.getPA().movePlayer(2148, 5299, 0);
			}

			if (c.absX == 2149 && c.absY == 5299 || c.absX == 2148
					&& c.absY == 5299) {
				c.getPA().movePlayer(2148, 5298, 0);
			}

			if (c.absX == 2149 && c.absY == 5301 || c.absX == 2148
					&& c.absY == 5301) {
				c.getPA().movePlayer(2148, 5302, 0);
			}

			if (c.absX == 2149 && c.absY == 5302 || c.absX == 2148
					&& c.absY == 5302) {
				c.getPA().movePlayer(2148, 5301, 0);
			}

			if (c.absX == 2163 && c.absY == 5290 || c.absX == 2162
					&& c.absY == 5290) {
				c.getPA().movePlayer(2162, 5289, 0);
			}

			if (c.absX == 2163 && c.absY == 5289 || c.absX == 2162
					&& c.absY == 5289) {
				c.getPA().movePlayer(2162, 5290, 0);
			}

			if (c.absX == 2163 && c.absY == 5287 || c.absX == 2162
					&& c.absY == 5287) {
				c.getPA().movePlayer(2162, 5286, 0);
			}

			if (c.absX == 2163 && c.absY == 5286 || c.absX == 2162
					&& c.absY == 5286) {
				c.getPA().movePlayer(2162, 5287, 0);
			}

			if (c.absX == 2168 && c.absY == 5297 || c.absX == 2167
					&& c.absY == 5297) {
				c.getPA().movePlayer(2167, 5296, 0);
			}

			if (c.absX == 2168 && c.absY == 5296 || c.absX == 2169
					&& c.absY == 5296) {
				c.getPA().movePlayer(2169, 5297, 0);
			}

			if (c.absX == 2168 && c.absY == 5294 || c.absX == 2167
					&& c.absY == 5294) {
				c.getPA().movePlayer(2167, 5293, 0);
			}

			if (c.absX == 2168 && c.absY == 5293 || c.absX == 2169
					&& c.absY == 5293) {
				c.getPA().movePlayer(2169, 5294, 0);
			}

			break;
		}
	}
}
