package com.dev.tanphamanh.chiasenhac.actitvity.model;


import com.dev.tanphamanh.chiasenhac.base.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by tanphamanhh on 26/02/2017.
 */

public class MainContentQueue {
    private ArrayList<BaseFragment> scrQueue;

    public MainContentQueue() {
        scrQueue = new ArrayList<>();
    }

    public void addScr(BaseFragment scr) {
        scrQueue.add(0, scr);
    }

    public BaseFragment getPreviousScr() {
        return scrQueue.size() > 0 ? scrQueue.get(1) : null;
    }


    public BaseFragment getCurrentScr() {
        return scrQueue.size() != 0 ? scrQueue.get(0) : null;
    }

    public void removeCurrScr() {
        if (scrQueue.size() != 0) {
            scrQueue.remove(0);
        }
    }

    public boolean isEmpty() {
        return (scrQueue.size() == 0);
    }
}
