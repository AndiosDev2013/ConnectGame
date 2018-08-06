package com.jiang.connectgame;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.jiang.connectgame.config.*;
import com.jiang.connectgame.R;

import android.content.Context;
import android.os.Bundle;


public class Game extends BaseGameActivity {
	public Camera mCamera = null;
	public Context mContext;
	public Engine mEngine = null;
	public Scene mScene = null;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		this.mContext = this;
		getWindow().getAttributes().windowAnimations = R.style.Animations_Activity;
	}

	protected void onDestroy() {
		super.onDestroy();
	}

	public void onLoadComplete() {
	}

	public Engine onLoadEngine() {
		this.mCamera = new Camera(0.0F, 0.0F, Config.SCREENWIDTH, Config.SCREENHEIGHT);
		this.mEngine = new Engine(new EngineOptions(true,
						Config.ScreenOrientation_Default,
						new RatioResolutionPolicy(Config.SCREENWIDTH, Config.SCREENHEIGHT), this.mCamera));
		return this.mEngine;
	}

	public void onLoadResources() {
	}

	public Scene onLoadScene() {
		this.mScene = new Scene();
		this.mScene.setTouchAreaBindingEnabled(true);
		return this.mScene;
	}
}