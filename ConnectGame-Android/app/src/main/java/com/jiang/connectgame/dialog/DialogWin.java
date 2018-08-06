package com.jiang.connectgame.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.R;
import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.database.ClassDollar;
import com.jiang.connectgame.database.Database;
import com.jiang.connectgame.sound.Sound;
import com.jiang.connectgame.util.Util;
import com.jiang.connectgame.util.UtilDialog;

public class DialogWin extends Dialog {
	Activity activity;

	public DialogWin(final Context context) {
		super(context, R.style.Theme_Dialog);
		UtilDialog.iniDialog(this);
		this.activity = ((Activity) context);
		setContentView(R.layout.dialog_win);
		
//		Database db = new Database(context);
//		db.openDatabase();
//		final int i = db.checkIsInsert(new ClassDollar(" ", Play.mPlay.dollar_current, Config.THEMES));
//		db.closeDatabase();
		
		Util.resizeDialog((RelativeLayout) findViewById(R.id.linearLayout1));
		((Button) findViewById(R.id.button_yes)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Menu.mSound.playClick();
				DialogWin.this.dismiss();
//				if (i != -1) {
					DialogWin.this.showDialogSaveDollar(context);
//					return;
//				}
				Play.mPlay.finish();
			}
		});
		TextView dollarView = (TextView) findViewById(R.id.textView_dollar);
		dollarView.setText(Integer.toString(Play.mPlay.dollar_current));
	}

	public void showDialogSaveDollar(Context context) {
		new DialogSaveDollar(context).show();
	}
}
