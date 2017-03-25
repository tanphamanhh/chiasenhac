package com.dev.tanphamanh.csnservice.ranking.service;

import android.text.TextUtils;
import android.util.Log;

import com.dev.tanphamanh.csnservice.ranking.model.SAudioItem;
import com.dev.tanphamanh.csnservice.ranking.model.SRankingAudioItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.dev.tanphamanh.csnservice.BuildConfig.DEBUG;

/**
 * Created by tanphamanhh on 12/02/2017.
 */
public class TypeMusicService {

    private final String TAG = this.getClass().getSimpleName();

    public static final int VIET_NAM = 0;
    private static final String VN_URL = "http://chiasenhac.vn/mp3/vietnam/";

    public static final int VIET_NAM_POP = 1;
    private static final String VN_POP_URL = "http://chiasenhac.vn/mp3/vietnam/v-pop/";

    public static final int VIET_NAM_RAP_HIPHOP = 2;
    private static final String VN_RAP_HIPHOP_URL = "http://chiasenhac.vn/mp3/vietnam/v-rap-hiphop/";

    public static final int VIET_NAM_DANCE_REMIX = 3;
    private static final String VN_DANCE_REMIX_URL = "http://chiasenhac.vn/mp3/vietnam/v-dance-remix/";

    public static final int VIET_NAM_TRUYEN_THONG = 4;
    private static final String VN_TRUYEN_THONG_URL = "http://chiasenhac.vn/mp3/vietnam/v-truyen-thong/";

    public static final int US_UK = 5;
    private static final String US_UK_URL = "http://chiasenhac.vn/mp3/us-uk/";

    public static final int US_UK_POP = 6;
    private static final String US_UK_POP_URL = "http://chiasenhac.vn/mp3/us-uk/us-pop/";

    public static final int US_UK_RAP_HIPHOP = 7;
    private static final String US_UK_RAP_HIPHOP_URL = "http://chiasenhac.vn/mp3/us-uk/us-rap-hiphop/";

    public static final int US_UK_DANCE_REMIX = 8;
    private static final String US_UK_DANCE_REMIX_URL = "http://chiasenhac.vn/mp3/us-uk/us-dance-remix/";

    private static final int TIME_LIMIT = 10000;

    private static TypeMusicService ourInstance = new TypeMusicService();

    public static TypeMusicService getInstance() {
        return ourInstance;
    }

    private TypeMusicService() {
    }


    /**
     * get top 20 audio of this type
     * @param type type of audio
     * @return array list 20 items audio, if error, return null
     */
    public ArrayList<SRankingAudioItem> getRankingByType(int type) throws IOException {
        ArrayList<SRankingAudioItem> sRankingAudioItems = new ArrayList<>();
        String url = getURLByType(type);
        Document doc = Jsoup.connect(url).timeout(TIME_LIMIT).get();
        Elements e = doc.select("div.list-r");
        for (int i = 0; i < 20; i++) {
            Element el = e.get(i);
            int rank = Integer.parseInt(el.select("span").get(0).text());
            String title = el.select("a").get(0).text();
            String artist = el.select("p").get(1).text();
            String quality = el.select("span").get(1).text();
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
     * get 20 new sharing audio
     * @param type type of audio
     * @return array list 20 items audio, if error, return null
     */
    public ArrayList<SAudioItem> getNewSharingMusicByType(int type) throws IOException {
        String url = getURLByType(type);
        ArrayList<SAudioItem> sAudioItems = new ArrayList<>();
        Document doc = Jsoup.connect(url).timeout(TIME_LIMIT).get();
        Elements e = doc.select("div.list-r");
        for (int i = 20; i < 40; i++) {
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
     * @param type type of audio
     * @return array list 20 items audio, if error, return null
     */
    public ArrayList<SAudioItem> getNewestDownloadByTyte(int type) throws IOException {
        String url = getURLByType(type);
        ArrayList<SAudioItem> sAudioItems = new ArrayList<>();
        Document doc = Jsoup.connect(url).timeout(TIME_LIMIT).get();
        Elements e = doc.select("div.list-l");
        for (int i = 0; i < 25; i++) {
            Element el = e.get(i);
            String title = el.select("a").get(0).text();
            String artist = el.select("p").get(0).text();
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
     * get URL by type of audio, see above
     * @param type type of audio
     * @return url corresponding to input type
     */
    public String getURLByType(int type) {
        String url = null;
        switch (type) {
            case VIET_NAM_POP:
                url = VN_POP_URL;
                break;
            case VIET_NAM_RAP_HIPHOP:
                url = VN_RAP_HIPHOP_URL;
                break;
            case VIET_NAM_DANCE_REMIX:
                url = VN_DANCE_REMIX_URL;
                break;
            case VIET_NAM_TRUYEN_THONG:
                url = VN_TRUYEN_THONG_URL;
                break;
            case US_UK_POP:
                url = US_UK_POP_URL;
                break;
            case US_UK_RAP_HIPHOP:
                url = US_UK_RAP_HIPHOP_URL;
                break;
            case US_UK_DANCE_REMIX:
                url = US_UK_DANCE_REMIX_URL;
                break;
            case VIET_NAM:
                url = VN_URL;
                break;
            case US_UK:
                url = US_UK_URL;
                break;
            default:
                url = VN_POP_URL;
                break;
        }
        return url;
    }
}
