package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.model.Topic;
import com.codementor.android.starwarsbattlefrontcommunity.view.PostViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 12/18/2015.
 */
public class TopicFragment extends Fragment {

    private static final String ARGS_TOPIC = "topic";

    private RecyclerView mRecyclerView;
    public static PostViewAdapter mViewAdapter;

    private List<Post> mPosts;

    private Topic mTopic;

    public static TopicFragment newInstance(Topic topic){
        Bundle args = new Bundle();
        args.putParcelable(ARGS_TOPIC, topic);

        TopicFragment topicFragment = new TopicFragment();
        topicFragment.setArguments(args);
        return topicFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTopic = getArguments().getParcelable(ARGS_TOPIC);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_topic,container,false);

        List<Post> posts = populateTopic();

        if (savedInstanceState == null) {
            mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_thread_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            mViewAdapter = new PostViewAdapter(posts);

            mRecyclerView.setAdapter(mViewAdapter);
        }

        return v;
    }


    public List<Post> populateTopic(){

        mPosts = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            Post post = mTopic.getPost();
            mPosts.add(post);
//            mPost = mPosts.get(i); -- not sure why this returns out of bounds
        }
        return mPosts;
    }

}

