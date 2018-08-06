package com.jiang.connectgame;

import com.jiang.connectgame.config.Config;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class Setting extends MyApp implements OnClickListener {
	public static boolean isMusic = true;
	public static boolean isSound = true;
	public static String[] nameTheme = { "Troll", "Card", "Sea Animal", "Christmas", "Candy" };
	Button button_back;
	CheckBox checkBox_music;
	CheckBox checkBox_sound;
	CheckBox checkBox_theme1;
	CheckBox checkBox_theme2;
	CheckBox checkBox_theme3;
	CheckBox checkBox_theme4;
	CheckBox checkBox_theme5;
	MySharedPreferences mySharedPreferences;

	public void onBackPressed() {
		Menu.mSound.playClick();
		finish();
	}

	public void onClick(View v) {
		if (Menu.mSound != null)
			Menu.mSound.playClick();
		
		switch (v.getId()) {
		case R.id.textView4:
			break;
		
		case R.id.button_back:
			finish();
			break;
		
		case R.id.checkBox_music:
			isMusic = this.checkBox_music.isChecked();
			this.mySharedPreferences.updateIsSound(isMusic);
			break;
		case R.id.checkBox_sound:
			isSound = this.checkBox_sound.isChecked();
			this.mySharedPreferences.updateIsSound(isSound);
			break;

		case R.id.checkBox_theme1:
			setThemes(1);
			break;
		case R.id.checkBox_theme2:
			setThemes(2);
			break;
		case R.id.checkBox_theme3:
			setThemes(3);
			break;
		case R.id.checkBox_theme4:
			setThemes(4);
			break;
		case R.id.checkBox_theme5:
			setThemes(5);
			break;
			
		default:
			break;
		}
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_setting);
		this.mySharedPreferences = new MySharedPreferences(this);
		this.mySharedPreferences.getIsSound();
		this.mySharedPreferences.getIsMusic();
		this.mySharedPreferences.getThemes();
		this.button_back = ((Button) findViewById(R.id.button_back));
		this.button_back.setOnClickListener(this);
		this.checkBox_music = ((CheckBox) findViewById(R.id.checkBox_music));
		this.checkBox_music.setOnClickListener(this);
		this.checkBox_music.setChecked(isMusic);
		this.checkBox_sound = ((CheckBox) findViewById(R.id.checkBox_sound));
		this.checkBox_sound.setOnClickListener(this);
		this.checkBox_sound.setChecked(isSound);
		this.checkBox_theme1 = ((CheckBox) findViewById(R.id.checkBox_theme1));
		this.checkBox_theme1.setOnClickListener(this);
		this.checkBox_theme2 = ((CheckBox) findViewById(R.id.checkBox_theme2));
		this.checkBox_theme2.setOnClickListener(this);
		this.checkBox_theme3 = ((CheckBox) findViewById(R.id.checkBox_theme3));
		this.checkBox_theme3.setOnClickListener(this);
		this.checkBox_theme4 = ((CheckBox) findViewById(R.id.checkBox_theme4));
		this.checkBox_theme4.setOnClickListener(this);
		this.checkBox_theme5 = ((CheckBox) findViewById(R.id.checkBox_theme5));
		this.checkBox_theme5.setOnClickListener(this);
		setThemes(Config.THEMES);
	}

	public void setThemes(int paramInt) {
		this.checkBox_theme1.setChecked(false);
		this.checkBox_theme2.setChecked(false);
		this.checkBox_theme3.setChecked(false);
		this.checkBox_theme4.setChecked(false);
		this.checkBox_theme5.setChecked(false);

		switch (paramInt) {
		case 1:
			this.checkBox_theme1.setChecked(true);
			break;

		case 2:
			this.checkBox_theme2.setChecked(true);
			break;

		case 3:
			this.checkBox_theme3.setChecked(true);
			break;

		case 4:
			this.checkBox_theme4.setChecked(true);
			break;

		case 5:
			this.checkBox_theme5.setChecked(true);
			break;

		default:
			break;
		}
		
		Config.THEMES = paramInt;
		this.mySharedPreferences.updateThemes(paramInt);
		Config.pathTHEME = "item/theme" + Config.THEMES + "/";
	}
}