package com.jiang.connectgame.dialog;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.R;
import com.jiang.connectgame.RatingActivity;
import com.jiang.connectgame.util.Util;
import com.jiang.connectgame.util.UtilDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DialogExit extends Dialog implements OnClickListener {
	Activity activity;
	ImageView icon_star;
	Button no;
	Button yes;

	public DialogExit(Context context) {
		super(context, R.style.Theme_Dialog);
		UtilDialog.iniDialog(this);
		this.activity = ((Activity) context);
		setContentView(R.layout.dialog_exit);
		Util.resizeDialog((RelativeLayout) findViewById(R.id.linearLayout1));
		this.yes = ((Button) findViewById(R.id.yes));
		this.yes.setOnClickListener(this);
		this.no = ((Button) findViewById(R.id.no));
		this.no.setOnClickListener(this);
		this.icon_star = ((ImageView) findViewById(R.id.icon_star));
		this.icon_star.setOnClickListener(this);
	}

	public void nextRate() {
		try {
			Intent localIntent = new Intent(getContext(), RatingActivity.class);
			getContext().startActivity(localIntent);
			return;
		} catch (Exception localException) {
		}
	}

	@Override
	public void onClick(View v) {
		Menu.mSound.playClick();
		int id = v.getId();
		if (id == R.id.yes) {
			dismiss();
			this.activity.finish();
		} else if (id == R.id.no) {
			dismiss();
		} else if (id == R.id.icon_star) {
			nextRate();
		} else {
			return;
		}
	}
}
