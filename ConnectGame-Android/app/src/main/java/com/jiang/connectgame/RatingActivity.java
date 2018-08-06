package com.jiang.connectgame;

import org.anddev.andengine.ui.activity.BaseActivity;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.jiang.connectgame.Play;

public class RatingActivity extends BaseActivity {
	static final String PREF_INTRO = "pref_intro";
	public static final int RATE_TEN_TIMES = 11;
	static final String RATE_TIMES = "rate_times";
	protected static final int RATING_APP = 1237;

	public static int getRateTimes(Context context) {
		return context.getSharedPreferences("pref_intro", 0).getInt("rate_times", 0);
	}

	@SuppressLint("NewApi")
	private void setDialogBackgroundDim(Window paramWindow, float paramFloat) {
		LayoutParams localLayoutParams = getWindow().getAttributes();
		localLayoutParams.dimAmount = paramFloat;
		getWindow().setAttributes(localLayoutParams);
		getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);
		//setFinishOnTouchOutside(false);
	}

	public static void setRateTimes(Context context, int paramInt) {
		context.getSharedPreferences("pref_intro", 0).edit().putInt("rate_times", paramInt).commit();
	}

	public static void startRating(Activity paramActivity, int paramInt) {
//		paramActivity.startActivityForResult(
//				new Intent("android.intent.action.VIEW", Uri.parse(MarketUtils
//						.getJmtRateAppLink(paramActivity,
//								paramActivity.getPackageName()))), paramInt);
	}

	protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
		if (Play.mPlay != null)
			Play.mPlay.showDialogPlay(1);
		finish();
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		setDialogBackgroundDim(getWindow(), 0.6F);
		setContentView(R.layout.rate);
		findViewById(R.id.rate_yes).setOnClickListener(new OnClickListener() {
			public void onClick(View paramAnonymousView) {
				RatingActivity localRatingActivity = RatingActivity.this;
				RatingActivity.setRateTimes(localRatingActivity, -1);
				RatingActivity.startRating(localRatingActivity, 1237);
			}
		});
		findViewById(R.id.rate_not_now).setOnClickListener(new OnClickListener() {
			public void onClick(View paramAnonymousView) {
				if (Play.mPlay != null)
					Play.mPlay.showDialogPlay(1);
				RatingActivity.this.finish();
			}
		});
		findViewById(R.id.rate_never).setOnClickListener(new OnClickListener() {
			public void onClick(View paramAnonymousView) {
				RatingActivity.setRateTimes(RatingActivity.this, -1);
				if (Play.mPlay != null)
					Play.mPlay.showDialogPlay(1);
				RatingActivity.this.finish();
			}
		});
	}
}