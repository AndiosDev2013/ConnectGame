package com.jiang.connectgame.components;

import android.graphics.Point;
import android.os.Handler;

import java.util.ArrayList;

import com.jiang.connectgame.Menu;
import com.jiang.connectgame.Play;
import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.log.MyLog;

public class ControllOnclick {
	public static boolean isOnClickItem = true;
	public static ItemPikachu item1 = null;
	public static ItemPikachu item2 = null;

	public static void activeSearhHint() {
	}

	public static void addIJToListPoint(int i, int j, ArrayList<Point> point_list) {
		Point point = new Point(i, j);
		if (!point_list.contains(point))
			point_list.add(point);
	}

	public static void click(ItemPikachu paramItemPikachu) {
		if (item1 == null) {
			item1 = paramItemPikachu;
			return;
		}
		item2 = paramItemPikachu;
		
		checkEat();
	}
	
	public static void checkEat() {
		isOnClickItem = false;
		int i1 = item1.getI();
		int j1 = item1.getJ();
		int i2 = item2.getI();
		int j2 = item2.getJ();
		
		if ((item1.getGT_ITEM() == item2.getGT_ITEM()) 
				&& ((i1 != i2) || (j1 != j2))
				&& MT.link(new Point(i1, j1), new Point(i2, j2))
				&& (MT.path.size() < 5)) {
			Menu.mSound.playGood();
			Play.mPlay.mDollar.addDollar(Config.SCORE_ITEM_LINK);
			Play.mPlay.mHint.setVisiable(false);
			Play.mPlay.mDrawLine.addLine(i1, j1, i2, j2, MT.path);
			Play.mPlay.removeItem(i1, j1);
			Play.mPlay.removeItem(i2, j2);
			MT.mt[i1][j1] = -1;
			MT.mt[i2][j2] = -1;
			item1.removeItem();
			item2.removeItem();
			item1 = null;
			item2 = null;
			
			// if all item is removed.
			if (Play.mPlay.mManagerItemPikachu.win()) {
				Play.mPlay.GameOver = true;
				Play.mPlay.showDialogCompleted();
				return;
			}
			
			Play.mPlay.mManagerItemPikachu.moveItem(i1, j1, i2, j2);
			
			if (MT.die()) {
				// if there is no item to link.
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Play.mPlay.mManagerItemPikachu.swapItem();
						MyLog.LogInfo("----------swapItem----------");
						
						Menu.mSound.playRandom();
					}
				}, 2000);
			}

		} else {
			Menu.mSound.playBad();
		}
		resetItem();
		isOnClickItem = true;
	}

	public static void resetItem() {
		if (item1 != null) {
			item1.setScale(1.0F);
			item1.setRotation(0);
		}
		if (item2 != null) {
			item2.setScale(1.0F);
			item2.setRotation(0);
		}
		item1 = null;
		item2 = null;
	}

//	public static ArrayList<Point> search(ArrayList<Point> paramArrayList) {
//		int i = 0;
//		if (i >= MT.row)
//			return paramArrayList;
//		int k;
//		for (int j = 0;; j++) {
//			if (j >= MT.column) {
//				i++;
//				break;
//			}
//			k = MT.mt[i][j];
//			if (k != -1)
//				;
//		}
//		int m = 0;
//		int j = 0;
//		if (m < MT.row)
//			;
//		for (int n = 0;; n++) {
//			if (n >= MT.column) {
//				m++;
//				break;
//			}
//			if ((k == MT.mt[m][n]) && ((i != m) || (j != n))) {
//				ArrayList localArrayList = new ArrayList();
//				if ((checkCondition(i, j, m, n, localArrayList))
//						&& (localArrayList.size() < 5)) {
//					paramArrayList.add(new Point(i, j));
//					paramArrayList.add(new Point(m, n));
//					return paramArrayList;
//				}
//			}
//		}
//		return null;
//	}
//
//	public static boolean searchHint() {
//		ArrayList localArrayList = new ArrayList();
//		search(localArrayList);
//		if (localArrayList.size() == 2) {
//			Point localPoint1 = (Point) localArrayList.get(0);
//			Point localPoint2 = (Point) localArrayList.get(1);
//			Play.mPlay.setHint(localPoint1.x, localPoint1.y, localPoint2.x,localPoint2.y);
//			return true;
//		}
//		return false;
//	}
}
