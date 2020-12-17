package server.game.players.content.skills;

import server.game.players.Client;

public class TanHide {

	

	
	public static enum tanningData {
		
		SOFT_LEATHER(new int[][] {{57225, 1}, {57217, 5}, {57201, 28}}, 1739, 1741, 1, new int[] {14777, 14785, 14769}, "Soft leather"),
		HARD_LEATHER(new int[][] {{57226, 1}, {57218, 5}, {57202, 28}}, 1739, 1743, 3, new int[] {14778, 14786, 14770}, "Hard leather"),
		SNAKESKIN(new int[][] {{57227, 1}, {57219, 5}, {57203, 28}}, 6287, 6289, 15, new int[] {14779, 14787, 14771}, "Snakeskin"),
		SNAKESKIN2(new int[][] {{57228, 1}, {57220, 5}, {57204, 28}}, 6287, 6289, 20, new int[] {14780, 14788, 14772}, "Snakeskin"),
		GREEN_DRAGON_LEATHER(new int[][] {{57229, 1}, {57221, 5}, {57205, 28}}, 1753, 1745, 20, new int[] {14781, 14789, 14773}, "Green d'hide"),
		BLUE_DRAGON_LEATHER(new int[][] {{57230, 1}, {57222, 5}, {57206, 28}}, 1751, 2505, 20, new int[] {14782, 14790, 14774}, "Blue d'hide"),
		RED_DRAGON_LEATHER(new int[][] {{57231, 1}, {57223, 5}, {57207, 28}}, 1749, 2507, 20, new int[] {14783, 14791, 14775}, "Red d'hide"),
		BLACK_DRAGON_LEATHER(new int[][] {{57232, 1}, {57224, 5}, {57208, 28}}, 1747, 2509, 20, new int[] {14784, 14792, 14776}, "Black d'hide");
		
		private int[][] buttonId;
		private int hideId, leatherId, price;
		private int[] frameId;
		private String name;
		
		private tanningData(final int[][] buttonId, final int hideId, final int leatherId, final int price, final int[] frameId, final String name) {
			this.buttonId = buttonId;
			this.hideId = hideId;
			this.leatherId = leatherId;
			this.price = price;
			this.frameId = frameId;
			this.name = name;
		}
		
		public int getButtonId(final int button) {
			for (int i = 0; i < buttonId.length; i++) {
				if (button == buttonId[i][0]) {
					return buttonId[i][0];
				}
			}
			return -1;
		}
		
		public int getAmount(final int button) {
			for (int i = 0; i < buttonId.length; i++) {
				if (button == buttonId[i][0]) {
					return buttonId[i][1];
				}
			}
			return -1;
		}
		
		public int getHideId() {
			return hideId;
		}
		
		public int getLeatherId() {
			return leatherId;
		}
		
		public int getPrice() {
			return price;
		}
		
		public int getNameFrame() {
			return frameId[0];
		}
		
		public int getCostFrame() {
			return frameId[1];
		}
		
		public int getItemFrame() {
			return frameId[2];
		}
		
		public String getName() {
			return name;
		}
	}public static void tanHide(final Client c, final int buttonId) {
		for (final tanningData t : tanningData.values()) {
			if (buttonId == t.getButtonId(buttonId)) {
				int amount = c.getItems().getItemCount(t.getHideId());
				if (amount > t.getAmount(buttonId)) {
					amount = t.getAmount(buttonId);
				}
				int price = (amount * t.getPrice());
				int coins = c.getItems().getItemCount(995);
				if (price > coins) {
					price = (coins - (coins % t.getPrice()));
				}
				if (price == 0) {
					c.sendMessage("You do not have enough coins to tan this hide.");
					return;
				}
				amount = (price / t.getPrice());
				final int hide = t.getHideId();
				final int leather = t.getLeatherId();
				if (c.getItems().playerHasItem(995, price)) {
					if (c.getItems().playerHasItem(hide)) {
						c.getItems().deleteItem(hide, amount);
						c.getItems().deleteItem(995, c.getItems().getItemSlot(995), t.getPrice());
						c.getItems().addItem(leather, amount);
						c.sendMessage("The tanner tans the hides for you.");
					} else {
						c.sendMessage("You do not have any hides to tan.");
						return;
					}
				} else {
					c.sendMessage("You do not have enough coins to tan this hide.");
					return;
				}
			}
		}
	}
	public static  void sendTanningInterface(Client c) {
		c.getPA().showInterface(14670);
		for (final tanningData t : tanningData.values()) {
			c.getPA().itemOnInterface(t.getItemFrame(), 250, t.getLeatherId());
			c.getPA().sendFrame126(t.getName(), t.getNameFrame());
			if (c.getItems().playerHasItem(995, t.getPrice())) {
				c.getPA().sendFrame126("@gre@Price: "+ t.getPrice(), t.getCostFrame());
				c.tanning = true;
			} else {
				c.tanning = true;
				c.getPA().sendFrame126("@red@Price: "+ t.getPrice(), t.getCostFrame());
			}
		}
	}
	

}
