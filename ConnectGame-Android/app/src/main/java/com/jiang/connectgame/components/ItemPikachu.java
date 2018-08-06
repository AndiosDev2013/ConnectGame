package com.jiang.connectgame.components;

import android.content.Context;
import android.os.Handler;






import java.util.HashMap;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.IEntityModifier;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.modifier.IModifier;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.config.Config;

public class ItemPikachu extends MySprite {
	int GT_ITEM = -1;
	int I = -1;
	int J = -1;
	int X = -1;
	int Y = -1;
	BitmapTextureAtlas item_BTA;
	Sprite item_SP;
	TextureRegion item_TR;

	public int getGT_ITEM() {
		return this.GT_ITEM;
	}

	public int getI() {
		return this.I;
	}

	public int getJ() {
		return this.J;
	}

	public int getX() {
		return this.X;
	}

	public int getY() {
		return this.Y;
	}

	public void move(int fromX, int fromY, int paramInt3) {
		MoveModifier localMoveModifier = new MoveModifier(1.5F, fromX, this.X, fromY, this.Y);
		setVisiable(true);
		SequenceEntityModifier modifier = new SequenceEntityModifier(new IEntityModifier[] { localMoveModifier });
		this.item_SP.registerEntityModifier(modifier);
	}

	public void moveXY(int paramInt1, int paramInt2) {
		MoveModifier localMoveModifier = new MoveModifier(0.5F, this.X, paramInt1, this.Y, paramInt2);
		SequenceEntityModifier localSequenceEntityModifier = new SequenceEntityModifier(
			new IEntityModifier.IEntityModifierListener() {
				@Override
				public void onModifierFinished(IModifier<IEntity> paramAnonymousIModifier, IEntity paramAnonymousIEntity) {
				}

				@Override
				public void onModifierStarted(IModifier<IEntity> paramAnonymousIModifier, IEntity paramAnonymousIEntity) {
				}

			}, new IEntityModifier[] { localMoveModifier });
		this.item_SP.registerEntityModifier(localSequenceEntityModifier);
		this.X = paramInt1;
		this.Y = paramInt2;
	}

	public void newItemPikachu(Scene paramScene, int i, int j, int gt_item, int x, int y) {
		this.I = i;
		this.J = j;
		this.GT_ITEM = gt_item;
		this.X = x;
		this.Y = y;
		onLoadResources();
		onLoadScene(paramScene);
	}

	@Override
	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
		this.mEngine.runOnUpdateThread(new Runnable() {
			public void run() {
				ItemPikachu.this.mScene.detachChild(ItemPikachu.this.item_SP);
				ItemPikachu.this.mScene.unregisterTouchArea(ItemPikachu.this.item_SP);
			}
		});
	}

	public void onLoadResources() {
		if (!ManagerItemPikachu.map_Resources.containsKey(Integer.valueOf(this.GT_ITEM))) {
			this.item_BTA = new BitmapTextureAtlas(128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			this.item_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
					this.item_BTA, this.mContext, Config.pathTHEME + "item" + this.GT_ITEM + ".png", 0, 0);
			TextureManager localTextureManager = this.mEngine.getTextureManager();
			ITexture[] arrayOfITexture = new ITexture[1];
			arrayOfITexture[0] = this.item_BTA;
			localTextureManager.loadTextures(arrayOfITexture);
			ManagerItemPikachu.map_Resources.put(Integer.valueOf(this.GT_ITEM), this.item_TR);
			return;
		}
		this.item_TR = ((TextureRegion) ManagerItemPikachu.map_Resources.get(Integer.valueOf(this.GT_ITEM)));
	}

	@Override
	public void onLoadScene(Scene scene) {
		this.mScene = scene;
		this.item_SP = new Sprite(-100.0F, -100.0F, Config.ITEM_WIDTH, Config.ITEM_HEIGHT, this.item_TR) {
			public boolean onAreaTouched(TouchEvent paramAnonymousTouchEvent, float paramAnonymousFloat1, float paramAnonymousFloat2) {
				if (paramAnonymousTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					if (ControllOnclick.isOnClickItem) {
						Menu.mSound.playClick();
						ItemPikachu.this.item_SP.setScale(0.8F);
						ItemPikachu.this.item_SP.setRotation(30.0F);
					}
					
				} else if (paramAnonymousTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					ControllOnclick.click(ItemPikachu.this);
				}

				return true;
			}
		};
		setVisiable(false);
		this.mScene.registerTouchArea(this.item_SP);
		this.mScene.attachChild(this.item_SP);
	}

	public void removeItem() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				ItemPikachu.this.mEngine.runOnUpdateThread(new Runnable() {
					public void run() {
						ItemPikachu.this.mScene.detachChild(ItemPikachu.this.item_SP);
						ItemPikachu.this.mScene.unregisterTouchArea(ItemPikachu.this.item_SP);
					}
				});
			}
		}, 300L);
	}

	public void setGT_ITEM(int gt_item) {
		this.GT_ITEM = gt_item;
	}

	public void setI(int i) {
		this.I = i;
	}

	public void setIJ(int i, int j) {
		this.I = i;
		this.J = j;
	}

	public void setJ(int j) {
		this.J = j;
	}

	public void setPosition(int x, int y) {
		this.X = x;
		this.Y = y;
		this.item_SP.setPosition(x, y);
	}

	public void setRotation(int angle) {
		this.item_SP.setRotation(angle);
	}

	public void setScale(float factor) {
		this.item_SP.setScale(factor);
	}

	public void setVisiable(boolean visible) {
		this.item_SP.setVisible(visible);
	}

	public void setX(int x) {
		this.X = x;
	}

	public void setY(int y) {
		this.Y = y;
	}
}