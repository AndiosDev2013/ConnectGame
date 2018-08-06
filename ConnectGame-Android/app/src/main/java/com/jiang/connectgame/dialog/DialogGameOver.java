package com.jiang.connectgame.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.R;
import com.jiang.connectgame.components.Level;
import com.jiang.connectgame.util.Util;
import com.jiang.connectgame.util.UtilDialog;

public class DialogGameOver extends Dialog implements View.OnClickListener {
	public DialogGameOver(Context context) {
		super(context, R.style.Theme_Dialog);
		UtilDialog.iniDialog(this);
		Menu.mSound.playGameOver();
		setContentView(R.layout.dialog_gameover);
		Util.resizeDialog((RelativeLayout) findViewById(R.id.linearLayout1));
		((TextView) findViewById(R.id.textView_dollar)).setText(Play.mPlay.dollar_current + " - L." + Level.levelCurrent);
		((Button) findViewById(R.id.button_yes)).setOnClickListener(this);
	}

	public void onClick(View v) {
		Menu.mSound.playClick();
		
		switch (v.getId()) {
		case R.id.button_yes:
			dismiss();
			Play.mPlay.finish();
			break;
		}
	}
}
