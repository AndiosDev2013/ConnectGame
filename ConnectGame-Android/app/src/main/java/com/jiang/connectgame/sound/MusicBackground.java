package com.jiang.connectgame.sound;

import com.jiang.connectgame.R;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicBackground {
	MediaPlayer mediaPlayer;

	public void loadMusic(Context context) {
		this.mediaPlayer = MediaPlayer.create(context, R.raw.backgroundmusic);
		this.mediaPlayer.setVolume(0.2F, 0.2F);
		this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer paramAnonymousMediaPlayer) {
				MusicBackground.this.play();
			}
		});
	}

	public void pause() {
		if (this.mediaPlayer.isPlaying())
			this.mediaPlayer.pause();
	}

	public void play() {
		this.mediaPlayer.seekTo(0);
		this.mediaPlayer.start();
	}

	public void release() {
		this.mediaPlayer.release();
	}

	public void resume() {
		if (!this.mediaPlayer.isPlaying())
			this.mediaPlayer.start();
	}
}
