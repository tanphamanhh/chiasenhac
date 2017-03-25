package com.dev.tanphamanh.chiasenhac.actitvity.screen;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dev.tanphamanh.chiasenhac.R;
import com.dev.tanphamanh.chiasenhac.actitvity.model.MainContentQueue;
import com.dev.tanphamanh.chiasenhac.bottombar.screen.BottomBarScr;
import com.dev.tanphamanh.chiasenhac.home.screen.HomeMenuScr;
import com.dev.tanphamanh.chiasenhac.topbar.screen.TopBarScr;

import static com.dev.tanphamanh.chiasenhac.BuildConfig.DEBUG;

public class MainActivity extends AppCompatActivity implements BottomBarScr.BottomBarListener,
        HomeMenuScr.HomeMenuScrListener {

    private final String TAG = this.getClass().getSimpleName();

    private BottomBarScr mBottomBarScr;
    private TopBarScr mTopBarScr;
    private MainContentQueue[] mMainContentQueues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEBUG) {
            Log.d(TAG, "onCreate: ");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeBottomBar();
        makeTopBar();

        createTabQueue();
    }

    private void createTabQueue() {
        mMainContentQueues = new MainContentQueue[4];
        for (int i = 0; i < mMainContentQueues.length; i++) {
            mMainContentQueues[i] = new MainContentQueue();
        }

        HomeMenuScr homeMenuScr = new HomeMenuScr();
        mMainContentQueues[0].addScr(homeMenuScr);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.crrContentMain, mMainContentQueues[0].getCurrentScr());
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.commit();
    }

    /**
     * make bottom bar
     */
    private void makeBottomBar() {
        if (DEBUG) {
            Log.d(TAG, "makeBottomBar: ");
        }
        mBottomBarScr = new BottomBarScr();
        mBottomBarScr.setmBottomBarListener(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.bottomBarMain, mBottomBarScr);
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.commit();
    }

    private void makeTopBar() {
        if (DEBUG) {
            Log.d(TAG, "makeTopBar: ");
        }
        mTopBarScr = new TopBarScr();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.topBarMain, mTopBarScr);
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.commit();
    }

    @Override
    public void onTabChange(int oldTabId, int newTabId) {
        if (DEBUG) {
            Log.d(TAG, "onTabChange() called with: oldTabId = [" + oldTabId + "], newTabId = [" + newTabId + "]");
        }
    }

    @Override
    public void onTapCurrentTab(int tabId) {
        if (DEBUG) {
            Log.d(TAG, "onTapCurrentTab() called with: tabId = [" + tabId + "]");
        }

    }


    @Override
    public void setTitle(String title) {
        mTopBarScr.setTitle(title);
    }
}
