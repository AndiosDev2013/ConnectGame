package com.jiang.connectgame.components;

import android.content.Context;
import android.graphics.Point;

import com.jiang.connectgame.Play;
import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.log.MyLog;
import com.jiang.connectgame.util.Util;

import java.util.ArrayList;
import java.util.HashMap;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class ManagerItemPikachu {
	public static HashMap<Integer, TextureRegion> map_Resources = new HashMap<Integer, TextureRegion>();
	ItemPikachu list_itemPikachu[][] = new ItemPikachu[MT.row-2][MT.column-2]; 
	public Camera mCamera = null;
	public Context mContext = null;
	public Engine mEngine = null;
	public Play mPlay = null;
	public Scene mScene = null;

	public ManagerItemPikachu(Context context, Engine paramEngine, Camera paramCamera) {
		this.mContext = context;
		this.mEngine = paramEngine;
		this.mCamera = paramCamera;
		this.mPlay = ((Play) context);
		map_Resources.clear();
	}

	public void addItem() {
		for (int i = 1; i < MT.row-1; i++) {
			for (int j = 1; j < MT.column-1; j++) {
				if (MT.mt[i][j] != -1) {
					int x = MT.XSTART + (j-1) * Config.ITEM_WIDTH;
					int y = MT.YSTART + (i-1) * Config.ITEM_HEIGHT;
					ItemPikachu item = new ItemPikachu();
					item.onCreate(this.mContext, this.mEngine, this.mCamera);
					item.newItemPikachu(this.mScene, i, j, MT.mt[i][j], x, y);
					list_itemPikachu[i-1][j-1] = item;
				}
			}
		}
	}

	public void move_up_down(ItemPikachu item, int deltaI) {
		int i = item.I;
		int j = item.J;
		MT.mt[i][j] = -1;
		MT.mt[i+deltaI][j] = item.GT_ITEM;
		item.setIJ(i+deltaI, j);
		Point localPoint = MT.getXYByIJ(i+deltaI, j);
		item.moveXY(localPoint.x, localPoint.y);

		list_itemPikachu[i-1+deltaI][j-1] = list_itemPikachu[i-1][j-1];
		list_itemPikachu[i-1][j-1] = null;
	}

	public void move_left_right(ItemPikachu item, int deltaJ) {
		int i = item.I;
		int j = item.J;
		MT.mt[i][j] = -1;
		MT.mt[i][j+deltaJ] = item.GT_ITEM;
		item.setIJ(i, j+deltaJ);
		Point localPoint = MT.getXYByIJ(i, j+deltaJ);
		item.moveXY(localPoint.x, localPoint.y);

		list_itemPikachu[i-1][j-1+deltaJ] = list_itemPikachu[i-1][j-1];
		list_itemPikachu[i-1][j-1] = null;
	}

	public void moveItem(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		switch ((Level.levelCurrent-1) % 9) {
		case 1:
			move_all_side_up(paramInt1, paramInt2, paramInt3, paramInt4);
			break;
		case 2:
			move_all_side_down(paramInt1, paramInt2, paramInt3, paramInt4);
			break;
		case 3:
			move_all_side_left(paramInt1, paramInt2, paramInt3, paramInt4);
			break;
		case 4:
			move_all_side_right(paramInt1, paramInt2, paramInt3, paramInt4);
			break;
		case 5:
			move_left_side_up_right_side_down(paramInt1, paramInt2, paramInt3, paramInt4);
			break;
		case 6:
			move_left_side_up_right_side_down(paramInt1, paramInt2, paramInt3, paramInt4);
			break;
		case 7:
			move_up_side_right_down_side_left(paramInt1, paramInt2, paramInt3, paramInt4);
			break;
		case 8:
			move_up_side_left_down_side_right(paramInt1, paramInt2, paramInt3, paramInt4);
			break;
		default:
			break;
		}
	}

	/*
	 * All side -> Up
	 */
	public void move_all_side_up(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt2 != paramInt4) {
			for (int i = paramInt1+1; i < MT.row-1; i++) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt2-1];
				if (item != null) {
					move_up_down(item, -1);
				}
			}
			for (int i = paramInt3+1; i < MT.row-1; i++) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt4-1];
				if (item != null) {
					move_up_down(item, -1);
				}
			}

		} else {
			if (paramInt1 > paramInt3) {
				int temp = 0;
				temp = paramInt1;
				paramInt1 = paramInt3;
				paramInt3 = temp;
				temp = paramInt2;
				paramInt2 = paramInt4;
				paramInt4 = temp;
			}
			for (int i = paramInt1+1; i < MT.row-1; i++) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt2-1];
				if (item != null) {
					if (i < paramInt3)
						move_up_down(item, -1);
					else if (i > paramInt3)
						move_up_down(item, -2);
				}
			}
		}
	}

	public void move_all_side_down(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt2 != paramInt4) {
			for (int i = paramInt1; i > 0; i--) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt2-1];
				if (item != null) {
					move_up_down(item, 1);
				}
			}
			for (int i = paramInt3; i > 0; i--) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt4-1];
				if (item != null) {
					move_up_down(item, 1);
				}
			}

		} else {
			if (paramInt1 < paramInt3) {
				int temp = 0;
				temp = paramInt1;
				paramInt1 = paramInt3;
				paramInt3 = temp;
				temp = paramInt2;
				paramInt2 = paramInt4;
				paramInt4 = temp;
			}

			for (int i = paramInt1; i > 0; i--) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt2-1];
				if (item != null) {
					if (i > paramInt3)
						move_up_down(item, 1);
					else if (i < paramInt3)
						move_up_down(item, 2);
				}
			}
		}
	}

	/*
	 * All side -> Left
	 */
	public void move_all_side_left(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt1 != paramInt3) {
			for (int j = paramInt2+1; j < MT.column-1; j++) {
				ItemPikachu item = list_itemPikachu[paramInt1-1][j-1];
				if (item != null) {
					move_left_right(item, -1);
				}
			}
			for (int j = paramInt4+1; j < MT.column-1; j++) {
				ItemPikachu item = list_itemPikachu[paramInt3-1][j-1];
				if (item != null) {
					move_left_right(item, -1);
				}
			}

		} else {
			if (paramInt2 > paramInt4) {
				int temp = 0;
				temp = paramInt1;
				paramInt1 = paramInt3;
				paramInt3 = temp;
				temp = paramInt2;
				paramInt2 = paramInt4;
				paramInt4 = temp;
			}
			for (int j = paramInt2+1; j < MT.column-1; j++) {
				ItemPikachu item = list_itemPikachu[paramInt1-1][j-1];
				if (item != null) {
					if (j < paramInt4)
						move_left_right(item, -1);
					else if (j > paramInt4)
						move_left_right(item, -2);
				}
			}
		}
	}

	/*
	 * All side -> Right
	 */
	public void move_all_side_right(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt1 != paramInt3) {
			for (int j = paramInt2-1; j > 0; j--) {
				ItemPikachu item = list_itemPikachu[paramInt1-1][j-1];
				if (item != null) {
					move_left_right(item, 1);
				}
			}
			for (int j = paramInt4-1; j > 0; j--) {
				ItemPikachu item = list_itemPikachu[paramInt3-1][j-1];
				if (item != null) {
					move_left_right(item, 1);
				}
			}

		} else {
			if (paramInt2 < paramInt4) {
				int temp = 0;
				temp = paramInt1;
				paramInt1 = paramInt3;
				paramInt3 = temp;
				temp = paramInt2;
				paramInt2 = paramInt4;
				paramInt4 = temp;
			}
			for (int j = paramInt2-1; j > 0; j--) {
				ItemPikachu item = list_itemPikachu[paramInt1-1][j-1];
				if (item != null) {
					if (j > paramInt4)
						move_left_right(item, 1);
					else if (j < paramInt4)
						move_left_right(item, 2);
				}
			}
		}
	}

	/*
	 * Left Side -> up
	 * Right Side -> down
	 */
	public void move_left_side_up_right_side_down(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt2 > paramInt4) {
			int temp = 0;
			temp = paramInt1;
			paramInt1 = paramInt3;
			paramInt3 = temp;
			temp = paramInt2;
			paramInt2 = paramInt4;
			paramInt4 = temp;
		}

		int boundary = MT.column/2;
		if (paramInt2 < boundary && paramInt4 < boundary) {
			move_all_side_up(paramInt1, paramInt2, paramInt3, paramInt4);

		} else if (paramInt2 >= boundary && paramInt4 >= boundary) {
			move_all_side_down(paramInt1, paramInt2, paramInt3, paramInt4);

		} else {
			for (int i = paramInt1+1; i < MT.row-1; i++) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt2-1];
				if (item != null) {
					move_up_down(item, -1);
				}
			}

			for (int i = paramInt3; i > 0; i--) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt4-1];
				if (item != null) {
					move_up_down(item, 1);
				}
			}
		}

	}

	/*
	 * Left Side -> up
	 * Right Side -> down
	 */
	public void move_left_side_down_right_side_up(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt2 > paramInt4) {
			int temp = 0;
			temp = paramInt1;
			paramInt1 = paramInt3;
			paramInt3 = temp;
			temp = paramInt2;
			paramInt2 = paramInt4;
			paramInt4 = temp;
		}

		int boundary = MT.column/2;
		if (paramInt2 < boundary && paramInt4 < boundary) {
			move_all_side_down(paramInt1, paramInt2, paramInt3, paramInt4);

		} else if (paramInt2 >= boundary && paramInt4 >= boundary) {
			move_all_side_up(paramInt1, paramInt2, paramInt3, paramInt4);

		} else {
			for (int i = paramInt1; i > 0; i--) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt2-1];
				if (item != null) {
					move_up_down(item, 1);
				}
			}

			for (int i = paramInt3+1; i < MT.row-1; i++) {
				ItemPikachu item = list_itemPikachu[i-1][paramInt4-1];
				if (item != null) {
					move_up_down(item, -1);
				}
			}
		}

	}

	/*
	 * Up Side -> Right
	 * Down Side -> Left
	 */

	public void move_up_side_right_down_side_left(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt1 > paramInt3) {
			int temp = 0;
			temp = paramInt1;
			paramInt1 = paramInt3;
			paramInt3 = temp;
			temp = paramInt2;
			paramInt2 = paramInt4;
			paramInt4 = temp;
		}
	
		int boundary = MT.row/2;
		if (paramInt1 < boundary && paramInt3 < boundary) {
			move_all_side_right(paramInt1, paramInt2, paramInt3, paramInt4);
	
		} else if (paramInt1 >= boundary && paramInt3 >= boundary) {
			move_all_side_left(paramInt1, paramInt2, paramInt3, paramInt4);
	
		} else {
			for (int j = paramInt2; j > 0; j--) {
				ItemPikachu item = list_itemPikachu[paramInt1-1][j-1];
				if (item != null) {
					move_left_right(item, 1);
				}
			}
	
			for (int j = paramInt4+1; j < MT.column-1; j++) {
				ItemPikachu item = list_itemPikachu[paramInt3-1][j-1];
				if (item != null) {
					move_left_right(item, -1);
				}
			}
		}
	}
	
	/*
	 * Up Side -> Left
	 * Down Side -> Right
	 */

	public void move_up_side_left_down_side_right(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt1 > paramInt3) {
			int temp = 0;
			temp = paramInt1;
			paramInt1 = paramInt3;
			paramInt3 = temp;
			temp = paramInt2;
			paramInt2 = paramInt4;
			paramInt4 = temp;
		}
	
		int boundary = MT.row/2;
		if (paramInt1 < boundary && paramInt3 < boundary) {
			move_all_side_left(paramInt1, paramInt2, paramInt3, paramInt4);
	
		} else if (paramInt1 >= boundary && paramInt3 >= boundary) {
			move_all_side_right(paramInt1, paramInt2, paramInt3, paramInt4);
	
		} else {
			for (int j = paramInt2+1; j < MT.column-1; j++) {
				ItemPikachu item = list_itemPikachu[paramInt1-1][j-1];
				if (item != null) {
					move_left_right(item, -1);
				}
			}
	
			for (int j = paramInt4; j > 0; j--) {
				ItemPikachu item = list_itemPikachu[paramInt3-1][j-1];
				if (item != null) {
					move_left_right(item, 1);
				}
			}
		}
	}

	public void removeItem(int i, int j) {
		list_itemPikachu[i-1][j-1] = null;
	}
	
	public void reset() {
		for (int i = 0; i < MT.row-2; i++) {
			for (int j = 0; j < MT.column-2; j++) {
				ItemPikachu item = list_itemPikachu[i][j];
				if (item == null)
					continue;
	
				item.setVisiable(false);
				item.onDestroy();
				item = null;
			}
		}
	}
	
	public void setScene(Scene paramScene) {
		this.mScene = paramScene;
	}
	
	public void showItemEffect(int mode, int paramInt2) {
		int move_mode = Util.getRandomIndex(0, 7);
		if (mode != -1)
			move_mode = mode;
	
		for (int i = 0; i < MT.row-2; i++) {
			for (int j = 0; j < MT.column-2; j++) {
				ItemPikachu item = (ItemPikachu) this.list_itemPikachu[i][j];
	
				if (item == null)
					continue;
	
				switch (move_mode) {
				default:
				case 0:
					item.move(-100, item.Y, paramInt2);
					break;
				case 1:
					item.move(100 + (int)Config.SCREENWIDTH, item.Y, paramInt2);
					break;
				case 2:
					item.move(item.X, -100, paramInt2);
					break;
				case 3:
					item.move(item.X, 100 + (int)Config.SCREENHEIGHT, paramInt2);
					break;
				case 4:
					item.move(-100, -100, paramInt2);
					break;
				case 5:
					item.move(100 + (int)Config.SCREENWIDTH, -100, paramInt2);
					break;
				case 6:
					item.move(-100, 100 + (int)Config.SCREENHEIGHT, paramInt2);
					break;
				case 7:
					item.move(100 + (int)Config.SCREENWIDTH, 100 + (int)Config.SCREENHEIGHT, paramInt2);
					break;
				}
			}
		}
	}
	
	public void swap(ItemPikachu item1, ItemPikachu item2) {
		int i = item1.I;
		int j = item1.J;
		int x = item1.X;
		int y = item1.Y;
	
		item1.setIJ(item2.I, item2.J);
		item1.setPosition(item2.X, item2.Y);
		MT.mt[item2.I][item2.J] = item1.GT_ITEM;
	
		item2.setIJ(i, j);
		item2.setPosition(x, y);
		MT.mt[i][j] = item2.GT_ITEM;
	}
	
	public void swapItem() {
		ArrayList<Integer> i_list = new ArrayList<Integer>();
		ArrayList<Integer> j_list = new ArrayList<Integer>();
		for (int i = 0; i < MT.row-2; i++) {
			for (int j = 0; j < MT.column-2; j++) {
				if (list_itemPikachu[i][j] != null) {
					i_list.add(i);
					j_list.add(j);
				}
			}
		}
	
		for (int n1 = 0; n1 < i_list.size(); n1++) {
			int n2 = Util.getRandomIndex(0, -1 + i_list.size());
			if (n1 != n2) {
				ItemPikachu item1 = list_itemPikachu[i_list.get(n1)][j_list.get(n1)];
				ItemPikachu item2 = list_itemPikachu[i_list.get(n2)][j_list.get(n2)];
				swap(item1, item2);
				ItemPikachu temp = list_itemPikachu[i_list.get(n2)][j_list.get(n2)];
				list_itemPikachu[i_list.get(n2)][j_list.get(n2)] = list_itemPikachu[i_list.get(n1)][j_list.get(n1)];
				list_itemPikachu[i_list.get(n1)][j_list.get(n1)] = temp;
			}
		}
	
		if (MT.die()) {
			swapItem();
		}
	}
	
	public boolean win() {
		for (int i = 0; i < MT.row-2; i++) {
			for (int j = 0; j < MT.column-2; j++) {
				if (list_itemPikachu[i][j] != null) {
					return false;
				}
			}
		}
	
		return true;
	}
}