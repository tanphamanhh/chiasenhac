package com.dev.tanphamanh.chiasenhac.home.model;

/**
 * Created by tanphamanhh on 26/02/2017.
 */

public class AudioItem {
    private String name;
    private String artist;
    private String url;
    private String coverUrl;

    public AudioItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
