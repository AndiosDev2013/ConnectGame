package com.jiang.connectgame.components;

import com.jiang.connectgame.config.Config;

public class Level {
	public static int levelCurrent = 1;
	public static int totalLevel = 15;

	public static int getStarByLevel(int finished_time) {
		int timelevel = getTimeLevel();
		
		int stars = 3;
		if (finished_time <= timelevel / 4)
			stars = 3;
		else if (finished_time <= timelevel / 3)
			stars = 2;
		else if (finished_time <= timelevel / 2)
			stars = 1;
		else
			stars = 0;
		
		return stars;
	}

	public static int getTimeLevel() {
		int time = Config.TIME_EVERY_ITEM * ((MT.row-2) * (MT.column-2))
				+  Config.TIME_DEC_BY_ROUND * (-1 + levelCurrent);
		if (time > Config.TIME_MAXIMUM)
			time = 540;
		
		return time;
	}
}
