package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.view.PostThreadView;
import com.codementor.android.starwarsbattlefrontcommunity.view.ThreadViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class TopicFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ThreadViewAdapter mViewAdapter;

    private PostThreadView mPostThreadView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_topic,container,false);

        mPostThreadView = new PostThreadView(getParentFragment().getActivity());

        List<Post> posts = populateTopic();

        if (savedInstanceState == null) {
            mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_thread_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            mViewAdapter = new ThreadViewAdapter(posts);
            mRecyclerView.setAdapter(mViewAdapter);
        }
        return v;
    }

    public List<Post> populateTopic(){

        List<Post> mPosts = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            Post post = mPostThreadView.createPost();
            mPosts.add(post);
//            mPost = mPosts.get(i); -- not sure why this returns out of bounds
        }
        return mPosts;
    }
}
