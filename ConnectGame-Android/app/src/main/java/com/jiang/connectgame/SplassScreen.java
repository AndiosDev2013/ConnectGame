package com.jiang.connectgame;

import com.jiang.connectgame.config.Config;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SplassScreen extends MyApp {

	@Override
	protected void onCreate(Bundle paramBundle) {
		// TODO Auto-generated method stub
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_splass_screen);

		((Button) findViewById(R.id.start)).setOnClickListener(new OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Intent localIntent = new Intent(SplassScreen.this, Menu.class);
				SplassScreen.this.startActivity(localIntent);
				SplassScreen.this.finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	public void onPause() {
		super.onPause();
	}

	protected void onResume() {
		super.onResume();
	}
}