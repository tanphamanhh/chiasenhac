package com.dev.tanphamanh.chiasenhac.home.model;

/**
 * Created by tanphamanhh on 26/02/2017.
 */

public class TitleItem {
    public static final int TYPE_RANKING = 383;
    public static final int TYPE_ALBUM = 923;
    public static final int TYPE_DOWNLOAD = 880;
    public static final int TYPE_SHARE = 886;
    public static final int TYPE_NEW = 657;


    private int type;
    private String title;
    private String url;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
