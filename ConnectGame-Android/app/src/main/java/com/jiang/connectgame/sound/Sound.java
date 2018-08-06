package com.jiang.connectgame.sound;

import com.jiang.connectgame.R;
import com.jiang.connectgame.Setting;

import android.content.Context;
import android.media.SoundPool;

public class Sound {
	int bad = -1;
	int click = -1;
	Context context;
	int finish = -1;
	int gameover = -1;
	int good = -1;
	SoundPool mSoundPool;
	int random = -1;
	float volume = 0.5F;

	public void loadSound(Context context) {
		this.context = context;
		this.mSoundPool = new SoundPool(3, 3, 100);
		this.bad = this.mSoundPool.load(this.context, R.raw.bad, 1);
		this.click = this.mSoundPool.load(this.context, R.raw.click, 1);
		this.finish = this.mSoundPool.load(this.context, R.raw.finish, 1);
		this.good = this.mSoundPool.load(this.context, R.raw.good, 1);
		this.random = this.mSoundPool.load(this.context, R.raw.random, 1);
		this.gameover = this.mSoundPool.load(this.context, R.raw.gameover, 1);
	}

	public void offSound() {
		this.volume = 0.0F;
	}

	public void playBad() {
		if (Setting.isSound)
			new Thread(new Runnable() {
				public void run() {
					Sound.this.mSoundPool.play(Sound.this.bad,
							Sound.this.volume, Sound.this.volume, 1, 0, 1.0F);
				}
			}).start();
	}

	public void playClick() {
		if (Setting.isSound)
			new Thread(new Runnable() {
				public void run() {
					Sound.this.mSoundPool.play(Sound.this.click, Sound.this.volume, Sound.this.volume, 1, 0, 1.0F);
				}
			}).start();
	}

	public void playFinish() {
		if (Setting.isSound)
			new Thread(new Runnable() {
				public void run() {
					Sound.this.mSoundPool.play(Sound.this.finish, Sound.this.volume, Sound.this.volume, 1, 0, 1.0F);
				}
			}).start();
	}

	public void playGameOver() {
		if (Setting.isSound)
			new Thread(new Runnable() {
				public void run() {
					Sound.this.mSoundPool.play(Sound.this.gameover, Sound.this.volume, Sound.this.volume, 1, 0, 1.0F);
				}
			}).start();
	}

	public void playGood() {
		if (Setting.isSound)
			new Thread(new Runnable() {
				public void run() {
					Sound.this.mSoundPool.play(Sound.this.good, Sound.this.volume, Sound.this.volume, 1, 0, 1.0F);
				}
			}).start();
	}

	public void playRandom() {
		if (Setting.isSound)
			new Thread(new Runnable() {
				public void run() {
					Sound.this.mSoundPool.play(Sound.this.random, Sound.this.volume, Sound.this.volume, 1, 0, 1.0F);
				}
			}).start();
	}
}
