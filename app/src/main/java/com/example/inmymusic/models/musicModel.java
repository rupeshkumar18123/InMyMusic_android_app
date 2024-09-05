package com.example.inmymusic.models;

public class musicModel {
    private long id;
    private String data;
    private String title;
    private String albumArtUri;



    // Add getters and setters as needed


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbumArtUri() {
        return albumArtUri;
    }

    public void setAlbumArtUri(String albumArtUri) {
        this.albumArtUri = albumArtUri;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public musicModel(long id, String data, String title,String albumArtUri) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.albumArtUri=albumArtUri;
    }
}
