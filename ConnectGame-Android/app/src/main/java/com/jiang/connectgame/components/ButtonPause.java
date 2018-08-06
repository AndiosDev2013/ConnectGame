package com.jiang.connectgame.components;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.config.Config;

import android.content.Context;

public class ButtonPause extends MySprite {
	BitmapTextureAtlas buttonpause_BTA;
	Sprite buttonpause_SP;
	TextureRegion buttonpause_TR;

	public float getMidY() {
		return (10.0F + this.buttonpause_SP.getHeight()) / 2.0F;
	}

	public float getStartX() {
		return this.buttonpause_SP.getX() - 20.0F;
	}

	public float getYPause() {
		return this.buttonpause_SP.getY() + this.buttonpause_TR.getHeight();
	}

	public void onClickButtonPause() {
		Play.mPlay.pauseGame();
	}

	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
	}

	public void onLoadResources() {
		this.buttonpause_BTA = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonpause_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.buttonpause_BTA, this.mContext, "pause.png", 0, 0);
		TextureManager localTextureManager = this.mEngine.getTextureManager();
		ITexture[] arrayOfITexture = new ITexture[1];
		arrayOfITexture[0] = this.buttonpause_BTA;
		localTextureManager.loadTextures(arrayOfITexture);
	}

	@Override
	public void onLoadScene(Scene paramScene) {
		this.mScene = paramScene;
		int i = Config.ITEM_HEIGHT;
		int j = Config.ITEM_HEIGHT;
		this.buttonpause_SP = new Sprite(-5 + (Config.SCREENWIDTH - i), 0, i, j, this.buttonpause_TR) {
			public boolean onAreaTouched(TouchEvent paramAnonymousTouchEvent, float paramAnonymousFloat1, float paramAnonymousFloat2) {
				if (paramAnonymousTouchEvent.getAction() == 0) {
					Menu.mSound.playClick();
					ButtonPause.this.buttonpause_SP.setScale(1.3F);
				}
				while (paramAnonymousTouchEvent.getAction() != 1)
					return true;
				ButtonPause.this.buttonpause_SP.setScale(1.0F);
				ButtonPause.this.onClickButtonPause();
				return true;
			}
		};
		this.mScene.registerTouchArea(this.buttonpause_SP);
		this.mScene.attachChild(this.buttonpause_SP);
	}
}