package com.dev.tanphamanh.csnservice.ranking.service;

import android.text.TextUtils;

import com.dev.tanphamanh.csnservice.ranking.model.SAudioDetail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by tanphamanhh on 15/02/2017.
 */
public class AudioDetailService {

    public static final int _32KBPS = 0;
    private static final String SUB_32KBPS = "http://chiasenhac.vn/quality.php?q=32&redirect=";
    public static final int _128KBPS = 1;
    private static final String SUB_128KBPS = "http://chiasenhac.vn/quality.php?q=128&redirect=";
    public static final int _320KBPS = 2;
    private static final String SUB_320KBPS = "http://chiasenhac.vn/quality.php?q=320&redirect=";
    public static final int _500KBPS = 3;
    private static final String SUB_500KBPS = "http://chiasenhac.vn/quality.php?q=500&redirect=";
    public static final int _LOSSLESS = 4;


    private static final int TIME_LIMIT = 10000;

    private static AudioDetailService ourInstance = new AudioDetailService();

    public static AudioDetailService getInstance() {
        return ourInstance;
    }

    private AudioDetailService() {
    }

    public SAudioDetail getAudioDetailByUrl(String url, int audioQuality, int defaultQuality) throws IOException {
        int quality = getQuality(audioQuality, defaultQuality);
        String newUrl = "";
        switch (quality) {
            case _32KBPS:
                newUrl = SUB_32KBPS + url;
                break;
            case _128KBPS:
                newUrl = SUB_128KBPS + url;
                break;
            case _320KBPS:
                newUrl = SUB_320KBPS + url;
                break;
            case _500KBPS:
                newUrl = SUB_500KBPS + url;
                break;
        }

        Document doc = Jsoup.connect(newUrl).timeout(TIME_LIMIT).get();
        String title = doc.select("span.maintitle").get(0).text();
        String artist = null;
        String author = null;
        String composer = null;
        String album = null;
        String coverUrl = doc.select("link[rel=image_src]").get(0).attr("href");

        Elements el = doc.select("span.genmed").get(0).getAllElements();
        for (int i = 0; i < el.size(); i++) {
            Element e = el.get(i);
            if (e.text().equalsIgnoreCase("Ca sĩ:")) {
                artist = el.get(i + 1).text();

            } else if (e.text().equalsIgnoreCase("Sáng tác:")) {
                author = el.get(i + 1).text();

            } else if (e.text().equalsIgnoreCase("Album:")) {
                album = el.get(i + 1).text();

            } else if (e.text().equalsIgnoreCase("Sản xuất:")) {
                composer = el.get(i + 1).text();

            }
        }
        String audioContent = doc.select("div.jplayer").get(0).nextElementSibling().toString();

        int x1;

        if (quality == _32KBPS || quality == _500KBPS) {
            x1 = audioContent.indexOf("m4a: ");
        } else {
            x1 = audioContent.indexOf("mp3: ");

        }
        int x2 = audioContent.indexOf("\"", x1);
        int x3 = audioContent.indexOf("\"", x2 + 1);

        String audioUrl = audioContent.substring(x2 + 1, x3);

        SAudioDetail ad = new SAudioDetail();
        if (!TextUtils.isEmpty(title)) {
            ad.setTitle(title);
        }
        if (!TextUtils.isEmpty(artist)) {
            ad.setArtist(artist);
        }
        if (!TextUtils.isEmpty(author)) {
            ad.setAuthor(author);
        }
        if (!TextUtils.isEmpty(composer)) {
            ad.setComposer(composer);
        }
        if (!TextUtils.isEmpty(album)) {
            ad.setAlbum(album);
        }
        if (!TextUtils.isEmpty(coverUrl)) {
            ad.setCoverUrl(coverUrl);
        }

        if (!TextUtils.isEmpty(audioUrl)) {
            ad.setAudioUrl(audioUrl);
        }

        return ad;

    }

    private int getQuality(int audioQuality, int defaultQuality) {
        if (audioQuality == -1) {
            audioQuality = _LOSSLESS;
        } else if (audioQuality >= 500) {
            audioQuality = _500KBPS;
        } else if (audioQuality >= 320) {
            audioQuality = _320KBPS;
        } else if (audioQuality >= 128) {
            audioQuality = _128KBPS;
        } else {
            audioQuality = _32KBPS;
        }

        if (defaultQuality <= audioQuality) {
            return defaultQuality;
        } else {
            return audioQuality;
        }

    }
}
