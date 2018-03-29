package com.example.administrator.demodownload;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity implements  View.OnClickListener{

    static MediaPlayer mp;
    ArrayList<File> mySongs;
    SeekBar seekBar;
    Button btPlay,btFF,btFB,btNext,btPrv;
    int i;
    Uri uri;
    Thread updateSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btPlay =(Button)findViewById(R.id.btnplay);
        btFF =(Button)findViewById(R.id.btnff);
        btFB =(Button)findViewById(R.id.btnfb);
        btNext =(Button)findViewById(R.id.btnnext);
        btPrv =(Button)findViewById(R.id.btnprv);

        btPlay.setOnClickListener(this);
        btFF.setOnClickListener(this);
        btFB.setOnClickListener(this);
        btNext.setOnClickListener(this);
        btPrv.setOnClickListener(this);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        updateSeekbar = new Thread(){
            @Override
            public void run() {
                int totalDuration = mp.getDuration();
                int currentPosition = 0;
//                seekBar.setMax(totalDuration);
                while (currentPosition < totalDuration){
                    try {
                        sleep(500);
                        currentPosition = mp.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }


            }
        };

        if(mp!=null){
            mp.stop();
            mp.release();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mySongs =(ArrayList) bundle.getParcelableArrayList("songlist");
        i = bundle.getInt("pos",0);

        uri = Uri.parse(mySongs.get(i).toString());
        mp = MediaPlayer.create(getApplicationContext(),uri);
        mp.start();
        seekBar.setMax(mp.getDuration());

        updateSeekbar.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mp.seekTo(seekBar.getProgress());
            }
        });

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id){
            case R.id.btnplay:
                if(mp.isPlaying()){
                    btPlay.setText("Play");
                    mp.pause();
                }
                else {
                    btPlay.setText("Pause");
                    mp.start();
                }
                break;
            case  R.id.btnff:
                mp.seekTo(mp.getCurrentPosition()+5000);
                break;
            case R.id.btnfb:
                mp.seekTo(mp.getCurrentPosition()-5000);
                break;
            case R.id.btnnext:
                mp.stop();
                mp.release();
                i = (i+1)%mySongs.size();
                uri = Uri.parse(mySongs.get(i).toString());
                mp = MediaPlayer.create(getApplicationContext(),uri);
                mp.start();
                seekBar.setMax(mp.getDuration());

                break;
            case  R.id.btnprv:
                mp.stop();
                mp.release();
                i = (i-1 < 0 )? mySongs.size()- 1: i-1;
//                if(i -1 < 0){
//                    i = mySongs.size() - 1;
//                }
//                else {
//                    i = i  - 1;
//                }
                uri = Uri.parse(mySongs.get(i).toString());
                mp = MediaPlayer.create(getApplicationContext(),uri);
                mp.start();
                seekBar.setMax(mp.getDuration());

                break;

        }

    }
}
