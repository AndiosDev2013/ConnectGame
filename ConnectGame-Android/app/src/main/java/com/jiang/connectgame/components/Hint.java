package com.jiang.connectgame.components;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.jiang.connectgame.Play;
import com.jiang.connectgame.config.Config;

import android.content.Context;
import android.graphics.Point;

public class Hint extends MySprite {
	AnimatedSprite hint1_AS;
	AnimatedSprite hint2_AS;
	BitmapTextureAtlas mBTA;
	TiledTextureRegion mTTR;

	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
	}

	public void onLoadResources() {
		this.mBTA = new BitmapTextureAtlas(1024, 512, TextureOptions.BILINEAR);
		this.mTTR = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
				this.mBTA, this.mContext, "animation_hint.png", 0, 0, 3, 1);
		this.mEngine.getTextureManager().loadTexture(this.mBTA);
	}

	public void onLoadScene(Scene paramScene) {
		this.mScene = paramScene;
		int i = Config.ITEM_WIDTH;
		int j = Config.ITEM_HEIGHT;
		this.hint1_AS = new AnimatedSprite(-100.0F, -100.0F, i, j, this.mTTR.deepCopy());
		this.hint1_AS.animate(150L);
		this.hint2_AS = new AnimatedSprite(-100.0F, -100.0F, i, j, this.mTTR.deepCopy());
		this.hint2_AS.animate(150L);
		setVisiable(false);
		this.hint1_AS.setZIndex(200);
		this.hint2_AS.setZIndex(200);
		this.mScene.attachChild(this.hint1_AS);
		this.mScene.attachChild(this.hint2_AS);
	}

	public void setHint(int i1, int j1, int i2, int j2) {
		Point p1 = MT.getXYByIJ(i1, j1);
		this.hint1_AS.setPosition(p1.x, p1.y);
		Point p2 = MT.getXYByIJ(i2, j2);
		this.hint2_AS.setPosition(p2.x, p2.y);
	}

	public void setVisiable(boolean paramBoolean) {
		if (Play.mPlay.GameOver)
			return;
		this.hint1_AS.setVisible(paramBoolean);
		this.hint2_AS.setVisible(paramBoolean);
	}

	public boolean visiable() {
		return this.hint1_AS.isVisible();
	}
}