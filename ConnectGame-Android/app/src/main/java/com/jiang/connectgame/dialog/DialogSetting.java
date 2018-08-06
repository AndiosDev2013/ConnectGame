package com.jiang.connectgame.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.MySharedPreferences;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.R;
import com.jiang.connectgame.Setting;
import com.jiang.connectgame.util.Util;
import com.jiang.connectgame.util.UtilDialog;

public class DialogSetting extends Dialog implements View.OnClickListener {
	Activity activity;
	CheckBox checkBox_music;
	CheckBox checkBox_sound;
	MySharedPreferences mySharedPreferences;
	Button yes;

	public DialogSetting(Context context) {
		super(context, R.style.Theme_Dialog);
		UtilDialog.iniDialog(this);
		this.activity = ((Activity) context);
		setContentView(R.layout.dialog_setting);
		this.mySharedPreferences = new MySharedPreferences(context);
		this.mySharedPreferences.getIsMusic();
		this.mySharedPreferences.getIsSound();
		Util.resizeDialog((RelativeLayout) findViewById(R.id.linearLayout1));
		this.yes = ((Button) findViewById(R.id.yes));
		this.yes.setOnClickListener(this);
		this.checkBox_music = ((CheckBox) findViewById(R.id.checkBox_music));
		this.checkBox_music.setOnClickListener(this);
		this.checkBox_music.setChecked(Setting.isMusic);
		this.checkBox_sound = ((CheckBox) findViewById(R.id.checkBox_sound));
		this.checkBox_sound.setOnClickListener(this);
		this.checkBox_sound.setChecked(Setting.isSound);
	}

	public void onClick(View paramView) {
		Menu.mSound.playClick();
		int id = paramView.getId();
		if (id == R.id.yes) {
			dismiss();
			return;
		} else if (id == R.id.checkBox_music) {
			Setting.isMusic = this.checkBox_music.isChecked();
			if (Setting.isMusic)
				Play.mPlay.musicBackground.resume();
			else
				Play.mPlay.musicBackground.pause();
			this.mySharedPreferences.updateIsMusic(Setting.isMusic);
			return;
		} else if (id == R.id.checkBox_sound) {
			Setting.isSound = this.checkBox_sound.isChecked();
			this.mySharedPreferences.updateIsSound(Setting.isSound);
			return;
		} else {
			return;
		}
	}
}
