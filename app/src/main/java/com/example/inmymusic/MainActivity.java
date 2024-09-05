//package com.example.inmymusic;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.provider.Settings;
//import android.util.Log;
//import android.view.WindowManager;
//
//import com.example.inmymusic.adapter.musicListAdapter;
//import com.example.inmymusic.models.musicModel;
//
//import java.util.ArrayList;
//import java.util.List;
//import android.Manifest;
//import android.widget.Toast;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    RecyclerView recyclerforlist;
//    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
//        recyclerforlist =findViewById(R.id.recycler_for_music_list);
//        recyclerforlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
////
////        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
////                != PackageManager.PERMISSION_GRANTED) {
////            // Request the permission
////            ActivityCompat.requestPermissions(this,
////                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
////                    REQUEST_READ_EXTERNAL_STORAGE);
////        } else {
////            // Permission is already granted, proceed to fetch music data
////            fetchMusicData();
////        }
//
//        // Check if READ_EXTERNAL_STORAGE permission is granted
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Request the permission
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    REQUEST_READ_EXTERNAL_STORAGE);
//
//            // Display a message to the user, informing them that the permission is required
//            Toast.makeText(this, "Please grant storage permission to access music.", Toast.LENGTH_LONG).show();
//        } else {
//            // Permission is already granted, proceed to fetch music data
//            fetchMusicData();
//        }
//
//
//    }
//    // Handle the permission request result
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults.length > 0) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted
//                Log.d("Permission", "READ_EXTERNAL_STORAGE granted");
//            } else {
//                // Permission denied
//                Log.d("Permission", "READ_EXTERNAL_STORAGE denied");
//            }
//        }
//        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, fetch music data
//                fetchMusicData();
//            } else {
//                // Permission denied, handle accordingly (e.g., show a message)
//                Toast.makeText(this, " storage permission not granted", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Uri uri = Uri.fromParts("package", getPackageName(), null);
//                intent.setData(uri);
//                startActivity(intent);
//            }
//        }
//    }
//
//    private void fetchMusicData() {
//        String[] projection = {
//                MediaStore.Audio.Media._ID,
//                MediaStore.Audio.Media.DATA,
//                MediaStore.Audio.Media.TITLE,
//                // Add other desired columns here
//        };
//
//        Cursor cursor = getContentResolver().query(
//                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                projection,
//                null,
//                null,
//                null
//        );
//
//        List<musicModel> musicList = new ArrayList<>();
//
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
//                @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
//                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//
//                // Create a MusicModel object and add it to the list
//                musicModel musicModel = new musicModel(id, data, title);
//                musicList.add(musicModel);
//            }
//            cursor.close();
//
//            // Now, you can use the musicList to populate your RecyclerView
//            // Example: Create an adapter and set it to your RecyclerView
//            // RecyclerViewAdapter adapter = new RecyclerViewAdapter(musicList);
//            // recyclerforlist.setAdapter(adapter);
//
//
//
//            musicListAdapter madapter=new musicListAdapter(MainActivity.this,musicList);
//            recyclerforlist.setAdapter(madapter);
//
//        }
//    }
//
//
//}


package com.example.inmymusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.inmymusic.adapter.musicListAdapter;
import com.example.inmymusic.models.musicModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    private RecyclerView recyclerforlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        recyclerforlist = findViewById(R.id.recycler_for_music_list);
        recyclerforlist.setLayoutManager(new LinearLayoutManager(this));

        // Check if READ_EXTERNAL_STORAGE permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_AUDIO},
                    REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            // Permission is already granted, proceed to fetch music data
            fetchMusicData();
        }
    }

    // Handle the permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, fetch music data
                fetchMusicData();
            } else {
                // Permission denied, show a message and direct to app settings
                Toast.makeText(this, "Storage permission not granted.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }
    }

    private String getAlbumArtUri(long albumId) {
        String selection = MediaStore.Images.Media._ID + "=?";
        String[] selectionArgs = { String.valueOf(albumId) };

        String[] projection = {
                MediaStore.Images.Media.DATA
        };

        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String albumArtUri = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return albumArtUri;
        }

        return null;
    }


    private void fetchMusicData() {
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM_ID,
                // Add other desired columns here
        };

        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        List<musicModel> musicList = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                String albumArtUri = getAlbumArtUri(albumId);
                // Create a MusicModel object and add it to the list
                musicModel musicModel = new musicModel(id, data, title,albumArtUri);
                musicList.add(musicModel);
            }
            cursor.close();

            // Create and set the adapter for the RecyclerView
            musicListAdapter madapter = new musicListAdapter(this, musicList);
            recyclerforlist.setAdapter(madapter);
        }
    }
}
