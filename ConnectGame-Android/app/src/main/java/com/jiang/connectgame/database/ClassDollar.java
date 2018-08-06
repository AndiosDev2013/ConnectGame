package com.jiang.connectgame.database;

public class ClassDollar
{
	int dollar;
	String name;
	int theme;

	public ClassDollar(String paramString, int paramInt1, int paramInt2) {
		this.name = paramString;
		this.dollar = paramInt1;
		this.theme = paramInt2;
	}

	public int getDollar() {
		return this.dollar;
	}

	public String getName() {
		return this.name;
	}

	public int getTheme() {
		return this.theme;
	}
}
