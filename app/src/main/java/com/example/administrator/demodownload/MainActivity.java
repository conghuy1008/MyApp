package com.example.administrator.demodownload;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.demodownload.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DownloadManager downloadManager;





    private long Music1_DownloadId, Music2_DownloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        //Download Music from URL
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        Button btn3 = (Button) findViewById(R.id.btn3);
//        btn3.setEnabled(false);
        btn3.setOnClickListener(this);





        //Cancel Current Download
//        Button CancelDownload = (Button) findViewById(R.id.btn4);
//        CancelDownload.setOnClickListener(this);
//        CancelDownload.setEnabled(false);

        //set filter to only when download is complete and register broadcast receiver
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);
//        btn3 = (Button) findViewById(R.id.btn3);
//        btn3.setEnabled(false);
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent);
//            }
//        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main1, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btn1:
                Uri uri1 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/test2-229c8.appspot.com/o/Em-Gai-Mua-Huong-Tram.mp3?alt=media&token=f5fcfb75-8071-438f-b356-fcd1be760441");
                Music1_DownloadId = DownloadData(uri1, v);
                break;

            //Download Music
            case R.id.btn2:
                Uri uri2 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/test2-229c8.appspot.com/o/Yeu-La-Tha-Thu-Em-Chua-18-OST-OnlyC.mp3?alt=media&token=47c5bafc-b744-4aab-8b0d-2b91e53699de");
                Music2_DownloadId = DownloadData(uri2, v);
                break;

            //check the status of all downloads
            case R.id.btn3:


  //                  addLisview();
              Check_Music1_Status();
//                Check_Music2_Status(Music2_DownloadId);

                break;

            //cancel the ongoing download
//            case R.id.btn4:
//
//                downloadManager.remove(Music1_DownloadId);
//                downloadManager.remove(Music2_DownloadId);
//                break;

        }
    }
//    private void addLisview() {
//        //First check if SD Card is present or not
//        if (new SongsManager().isSDCardPresent()) {
//
//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent);
//
//
//        } else
//            Toast.makeText(MainActivity.this, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();
//
//    }
//
    private void Check_Music1_Status() {
////
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(intent);
//////        DownloadManager.Query Music1DownloadQuery = new DownloadManager.Query();
//////        //set the query filter to our previously Enqueued download
//////        Music1DownloadQuery.setFilterById(Music1_DownloadId);
//////
//////        //Query the download manager about downloads that have been requested.
//////        Cursor cursor = downloadManager.query(Music1DownloadQuery);
//////        if(cursor.moveToFirst()){
////            DownloadStatus(cursor, Music1_DownloadId);
        }
////


