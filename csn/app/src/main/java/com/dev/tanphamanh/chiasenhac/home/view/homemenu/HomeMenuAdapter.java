package com.dev.tanphamanh.chiasenhac.home.view.homemenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.tanphamanh.chiasenhac.R;
import com.dev.tanphamanh.chiasenhac.home.listener.homemenu.ListItemClickEventListener;
import com.dev.tanphamanh.chiasenhac.home.model.AudioItem;
import com.dev.tanphamanh.chiasenhac.home.model.RankingAudioItem;
import com.dev.tanphamanh.chiasenhac.home.model.TitleItem;
import com.dev.tanphamanh.chiasenhac.home.model.VideoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by pes10 on 3/5/2017.
 */

public class HomeMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<Object> mData;
    private Context mContext;
    private ListItemClickEventListener mListItemClickEventListener;

    public HomeMenuAdapter(ArrayList<Object> mData, Context mContext, ListItemClickEventListener mListItemClickEventListener) {
        this.mData = mData;
        this.mContext = mContext;
        this.mListItemClickEventListener = mListItemClickEventListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_menu_title_item, parent, false);
                return new TitleViewHolder(itemView, mListItemClickEventListener);
            case 1:
            case 2:
                View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_menu_audio_item, parent, false);
                return new AudioViewHolder(itemView2, mListItemClickEventListener);
            case 3:
                View itemView3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_menu_video_item, parent, false);
                return new VideoViewHolder(itemView3, mListItemClickEventListener);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                TitleItem ti = (TitleItem) mData.get(position);
                TitleViewHolder tvh = (TitleViewHolder) holder;
                if (!TextUtils.isEmpty(ti.getTitle())) {
                    tvh.getTxvTitle().setText(ti.getTitle());
                } else {
                    tvh.getTxvTitle().setText("");
                }
                switch (ti.getType()) {
                    case TitleItem.TYPE_RANKING:
                        tvh.getImgType().setImageResource(R.drawable.home_menu_ranking);
                        break;
                    case TitleItem.TYPE_ALBUM:
                        tvh.getImgType().setImageResource(R.drawable.home_menu_album);
                        break;
                    case TitleItem.TYPE_NEW:
                        tvh.getImgType().setImageResource(R.drawable.home_menu_new);
                        break;
                    case TitleItem.TYPE_SHARE:
                        tvh.getImgType().setImageResource(R.drawable.home_menu_share);
                        break;
                    case TitleItem.TYPE_DOWNLOAD:
                        tvh.getImgType().setImageResource(R.drawable.home_menu_download);
                        break;

                }
                break;
            case 1:
                RankingAudioItem rai = (RankingAudioItem) mData.get(position);
                AudioViewHolder avh = (AudioViewHolder) holder;
                avh.getRlLayRanking().setVisibility(VISIBLE);
                if (rai.getNo() > 3) {
                    avh.getImgStar().setVisibility(GONE);
                } else {
                    avh.getImgStar().setVisibility(VISIBLE);
                }
                avh.getTxvNo().setText(String.valueOf(rai.getNo()));
                avh.getTxvTitle().setText(rai.getName());
                avh.getTxvArtist().setText(rai.getArtist());
                if (TextUtils.isEmpty(rai.getCoverUrl())) {
                    avh.getImgCover().setImageResource(R.drawable.logo_csn_100x100);
                } else {
                    Picasso.with(mContext).load(rai.getCoverUrl()).into(avh.getImgCover());
                }
                break;
            case 2:
                AudioItem ai = (AudioItem) mData.get(position);
                AudioViewHolder avh2 = (AudioViewHolder) holder;
                avh2.getRlLayRanking().setVisibility(GONE);
                avh2.getTxvTitle().setText(ai.getName());
                avh2.getTxvArtist().setText(ai.getArtist());
                if (TextUtils.isEmpty(ai.getCoverUrl())) {
                    avh2.getImgCover().setImageResource(R.drawable.logo_csn_100x100);
                } else {
                    Picasso.with(mContext).load(ai.getCoverUrl()).into(avh2.getImgCover());
                }
                break;
            case 3:
                VideoItem vi = (VideoItem) mData.get(position);
                VideoViewHolder vvh = (VideoViewHolder) holder;
                vvh.getRlLayRanking().setVisibility(GONE);
                vvh.getTxvTitle().setText(vi.getName());
                vvh.getTxvArtist().setText(vi.getArtist());
                if (TextUtils.isEmpty(vi.getCoverUrl())) {
                    vvh.getImgCover().setImageResource(R.drawable.logo_csn_100x100);
                } else {
                    Picasso.with(mContext).load(vi.getCoverUrl()).into(vvh.getImgCover());
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = mData.get(position);
        if (o instanceof TitleItem) {
            return 0;
        } else if (o instanceof RankingAudioItem) {
            return 1;
        } else if (o instanceof AudioItem) {
            return 2;
        } else if (o instanceof VideoItem) {
            return 3;
        }
        return 0;
    }
}
