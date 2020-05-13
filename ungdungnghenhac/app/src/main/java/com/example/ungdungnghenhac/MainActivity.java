package com.example.ungdungnghenhac;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    ImageButton btnprevious, btnnext, btnshuffle, btnplay, btnrepeat;
    MediaPlayer mediaPlayer;
    int totalTime;
    int position = 0; //get index số bao nhiêu
    ArrayList<Song> listSong;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addSong();
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, listSong.get(position).getFile());
                mediaPlayer.start();
            }
        });


    }

    private void addSong() {
        listSong = new ArrayList<>();
        listSong.add(new Song("Mùa để yêu thương", R.raw.mua_de_yeu_thuong));
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        seekbar = findViewById(R.id.seekbar);
        textviewtimesong = (TextView) findViewById(R.id.textviewtimesong);
        textviewtotaltimesong = (TextView) findViewById(R.id.textviewtotaltimesong);
        btnprevious = (ImageButton) findViewById(R.id.btnprevious);
        btnnext = (ImageButton) findViewById(R.id.btnnext);
        btnshuffle = (ImageButton) findViewById(R.id.btnshuffle);
        btnplay = (ImageButton) findViewById(R.id.btnplay);
        btnrepeat = (ImageButton) findViewById(R.id.btnrepeat);


    }
}
