//package com.example.inmymusic.adapter;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.inmymusic.R;
//import com.example.inmymusic.models.musicModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class musicListAdapter extends RecyclerView.Adapter<musicListAdapter.viewholder> {
//    Context context;
//    List<musicModel> arr;
//
//
//    private final MediaPlayer mediaPlayer;
//    public musicListAdapter(@NonNull Context context, List arr) {
//
//        this.arr=arr;
//        this.context=context;
//        this.mediaPlayer = new MediaPlayer();
//    }
//
//    public class viewholder extends RecyclerView.ViewHolder{
//        TextView title;
//        ImageView music_image,play_button;
//        public viewholder(@NonNull View itemView) {
//            super(itemView);
//            music_image=itemView.findViewById(R.id.image_view_holder);
//            play_button=itemView.findViewById(R.id.play_button_view);
//            title=itemView.findViewById(R.id.title_music_view);
//
//
//            // Set an onClickListener for the play button
//            play_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        // Get the audio file path for the clicked item
//                        String audioFilePath = arr.get(position).getData();
//
//                        // Handle media playback
//                        try {
//                            mediaPlayer.reset();
//                            mediaPlayer.setDataSource(audioFilePath);
//                            mediaPlayer.prepare();
//                            mediaPlayer.start();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            // Handle exceptions here
//                        }
//                    }
//                }
//            });
//
//        }
//    }
//
//    @NonNull
//    @Override
//    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(context).inflate(R.layout.music_view_holder,parent,false);
//        viewholder vh=new viewholder(view);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull viewholder holder, int position) {
//        holder.title.setText(arr.get(position).getTitle());
//    }
//
//    @Override
//    public int getItemCount() {
//        return arr.size();
//    }
//
//    // Ensure that you release the MediaPlayer when the adapter is no longer needed
//    public void releaseMediaPlayer() {
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//        }
//    }
//
//}

package com.example.inmymusic.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmymusic.R;
import com.example.inmymusic.models.musicModel;

import java.io.IOException;
import java.util.List;

public class musicListAdapter extends RecyclerView.Adapter<musicListAdapter.viewholder> {
    private Context context;
    private List<musicModel> arr;
    private MediaPlayer mediaPlayer;

    public musicListAdapter(@NonNull Context context, List<musicModel> arr) {
        this.context = context;
        this.arr = arr;
        this.mediaPlayer = new MediaPlayer();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView music_image, play_button;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            music_image = itemView.findViewById(R.id.image_view_holder);
            play_button = itemView.findViewById(R.id.play_button_view);
            title = itemView.findViewById(R.id.title_music_view);

            // Set an onClickListener for the play button
            play_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Get the audio file path for the clicked item
                        String audioFilePath = arr.get(position).getData();

                        // Handle media playback
                        try {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                                mediaPlayer.reset();
                            }
                            mediaPlayer.setDataSource(audioFilePath);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                            // Handle exceptions here
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_view_holder, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.title.setText(arr.get(position).getTitle());

//        // Load the image into the music_image ImageView
//        String imagePath = arr.get(position).getImagePath(); // Replace with your image path
//
//        if (imagePath != null) {
//            // If you have an image path (local file path or URI), load it into the ImageView
//            Uri imageUri = Uri.parse(imagePath);
//            holder.music_image.setImageURI(imageUri);
//        } else {
//            // If you have a resource ID for the image, load it into the ImageView
//            int imageResourceId = R.drawable.default_image; // Replace with your image resource ID
//            holder.music_image.setImageResource(imageResourceId);
//        }
        // Load the album art into the music_image ImageView
        String albumArtUri = arr.get(position).getAlbumArtUri();

        if (albumArtUri != null) {
            // If you have an album art URI, load it into the ImageView
            Uri imageUri = Uri.parse(albumArtUri);
            holder.music_image.setImageURI(imageUri);
        } else {
            // If you don't have an album art URI, you can load a default image
            int defaultImageResourceId = R.drawable.main_logo; // Replace with your default image resource ID
            holder.music_image.setImageResource(defaultImageResourceId);
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    // Ensure that you release the MediaPlayer when the adapter is no longer needed
    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

