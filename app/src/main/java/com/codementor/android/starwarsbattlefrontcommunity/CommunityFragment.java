package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.codementor.android.starwarsbattlefrontcommunity.view.TopicPagerAdapter;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class CommunityFragment extends FragmentActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_community);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        TopicPagerAdapter topicPagerAdapter = new TopicPagerAdapter(getSupportFragmentManager());
        topicPagerAdapter.addFragment(new TopicFragmentHeroHunt(), "Hero Hunt");
        topicPagerAdapter.addFragment(new TopicFragmentSupremacy(),"Supremacy");
        topicPagerAdapter.addFragment(new TopicFragmentWalkerAssault(),"Walker Assault");
        mViewPager.setAdapter(topicPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
