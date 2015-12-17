package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.view.ThreadViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class TopicFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ThreadViewAdapter mViewAdapter;

    private List<Post> mPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_topic,container,false);

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

        mPosts = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            Post post = createPost();
            mPosts.add(post);
//            mPost = mPosts.get(i); -- not sure why this returns out of bounds
        }
        return mPosts;
    }

    public Post createPost(){

        Post post = new Post(R.id.thread_title,R.id.author_name,
                R.id.post_date, R.id.post_content, R.id.author_photo);

        post.setTitle(R.string.placeholder_thread_title);
        post.setAuthor(R.string.placeholder_author_name);
        post.setDate(R.string.placeholder_date);
        post.setContent(R.string.placeholder_top_post_content);
        post.setAuthorPhoto(R.mipmap.ic_launcher);

        return post;
    }
}
