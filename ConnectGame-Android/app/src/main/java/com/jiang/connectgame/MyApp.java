package com.jiang.connectgame;


import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.util.UtilActivity;
import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmActivity;
import com.swarmconnect.SwarmLeaderboard;

import android.app.Activity;
import android.os.Bundle;

public class MyApp extends SwarmActivity  {
	
	public static MyApp instance;
	
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		UtilActivity.requestWindowFeature(this);
		getWindow().getAttributes().windowAnimations = R.style.Animations_Activity;
		
		instance = this;
		
		Swarm.init(this, Config.SWARM_CONNECT_APPID, Config.SWARM_CONNECT_APPKEY);
//		Swarm.setActive(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		Swarm.setActive(this);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		Swarm.setActive(this);
	}
	
	protected void onStart() {
		super.onStart();
	}

	protected void onStop() {
		super.onStop();
	}
	
	public void highscoreToSwarmLeaderBoard(int score) {
		SwarmLeaderboard.submitScore(Config.SWARM_CONNECT_LEADERBOARD_ID, score);
	}
}