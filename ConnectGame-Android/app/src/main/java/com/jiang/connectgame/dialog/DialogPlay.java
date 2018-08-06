package com.jiang.connectgame.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.R;
import com.jiang.connectgame.components.Level;
import com.jiang.connectgame.util.Util;
import com.jiang.connectgame.util.UtilDialog;
import com.jiang.connectgame.util.UtilFormat;

public class DialogPlay extends Dialog {
	Activity activity;

	public DialogPlay(Context context, final int paramInt) {
		super(context, R.style.Theme_Dialog);
		UtilDialog.iniDialog(this);
		this.activity = ((Activity) context);
		setContentView(R.layout.dialog_play);
		Util.resizeDialog((ViewGroup) findViewById(R.id.linearLayout1));
		
		((Button) findViewById(R.id.play)) .setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Menu.mSound.playClick();
				if (paramInt == 0) {
					Play.mPlay.startGame();
				} else {
					Play.mPlay.resetGame();
				}
				DialogPlay.this.dismiss();
			}
		});
		
		((TextView) findViewById(R.id.textView_level)).setText("Level " + String.valueOf(Level.levelCurrent));
		((TextView) findViewById(R.id.textView_time)).setText(UtilFormat.getTime(Level.getTimeLevel()));
	}
}
