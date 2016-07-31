package com.example.audioplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class HomeActivity extends Activity {
	Button buttonPlay, ButtonPause;
	MediaPlayer player;
	SeekBar seekBar1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		buttonPlay = (Button) findViewById(R.id.button1);
		ButtonPause = (Button) findViewById(R.id.button2);
		seekBar1 = (SeekBar) findViewById(R.id.seekBar1);

		// intialize mediaPlayer with file
		player = MediaPlayer.create(HomeActivity.this, R.raw.waka);

		buttonPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!player.isPlaying()) {
					try {
						// player.prepare();
						player.start();
						ProgressBar task = new ProgressBar();
						task.execute();
					} catch (Exception ex) {
						Log.e("error", ex.toString());
					}
				}// eof if

			}// eof onclick
		});

		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					int seekPosition = progress;
					player.seekTo(seekPosition);
				}
			}
		});

		ButtonPause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (player.isPlaying()) {

					player.pause();

				}

			}// eof onclick

		});

	}// eofoncreate

	class ProgressBar extends AsyncTask<Void, Integer, String> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			seekBar1.setMax(player.getDuration());

		}

		@Override
		protected String doInBackground(Void... params) {

			while (player.isPlaying()) {
				// show the current position of playing
				int pos = player.getCurrentPosition();
				publishProgress(pos);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return "finsh";

		}// eof doiInBackground()

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			seekBar1.setProgress(values[0]);
		}
	}

}// eof onActivity
