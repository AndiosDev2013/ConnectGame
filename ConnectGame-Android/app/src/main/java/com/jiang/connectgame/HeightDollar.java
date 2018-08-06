package com.jiang.connectgame;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.database.ClassDollar;
import com.jiang.connectgame.database.Database;

public class HeightDollar extends MyApp {
	LinearLayout content;
	MySharedPreferences mySharedPreferences;

	public View getView(int paramInt, ClassDollar paramClassDollar) {
		View localView = View.inflate(this, R.layout.item_top10, null);
		TextView localTextView1 = (TextView)localView.findViewById(R.id.txt_stt);
		TextView localTextView2 = (TextView)localView.findViewById(R.id.txt_name);
		TextView localTextView3 = (TextView)localView.findViewById(R.id.txt_dollar);
		localTextView1.setText(String.valueOf(paramInt + 1));
		localTextView2.setText(paramClassDollar.getName());
		localTextView3.setText(String.valueOf(paramClassDollar.getDollar()));
		return localView;
	}

	public void onBackPressed() {
		super.onBackPressed();
		Menu.mSound.playClick();
		finish();
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_heightscore);
//		this.mySharedPreferences = new MySharedPreferences(this);
//		this.mySharedPreferences.getThemes();
//		this.content = ((LinearLayout)findViewById(R.id.content));
//		Database localDatabase = new Database(this);
//		localDatabase.openDatabase();
//		ArrayList localArrayList = localDatabase.getListDollar(Config.THEMES);
//		localDatabase.closeDatabase();
//		for (int i = 0; ; i++) {
//			if (i >= localArrayList.size()) {
//				((Button)findViewById(R.id.button_yes)).setOnClickListener(new View.OnClickListener() {
//					public void onClick(View paramAnonymousView) {
//						Menu.mSound.playClick();
//						HeightDollar.this.finish();
//					}
//				});
//				((TextView)findViewById(R.id.txt_theme)).setText("Theme: " + Setting.nameTheme[(-1 + Config.THEMES)]);
//				return;
//			}
//			this.content.addView(getView(i, (ClassDollar)localArrayList.get(i)));
//		}
	}
}