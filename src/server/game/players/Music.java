package server.game.players;

import server.game.minigames.Godwars;

public class Music {



	/**
	 * Gets the music ID depending on the player's coordinations
	 * 
	 * @param c
	 * 			The player
	 */
	public static void getMusic(final Client c) {
		for (int i = 0; i < musicData.length; i++) {
			if (c.absX > musicData[i][1] && c.absX < musicData[i][3]
					&& c.absY > musicData[i][2] && c.absY < musicData[i][4]) {
				playMusic(c, musicData[i][0]);
				return;
			} 
		}
		if(c.inStronghold) {
			playMusic(c, 68);
			return;
		}
		if(c.inEssence) {
			playMusic(c, 47);
			return;
		}
		if(Godwars.inDungeon(c)) {
			playMusic(c, 8);
			return;
		}
		playMusic(c, 64);
		return;
	}

	/**
	 * Plays the music
	 * 
	 * @param c
	 * 			The player
	 * @param song
	 * 			The song to play
	 */
	public static void playMusic(final Client c, final int song) {
		c.outStream.createFrame(74);
		c.outStream.writeWordBigEndian(song);
		// c.sM("Playing song "+song);
	}



	/**
	 * Holds the music data. http://forum.runelocus.com/topic/83702-music-id-list/
	 * https://www.rune-server.ee/runescape-development/rs-503-client-server/configuration/620003-just-some-basic-music-ids-region-id.html
	 */
	//First numbers are f2p cools
	public static short[][] musicData = { { 76, 3200, 3199, 3273, 3302 }, // Harmony
		{ 56, 2310, 3784, 2358, 3836 }, // neitiznot
			{ 2, 3200, 3303, 3273, 3353 }, // Autumn Voyage
			{ 111, 3274, 3328, 3315, 3353 }, // Still Night
			{ 123, 3274, 3266, 3323, 3327 }, // Arabian 2
			{ 36, 3274, 3200, 3323, 3265 }, // Arabian
			{ 50, 3257, 3112, 3333, 3199 }, // Al-Kharid
			{ 122, 3324, 3263, 3408, 3285 }, // Shine
			{ 3, 3066, 3200, 3120, 3272 }, // Unknown land
			{ 327, 3121, 3200, 3199, 3268 }, // Dream
			{ 163, 3121, 3269, 3199, 3314 }, // Flute Salad
			{ 151, 3066, 3273, 3120, 3314 }, // Start
			{ 333, 3066, 3315, 3147, 3394 }, // Spooky
			{ 116, 3148, 3315, 3199, 3394 }, // Greatness
			{ 106, 3200, 3354, 3315, 3394 }, // Expanse
			{ 157, 3248, 3395, 3328, 3468 }, // Medieval
			{ 125, 3166, 3395, 3247, 3468 }, // Garden
			{ 175, 3111, 3395, 3165, 3468 }, // Spirit
			{ 177, 3111, 3469, 3264, 3524 }, // Adventure
			{ 93, 3265, 3469, 3328, 3524 }, // Parade
			{ 35, 2993, 3186, 3065, 3260 }, // Sea Shanty 2
			{ 107, 2889, 3265, 2940, 3324 }, // Miles Away
			{ 127, 2941, 3261, 3013, 3324 }, // Nightfall
			{ 49, 3014, 3261, 3065, 3324 }, // Wander
			{ 186, 2880, 3325, 2935, 3394 }, // Arrival
			{ 72, 2936, 3325, 3065, 3394 }, // Fanfare
			{ 54, 2944, 3395, 3008, 3458 }, // Scape Soft
			{ 54, 2944, 3459, 2987, 3474 }, // Scape Soft
			{ 141, 3066, 3395, 3110, 3450 }, // Barbarianism
			{ 23, 2937, 3475, 2987, 3524 }, // Goblin Village
			{ 102, 2988, 3459, 3065, 3524 }, // Alone
			{ 98, 3066, 3451, 3110, 3524 }, // Forever
			{ 34, 2944, 3525, 2991, 3591 }, // Wonder
			{ 96, 2992, 3525, 3034, 3555 }, // Witching
			{ 96, 2992, 3556, 3124, 3605 }, // Inspiration
			{ 182, 3035, 3525, 3124, 3555 }, // Dangerous
			{ 169, 3125, 3525, 3264, 3579 }, // Crystal Sword
			{ 121, 3265, 3563, 3392, 3619 }, // Forbidden
			{ 113, 2944, 3592, 2991, 3655 }, // Lightness
			{ 160, 2992, 3606, 3055, 3655 }, // Army of Darkness
			{ 176, 3056, 3606, 3124, 3655 }, // Undercurrent
			{ 10, 3125, 3581, 3205, 3655 }, // Moody
			{ 179, 3206, 3580, 3264, 3655 }, // Underground
			{ 183, 2944, 3656, 3003, 3722 }, // Troubled
			{ 66, 3004, 3656, 3064, 3722 }, // Legion
			{ 476, 3065, 3656, 3126, 3722 }, // Dead Can Dance
			{ 43, 3127, 3656, 3197, 3714 }, // Wilderness3
			{ 8, 3198, 3656, 3264, 3702 }, // Wildwood
			{ 337, 3265, 3621, 3392, 3716 }, // Faithless
			{ 435, 3048, 3723, 3126, 3799 }, // Wilderness
			{ 34, 2879, 3401, 2923, 3456 },//taverl
			{ 68, 3127, 3715, 3197, 3758 }, // Cavern
			{ 332, 3198, 3704, 3264, 3758 }, // Scape Wild
			{ 182, 3265, 3717, 3392, 3842 }, // Dangerous
			{ 37, 2944, 3800, 3003, 3903 }, // Deep Wildy
			{ 331, 3212, 3843, 3392, 3903 }, // Scape Sad
			{ 52, 2944, 3904, 3009, 3969 }, // Serene
			
			//{ 333, 2879, 3401, 2923, 3456 }, // draynor manor
	};

}