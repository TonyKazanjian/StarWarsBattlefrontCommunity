package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.util.List;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class ThreadViewAdapter extends RecyclerView.Adapter<ThreadViewAdapter.ThreadHolder> {

    private List<Post> mPosts;

    public ThreadViewAdapter(List<Post> posts){
        mPosts = posts;
    }


    @Override
    public ThreadViewAdapter.ThreadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThreadHolder(new PostThread(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ThreadViewAdapter.ThreadHolder holder, int position) {

        holder.mPostThread.createThreads();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ThreadHolder extends RecyclerView.ViewHolder{

        public PostThread mPostThread;

        public ThreadHolder(PostThread postThread) {
            super(postThread);

            mPostThread = postThread;
        }
    }
}
