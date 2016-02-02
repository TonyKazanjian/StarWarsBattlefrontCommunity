package com.codementor.android.starwarsbattlefrontcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codementor.android.starwarsbattlefrontcommunity.model.Comment;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.model.Topic;
import com.codementor.android.starwarsbattlefrontcommunity.view.TopicPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class CommunityFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mBackgroundImage;
    private Toolbar mToolbar;

    private Topic droidRun;
    private Topic heroHunt;
    private Topic walkerAssault;

    private static final int REQUEST_CODE_POST = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        droidRun = new Topic("Droid Run", R.drawable.droidrun);
        heroHunt = new Topic("Hero Hunt", R.drawable.herohunt);
        walkerAssault = new Topic("Walker Assault", R.drawable.walkerassault);

        droidRun.setPost(populateDroidRun());
        heroHunt.setPost(populateHeroHunt());
        walkerAssault.setPost(populateWalkerAssault());

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setVisibility(View.INVISIBLE);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        final TopicPagerAdapter topicPagerAdapter = new TopicPagerAdapter(getActivity().getSupportFragmentManager());
        topicPagerAdapter.addFragment(TopicFragment.newInstance(droidRun), droidRun.getTopicTitle(),
                droidRun.getBackgroundImage());
        topicPagerAdapter.addFragment(TopicFragment.newInstance(heroHunt), heroHunt.getTopicTitle(),
                heroHunt.getBackgroundImage());
        topicPagerAdapter.addFragment(TopicFragment.newInstance(walkerAssault),walkerAssault.getTopicTitle(),
                walkerAssault.getBackgroundImage());

        mViewPager.setAdapter(topicPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        mBackgroundImage = (ImageView) view.findViewById(R.id.htab_header);
        mBackgroundImage.setImageResource(topicPagerAdapter.getBackgroundImage(0));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mBackgroundImage.setImageResource(topicPagerAdapter.getBackgroundImage(position));

                //you could have the adapter get the topic model at the position
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Adds new post", Toast.LENGTH_SHORT).show();
                Intent i = NewContentActivity.newIntent(getActivity(), new Post(null, null, 0, null, 0, null));
                startActivityForResult(i, REQUEST_CODE_POST);
            }
        });

        return view;
    }

    //TODO: add onActivityResult

    public Post populateDroidRun(){

        List<Comment> comments = new ArrayList<>();

        Post post = new Post(String.valueOf(R.id.thread_title),String.valueOf(R.id.author_name),
                R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo, comments);

        post.setAuthorPhoto(R.drawable.stormtrooper);
        post.setAuthor(String.valueOf(R.string.droidhunt_author_name));
        post.setContent(String.valueOf(R.string.droidhunt_top_post_content));
        post.setTitle(String.valueOf(R.string.droidhunt_thread_title));
        post.setDate(R.string.placeholder_date);

        comments.add(new Comment(String.valueOf(R.id.author_name), R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo));
        comments.get(0).setAuthor(String.valueOf(R.string.BB8_name));
        comments.get(0).setAuthorPhoto(R.drawable.bb8);
        comments.get(0).setContent(String.valueOf(R.string.BB8_comment));
        comments.get(0).setDate(R.string.placeholder_date);

        post.setComments(comments);

        return post;
    }

    public Post populateHeroHunt(){

        List<Comment> comments = new ArrayList<>();

        Post post = new Post(String.valueOf(R.id.thread_title),String.valueOf(R.id.author_name),
                R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo, comments);

        post.setAuthorPhoto(R.drawable.vader);
        post.setAuthor(String.valueOf(R.string.herohunt_author_name));
        post.setContent(String.valueOf(R.string.herohunt_top_post_content));
        post.setTitle(String.valueOf(R.string.herohunt_thread_title));
        post.setDate(R.string.placeholder_date);

        comments.add(new Comment(String.valueOf(R.id.author_name), R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo));
        comments.get(0).setAuthor(String.valueOf(R.string.kyloren_name));
        comments.get(0).setAuthorPhoto(R.drawable.kyloren);
        comments.get(0).setContent(String.valueOf(R.string.kyloren_comment));
        comments.get(0).setDate(R.string.placeholder_date);

        comments.add(new Comment(String.valueOf(R.id.author_name), R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo));
        comments.get(1).setAuthor(String.valueOf(R.string.rey_name));
        comments.get(1).setAuthorPhoto(R.drawable.rey);
        comments.get(1).setContent(String.valueOf(R.string.rey_comment));
        comments.get(1).setDate(R.string.placeholder_date);

        post.setComments(comments);

        return post;
    }

    public Post populateWalkerAssault(){

        List<Comment> comments = new ArrayList<>();

        Post post = new Post(String.valueOf(R.id.thread_title),String.valueOf(R.id.author_name),
                R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo, comments);

        post.setAuthorPhoto(R.drawable.hansolo);
        post.setAuthor(String.valueOf(R.string.walkerassault_author_name));
        post.setContent(String.valueOf(R.string.walkerassault_top_post_content));
        post.setTitle(String.valueOf(R.string.walkerassault_thread_title));
        post.setDate(R.string.placeholder_date);

        comments.add(new Comment(String.valueOf(R.id.author_name), R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo));
        comments.get(0).setAuthor(String.valueOf(R.string.chewbacca_name));
        comments.get(0).setAuthorPhoto(R.drawable.choobs);
        comments.get(0).setContent(String.valueOf(R.string.chewbacca_comment));
        comments.get(0).setDate(R.string.placeholder_date);

        comments.add(new Comment(String.valueOf(R.id.author_name), R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo));
        comments.get(1).setAuthor(String.valueOf(R.string.leia_name));
        comments.get(1).setAuthorPhoto(R.drawable.leia);
        comments.get(1).setContent(String.valueOf(R.string.leia_comment));
        comments.get(1).setDate(R.string.placeholder_date);

        comments.add(new Comment(String.valueOf(R.id.author_name), R.id.post_date, String.valueOf(R.id.post_content), R.id.author_photo));
        comments.get(2).setAuthor(String.valueOf(R.string.finn_name));
        comments.get(2).setAuthorPhoto(R.drawable.finn);
        comments.get(2).setContent(String.valueOf(R.string.finn_comment));
        comments.get(2).setDate(R.string.placeholder_date);

        post.setComments(comments);

        return post;
    }

}