//    private void Check_Music2_Status(long Music2_DownloadId) {
//
//        DownloadManager.Query Music2DownloadQuery = new DownloadManager.Query();
//        //set the query filter to our previously Enqueued download
//        Music2DownloadQuery.setFilterById(Music2_DownloadId);
//
//        //Query the download manager about downloads that have been requested.
//        Cursor cursor = downloadManager.query(Music2DownloadQuery);
//        if(cursor.moveToFirst()){
//            DownloadStatus(cursor, Music2_DownloadId);
//        }
//
//    }

    private void DownloadStatus(Cursor cursor, long DownloadId){

        //column for download  status
        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);
        //column for reason code if the download failed or paused
        int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
        int reason = cursor.getInt(columnReason);
        //get the download filename
        int filenameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
        String filename = cursor.getString(filenameIndex);

        String statusText = "";
        String reasonText = "";

        switch(status){
            case DownloadManager.STATUS_FAILED:
                Toast.makeText(this, "STATUS_FAILED", Toast.LENGTH_SHORT).show();
                switch(reason){
                    case DownloadManager.ERROR_CANNOT_RESUME:
                        reasonText = "ERROR_CANNOT_RESUME";
                        break;
                    case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                        reasonText = "ERROR_DEVICE_NOT_FOUND";
                        break;
                    case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                        reasonText = "ERROR_FILE_ALREADY_EXISTS";
                        break;
                    case DownloadManager.ERROR_FILE_ERROR:
                        reasonText = "ERROR_FILE_ERROR";
                        break;
                    case DownloadManager.ERROR_HTTP_DATA_ERROR:
                        reasonText = "ERROR_HTTP_DATA_ERROR";
                        break;
                    case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                        reasonText = "ERROR_INSUFFICIENT_SPACE";
                        break;
                    case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                        reasonText = "ERROR_TOO_MANY_REDIRECTS";
                        break;
                    case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                        reasonText = "ERROR_UNHANDLED_HTTP_CODE";
                        break;
                    case DownloadManager.ERROR_UNKNOWN:
                        reasonText = "ERROR_UNKNOWN";
                        break;
                }
                break;
            case DownloadManager.STATUS_PAUSED:
                statusText = "STATUS_PAUSED";
                switch(reason){
                    case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                        reasonText = "PAUSED_QUEUED_FOR_WIFI";
                        break;
                    case DownloadManager.PAUSED_UNKNOWN:
                        reasonText = "PAUSED_UNKNOWN";
                        break;
                    case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                        reasonText = "PAUSED_WAITING_FOR_NETWORK";
                        break;
                    case DownloadManager.PAUSED_WAITING_TO_RETRY:
                        reasonText = "PAUSED_WAITING_TO_RETRY";
                        break;
                }
                break;
            case DownloadManager.STATUS_PENDING:
                statusText = "STATUS_PENDING";
                break;
            case DownloadManager.STATUS_RUNNING:
                statusText = "STATUS_RUNNING";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                statusText = "STATUS_SUCCESSFUL";
                reasonText = "Filename:\n" + filename;
                break;
        }

        if(DownloadId == Music2_DownloadId) {

            Toast toast = Toast.makeText(MainActivity.this,
                    "Download Status:" + "\n" + statusText + "\n" +
                            reasonText,
                    Toast.LENGTH_LONG);
            toast.show();

        }
        else {

            Toast toast = Toast.makeText(MainActivity.this,
                    "Download Status:"+ "\n" + statusText + "\n" +
                            reasonText,
                    Toast.LENGTH_LONG);
            toast.show();

            // Make a delay of 3 seconds so that next toast (Music Status) will not merge with this one.
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 3000);

        }

    }


    private long DownloadData (Uri uri, View v) {

        long downloadReference;

        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        //Setting title of request
        request.setTitle("Data Download");

        //Setting description of request
        request.setDescription("Android Data download using DownloadManager.");

        //Set the local destination for the downloaded file to a path within the application's external files directory
        if(v.getId() == R.id.btn1){
            check("Em-gai-mua.mp3");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC+ "/" +"Music Fubiz","Em-gai-mua.mp3");
            Button btn1 = (Button) findViewById(R.id.btn1);
            btn1.setEnabled(false);}
        else if(v.getId() == R.id.btn2){
            check("Yeu-la-tha-thu.mp3");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC+ "/" +"Music Fubiz","Yeu-la-tha-thu.mp3");
            Button btn2 = (Button) findViewById(R.id.btn2);
            btn2.setEnabled(false);}

//        //Enqueue download and save the referenceId
        downloadReference = downloadManager.enqueue(request);
////
        Button btn3 = (Button) findViewById(R.id.btn3);
//        btn3.setEnabled(true);
//
////        Button CancelDownload = (Button) findViewById(R.id.btn4);
////        CancelDownload.setEnabled(true);
//
        return downloadReference;

    }
    private void check(String name) {
        File apkStorage = new File(
                Environment.DIRECTORY_MUSIC + "/"
                        + "Music Fubiz");
        if (!apkStorage.exists()) {
            apkStorage.mkdir();
            Log.e("MainActivity", "Directory Created.");
        }

        File outputFile = new File(apkStorage, name);//Create Output file in Main File

        //Create New File if not present
        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("MainActivity", "File Created");
        }

       //Create Output file in Main File

        //Create New File if not present

    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            //check if the broadcast message is for our Enqueued download
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            if(referenceId == Music1_DownloadId) {

                Toast toast = Toast.makeText(MainActivity.this,
                        "Download Complete", Toast.LENGTH_LONG);
                toast.show();
            }
            else if(referenceId == Music2_DownloadId) {

                Toast toast = Toast.makeText(MainActivity.this,
                        "Download Complete", Toast.LENGTH_LONG);
                toast.show();
            }

        }
    };

}
