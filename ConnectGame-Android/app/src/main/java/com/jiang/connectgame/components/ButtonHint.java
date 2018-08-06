package com.jiang.connectgame.components;

import java.util.ArrayList;

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
import android.graphics.Point;

public class ButtonHint extends MySprite {
	BitmapTextureAtlas buttonhint_BTA;
	Sprite buttonhint_SP;
	TextureRegion buttonhint_TR;

	public void onClickButtonHint() {
		if (MT.path.size() < 2)
			return;
		
		Point pt1 = MT.path.get(0);
		Point pt2 = MT.path.get(MT.path.size()-1);
		
		Play.mPlay.setHint(pt1.x, pt1.y, pt2.x, pt2.y);
		if (!Play.mPlay.mHint.visiable()) {
			Play.mPlay.mDollar.addDollar(Config.SCORE_HINT);
			Play.mPlay.mDollar.addTextSubDollar(Config.SCORE_HINT + " $");
		}
		Play.mPlay.mHint.setVisiable(true);
	}

	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
	}

	public void onLoadResources() {
		this.buttonhint_BTA = new BitmapTextureAtlas(128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonhint_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.buttonhint_BTA, this.mContext, "icon_question.PNG", 0, 0);
		TextureManager localTextureManager = this.mEngine.getTextureManager();
		ITexture[] arrayOfITexture = new ITexture[1];
		arrayOfITexture[0] = this.buttonhint_BTA;
		localTextureManager.loadTextures(arrayOfITexture);
	}

	public void onLoadScene(Scene paramScene) {
		this.mScene = paramScene;
		int i = Config.ITEM_HEIGHT;
		int j = Config.ITEM_HEIGHT;
		int k = Config.SCREENWIDTH - i;
		int m = -5 + (Config.SCREENHEIGHT - j);
		this.buttonhint_SP = new Sprite(k, m, i, j, this.buttonhint_TR) {
			public boolean onAreaTouched(TouchEvent paramAnonymousTouchEvent, float paramAnonymousFloat1, float paramAnonymousFloat2) {
				if (paramAnonymousTouchEvent.getAction() == 0) {
					Menu.mSound.playClick();
					ButtonHint.this.buttonhint_SP.setScale(1.3F);
				}
				while (paramAnonymousTouchEvent.getAction() != 1)
					return true;
				ButtonHint.this.buttonhint_SP.setScale(1.0F);
				ButtonHint.this.onClickButtonHint();
				return true;
			}
		};
		this.mScene.registerTouchArea(this.buttonhint_SP);
		this.mScene.attachChild(this.buttonhint_SP);
	}
}