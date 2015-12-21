package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.view.TopicPagerAdapter;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class CommunityFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_community,container,false);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        TopicPagerAdapter topicPagerAdapter = new TopicPagerAdapter(getActivity().getSupportFragmentManager());
        topicPagerAdapter.addFragment(new TopicFragmentHeroHunt(), "Hero Hunt");
        topicPagerAdapter.addFragment(new TopicFragmentSupremacy(),"Supremacy");
        topicPagerAdapter.addFragment(new TopicFragmentWalkerAssault(), "Walker Assault");
        mViewPager.setAdapter(topicPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }


}
