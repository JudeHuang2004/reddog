package util;

import pojo.Player;

public class PlayerFactory {
	public static Player getPlayer(String roomId, Integer orderNum) {
		return new Player(roomId, orderNum);
	}
}
