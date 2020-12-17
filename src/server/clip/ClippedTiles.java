package server.clip;

import java.util.ArrayList;

import server.game.players.Client;

public class ClippedTiles {
	public static ArrayList<Integer>[][] list = new ArrayList[20][20];
	public static void addToList(int i) {
		
	}
	public static void buildPermanentClip() {
		//list[0].add(2342);
	}
	public static int clippedTiles[][] = {
		{2340,3796},//gwd port
		{2341,3796}, //gwd port
		{2342,3796}, // city port
		{2343,3796}, //city port
		{2344,3796},//city port
		{2345,3796},//citiport
		{2339,3806},//stall
		{2333,3806},//altar
		{2333,3807},//altar
		{2333,3798},//altar
		{2333,3799},//altar
		{2333,3800},//altar
	};
	public static int getX(int i) {
		return clippedTiles[i][0];
	}
	public static int getY(int i) {
		return clippedTiles[i][1];
	}
	public static boolean canWalk1(Client c, int x, int y) {
		for (int kk = 0; kk < ClippedTiles.clippedTiles.length; kk++) {
			//System.out.println("Checking clip"+kk);
			//c.sendMessage("Checking clip"+kk);
			if (x == ClippedTiles.getX(kk)
					&& y == ClippedTiles.getY(kk)) {
				System.out.println("clip is clipped"+kk);
				return false;
			}
		}
		return true;
	}
}
