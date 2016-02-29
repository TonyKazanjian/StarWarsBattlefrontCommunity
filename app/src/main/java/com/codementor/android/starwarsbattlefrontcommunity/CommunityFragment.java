package com.codementor.android.starwarsbattlefrontcommunity;

import android.app.Activity;
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
import java.util.Date;
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

    private TopicFragment droidRunFragment;
    private TopicFragment heroHuntFragment;
    private TopicFragment walkerAssaultFragment;

    private TopicPagerAdapter mTopicPagerAdapter;

    private int mTopicPage;

    public static final int REQUEST_CODE_POST = 0;

    public static final String EXTRA_TOPIC_PAGE_POSITION = "topic page";
    public static final String EXTRA_CONTENT_TYPE_POST = "post";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check to see if savedInstanceState exists, and if it does, then restore state

        if (savedInstanceState != null){
            mTopicPage = (int) savedInstanceState.get(EXTRA_TOPIC_PAGE_POSITION);
        }
    }

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
        mTopicPagerAdapter = new TopicPagerAdapter(getActivity().getSupportFragmentManager());

        droidRunFragment = TopicFragment.newInstance(droidRun);
        heroHuntFragment = TopicFragment.newInstance(heroHunt);
        walkerAssaultFragment = TopicFragment.newInstance(walkerAssault);

        mTopicPagerAdapter.addFragment(droidRunFragment, droidRun.getTopicTitle(),
                droidRun.getBackgroundImage());
        mTopicPagerAdapter.addFragment(heroHuntFragment, heroHunt.getTopicTitle(),
                heroHunt.getBackgroundImage());
        mTopicPagerAdapter.addFragment(walkerAssaultFragment, walkerAssault.getTopicTitle(),
                walkerAssault.getBackgroundImage());

        mViewPager.setAdapter(mTopicPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        mBackgroundImage = (ImageView) view.findViewById(R.id.htab_header);
        mBackgroundImage.setImageResource(mTopicPagerAdapter.getBackgroundImage(0));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mBackgroundImage.setImageResource(mTopicPagerAdapter.getBackgroundImage(position));
                mTopicPage = mViewPager.getCurrentItem();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContentAdd();
            }
        });

        return view;
    }

    public void onContentAdd(){
        Bundle b = new Bundle();
        Intent i = new Intent(getActivity(), NewContentActivity.class);
        b.putInt(EXTRA_TOPIC_PAGE_POSITION, mTopicPage);
        b.putBoolean(EXTRA_CONTENT_TYPE_POST, true);
        i.putExtras(b);
        startActivityForResult(i, REQUEST_CODE_POST);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save custom state information to outState
        outState.putInt(EXTRA_TOPIC_PAGE_POSITION, mTopicPage);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_CODE_POST){
            if (data == null){
                return;
            }

            Bundle extras = data.getExtras();
            if(extras == null) {
                return;
            }

            Post newPost = extras.getParcelable(Post.EXTRA_NEW_POST);
            mTopicPage = extras.getInt(EXTRA_TOPIC_PAGE_POSITION);
            mViewPager.setCurrentItem(mTopicPage);
            TopicFragment updatedFragment = (TopicFragment)mTopicPagerAdapter.getItem(mTopicPage);

            newPost.setAuthorPhoto(R.drawable.bb8);
            newPost.setAuthor("AndroidPadawan");
            updatedFragment.addPostToList(newPost);
        }
    }

    public Post populateDroidRun(){

        List<Comment> comments = new ArrayList<>();

        Post post = new Post(String.valueOf(R.id.thread_title),String.valueOf(R.id.author_name), new Date(),
                String.valueOf(R.id.post_content), R.id.author_photo, null, comments);

        post.setAuthorPhoto(R.drawable.stormtrooper);
        post.setAuthor(getString(R.string.droidhunt_author_name));
        post.setContent(getString(R.string.droidhunt_top_post_content));
        post.setTitle(getString(R.string.droidhunt_thread_title));
        post.setDate(post.getDate());

        comments.add(new Comment(String.valueOf(R.id.author_name),new Date(), String.valueOf(R.id.post_content), R.id.author_photo, null));
        comments.get(0).setAuthor(getString(R.string.BB8_name));
        comments.get(0).setAuthorPhoto(R.drawable.bb8);
        comments.get(0).setContent(getString(R.string.BB8_comment));
        comments.get(0).setDate(comments.get(0).getDate());

        post.setComments(comments);

        return post;
    }

    public Post populateHeroHunt(){

        List<Comment> comments = new ArrayList<>();

        Post post = new Post(String.valueOf(R.id.thread_title),String.valueOf(R.id.author_name), new Date(),
                String.valueOf(R.id.post_content), R.id.author_photo, null, comments);

        post.setAuthorPhoto(R.drawable.vader);
        post.setAuthor(getString(R.string.herohunt_author_name));
        post.setContent(getString(R.string.herohunt_top_post_content));
        post.setTitle(getString(R.string.herohunt_thread_title));
        post.setDate(post.getDate());

        comments.add(new Comment(String.valueOf(R.id.author_name), new Date(), String.valueOf(R.id.post_content), R.id.author_photo, null));
        comments.get(0).setAuthor(getString(R.string.kyloren_name));
        comments.get(0).setAuthorPhoto(R.drawable.kyloren);
        comments.get(0).setContent(getString(R.string.kyloren_comment));
        comments.get(0).setDate(comments.get(0).getDate());

        comments.add(new Comment(String.valueOf(R.id.author_name), new Date(), String.valueOf(R.id.post_content), R.id.author_photo, null));
        comments.get(1).setAuthor(getString(R.string.rey_name));
        comments.get(1).setAuthorPhoto(R.drawable.rey);
        comments.get(1).setContent(getString(R.string.rey_comment));
        comments.get(1).setDate(comments.get(0).getDate());

        post.setComments(comments);

        return post;
    }

    public Post populateWalkerAssault(){

        List<Comment> comments = new ArrayList<>();

        Post post = new Post(String.valueOf(R.id.thread_title),String.valueOf(R.id.author_name), new Date(),
                String.valueOf(R.id.post_content), R.id.author_photo, null, comments);

        post.setAuthorPhoto(R.drawable.hansolo);
        post.setAuthor(getString(R.string.walkerassault_author_name));
        post.setContent(getString(R.string.walkerassault_top_post_content));
        post.setTitle(getString(R.string.walkerassault_thread_title));
        post.setDate(post.getDate());

        comments.add(new Comment(String.valueOf(R.id.author_name), new Date(), String.valueOf(R.id.post_content), R.id.author_photo, null));
        comments.get(0).setAuthor(getString(R.string.chewbacca_name));
        comments.get(0).setAuthorPhoto(R.drawable.choobs);
        comments.get(0).setContent(getString(R.string.chewbacca_comment));
        comments.get(0).setDate(comments.get(0).getDate());

        comments.add(new Comment(String.valueOf(R.id.author_name), new Date(), String.valueOf(R.id.post_content), R.id.author_photo, null));
        comments.get(1).setAuthor(getString(R.string.leia_name));
        comments.get(1).setAuthorPhoto(R.drawable.leia);
        comments.get(1).setContent(getString(R.string.leia_comment));
        comments.get(1).setDate(comments.get(0).getDate());

        comments.add(new Comment(String.valueOf(R.id.author_name), new Date(), String.valueOf(R.id.post_content), R.id.author_photo, null));
        comments.get(2).setAuthor(getString(R.string.finn_name));
        comments.get(2).setAuthorPhoto(R.drawable.finn);
        comments.get(2).setContent(getString(R.string.finn_comment));
        comments.get(2).setDate(comments.get(0).getDate());

        post.setComments(comments);

        return post;
    }

//    private Date getDateFormat(Date date){
//        mDateFormat = new DateFormat();
//    }

}

