package com.jiang.connectgame.config;

import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;

public class Config {
	/*
	 * Screen Size
	 */
	public static int SCREENWIDTH = 800;
	public static int SCREENHEIGHT = 480;
	public static int getCenterX() { return (int)SCREENWIDTH / 2; }
	public static int getCenterY() { return (int)SCREENHEIGHT / 2; }
	
	public static float getRaceWidth() { return 1.0f; }
	public static float getRaceHeight() { return 1.0f; }
	
	public static ScreenOrientation ScreenOrientation_Default = ScreenOrientation.LANDSCAPE;
	
	/*
	 * Themes
	 */
	public static int THEMES = 5;
	public static String pathTHEME = "item/theme1/";
	
	/*
	 * Items
	 */
	public static final int ITEM_WIDTH = 60;
	public static final int ITEM_HEIGHT = 65;
	
	/*
	 * Round Time
	 */
	public static final int TIME_EVERY_ITEM = 5;
	public static final int TIME_DEC_BY_ROUND = -5;
	public static final int TIME_MAXIMUM = 540;
	
	/*
	 * Score
	 */
	public static final int SCORE_ITEM_LINK = 100;
	public static final int SCORE_HINT = -1000;
	public static final int SCORE_ROUND_SUCCESS = 1000;
	public static final int SCORE_STAR_BONUS1 = 10000;
	public static final int SCORE_STAR_BONUS2 = 20000;
	public static final int SCORE_STAR_BONUS3 = 40000;
	
	/*
	 * Advertise
	 */
	
	public static final String ADMOB_PUBID = "a152220d147470b";
	public static final String REVMOB_PUBID = "5295eaec88a17f25ef000052";
	
	/*
	 * SwarmConnect
	 */
	
	public static final int SWARM_CONNECT_APPID = 8622;
	public static final String SWARM_CONNECT_APPKEY = "65d038a57c2752fb6651c3c206877f76";
	public static final int SWARM_CONNECT_LEADERBOARD_ID = 12680;
	
}