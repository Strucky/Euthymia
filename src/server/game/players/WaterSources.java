package server.game.players;

public class WaterSources {
	public static final int EMPTY_VIAL = 229;
	public static final int VIAL_WATER = 227;
	public static final int EMPTY_BUCKET = 1925;
	public static final int BUCKET_WATER = 1929;
	public static int waterSources[] = { 21355, 873, 874, 4063, 6151, 8699, 9143, 9684, 10175, 12279, 12974, 13563,
			13564, 879, 880, 2638, 2864, 6232, 10436, 10437, 10827, 11007, 11759, 13478, 13479, 153 };

	public static void containerOnWater(Client player, int object, int item) {
		switch (item) {
		case EMPTY_VIAL:
			for (int i = 0; i < waterSources.length; i++) {
				if (object == waterSources[i]) {
					player.getItems().deleteItem(EMPTY_VIAL, 1);
					player.getItems().addItem(VIAL_WATER, 1);
					break;
				}
			}
			break;
		case EMPTY_BUCKET:
			for (int i = 0; i < waterSources.length; i++) {
				if (object == waterSources[i]) {
					player.getItems().deleteItem(EMPTY_BUCKET, 1);
					player.getItems().addItem(BUCKET_WATER, 1);
					break;
				}
			}
			break;
		}
	}
}
