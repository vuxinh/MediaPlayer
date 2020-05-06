package com.example.ungdungnghenhac;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   Toolbar toolbar;
   SeekBar seekbar;
   TextView textviewtimesong, textviewtotaltimesong;
   ImageButton iconprevious, iconnext, iconshuffle, iconplay, iconrepeat;
   ViewPager viewPager;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        addSong();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(){
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
        seekbar = findViewById(R.id.seekbar);
        textviewtimesong = (TextView)findViewById(R.id.textviewtimesong);
        textviewtotaltimesong = (TextView)findViewById(R.id.textviewtotaltimesong);
        iconprevious = (ImageButton)findViewById(R.id.iconprevious);
        iconnext = (ImageButton)findViewById(R.id.iconnext);
        iconshuffle = (ImageButton)findViewById(R.id.iconshuffle);
        iconplay = (ImageButton)findViewById(R.id.iconplay);
        iconrepeat = (ImageButton)findViewById(R.id.iconrepeat);
        MediaPlayer mediaPlayer;
//        mediaPlayer = MediaPlayer.create(this,R.raw.Hello );
//        mediaPlayer.setLooping(true);
    }
//    private void addSong(){
//        song = new ArrayList<Song>()
//    }
    class playMusic extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }
        protected void OnPostExecute(String song) throws IOException {
            super.onPostExecute(song);
            final MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.setDataSource(song);
            mediaPlayer.prepare();
            mediaPlayer.start();



        }
    }
}
