package com.jiang.connectgame;

import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.log.MyLog;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
	public final String PREFS_NAME = "MyPrefs";
	SharedPreferences.Editor editor;
	SharedPreferences my_share;

	public MySharedPreferences(Context context) {
		this.my_share = context.getSharedPreferences("MyPrefs", 0);
		this.editor = this.my_share.edit();
	}

	public void getIsMusic() {
		Setting.isMusic = this.my_share.getBoolean("isMusic", true);
	}

	public void getIsSound() {
		Setting.isSound = this.my_share.getBoolean("isSound", true);
		MyLog.LogInfo("getIsSound = " + Setting.isSound);
	}

	public void getThemes() {
		Config.THEMES = this.my_share.getInt("THEMES", 1);
	}

	public void updateIsMusic(boolean paramBoolean) {
		Setting.isMusic = paramBoolean;
		this.editor.putBoolean("isMusic", paramBoolean);
		this.editor.commit();
	}

	public void updateIsSound(boolean paramBoolean) {
		Setting.isSound = paramBoolean;
		this.editor.putBoolean("isSound", paramBoolean);
		this.editor.commit();
		MyLog.LogInfo("updateIsSound = " + paramBoolean);
	}

	public void updateThemes(int paramInt) {
		Config.THEMES = paramInt;
		this.editor.putInt("THEMES", paramInt);
		this.editor.commit();
	}
}