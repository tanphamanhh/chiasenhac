package com.dev.tanphamanh.chiasenhac.home.screen;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.tanphamanh.chiasenhac.R;
import com.dev.tanphamanh.chiasenhac.base.fragment.BaseFragment;
import com.dev.tanphamanh.chiasenhac.home.listener.homemenu.ListItemClickEventListener;
import com.dev.tanphamanh.chiasenhac.home.model.AudioItem;
import com.dev.tanphamanh.chiasenhac.home.model.RankingAudioItem;
import com.dev.tanphamanh.chiasenhac.home.model.TitleItem;
import com.dev.tanphamanh.chiasenhac.home.model.VideoItem;
import com.dev.tanphamanh.chiasenhac.home.view.homemenu.HomeMenuAdapter;
import com.dev.tanphamanh.csnservice.ranking.model.SAudioItem;
import com.dev.tanphamanh.csnservice.ranking.model.SRankingAudioItem;
import com.dev.tanphamanh.csnservice.ranking.model.SSongItem;
import com.dev.tanphamanh.csnservice.ranking.model.SVideoItem;
import com.dev.tanphamanh.csnservice.ranking.service.HomePageService;
import com.dev.tanphamanh.csnservice.ranking.service.TypeMusicService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMenuScr extends BaseFragment implements ListItemClickEventListener {


    private static final String TAG = HomeMenuScr.class.getSimpleName();

    public HomeMenuScr() {
        // Required empty public constructor
    }

    HomeMenuScrListener mListener;

    // Container Activity must implement this interface
    public interface HomeMenuScrListener {
        public void setTitle(String title);
    }

    private RecyclerView mRec;
    private ArrayList<Object> mData;
    private HomeMenuAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (HomeMenuScrListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement HomeMenuScrListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.home_menu_scr, container, false);
        mRec = (RecyclerView) v.findViewById(R.id.recHomeMenu);
        init();
        mTitle = getResources().getString(R.string.home_menu_title);
        return v;

    }



    private void init() {
        mData = new ArrayList<>();
        VietnamRankingTask task = new VietnamRankingTask();
        task.execute();
        mAdapter = new HomeMenuAdapter(mData, getContext(), this);
        mRec.setAdapter(mAdapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return (3 - position % 3);
//            }
//        });
        mRec.setLayoutManager(manager);
    }

    private void initVietnamRanking() {
        try {
            ArrayList<SRankingAudioItem> vietnamRanking = HomePageService.getInstance()
                    .getVietnamRanking();
            TitleItem vietnamTitle = new TitleItem();
            vietnamTitle.setType(TitleItem.TYPE_RANKING);
            vietnamTitle.setTitle(getResources().getString(R.string.home_menu_vietnam_ranking_title));
            vietnamTitle.setUrl(getResources().getString(R.string.home_menu_vietnam_ranking_url));
            mData.add(vietnamTitle);
            for (int i = 0; i < vietnamRanking.size(); i++) {
                SRankingAudioItem sRAI = vietnamRanking.get(i);
                RankingAudioItem rai = new RankingAudioItem();
                rai.setName(sRAI.getTitle());
                rai.setUrl(sRAI.getUrl());
                rai.setNo(sRAI.getRank());
                rai.setArtist(sRAI.getArtist());
                rai.setCoverUrl(sRAI.getCoverUrl());
                mData.add(rai);
            }
        } catch (IOException e) {
            handleWhenLoadError();
            Log.d(TAG, "initVietnamRanking: error");
            e.printStackTrace();
        }
    }

    private void initNewestDownload() {
        try {
            ArrayList<SSongItem> newestDown = HomePageService.getInstance()
                    .getNewestDownload();
            TitleItem newDownTitle = new TitleItem();
            newDownTitle.setType(TitleItem.TYPE_DOWNLOAD);
            newDownTitle.setTitle(getResources().getString(R.string.home_menu_new_download_title));
            newDownTitle.setUrl(getResources().getString(R.string.home_menu_new_download_url));
            mData.add(newDownTitle);
            for (int i = 0; i < newestDown.size(); i++) {
                SSongItem sAI = newestDown.get(i);
                if (sAI instanceof SAudioItem) {
                    AudioItem ai = new AudioItem();
                    ai.setName(sAI.getTitle());
                    ai.setUrl(sAI.getUrl());
                    ai.setArtist(sAI.getArtist());
                    ai.setCoverUrl(sAI.getCoverUrl());
                    mData.add(ai);
                } else if (sAI instanceof SVideoItem) {
                    VideoItem vi = new VideoItem();
                    vi.setName(sAI.getTitle());
                    vi.setArtist(sAI.getArtist());
                    vi.setUrl(sAI.getUrl());
                    vi.setCoverUrl(sAI.getCoverUrl());
                    mData.add(vi);
                }

            }
        } catch (IOException e) {
            handleWhenLoadError();
            Log.d(TAG, "initNewestDownload: error");
            e.printStackTrace();
        }
    }

//    private void initNewSharing() {
//        try {
//            ArrayList<SAudioItem> newestDown = HomePageService.getInstance()
//                    .getNewSharingMusic();
//            TitleItem newShareTitle = new TitleItem();
//            newShareTitle.setType(TitleItem.TYPE_RANKING);
//            newShareTitle.setTitle(getResources().getString(R.string.home_menu_new_sharing_title));
//            newShareTitle.setUrl(getResources().getString(R.string.home_menu_new_sharing_url));
//            mData.add(newShareTitle);
//            for (int i = 0; i < newestDown.size(); i++) {
//                SAudioItem sAI = newestDown.get(i);
//                AudioItem ai = new RankingAudioItem();
//                ai.setName(sAI.getTitle());
//                ai.setUrl(sAI.getAudioUrl());
//                ai.setArtist(sAI.getArtist());
//                ai.setCoverUrl(sAI.getCoverUrl());
//                mData.add(ai);
//            }
//        } catch (IOException e) {
//            handleWhenLoadError();
//            Log.d(TAG, "initNewestDownload: error");
//            e.printStackTrace();
//        }
//    }

    private void handleWhenLoadError() {
        // TODO: 3/24/2017 Show error when cant load data from web
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.setTitle(mTitle);

    }

    private class VietnamRankingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            initVietnamRanking();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.notifyDataSetChanged();
            NewestDownloadTask newestDownloadTask = new NewestDownloadTask();
            newestDownloadTask.execute();
        }
    }

    private class NewestDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            initNewestDownload();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.notifyDataSetChanged();
            NewSharingTask newSharingTask = new NewSharingTask();
            newSharingTask.execute();
        }
    }

    private class NewSharingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
//            initVietnamRanking();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(int position, int eventInfo) {

    }
}
