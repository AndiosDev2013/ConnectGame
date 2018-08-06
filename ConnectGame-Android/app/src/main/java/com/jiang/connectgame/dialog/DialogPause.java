package com.jiang.connectgame.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.R;
import com.jiang.connectgame.util.Util;
import com.jiang.connectgame.util.UtilDialog;

public class DialogPause extends Dialog implements View.OnClickListener {
	Activity activity;

	public DialogPause(Context context) {
		super(context, R.style.Theme_Dialog);
		UtilDialog.iniDialog(this);
		this.activity = ((Activity) context);
		setContentView(R.layout.dialog_pause);
		Util.resizeDialog((RelativeLayout) findViewById(R.id.linearLayout1));
		((Button) findViewById(R.id.button_continue)).setOnClickListener(this);
		((Button) findViewById(R.id.button_refresh)).setOnClickListener(this);
		((Button) findViewById(R.id.button_setting)).setOnClickListener(this);
		((Button) findViewById(R.id.button_level)).setOnClickListener(this);
	}

	public void onClick(View view) {
		Menu.mSound.playClick();
		switch (view.getId()) {
		case R.id.button_continue:
			dismiss();
			Play.mPlay.resumeGame();
			break;
			
		case R.id.button_refresh:
			dismiss();
			Play.mPlay.resetGame();
			break;
			
		case R.id.button_setting:
			showDialogSetting();
			break;

		case R.id.button_level:
			dismiss();
			Play.mPlay.finish();
			break;

		default:
			break;
		}
		
	}

	public void showDialogSetting() {
		new DialogSetting(this.activity).show();
	}
}