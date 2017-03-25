package com.dev.tanphamanh.chiasenhac.home.view.homemenu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.tanphamanh.chiasenhac.R;
import com.dev.tanphamanh.chiasenhac.home.listener.homemenu.ListItemClickEventListener;

/**
 * Created by pes10 on 3/3/2017.
 */

public class TitleViewHolder extends RecyclerView.ViewHolder{

    private ListItemClickEventListener listItemClickEventListener;

    private ImageView imgType;
    private TextView txvTitle;
    private ImageView imgPlay;
    private TextView txvSeeMore;

    public TitleViewHolder(View itemView, final ListItemClickEventListener listItemClickEventListener) {
        super(itemView);
        this.listItemClickEventListener = listItemClickEventListener;
        imgType = (ImageView) itemView.findViewById(R.id.imgTitleTypeHomeMenuTitleItem);
        txvTitle = (TextView) itemView.findViewById(R.id.txvTitleHomeMenuTitleItem);
        imgPlay = (ImageView) itemView.findViewById(R.id.playHomeMenuTitleItem);
        txvSeeMore = (TextView) itemView.findViewById(R.id.seeMoreHomeMenuTitleItem);
//        imgPlay.setOnTouchListener(new WidgetTouchListener(imgPlay) {
//            @Override
//            public void doAction() {
//                listItemClickEventListener.onItemClick(getAdapterPosition(), EventInfo.TITLE_ITEM_PLAY_ALL);
//            }
//        });
//        txvSeeMore.setOnTouchListener(new WidgetTouchListener(txvSeeMore) {
//            @Override
//            public void doAction() {
//                listItemClickEventListener.onItemClick(getAdapterPosition(), EventInfo.TITLE_ITEM_SEE_MORE);
//            }
//        });
    }

    public ListItemClickEventListener getListItemClickEventListener() {
        return listItemClickEventListener;
    }

    public void setListItemClickEventListener(ListItemClickEventListener listItemClickEventListener) {
        this.listItemClickEventListener = listItemClickEventListener;
    }

    public ImageView getImgType() {
        return imgType;
    }

    public void setImgType(ImageView imgType) {
        this.imgType = imgType;
    }

    public TextView getTxvTitle() {
        return txvTitle;
    }

    public void setTxvTitle(TextView txvTitle) {
        this.txvTitle = txvTitle;
    }

    public ImageView getImgPlay() {
        return imgPlay;
    }

    public void setImgPlay(ImageView imgPlay) {
        this.imgPlay = imgPlay;
    }

    public TextView getTxvSeeMore() {
        return txvSeeMore;
    }

    public void setTxvSeeMore(TextView txvSeeMore) {
        this.txvSeeMore = txvSeeMore;
    }
}
