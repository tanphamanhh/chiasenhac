package com.dev.tanphamanh.csnservice.ranking.service;

import com.dev.tanphamanh.csnservice.ranking.model.SAudioItem;
import com.dev.tanphamanh.csnservice.ranking.model.SRankingAudioItem;
import com.dev.tanphamanh.csnservice.ranking.model.SSongItem;
import com.dev.tanphamanh.csnservice.ranking.model.SVideoItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pes10 on 3/24/2017.
 */

public class HomePageService {
    private static final HomePageService ourInstance = new HomePageService();

    public static HomePageService getInstance() {
        return ourInstance;
    }

    private static final int TIME_LIMIT = 10000;
    private static final String URL = "http://chiasenhac.vn/";

    private HomePageService() {
    }

    /**
     * get top 10 vietnam ranking
     * @return array list 10 items audio, if error, return null
     */
    public ArrayList<SRankingAudioItem> getVietnamRanking() throws IOException {
        ArrayList<SRankingAudioItem> sRankingAudioItems = new ArrayList<>();
        Document doc = Jsoup.connect(URL).timeout(TIME_LIMIT).get();
        Elements e = doc.select("div.list-r");
        for (int i = 0; i < 10; i++) {
            Element el = e.get(i);
            int rank = Integer.parseInt(el.select("p").get(0).text());
            String title = el.select("a").get(0).text();
            String artist = el.select("p").get(1).text();
            String quality = el.select("p").get(3).text();
            int qu = getQuality(quality);
            String audioUrl = el.select("a").get(0).attr("href");
            Document doc2 = Jsoup.connect(audioUrl).timeout(TIME_LIMIT).get();
            String coverUrl = doc2.select("link[rel=image_src]").get(0).attr("href");
            SRankingAudioItem sRankingAudioItem = new SRankingAudioItem();
            sRankingAudioItem.setRank(rank);
            sRankingAudioItem.setTitle(title);
            sRankingAudioItem.setArtist(artist);
            sRankingAudioItem.setCoverUrl(coverUrl);
            sRankingAudioItem.setUrl(audioUrl);
            sRankingAudioItem.setQuality(qu);
            sRankingAudioItems.add(sRankingAudioItem);
        }

        return sRankingAudioItems;
    }

    private int getQuality(String quality) {
        if (quality.equalsIgnoreCase("Lossless")) {
            return -1;
        } else {
            int kpsPosition = quality.indexOf("k");
            return Integer.parseInt(quality.substring(0, kpsPosition));
        }
    }

    /**
     * get 15 new sharing audio in homepage
     * @return array list 15 items audio, if error, return null
     */
    public ArrayList<SAudioItem> getNewSharingMusic() throws IOException {
        ArrayList<SAudioItem> sAudioItems = new ArrayList<>();
        Document doc = Jsoup.connect(URL).timeout(TIME_LIMIT).get();
        Elements e = doc.select("div.list-r");
        for (int i = 10; i < 25; i++) {
            Element el = e.get(i);
            String title = el.select("a").get(0).text();
            String artist = el.select("p").get(1).text();
            String quality = el.select("span").get(0).text();
            int qu = getQuality(quality);
            String audioUrl = el.select("a").get(0).attr("href");
            Document doc2 = Jsoup.connect(audioUrl).timeout(TIME_LIMIT).get();
            String coverUrl = doc2.select("link[rel=image_src]").get(0).attr("href");
            SAudioItem sAudioItem = new SAudioItem();
            sAudioItem.setTitle(title);
            sAudioItem.setArtist(artist);
            sAudioItem.setCoverUrl(coverUrl);
            sAudioItem.setUrl(audioUrl);
            sAudioItem.setQuality(qu);
            sAudioItems.add(sAudioItem);
        }

        return sAudioItems;
    }

    /**
     * get 20 newest download audio
     * @return array list 20 items audio, if error, return null
     */
    public ArrayList<SSongItem> getNewestDownload() throws IOException {
        ArrayList<SSongItem> sSongItems = new ArrayList<>();
        Document doc = Jsoup.connect(URL).timeout(TIME_LIMIT).get();
        Elements e = doc.select("div.list-l");
        for (int i = 6; i < 21; i++) {
            Element el = e.get(i);
            String title = el.select("a").get(0).text();
            String artist = el.select("p").get(0).text();
            Elements checkVideo = el.select("img");
            String quality = "";
            SSongItem item;
            if (checkVideo.isEmpty()) {
                // a audio
                item = new SAudioItem();
                String checkBeat = el.select("span").get(0).text();
                if (checkBeat.equalsIgnoreCase("Beat")) {
                    //is beat
                    quality = el.select("span").get(1).text();
                } else {
                    //a normal audio
                    quality = el.select("span").get(0).text();
                }

                int qu = getQuality(quality);
                String audioUrl = el.select("a").get(0).attr("href");
                Document doc2 = Jsoup.connect(audioUrl).timeout(10000).get();
                String coverUrl = doc2.select("link[rel=image_src]").get(0).attr("href");

                item.setTitle(title);
                item.setArtist(artist);
                item.setUrl(audioUrl);
                item.setCoverUrl(coverUrl);
                ((SAudioItem) item).setQuality(qu);
            } else {
                //a video
                item = new SVideoItem();
                quality = el.select("span").get(0).text();
                String videoImg = el.select("img").attr("src");
                String videoUrl = el.select("a").get(0).attr("href");
                item.setTitle(title);
                item.setArtist(artist);
                item.setUrl(videoUrl);
                item.setCoverUrl(videoImg);
                ((SVideoItem) item).setQuality(quality);
            }
            sSongItems.add(item);
        }

        return sSongItems;
    }
}
