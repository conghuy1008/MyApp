//package com.example.administrator.demodownload;
//
//
//import android.Manifest;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity2 extends AppCompatActivity {
//
//
////    final static int MY_PERMISSION_REQUEST = 1;
//
////    ListView lv1;
//    ArrayAdapter arrayAdapter;
////    ArrayList<String> arrayList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        final ListView lv1 = (ListView) findViewById(R.id.lv1);
//        lv1.setAdapter(new CustomAdapter(MainActivity2.this,getMusic()));
//        Button btnback = (Button) findViewById(R.id.btnback1);
//        btnback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
////        if(ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
////            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
////                ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
////            } else {
////                ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
////            }
////        }
////        else
////        {
////            result();
//
//    }
//
//
////    public void result(){
////        lv1 = (ListView)findViewById(R.id.lv1);
////        arrayList = new ArrayList<>();
////        getMusic();
////        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
////        lv1.setAdapter(arrayAdapter);
////        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////
////            }
////        });
////    }
//
//    public List<Spacecraft> getMusic(final Context context) {
////        ContentResolver contentResolver = getContentResolver();
////        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
////        Cursor songcursor = contentResolver.query(uri, null, null, null, null);
////        if (songcursor != null && songcursor.moveToFirst()) {
////
////            int songTitle = songcursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
////            int songArtist = songcursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
////            int songLocation = songcursor.getColumnIndex(MediaStore.Audio.Media.DATA);
////            do {
////                String currentTitle = songcursor.getString(songTitle);
////                String currentArtist = songcursor.getString(songArtist);
////                String currentLocation = songcursor.getString(songLocation);
////                arrayList.add("Title: " + currentTitle + "\n" +"Artist: "+ currentArtist + "\n" +"Location: "+ currentLocation);
////            }while (songcursor.moveToNext());
////        }
//
//        final List<Spacecraft> tempAudioList = new ArrayList<>();
//
////        Uri uri1 = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
//        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.ALBUM,
//                MediaStore.Audio.ArtistColumns.ARTIST, MediaStore.Audio.AudioColumns.DURATION};
//        Cursor c = context.getContentResolver().query(uri, projection, MediaStore.Audio.Media.DATA + " like ? ", new String[]{"/storage/emulated/0/Music"}, null);
//
////        Cursor c = context.getContentResolver().query(uri,
////                projection,
////                null,
////                null,
////                null);
//
//        if (c != null) {
//            while (c.moveToNext()) {
//
//                Spacecraft audioModel = new Spacecraft();
//                String path = c.getString(0);
//                String album = c.getString(1);
//                String artist = c.getString(2);
//                Long duration = c.getLong(3);
//
//
//                String name = path.substring(path.lastIndexOf("/") + 1);
//
//                audioModel.setName(name);
//                audioModel.setAlbum(album);
//                audioModel.setArtist(artist);
//                audioModel.setPath(path);
//                audioModel.setDuration(duration);
//
////                Log.e("Name :" + name, " Album :" + album);
////                Log.e("Path :" + path, " Artist :" + artist);
//
//                tempAudioList.add(audioModel);
//            }
//            c.close();
//        }
//
//        return tempAudioList;
//    }
//
//    public static String convertMillieToHMmSsNew(long millie) {
//        long seconds = (millie / 1000);
//        long second = seconds % 60;
//        long minute = (seconds / 60) % 60;
//        long hour = (seconds / (60 * 60)) % 24;
//
//        String result = "";
//        if (hour > 0) {
//            return String.format("%02d:%02d:%02d", hour, minute, second);
//        } else {
//            return String.format("%02d:%02d", minute, second);
//        }
//    }
//}
//
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        switch (requestCode){
////            case MY_PERMISSION_REQUEST: {
////                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
////                    if(ContextCompat.checkSelfPermission(MainActivity2.this,
////                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
////                        Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
////                        result();
////                    }else {
////                        Toast.makeText(this, "No permission granted", Toast.LENGTH_SHORT).show();
////                        finish();
////                    }
////                    return;
////                }
////            }
////        }
////    }
//
//
//
//
//
//
//
