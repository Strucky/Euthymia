package server.game.players;

public class City {

	public static String getCity(Client c) {
		for (cities cit: cities.values()) {
			if (c.absX > cit.lowX && c.absX < cit.maxX
					&& c.absY > cit.lowY && c.absY < cit.maxY) {
			return cit.name;
			} 
		}
		return "Unknown";
	}
	
	public enum cities 
	{
		Taverly("Taverly", 2879,3401, 2923, 3456);
		String name;
		int lowX, maxX, lowY, maxY;
		private cities(String name, int lowX, int maxX, int lowY, int maxY) {
			this.name = name;
			this.lowX = lowX;
			this.maxX = maxX;
			this.lowY = lowY;
			this.maxY = maxY;
		}
	}
	//{ 34, 2879, 3401, 2923, 3456 },//taverl
}
