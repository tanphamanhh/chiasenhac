package com.dev.tanphamanh.csnservice.ranking.model;

/**
 * Created by pes10 on 3/24/2017.
 */

public class SSongItem {
    private String title;
    private String url;
    private String artist;
    private String coverUrl;

    public SSongItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
