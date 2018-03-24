package com.example.administrator.demodownload;


import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {


    final static int MY_PERMISSION_REQUEST = 1;

    ListView lv1;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        if(ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        }
        else
        {
            result();

        }

    }



    public void result(){
        lv1 = (ListView)findViewById(R.id.lv1);
        arrayList = new ArrayList<>();
        getMusic();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        lv1.setAdapter(arrayAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    public void getMusic() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songcursor = contentResolver.query(uri, null, null, null, null);
        if (songcursor != null && songcursor.moveToFirst()) {

            int songTitle = songcursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songcursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songLocation = songcursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                String currentTitle = songcursor.getString(songTitle);
                String currentArtist = songcursor.getString(songArtist);
                String currentLocation = songcursor.getString(songLocation);
                arrayList.add("Title: " + currentTitle + "\n" +"Artist: "+ currentArtist + "\n" +"Location: "+ currentLocation);
            }while (songcursor.moveToNext());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity2.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                        result();
                    }else {
                        Toast.makeText(this, "No permission granted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    return;
                }
            }
        }
    }
}







