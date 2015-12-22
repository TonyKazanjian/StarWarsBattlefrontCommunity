package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codementor.android.starwarsbattlefrontcommunity.view.TopicPagerAdapter;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class CommunityFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mBackgroundImage;
    private Toolbar mToolbar;
    private int[] mImages = new int[]{R.drawable.herohunt,R.drawable.droidrun,R.drawable.walkerassault};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        //needed an image to start with
        mBackgroundImage = (ImageView)view.findViewById(R.id.htab_header);
        mBackgroundImage.setImageResource(R.drawable.herohunt);

        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mToolbar.setVisibility(View.INVISIBLE);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        TopicPagerAdapter topicPagerAdapter = new TopicPagerAdapter(getActivity().getSupportFragmentManager());
        topicPagerAdapter.addFragment(new TopicFragmentHeroHunt(), "Hero Hunt");
        topicPagerAdapter.addFragment(new TopicFragmentDroidRun(),"Droid Run");
        topicPagerAdapter.addFragment(new TopicFragmentWalkerAssault(), "Walker Assault");
        mViewPager.setAdapter(topicPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBackgroundImage.setImageResource(mImages[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        
        return view;
    }


}
