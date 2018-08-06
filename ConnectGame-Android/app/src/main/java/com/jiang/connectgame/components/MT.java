package com.jiang.connectgame.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jiang.connectgame.config.Config;
import com.jiang.connectgame.util.Util;

import android.graphics.Point;

public class MT {
	public static int XSTART = 20;
	public static int YSTART = 70;
	
	public static int row = 7;
	public static int column = 14;
//	public static int row = 7;
//	public static int column = 8;
	
	public static int iconCounts = 35;
	
	public static int mt[][] = new int[row][column];
	
	public static void initMT() {
		int x = 0;
		int y = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				mt[i][j] = -1;
			}
		}
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < column - 1; j++) {
				mt[i][j] = x;
				if (y == 1) {
					x++;
					y = 0;
					if (x == iconCounts) {
						x = 1;
					}
				} else {
					y = 1;
				}
			}
		}
		change();
	}
	
	public static void change() {
		Random random = new Random();
		int tmpV, tmpX, tmpY;
		for (int x = 1; x < row - 1; x++) {
			for (int y = 1; y < column - 1; y++) {
				tmpX = 1 + random.nextInt(row - 2);
				tmpY = 1 + random.nextInt(column - 2);
				tmpV = mt[x][y];
				mt[x][y] = mt[tmpX][tmpY];
				mt[tmpX][tmpY] = tmpV;
			}
		}
		if (die()) {
			change();
		}
	}
	
	public static boolean die() {
		for (int y = 1; y < column - 1; y++) {
			for (int x = 1; x < row - 1; x++) {
				if (mt[x][y] != -1) {
					for (int j = y; j < column - 1; j++) {
						if (j == y) {
							for (int i = x + 1; i < row - 1; i++) {
								if (mt[i][j] == mt[x][y] && link(new Point(x, y), new Point(i, j))) {
									return false;
								}
							}
						} else {
							for (int i = 1; i < row - 1; i++) {
								if (mt[i][j] == mt[x][y] && link(new Point(x, y), new Point(i, j))) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public static ArrayList<Point> path = new ArrayList<Point>();
	static List<Point> p1E = new ArrayList<Point>();
	static List<Point> p2E = new ArrayList<Point>();

	public static boolean link(Point p1, Point p2) {
		if (p1.equals(p2)) {
			return false;
		}
		path.clear();
		if (mt[p1.x][p1.y] == mt[p2.x][p2.y]) {
			if (linkD(p1, p2)) {
				path.add(p1);
				path.add(p2);
				return true;
			}

			Point p = new Point(p1.x, p2.y);
			if (mt[p.x][p.y] == -1) {
				if (linkD(p1, p) && linkD(p, p2)) {
					path.add(p1);
					path.add(p);
					path.add(p2);
					return true;
				}
			}
			p = new Point(p2.x, p1.y);
			if (mt[p.x][p.y] == -1) {
				if (linkD(p1, p) && linkD(p, p2)) {
					path.add(p1);
					path.add(p);
					path.add(p2);
					return true;
				}
			}
			expandX(p1, p1E);
			expandX(p2, p2E);

			for (Point pt1 : p1E) {
				for (Point pt2 : p2E) {
					if (pt1.x == pt2.x) {
						if (linkD(pt1, pt2)) {
							path.add(p1);
							path.add(pt1);
							path.add(pt2);
							path.add(p2);
							return true;
						}
					}
				}
			}

			expandY(p1, p1E);
			expandY(p2, p2E);
			for (Point pt1 : p1E) {
				for (Point pt2 : p2E) {
					if (pt1.y == pt2.y) {
						if (linkD(pt1, pt2)) {
							path.add(p1);
							path.add(pt1);
							path.add(pt2);
							path.add(p2);
							return true;
						}
					}
				}
			}
			return false;
		}
		return false;
	}

	private static boolean linkD(Point p1, Point p2) {
		if (p1.x == p2.x) {
			int y1 = Math.min(p1.y, p2.y);
			int y2 = Math.max(p1.y, p2.y);
			boolean flag = true;
			for (int y = y1 + 1; y < y2; y++) {
				if (mt[p1.x][y] != -1) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return true;
			}
		}
		if (p1.y == p2.y) {
			int x1 = Math.min(p1.x, p2.x);
			int x2 = Math.max(p1.x, p2.x);
			boolean flag = true;
			for (int x = x1 + 1; x < x2; x++) {
				if (mt[x][p1.y] != -1) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return true;
			}
		}
		return false;
	}

	private static void expandX(Point p, List<Point> l) {
		l.clear();
		for (int x = p.x + 1; x < row; x++) {
			if (mt[x][p.y] != -1) {
				break;
			}
			l.add(new Point(x, p.y));
		}
		for (int x = p.x - 1; x >= 0; x--) {
			if (mt[x][p.y] != -1) {
				break;
			}
			l.add(new Point(x, p.y));
		}
	}

	private static void expandY(Point p, List<Point> l) {
		l.clear();
		for (int y = p.y + 1; y < column; y++) {
			if (mt[p.x][y] != -1) {
				break;
			}
			l.add(new Point(p.x, y));
		}
		for (int y = p.y - 1; y >= 0; y--) {
			if (mt[p.x][y] != -1) {
				break;
			}
			l.add(new Point(p.x, y));
		}
	}

	public static void getTotalRowColumn(int end) {
//		row = 5;
//		column = 12;
	}
	
	public static Point getXYByIJ(int i, int j) {
		Point value = new Point();
		value.x = MT.XSTART + (j-1) * Config.ITEM_WIDTH;
		value.y = MT.YSTART + (i-1) * Config.ITEM_HEIGHT;
		return value;
	}
	
}