package com.jiang.connectgame.util;

import android.app.Activity;

public class UtilActivity {
	public static void requestWindowFeature(Activity paramActivity) {
		paramActivity.requestWindowFeature(1);
		paramActivity.getWindow().setFlags(1024, 1024);
	}
}
