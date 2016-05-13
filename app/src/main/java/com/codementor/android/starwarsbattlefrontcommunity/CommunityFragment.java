package com.codementor.android.starwarsbattlefrontcommunity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.model.Topic;
import com.codementor.android.starwarsbattlefrontcommunity.view.TopicPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class CommunityFragment extends Fragment {

    private static final String TAG = "CommunityFragment";

    private Post mNewPost;

    private Topic mTopic;
    private TabLayout mTabLayout;
    private TopicFragment mTopicFragment;

    private ViewPager mViewPager;
    private ImageView mBackgroundImage;

    private TopicPagerAdapter mTopicPagerAdapter;

    private int mTopicPage;
    private List<String> mTopicTitles = new ArrayList<>();

    private String mImageUrl;

    public static final int REQUEST_CODE_POST = 0;

    public static final String EXTRA_TOPIC_PAGE_POSITION = "topic page";
    public static final String EXTRA_TOPIC_LIST = "topics";
    public static final String EXTRA_TOPIC_ID = "topic";
    public static final String EXTRA_CONTENT_TYPE_POST = "post";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_community, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.INVISIBLE);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTopicPagerAdapter = new TopicPagerAdapter(getActivity().getSupportFragmentManager());

        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);

        mBackgroundImage = (ImageView) view.findViewById(R.id.htab_header);

        //creates a REST adaper which points to the Battlefront API endpoint
        BattlefrontClient client = APIServiceGenerator.createService(BattlefrontClient.class);
        getTopicsCallback(client);

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
        b.putStringArrayList(EXTRA_TOPIC_LIST, (ArrayList<String>) mTopicTitles);
        b.putBoolean(EXTRA_CONTENT_TYPE_POST, true);
        i.putExtras(b);
        startActivityForResult(i, REQUEST_CODE_POST);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mTopicPage = mViewPager.getCurrentItem();
        // save custom state information to outState
        outState.putInt(EXTRA_TOPIC_PAGE_POSITION, mTopicPage);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mTopicPage = (int) savedInstanceState.get(EXTRA_TOPIC_PAGE_POSITION);
            mViewPager.setCurrentItem(mTopicPage);
        }
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

            mNewPost = extras.getParcelable(Post.EXTRA_NEW_POST);
//            mTopicPage = extras.getInt(EXTRA_TOPIC_PAGE_POSITION);
        }
    }

    public void getTopicsCallback(BattlefrontClient client){
        Call<List<Topic>> call = client.getTopics();

        call.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, final Response<List<Topic>> response) {
                if (response.isSuccessful()) {
                    Picasso.with(getActivity()).load(response.body().get(0).getImage_url()).into(mBackgroundImage);
                    for (int i = 0; i < response.body().size(); i++) {
                        mTopic = response.body().get(i);
                        mTopicFragment = TopicFragment.newInstance(mTopic);
                        mTopicPagerAdapter.addFragment(mTopicFragment, mTopic.getTitle(), mTopic.getImage_url());
                        mImageUrl = mTopicPagerAdapter.getBackgroundImage(i);

                        //needed to pass titles to NewContentActivity's spinner
                        mTopicTitles.add(response.body().get(i).getTitle());

                        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                mBackgroundImage.setImageURI(Uri.parse(mImageUrl));
                                Picasso.with(getActivity()).load(mTopicPagerAdapter.getBackgroundImage(position)).into(mBackgroundImage);
                                mTopicPage = mViewPager.getCurrentItem();
                                Log.i(TAG, "new page was selected");
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                        mTopicPagerAdapter.notifyDataSetChanged();
                    }
                }

                mViewPager.setAdapter(mTopicPagerAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
                mViewPager.setCurrentItem(mTopicPage);

                if (mNewPost != null) {
                    TopicFragment updatedFragment = (TopicFragment)mTopicPagerAdapter.getItem(mTopicPage);
                    updatedFragment.addPostToList(mNewPost);
                }
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }
}