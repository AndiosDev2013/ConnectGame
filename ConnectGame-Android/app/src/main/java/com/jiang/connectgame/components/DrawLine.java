package com.jiang.connectgame.components;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;



import java.util.ArrayList;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.scene.Scene;

import com.jiang.connectgame.config.Config;

public class DrawLine extends MySprite {
	int heightLine = 20;

	public void addLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt1 == paramInt3) {
			if (paramInt2 < paramInt4) {
				paramInt2 -= 4;
				paramInt4 += 4;
			} else {
				paramInt2 += 4;
				paramInt4 -= 4;
			}
		} else if (paramInt2 == paramInt4) {
			if (paramInt1 < paramInt3) {
				paramInt1 -= 4;
				paramInt3 += 4;
			} else {
				paramInt1 += 4;
				paramInt3 -= 4;
			}
		}

		int i = paramInt1 + Config.ITEM_WIDTH / 2;
		int j = paramInt2 + Config.ITEM_HEIGHT / 2;
		int k = paramInt3 + Config.ITEM_WIDTH / 2;
		int m = paramInt4 + Config.ITEM_HEIGHT / 2;
	
		Line localLine = new Line(i, j, k, m, this.heightLine);
		localLine.setColor(1.0F, 1.0F, 1.0F);
		this.mScene.attachChild(localLine);
		removeLine(localLine);
		
		return;
	}

	public void addLine(int i1, int j1, int i2, int j2, ArrayList<Point> paramArrayList) {
		Point p1 = MT.getXYByIJ(i1, j1);
		Point p2 = MT.getXYByIJ(i2, j2);
		if (paramArrayList.size() == 2) {
			addLine(p1.x, p1.y, p2.x, p2.y);
			return;
		}
		
		if (paramArrayList.size() == 3) {
			Point index3 = paramArrayList.get(1);
			Point p3 = MT.getXYByIJ(index3.x, index3.y);
			addLine(p1.x, p1.y, p3.x, p3.y);
			addLine(p3.x, p3.y, p2.x, p2.y);
			return;
		}
		
		if (paramArrayList.size() == 4) {
			Point index3 = paramArrayList.get(1);
			Point index4 = paramArrayList.get(2);
			Point p3 = MT.getXYByIJ(index3.x, index3.y);
			Point p4 = MT.getXYByIJ(index4.x, index4.y);
			addLine(p1.x, p1.y, p3.x, p3.y);
			addLine(p3.x, p3.y, p4.x, p4.y);
			addLine(p4.x, p4.y, p2.x, p2.y);
			return;
		}
	}

	public void onCreate(Context context, Engine paramEngine, Camera paramCamera) {
		ini(context, paramEngine, paramCamera);
	}

	public void onDestroy() {
	}

	public void onLoadResources() {
	}

	public void onLoadScene(Scene paramScene) {
		this.mScene = paramScene;
		if ((Config.SCREENHEIGHT >= 320) && (Config.SCREENHEIGHT < 480))
			this.heightLine = 10;
		else if (Config.SCREENHEIGHT < 320)
			this.heightLine = 6;
		return;
	}

	public void removeLine(final Line paramLine) {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				DrawLine.this.mEngine.runOnUpdateThread(new Runnable() {
					public void run() {
						DrawLine.this.mScene.detachChild(paramLine);
					}
				});
			}
		}, 300L);
	}
}