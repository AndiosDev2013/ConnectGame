package com.jiang.connectgame.util;

import com.jiang.connectgame.config.Config;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class Util {
	public static int getRandomIndex(int paramInt1, int paramInt2) {
		return paramInt1 + (int) (Math.random() * (1 + (paramInt2 - paramInt1)));
	}

	public static void resizeDialog(View paramView) {
		LayoutParams localLayoutParams = paramView.getLayoutParams();
		localLayoutParams.width = ((int) (0.7D * Config.SCREENWIDTH));
		localLayoutParams.height = ((int) (0.7D * Config.SCREENHEIGHT));
		paramView.setLayoutParams(localLayoutParams);
	}

	public static void showToast(Context context, String paramString) {
		Toast.makeText(context, paramString, 0).show();
	}
}