package com.jiang.connectgame.components;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import android.content.Context;
import android.graphics.Typeface;

import com.jiang.connectgame.config.Config;

public class TextLoading extends MySprite {
	int heightFont = 50;
	boolean isStop = false;
	Font mFont;
	BitmapTextureAtlas mFontTexture;
	ChangeableText txt_loading;

	public void hideTextLoading() {
		this.txt_loading.setVisible(false);
		this.isStop = true;
	}

	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
		this.isStop = true;
		this.mEngine.runOnUpdateThread(new Runnable() {
			public void run() {
				try {
					TextLoading.this.mScene.detachChild(TextLoading.this.txt_loading);
					return;
				} catch (Exception localException) {
				}
			}
		});
	}

	public void onLoadResources() {
		this.mFontTexture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mFont = new Font(this.mFontTexture, Typeface.createFromAsset(
				this.mContext.getAssets(), "font/BrushScriptStd.otf"),this.heightFont, true, -1);
		this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
		this.mEngine.getFontManager().loadFont(this.mFont);
	}

	public void onLoadScene(Scene paramScene) {
		this.mScene = paramScene;
		this.heightFont = ((int) (this.heightFont * Config.getRaceHeight()));
		int i = Config.getCenterX() - this.mFont.getStringWidth("Loading...") / 2;
		int j = Config.getCenterY() - this.heightFont / 2;
		this.txt_loading = new ChangeableText(i, j, this.mFont, "Loading...", 30);
		this.mScene.attachChild(this.txt_loading);
		threadShowTextLoading();
	}

	public void showTextLoading() {
		this.txt_loading.setVisible(true);
		threadShowTextLoading();
	}

	public void threadShowTextLoading() {
		this.isStop = false;
		new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (true) {
					if (TextLoading.this.isStop)
						return;
					try {
						Thread.sleep(400L);
						if (i == 0) {
							TextLoading.this.txt_loading.setText("Loading");
							i = 1;
						} else if (i == 1) {
							TextLoading.this.txt_loading.setText("Loading.");
							i = 2;
						} else if (i == 2) {
							TextLoading.this.txt_loading.setText("Loading..");
							i = 3;
						} else if (i == 3) {
							TextLoading.this.txt_loading.setText("Loading...");
							i = 0;
						}
					} catch (Exception localException) {
						localException.printStackTrace();
					}
				}
			}
		}).start();
	}
}