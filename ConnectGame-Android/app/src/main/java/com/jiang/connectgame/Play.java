package com.jiang.connectgame;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.view.RenderSurfaceView;

import com.jiang.connectgame.components.Background;
import com.jiang.connectgame.components.ButtonHint;
import com.jiang.connectgame.components.ButtonPause;
import com.jiang.connectgame.components.ControllOnclick;
import com.jiang.connectgame.components.Dollar;
import com.jiang.connectgame.components.DrawLine;
import com.jiang.connectgame.components.Hint;
import com.jiang.connectgame.components.Level;
import com.jiang.connectgame.components.MT;
import com.jiang.connectgame.components.ManagerItemPikachu;
import com.jiang.connectgame.components.ProgressBar;
import com.jiang.connectgame.components.TextLoading;
import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.dialog.DialogCompleted;
import com.jiang.connectgame.dialog.DialogGameOver;
import com.jiang.connectgame.dialog.DialogPause;
import com.jiang.connectgame.dialog.DialogPlay;
import com.jiang.connectgame.log.MyLog;
import com.jiang.connectgame.sound.MusicBackground;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.widget.FrameLayout;

public class Play extends Game {
	public static Play mPlay;
	public boolean GameOver = false;
	public int dollar_current = 0;
	Background mBackground;
	ButtonHint mButtonHint;
	ButtonPause mButtonPause;
	public Dollar mDollar;
	public DrawLine mDrawLine;
	Handler mHandler_gameover = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			new DialogGameOver(Play.this).show();
		}
	};
	Handler mHandler_showDialogPlay = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			new DialogPlay(Play.this, message.what).show();
		}
	};
	public Hint mHint;
	public ManagerItemPikachu mManagerItemPikachu;
	ProgressBar mProgressBar;
	TextLoading mTextLoading;
	public MusicBackground musicBackground;
	public int total_dollar = 0;

	public void gameOver() {
		this.mHandler_gameover.sendEmptyMessage(0);
	}

	public void load() {
		new Thread(new Runnable() {
			public void run() {
				Play.this.mProgressBar.onLoadResources();
				Play.this.mButtonPause.onLoadResources();
				Play.this.mButtonHint.onLoadResources();
				Play.this.mDollar.onLoadResources();
				Play.this.mHint.onLoadResources();
				Play.this.mButtonPause.onLoadScene(Play.this.mScene);
				Play.this.mButtonHint.onLoadScene(Play.this.mScene);
				Play.this.mDollar.onLoadScene(Play.this.mScene);
				Play.this.mProgressBar.setXStartEnd(Play.this.mDollar.getXendDollar(),Play.this.mButtonPause.getStartX());
				Play.this.mProgressBar.setMidYButtonPause(Play.this.mButtonPause.getMidY());
				Play.this.mProgressBar.onLoadScene(Play.this.mScene);
				MT.initMT();
				Play.this.mManagerItemPikachu.setScene(Play.this.mScene);
				Play.this.mManagerItemPikachu.addItem();
				Play.this.mHint.onLoadScene(Play.this.mScene);
				Play.this.mDrawLine.onLoadScene(Play.this.mScene);
				Play.this.mTextLoading.hideTextLoading();
				Play.this.showDialogPlay(0);
			}
		}).start();
	}

	public void onBackPressed() {
		Menu.mSound.playClick();
		mPlay.pauseGame();
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		MyLog.LogInfo("onCreate");
		this.musicBackground = new MusicBackground();
		this.musicBackground.loadMusic(this);
		if (Setting.isMusic)
			this.musicBackground.play();
		else
			this.musicBackground.pause();
		
		Level.levelCurrent = 1;
		ControllOnclick.isOnClickItem = true;
		mPlay = this;
		this.mBackground = new Background();
		this.mBackground.onCreate(this.mContext, this.mEngine, this.mCamera);
		this.mTextLoading = new TextLoading();
		this.mTextLoading.onCreate(mPlay, this.mEngine, this.mCamera);
		this.mButtonPause = new ButtonPause();
		this.mButtonPause.onCreate(this.mContext, this.mEngine, this.mCamera);
		this.mButtonHint = new ButtonHint();
		this.mButtonHint.onCreate(mPlay, this.mEngine, this.mCamera);
		this.mDollar = new Dollar();
		this.mDollar.onCreate(this.mContext, this.mEngine, this.mCamera);
		this.mProgressBar = new ProgressBar();
		this.mProgressBar.onCreate(this.mContext, this.mEngine, this.mCamera);
		this.mHint = new Hint();
		this.mHint.onCreate(this.mContext, this.mEngine, this.mCamera);
		this.mManagerItemPikachu = new ManagerItemPikachu(this.mContext, this.mEngine, this.mCamera);
		this.mDrawLine = new DrawLine();
		this.mDrawLine.onCreate(mPlay, this.mEngine, this.mCamera);
	}
	
	@Override
	protected void onSetContentView() {
		// TODO Auto-generated method stub
		super.onSetContentView();
		
		this.mRenderSurfaceView = new RenderSurfaceView(this);
        this.mRenderSurfaceView.setRenderer(this.mEngine);
        final FrameLayout.LayoutParams surfaceViewLayoutParams = new FrameLayout.LayoutParams(super.createSurfaceViewLayoutParams());

        //Creating the banner view.
        final FrameLayout.LayoutParams adViewLayoutParams = new FrameLayout.LayoutParams(
        				FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, 
        				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);

        final FrameLayout frameLayout = new FrameLayout(this);
        final FrameLayout.LayoutParams frameLayoutLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT);

        frameLayout.addView(this.mRenderSurfaceView,surfaceViewLayoutParams);
        this.setContentView(frameLayout, frameLayoutLayoutParams);
	}

	protected void onDestroy() {
		super.onDestroy();
		MyLog.LogInfo("onDestroy");
		try {
			this.mProgressBar.onDestroy();
			this.mHint.onDestroy();
			this.musicBackground.release();
			return;
		} catch (Exception localException) {
			MyLog.LogError("Play onDestroy " + localException.toString());
		}
	}

	public void onLoadComplete() {
	}

	public Engine onLoadEngine() {
		super.onLoadEngine();
		MyLog.LogInfo("onLoadEngine");
		return this.mEngine;
	}

	public void onLoadResources() {
		super.onLoadResources();
		this.mBackground.onLoadResources();
		this.mTextLoading.onLoadResources();
		MyLog.LogInfo("onLoadResources");
	}

	public Scene onLoadScene() {
		super.onLoadScene();
		this.mBackground.onLoadScene(this.mScene);
		this.mTextLoading.onLoadScene(this.mScene);
		load();
		MyLog.LogInfo("onLoadScene");
		return this.mScene;
	}

	protected void onStop() {
		super.onStop();
		MyLog.LogInfo("onStop");
	}

	public void pauseGame() {
		this.mProgressBar.setPause(true);
		new DialogPause(this).show();
	}

	public void removeItem(int paramInt1, int paramInt2) {
		mManagerItemPikachu.removeItem(paramInt1, paramInt2);
	}

	public void resetGame() {
		this.GameOver = false;
		MyLog.LogInfo("resetGame");
		this.mProgressBar.setStop(true);
		this.mTextLoading.showTextLoading();
		this.mHint.setVisiable(false);
		new Thread(new Runnable() {
			public void run() {
				ControllOnclick.isOnClickItem = true;
				Play.this.mBackground.resetBackground();
				Play.this.mManagerItemPikachu.reset();
				MT.initMT();
				Play.this.mManagerItemPikachu.addItem();
				try {
					Thread.sleep(1000L);
					Play.this.mTextLoading.hideTextLoading();
					Play.this.mDollar.reset();
					Play.this.mManagerItemPikachu.showItemEffect(Level.levelCurrent % 8, 1);
					Play.this.mProgressBar.setTotalTime(Level.getTimeLevel());
					Play.this.mProgressBar.start();
					Play.this.sortChildren();
					return;
				} catch (InterruptedException localInterruptedException) {
					localInterruptedException.printStackTrace();
				}
			}
		}).start();
	}
	
	public void resumeGame() {
		this.mProgressBar.setPause(false);
	}

	public void setHint(int i1, int j1, int i2, int j2) {
		this.mHint.setHint(i1, j1, i2, j2);
	}

	public void showDialogCompleted() {
		Menu.mSound.playFinish();
		final int finishedTime = this.mProgressBar.getTimeEnd();
		this.mProgressBar.setStop(true);
		mPlay.total_dollar = mPlay.dollar_current;
		Level.levelCurrent = 1 + Level.levelCurrent;
		new Handler().postDelayed(new Runnable() {
			public void run() {
				new DialogCompleted(Play.this, finishedTime).show();
			}
		}, 800L);
	}

	public void showDialogPlay(int paramInt) {
		this.mHandler_showDialogPlay.sendEmptyMessage(paramInt);
	}

	public void sortChildren() {
		this.mScene.sortChildren();
	}

	public void startGame() {
		ControllOnclick.isOnClickItem = true;
		this.GameOver = false;
		this.mProgressBar.setTotalTime(Level.getTimeLevel());
		this.mProgressBar.start();
		this.mManagerItemPikachu.showItemEffect(Level.levelCurrent % 8, 1);
	}
}