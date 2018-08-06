package com.jiang.connectgame.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.R;
import com.jiang.connectgame.RatingActivity;
import com.jiang.connectgame.components.Dollar;
import com.jiang.connectgame.components.Level;
import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.sound.Sound;
import com.jiang.connectgame.util.Util;
import com.jiang.connectgame.util.UtilDialog;
import com.jiang.connectgame.util.UtilFormat;

public class DialogCompleted extends Dialog {
	Activity activity;

	public DialogCompleted(final Context context, int finished_time) {
		super(context, R.style.Theme_Dialog);
		UtilDialog.iniDialog(this);
		this.activity = ((Activity)context);
		setContentView(R.layout.dialog_completed);
		Util.resizeDialog((RelativeLayout)findViewById(R.id.linearLayout1));

		Button btn_next = (Button)findViewById(R.id.button_next);
		btn_next.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Menu.mSound.playClick();
				int time = RatingActivity.getRateTimes(DialogCompleted.this.getContext());
				if (time >= 0)
					RatingActivity.setRateTimes(DialogCompleted.this.getContext(), time + 1);
				
				if (((time + 1) % 2 == 0) && (time > 0) && (time < 11)) {
					Intent intent = new Intent(DialogCompleted.this.getContext(), RatingActivity.class);
					DialogCompleted.this.getContext().startActivity(intent);
				}
				DialogCompleted.this.dismiss();
				Play.mPlay.showDialogPlay(1);
			}
		});

		Button btn_level = (Button)findViewById(R.id.button_level);
		btn_level.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Menu.mSound.playClick();
				DialogCompleted.this.dismiss();
				DialogCompleted.this.activity.finish();
			}
		});
		((TextView)findViewById(R.id.textView_time)).setText(UtilFormat.getTime(finished_time));
		ImageView img_star1 = (ImageView)findViewById(R.id.imageView_star1);
		ImageView img_star2 = (ImageView)findViewById(R.id.imageView_star2);
		ImageView img_star3 = (ImageView)findViewById(R.id.imageView_star3);
		TextView txt_bonus = (TextView)findViewById(R.id.textView_bonus);

		img_star1.setVisibility(View.GONE);
		img_star2.setVisibility(View.GONE);
		img_star3.setVisibility(View.GONE);

		int star = Level.getStarByLevel(finished_time);
		int bonus = Config.SCORE_ROUND_SUCCESS * Level.levelCurrent;
		switch (star) {
		case 1:
			img_star1.setVisibility(View.VISIBLE);
			bonus += Config.SCORE_STAR_BONUS1;
			break;

		case 2:
			img_star1.setVisibility(View.VISIBLE);
			img_star2.setVisibility(View.VISIBLE);
			bonus += Config.SCORE_STAR_BONUS2;
			break;

		case 3:
			img_star1.setVisibility(View.VISIBLE);
			img_star2.setVisibility(View.VISIBLE);
			img_star3.setVisibility(View.VISIBLE);
			bonus += Config.SCORE_STAR_BONUS3;
			break;
		}

		Play.mPlay.total_dollar = (bonus + Play.mPlay.total_dollar);
		Play.mPlay.dollar_current = Play.mPlay.total_dollar;
		Play.mPlay.mDollar.updateDollar();
		txt_bonus.setText("+" + bonus);

		if (Level.levelCurrent > Level.totalLevel) {
			btn_next.setVisibility(View.GONE);
			btn_level.setVisibility(View.GONE);
			Button btn_yes = (Button)findViewById(R.id.button_yes);
			btn_yes.setVisibility(View.VISIBLE); 
			btn_yes.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Menu.mSound.playClick();
					DialogCompleted.this.dismiss();
					new Handler().postDelayed(new Runnable() {
						public void run() {
							new DialogWin(activity).show();
						}
					}, 0L);
				}
			});
		}
	}
}