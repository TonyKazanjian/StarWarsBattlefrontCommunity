package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 12/18/2015.
 */
public class TopicPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mTopicFragmentList = new ArrayList<>();
    private final List<String> mTopicFragmentTitleList = new ArrayList<>();
    private final List<String> mBackgroundImageUrlList = new ArrayList<>();

    public TopicPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mTopicFragmentList.get(position);
    }

    public String getBackgroundImage(int position){
        return mBackgroundImageUrlList.get(position);
    }

    @Override
    public int getCount() {
        return mTopicFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title, String imageUrl) {
        mTopicFragmentList.add(fragment);
        mTopicFragmentTitleList.add(title);
        mBackgroundImageUrlList.add(imageUrl);
    }

    @Override
         public CharSequence getPageTitle(int position) {
        return mTopicFragmentTitleList.get(position);
    }
}
