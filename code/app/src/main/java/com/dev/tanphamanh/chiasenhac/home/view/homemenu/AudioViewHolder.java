package com.dev.tanphamanh.chiasenhac.home.view.homemenu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.tanphamanh.chiasenhac.R;
import com.dev.tanphamanh.chiasenhac.base.listener.WidgetTouchListener;
import com.dev.tanphamanh.chiasenhac.home.listener.homemenu.ListItemClickEventListener;
import com.dev.tanphamanh.chiasenhac.home.model.EventInfo;

/**
 * Created by pes10 on 3/4/2017.
 */

public class AudioViewHolder extends RecyclerView.ViewHolder {
    private RelativeLayout rlLayRanking;
    private ImageView imgStar;
    private TextView txvNo;
    private ImageView imgCover;
    private TextView txvTitle;
    private TextView txvArtist;
    private ImageView imgAddToPlaylist;
    private ImageView imgDownload;

    private ListItemClickEventListener listItemClickEventListener;

    public AudioViewHolder(View itemView, ListItemClickEventListener listItemClickEventListener) {
        super(itemView);
        this.listItemClickEventListener = listItemClickEventListener;
        rlLayRanking = (RelativeLayout) itemView.findViewById(R.id.rlLayRankingHomeMenuAudioItem);
        imgStar = (ImageView) itemView.findViewById(R.id.imgStarHomeMenuAudioItem);
        txvNo = (TextView) itemView.findViewById(R.id.txvNoHomeMenuAudioItem);
        imgCover = (ImageView) itemView.findViewById(R.id.imgCoverHomeMenuAudioItem);
        txvTitle = (TextView) itemView.findViewById(R.id.txvTitleHomeMenuAudioItem);
        txvArtist = (TextView) itemView.findViewById(R.id.txvArtistHomeMenuAudioItem);
        imgAddToPlaylist = (ImageView) itemView.findViewById(R.id.imgAddToPlaylistHomeMenuAudioItem);
        imgDownload = (ImageView) itemView.findViewById(R.id.imgDownloadHomeMenuAudioItem);
//        initTouchEvent();
    }

    private void initTouchEvent() {
        itemView.setOnTouchListener(new WidgetTouchListener(itemView) {
            @Override
            public void doAction() {
                listItemClickEventListener.onItemClick(getAdapterPosition(), EventInfo.AUDIO_ITEM_PLAY);
            }
        });
        imgAddToPlaylist.setOnTouchListener(new WidgetTouchListener(imgAddToPlaylist) {
            @Override
            public void doAction() {
                listItemClickEventListener.onItemClick(getAdapterPosition(), EventInfo.AUDIO_ITEM_ADD_TO_LIST);
            }
        });
        imgDownload.setOnTouchListener(new WidgetTouchListener(imgDownload) {
            @Override
            public void doAction() {
                listItemClickEventListener.onItemClick(getAdapterPosition(), EventInfo.AUDIO_ITEM_DOWNLOAD);
            }
        });
    }

    public RelativeLayout getRlLayRanking() {
        return rlLayRanking;
    }

    public void setRlLayRanking(RelativeLayout rlLayRanking) {
        this.rlLayRanking = rlLayRanking;
    }

    public ImageView getImgStar() {
        return imgStar;
    }

    public void setImgStar(ImageView imgStar) {
        this.imgStar = imgStar;
    }

    public TextView getTxvNo() {
        return txvNo;
    }

    public void setTxvNo(TextView txvNo) {
        this.txvNo = txvNo;
    }

    public ImageView getImgCover() {
        return imgCover;
    }

    public void setImgCover(ImageView imgCover) {
        this.imgCover = imgCover;
    }

    public TextView getTxvTitle() {
        return txvTitle;
    }

    public void setTxvTitle(TextView txvTitle) {
        this.txvTitle = txvTitle;
    }

    public TextView getTxvArtist() {
        return txvArtist;
    }

    public void setTxvArtist(TextView txvArtist) {
        this.txvArtist = txvArtist;
    }

    public ImageView getImgAddToPlaylist() {
        return imgAddToPlaylist;
    }

    public void setImgAddToPlaylist(ImageView imgAddToPlaylist) {
        this.imgAddToPlaylist = imgAddToPlaylist;
    }

    public ImageView getImgDownload() {
        return imgDownload;
    }

    public void setImgDownload(ImageView imgDownload) {
        this.imgDownload = imgDownload;
    }

    public ListItemClickEventListener getListItemClickEventListener() {
        return listItemClickEventListener;
    }

    public void setListItemClickEventListener(ListItemClickEventListener listItemClickEventListener) {
        this.listItemClickEventListener = listItemClickEventListener;
    }
}
