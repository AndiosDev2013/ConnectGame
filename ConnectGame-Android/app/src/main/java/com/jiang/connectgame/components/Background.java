package com.jiang.connectgame.components;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.jiang.connectgame.config.Config;

import android.content.Context;

public class Background extends MySprite {
	BitmapTextureAtlas bg_BTA;
	TextureRegion bg_TR;
	int total_bg = 1;

	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
	}

	public void onLoadResources()
	{
		this.bg_BTA = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bg_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bg_BTA, this.mContext, "bg/bg/bg6.jpg", 0, 0);
		TextureManager localTextureManager = this.mEngine.getTextureManager();
		ITexture[] arrayOfITexture = new ITexture[1];
		arrayOfITexture[0] = this.bg_BTA;
		localTextureManager.loadTextures(arrayOfITexture);
	}

	public void onLoadScene(Scene paramScene) {
		this.mScene = paramScene;
		SpriteBackground localSpriteBackground = new SpriteBackground(new Sprite(0.0F, 0.0F, Config.SCREENWIDTH, Config.SCREENHEIGHT, this.bg_TR));
		this.mScene.setBackground(localSpriteBackground);
	}

	public void resetBackground() {
		onLoadResources();
		onLoadScene(this.mScene);
	}
}