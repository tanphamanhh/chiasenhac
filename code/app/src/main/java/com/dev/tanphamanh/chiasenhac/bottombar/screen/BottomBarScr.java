package com.dev.tanphamanh.chiasenhac.bottombar.screen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev.tanphamanh.chiasenhac.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomBarScr extends Fragment {


    public BottomBarScr() {
        // Required empty public constructor
    }

    public interface BottomBarListener {
        void onTabChange(int oldTabId, int newTabId);
        void onTapCurrentTab(int tabId);
    }

    private BottomBarListener mBottomBarListener;
    private int mCurrentTab;
    private ImageView[] mIconBottomBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.bottom_bar_scr, container, false);
        mIconBottomBar = new ImageView[4];
        mIconBottomBar[0] = (ImageView) v.findViewById(R.id.homeBottomBar);
        mIconBottomBar[1] = (ImageView) v.findViewById(R.id.exploreBottomBar);
        mIconBottomBar[2] = (ImageView) v.findViewById(R.id.personalBottomBar);
        mIconBottomBar[3] = (ImageView) v.findViewById(R.id.settingBottomBar);

        //set home tab selected
        mCurrentTab = 0;
        mIconBottomBar[0].setSelected(true);

        //setlistenner

        setOnClickForIcon();
        return v;
    }

    private void setOnClickForIcon() {
        for (int i = 0; i < mIconBottomBar.length; i++) {
            final int finalI = i;
            mIconBottomBar[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCurrentTab == finalI) {
                        mBottomBarListener.onTapCurrentTab(finalI);
                    } else {
                        mBottomBarListener.onTabChange(mCurrentTab, finalI);
                        mIconBottomBar[mCurrentTab].setSelected(false);
                        mIconBottomBar[finalI].setSelected(true);
                        mCurrentTab = finalI;
                    }
                }
            });
        }
    }

    public BottomBarListener getmBottomBarListener() {
        return mBottomBarListener;
    }

    public void setmBottomBarListener(BottomBarListener mBottomBarListener) {
        this.mBottomBarListener = mBottomBarListener;
    }
}
