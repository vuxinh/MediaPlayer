package com.example.ungdungnghenhac;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
        findID();
        addSong();

         mediaPlayer = MediaPlayer.create(MainActivity.this, listSong.get(position).getFile());
//        mediaPlayer.start();

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnplay.setImageResource(R.drawable.icon_play);
                }
                else {
                    mediaPlayer.start();
                    btnplay.setImageResource(R.drawable.pause);
                }
                setTotalTime();
                updateTimeSong();
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position ++;
                if(position > listSong.size() - 1){
                    position = 0;

                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(MainActivity.this, listSong.get(position).getFile());
                mediaPlayer.start();
                btnplay.setImageResource(R.drawable.pause);
                setTotalTime();
                updateTimeSong();
            }
        });

        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position --;
                if(position < 0){
                    position = listSong.size() - 1;

                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(MainActivity.this, listSong.get(position).getFile());
                mediaPlayer.start();
                btnplay.setImageResource(R.drawable.pause);
                setTotalTime();
                updateTimeSong();

            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // cập nhật giá trị liên tục khi kéo
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
             // lấy khoảng khi chạm, di chuyển không có gì thay đổi
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });


    }

    private void updateTimeSong(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGIo = new SimpleDateFormat("mm:ss");
                textviewtimesong.setText(dinhDangGIo.format(mediaPlayer.getCurrentPosition()));
                // update tiến trình của seekbar
                seekbar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        },100);
    }


    private void setTotalTime(){
        SimpleDateFormat dinhDangGIo = new SimpleDateFormat("mm:ss");
        textviewtotaltimesong.setText(dinhDangGIo.format(mediaPlayer.getDuration() ));
        // gán max seekbar = mediaPlayer.getDuration()
        seekbar.setMax(mediaPlayer.getDuration());

    }

    private void addSong() {
        listSong = new ArrayList<>();
        listSong.add(new Song("Mùa để yêu thương", R.raw.mua_de_yeu_thuong));
        listSong.add(new Song("Spring Day", R.raw.spring_day ));
        listSong.add(new Song("Mặt trời tắm mát trong mưa xuân", R.raw.mat_troi_tam_mat_trong_mua_xuan));

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void findID() {
      //  toolbar = (Toolbar) findViewById(R.id.toolbar);
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
