package com.jiang.connectgame.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.MyApp;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.R;
import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.database.ClassDollar;
import com.jiang.connectgame.database.Database;
import com.jiang.connectgame.sound.Sound;
import com.jiang.connectgame.util.Util;
import com.jiang.connectgame.util.UtilDialog;

public class DialogSaveDollar extends Dialog {
	Activity activity;
	EditText editText_name;

	public DialogSaveDollar(final Context context) {
		super(context, R.style.Theme_Dialog);
		UtilDialog.iniDialog(this);
		this.activity = ((Activity) context);
		setContentView(R.layout.dialog_savedollar);
		Util.resizeDialog((RelativeLayout) findViewById(R.id.linearLayout1));
		this.editText_name = ((EditText) findViewById(R.id.editText_name));
		((Button) findViewById(R.id.button_yes)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Menu.mSound.playClick();
				String str = DialogSaveDollar.this.editText_name.getText().toString();
				if (str.length() == 0)
					str = "Player";
				
				// save to database
//				Database localDatabase = new Database(context);
//				localDatabase.openDatabase();
//				localDatabase.addDollar(new ClassDollar(str, Play.mPlay.dollar_current, Config.THEMES));
//				localDatabase.closeDatabase();
				
				// save high score to SWARM LEADER BOARD
				MyApp.instance.highscoreToSwarmLeaderBoard(Play.mPlay.dollar_current);
				
				DialogSaveDollar.this.dismiss();
				Util.showToast(context, "Save success.");
				Play.mPlay.finish();
			}
		});
	}
}
