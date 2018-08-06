package com.jiang.connectgame.components;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.IEntityModifier;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.modifier.IModifier;

import com.jiang.connectgame.Play;
import com.jiang.connectgame.config.Config;

import android.content.Context;
import android.graphics.Typeface;

public class Dollar extends MySprite {
	BitmapTextureAtlas dollar_BTA;
	Sprite dollar_SP;
	TextureRegion dollar_TR;
	int heightFont = 30;
	Font mFont;
	BitmapTextureAtlas mFontTexture;
	ChangeableText txt_dollar;
	int x_end_dollar = 0;

	public void addDollar(int paramInt) {
		Play.mPlay.dollar_current = (paramInt + Play.mPlay.dollar_current);
		updateDollar();
	}

	public void addTextSubDollar(String paramString) {
		try {
			int i = Config.getCenterX() - this.mFont.getStringWidth(paramString) / 2;
			int j = Config.getCenterY() - this.heightFont / 2;
			final ChangeableText localChangeableText = new ChangeableText(i, j, this.mFont, paramString);
			this.mScene.attachChild(localChangeableText);
			MoveModifier localMoveModifier = new MoveModifier(2.0F, i, i, j, -100.0F);
			localChangeableText.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifier.IEntityModifierListener() {
					public void onModifierFinished(IModifier<IEntity> paramAnonymousIModifier, IEntity paramAnonymousIEntity) {
						Dollar.this.removeChangeableText(localChangeableText);
					}
	
					public void onModifierStarted(IModifier<IEntity> paramAnonymousIModifier, IEntity paramAnonymousIEntity) {
					}
				}, new IEntityModifier[] { localMoveModifier }));
			return;
		} catch (Exception localException) {
		}
	}

	public int getEndY() {
		return (int) (this.dollar_SP.getY() + this.dollar_SP.getHeight());
	}

	public int getXendDollar() {
		return this.x_end_dollar;
	}

	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
	}

	public void onLoadResources() {
		this.heightFont = ((int) (this.heightFont * Config.getRaceHeight()));
		this.dollar_BTA = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.dollar_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.dollar_BTA, this.mContext, "dollar.png", 0, 0);
		TextureManager localTextureManager = this.mEngine.getTextureManager();
		ITexture[] arrayOfITexture = new ITexture[1];
		arrayOfITexture[0] = this.dollar_BTA;
		localTextureManager.loadTextures(arrayOfITexture);
		this.mFontTexture = new BitmapTextureAtlas(1024, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mFont = new Font(this.mFontTexture, Typeface.createFromAsset(
				this.mContext.getAssets(), "font/BrushScriptStd.otf"), this.heightFont, true, -1);
		this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
		this.mEngine.getFontManager().loadFont(this.mFont);
	}

	public void onLoadScene(Scene paramScene) {
		this.mScene = paramScene;
		int i = (int) (this.dollar_TR.getWidth() * Config.getRaceWidth());
		int j = (int) (i * this.dollar_TR.getHeight() / this.dollar_TR.getWidth());
		this.dollar_SP = new Sprite(5, 0, i, j, this.dollar_TR);
		this.mScene.attachChild(this.dollar_SP);
		int k = 5 + i;
		int m = 0 + (j / 2 - this.heightFont / 2);
		this.txt_dollar = new ChangeableText(k, m, this.mFont, "", 30);
		this.x_end_dollar = (k + this.mFont.getStringWidth("0000000 - L.00"));
		updateDollar();
		this.mScene.attachChild(this.txt_dollar);
	}

	public void removeChangeableText(final ChangeableText paramChangeableText) {
		this.mEngine.runOnUpdateThread(new Runnable() {
			public void run() {
				try {
					Dollar.this.mScene.detachChild(paramChangeableText);
					return;
				} catch (Exception localException) {
				}
			}
		});
	}

	public void reset() {
		Play.mPlay.dollar_current = Play.mPlay.total_dollar;
		updateDollar();
	}

	public void updateDollar() {
		String str1 = String.valueOf(Play.mPlay.dollar_current);
		if (Play.mPlay.dollar_current == 0)
			str1 = "000";
		String str2 = str1 + " - L." + String.valueOf(Level.levelCurrent);
		this.txt_dollar.setText(str2);
	}
}