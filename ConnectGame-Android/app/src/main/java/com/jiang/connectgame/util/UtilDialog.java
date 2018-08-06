package com.jiang.connectgame.util;

import com.jiang.connectgame.R;

import android.app.Dialog;

public class UtilDialog {
	public static void iniDialog(Dialog paramDialog) {
		paramDialog.requestWindowFeature(1);
		paramDialog.setCancelable(false);
		paramDialog.getWindow().getAttributes().windowAnimations = R.style.Animations_Dialog;
	}
}
