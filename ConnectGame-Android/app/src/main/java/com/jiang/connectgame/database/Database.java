package com.jiang.connectgame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.jiang.connectgame.log.MyLog;

public class Database extends SQLiteOpenHelper {
	private static final String NAME_DB = "pikachu_hd.db";
	private static final int VERSION = 1;
	public final String DOLLAR = "DOLLAR";
	public final String ID = "ID";
	public final String NAME = "NAME";
	public final String TABLE_DOLLAR = "TABLE_DOLLAR";
	public final String THEME = "THEME";
	private SQLiteDatabase mSQLiteDatabase = null;

	public Database(Context context) {
		super(context, "pikachu_hd.db", null, 1);
	}

	public void addDollar(ClassDollar paramClassDollar) {
		int id = 0;
		if (this.mSQLiteDatabase.isOpen()) {
			id = checkIsInsert(paramClassDollar);
			if (id <= 10) {
				ContentValues localContentValues = new ContentValues();
				localContentValues.put("NAME", paramClassDollar.getName());
				localContentValues.put("DOLLAR", Integer.valueOf(paramClassDollar.getDollar()));
				localContentValues.put("THEME", Integer.valueOf(paramClassDollar.getTheme()));
				this.mSQLiteDatabase.insert("TABLE_DOLLAR", null, localContentValues);
			}
		}
		logList(paramClassDollar.getTheme());
		updateDollar(paramClassDollar, id);
	}

	public int checkIsInsert(ClassDollar item) {
		if (this.mSQLiteDatabase.isOpen()) {
			Cursor cursor = getCursorQuery("TABLE_DOLLAR", null, "THEME="+item.getTheme(), null, null, null, "DOLLAR DESC");
			int dollar = 0;
			int id = 0;
			do {
				if (cursor.moveToNext()) {
					dollar = cursor.getInt(cursor.getColumnIndex("DOLLAR"));
					id = cursor.getInt(cursor.getColumnIndex("ID"));
					
				} else {
					break;
				}
			} while (item.dollar > dollar);
			
			cursor.close();
			return id;
		}
		return -1;
	}

	public void closeDatabase() {
		close();
	}

	public void execSQL(String str_sql) {
		execSQL(str_sql);
	}

	public Cursor getCursorQuery(String table, String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String orderBy) {
		return this.mSQLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
	}

	public ArrayList<ClassDollar> getListDollar(int theme) {
		ArrayList<ClassDollar> item_list = new ArrayList<ClassDollar>();
		Cursor cursor = null;
		if (this.mSQLiteDatabase.isOpen()) {
			cursor = getCursorQuery("TABLE_DOLLAR", null, "THEME=" + theme, null, null, null, "DOLLAR DESC");
			while (true) {
				if (!cursor.moveToNext()) {
					cursor.close();
					return item_list;
				}
				item_list.add(new ClassDollar(
						cursor.getString(cursor.getColumnIndex("NAME")),
						cursor.getInt(cursor.getColumnIndex("DOLLAR")),
						cursor.getInt(cursor.getColumnIndex("THEME"))
						));
			}
		}
		
		return item_list;
	}

	public void logList(int paramInt) {
		ArrayList<ClassDollar> localArrayList = getListDollar(paramInt);
		for (int i = 0;; i++) {
			if (i >= localArrayList.size())
				return;
			ClassDollar item = (ClassDollar) localArrayList.get(i);
			MyLog.LogInfo("Name " + item.getName() + " - Dollar = "
					+ item.getDollar() + " - Theme = "
					+ item.getTheme());
		}
	}

	public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
		paramSQLiteDatabase.execSQL("CREATE TABLE TABLE_DOLLAR ( ID INTEGER PRIMARY KEY ,NAME TEXT NOT NULL,DOLLAR INTEGER NOT NULL,THEME INTEGER NOT NULL );");
		MyLog.LogInfo("Database onCreate");
	}

	public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
		paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS TABLE_DOLLAR");
		onCreate(paramSQLiteDatabase);
		MyLog.LogInfo("Database onUpgrade");
	}

	public void openDatabase() {
		this.mSQLiteDatabase = getWritableDatabase();
	}

	public void updateDollar(ClassDollar paramClassDollar, int paramInt) {
		if (this.mSQLiteDatabase.isOpen()) {
			ContentValues value = new ContentValues();
			value.put("NAME", paramClassDollar.getName());
			value.put("DOLLAR", Integer.valueOf(paramClassDollar.getDollar()));
			value.put("THEME", Integer.valueOf(paramClassDollar.getTheme()));
			
			this.mSQLiteDatabase.update("TABLE_DOLLAR", value, "ID=" + paramInt, null);
		}
	}
}