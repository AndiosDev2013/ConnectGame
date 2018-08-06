package com.jiang.connectgame.components;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;

import android.content.Context;

import com.jiang.connectgame.Play;

public abstract class MySprite {
	public Camera mCamera = null;
	public Context mContext = null;
	public Engine mEngine = null;
	public Play mPlay = null;
	public Scene mScene = null;

	public void ini(Context context, Engine paramEngine, Camera paramCamera) {
		this.mContext = context;
		this.mEngine = paramEngine;
		this.mCamera = paramCamera;
		this.mPlay = ((Play) context);
	}

	public abstract void onCreate(Context context, Engine paramEngine, Camera paramCamera);

	public abstract void onDestroy();

	public abstract void onLoadResources();

	public abstract void onLoadScene(Scene paramScene);
}