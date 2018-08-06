package com.jiang.connectgame.components;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;

import com.jiang.connectgame.Play;
import com.jiang.connectgame.config.Config;

public class ProgressBar extends MySprite {
	int current_time = 0;
	boolean isPause = false;
	boolean isStop = false;
	Rectangle mRectangle;
	float midYButtonPause = 0.0F;
	float pX_end = 0.0F;
	float pX_start = 0.0F;
	BitmapTextureAtlas prb_BTA;
	Sprite prb_SP;
	TextureRegion prb_TR;
	int total_time = 0;
	float w = -1.0F;
	float width_rect = 0.0F;

	public int getTimeEnd() {
		return Math.abs(this.current_time - this.total_time);
	}

	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
		this.isStop = true;
	}

	public void onLoadResources() {
		this.prb_BTA = new BitmapTextureAtlas(512, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.prb_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.prb_BTA, this.mContext, "progressbar.png", 0, 0);
		TextureManager localTextureManager = this.mEngine.getTextureManager();
		ITexture[] arrayOfITexture = new ITexture[1];
		arrayOfITexture[0] = this.prb_BTA;
		localTextureManager.loadTextures(arrayOfITexture);
	}

	public void onLoadScene(Scene paramScene) {
		this.mScene = paramScene;
		int i = (int) (this.prb_TR.getHeight() * Config.getRaceHeight());
		int j = 5 + (int) (this.midYButtonPause - this.prb_TR.getHeight() / 2);
		int k = (int) (this.pX_end - this.pX_start);
		this.width_rect = (k - 6);
		this.mRectangle = new Rectangle(3.0F + this.pX_start, j + 3, this.width_rect, i - 6);
		this.mRectangle.setColor(1.0F, 0.01F, 0.02F);
		this.mScene.attachChild(this.mRectangle);
		this.prb_SP = new Sprite(this.pX_start, j, k, i, this.prb_TR);
		this.mScene.attachChild(this.prb_SP);
	}

	public void setMidYButtonPause(float paramFloat) {
		this.midYButtonPause = paramFloat;
	}

	public void setPause(boolean paramBoolean) {
		this.isPause = paramBoolean;
	}

	public void setStop(boolean paramBoolean) {
		this.isStop = paramBoolean;
	}

	public void setTotalTime(int paramInt) {
		this.total_time = paramInt;
	}

	public void setXStartEnd(float paramFloat1, float paramFloat2) {
		this.pX_start = paramFloat1;
		this.pX_end = paramFloat2;
	}

	public void start() {
		if (this.total_time < 0)
			return;
		this.current_time = this.total_time;
		this.w = (this.width_rect / this.current_time);
		this.mRectangle.setVisible(true);
		this.mRectangle.setWidth(this.width_rect);
		this.isStop = false;
		this.isPause = false;
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					if ((ProgressBar.this.isStop) || (ProgressBar.this.current_time <= 0)) {
						if (ProgressBar.this.current_time <= 0)
							Play.mPlay.gameOver();
						return;
					}
					
					if (!ProgressBar.this.isPause) {
						try {
							Thread.sleep(1000L);
							ProgressBar localProgressBar = ProgressBar.this;
							localProgressBar.current_time = (-1 + localProgressBar.current_time);
							ProgressBar.this.updateProgressBar(ProgressBar.this.w);
							
						} catch (InterruptedException localInterruptedException) {
							localInterruptedException.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	public void updateProgressBar(float paramFloat) {
		if ((this.current_time > 0) && (!this.isStop)) {
			this.mRectangle.setWidth(this.mRectangle.getWidth() - paramFloat);
			return;
		}
		this.mRectangle.setVisible(false);
	}

}
