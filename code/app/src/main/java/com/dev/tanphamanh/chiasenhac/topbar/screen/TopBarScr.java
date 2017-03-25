package com.dev.tanphamanh.chiasenhac.topbar.screen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.tanphamanh.chiasenhac.R;
import com.dev.tanphamanh.chiasenhac.base.listener.WidgetTouchListener;

import static android.view.View.GONE;
import static com.dev.tanphamanh.chiasenhac.base.listener.WidgetTouchListener.FADE_ANIMATION_DURATION;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopBarScr extends Fragment{


    private final String TAG = this.getClass().getSimpleName();
    // search layout include: search icon, edit text and Cancel button
    private RelativeLayout mSearchLayout;
    //default layout include: back button, title, search button
    private RelativeLayout mDefaultLayout;

    private ImageView mSearchIcon;
    private TextView mTitle;
    private TextView mCancelSearch;

    public TopBarScr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.top_bar_scr, container, false);

//        mContentLayout = (RelativeLayout) view.findViewById(R.id.contentTopBar);

        mDefaultLayout = (RelativeLayout) view.findViewById(R.id.defaultLayoutTopBar);

        mSearchLayout = (RelativeLayout) view.findViewById(R.id.searchLayoutTopBar);
        mCancelSearch = (TextView) view.findViewById(R.id.cancelSearchTopBar);
        mSearchLayout.setVisibility(GONE);

        mSearchIcon = (ImageView) view.findViewById(R.id.searchRightIconTopBar);
        mTitle = (TextView) view.findViewById(R.id.titleTopBar);
//        mSearchIcon.setAlpha(0.3f);
        return view;
    }

    @Override
    public void onResume() {
        setupOnClickForView();
        super.onResume();
    }

    private void setupOnClickForView() {

        WidgetTouchListener searchTouchListener = new WidgetTouchListener(mSearchIcon, mDefaultLayout) {
            @Override
            public void doAction() {
                showSearch();
            }
        };
        searchTouchListener.setFadeAfterClick(true);

        mSearchIcon.setOnTouchListener(searchTouchListener);

        WidgetTouchListener cancelSearchTouchListener = new WidgetTouchListener(mCancelSearch, mSearchLayout) {
            @Override
            public void doAction() {
                cancelSearch();
            }
        };
        cancelSearchTouchListener.setFadeAfterClick(true);

        mCancelSearch.setOnTouchListener(cancelSearchTouchListener);
    }

    private void cancelSearch() {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(FADE_ANIMATION_DURATION);
        aa.setInterpolator(new DecelerateInterpolator());
        aa.setFillAfter(true);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                mSearchLayout.setVisibility(GONE);
                mDefaultLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDefaultLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mDefaultLayout.startAnimation(aa);
    }

    private void showSearch() {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(FADE_ANIMATION_DURATION);
        aa.setInterpolator(new DecelerateInterpolator());
        aa.setFillAfter(true);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                mDefaultLayout.setVisibility(GONE);
                mSearchLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                mSearchLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mSearchLayout.startAnimation(aa);
    }



    public void setTitle(String title) {
        mTitle.setText(title);
    }
}
