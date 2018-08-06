package com.jiang.connectgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jiang.connectgame.dialog.DialogExit;
import com.jiang.connectgame.sound.Sound;
import com.swarmconnect.Swarm;

public class Menu extends MyApp implements OnClickListener {

	public static Sound mSound;
	MySharedPreferences mySharedPreferences;
	private Button exit;
	private Button play;
	private Button top10;
	private Button rate;
	private Button help;
	private Button setting;
	private Button more;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_menu);
		this.mySharedPreferences = new MySharedPreferences(this);
		this.mySharedPreferences.getIsMusic();
		this.mySharedPreferences.getIsSound();
		this.mySharedPreferences.getThemes();
		mSound = new Sound();
		mSound.loadSound(this);
		//Config.getDisplayScreen(this);
		this.play = ((Button) findViewById(R.id.play));
		this.play.setOnClickListener(this);
		this.help = ((Button) findViewById(R.id.help));
		this.help.setOnClickListener(this);
		this.setting = ((Button) findViewById(R.id.setting));
		this.setting.setOnClickListener(this);
		this.more = ((Button) findViewById(R.id.more));
		this.more.setOnClickListener(this);
		this.exit = ((Button) findViewById(R.id.exit));
		this.exit.setOnClickListener(this);
		this.top10 = ((Button) findViewById(R.id.top10));
		this.top10.setOnClickListener(this);
		this.rate = ((Button) findViewById(R.id.rate));
		this.rate.setOnClickListener(this);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mSound.playClick();
		switch (v.getId()) {
		case R.id.play:
			clickPlay();
			return;
		case R.id.help:
			clickHelp();
			return;
		case R.id.setting:
			clickSetting();
			return;
		case R.id.more:
			nextApplicationOther();
			return;
		case R.id.exit:
			clickExit();
			return;
		case R.id.top10:
			Swarm.showLeaderboards();
			return;
		case R.id.rate:
			clickRate();
			return;
		default:
		}
	}

	public void clickExit() {
		try {
			new DialogExit(this).show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickHelp() {
		try {
			startActivity(new Intent(this, Help.class));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickPlay() {
		try {
			startActivity(new Intent(this, Play.class));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickRate() {
		try {
			startActivity(new Intent(this, RatingActivity.class));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSetting() {
		try {
			startActivity(new Intent(this, Setting.class));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void nextApplicationOther() {
		try {
			//			startActivity(new Intent("android.intent.action.VIEW",
			//					Uri.parse(MarketUtils.getJmtAppsLink(this))));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onBackPressed() {
		mSound.playClick();
		clickExit();
	}


}