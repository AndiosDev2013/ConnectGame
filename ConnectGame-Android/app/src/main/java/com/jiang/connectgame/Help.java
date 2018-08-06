package com.jiang.connectgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends MyApp implements View.OnClickListener {
	Button back;

	public void onBackPressed() {
		super.onBackPressed();
		Menu.mSound.playClick();
		finish();
	}

	@Override
	public void onClick(View v) {
		Menu.mSound.playClick();
		int id = v.getId();
		if (id == R.id.back) {
			finish();
		} else {
			return;
		}
	}
	
	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_help);
		this.back = ((Button) findViewById(R.id.back));
		this.back.setOnClickListener(this);
	}
}
