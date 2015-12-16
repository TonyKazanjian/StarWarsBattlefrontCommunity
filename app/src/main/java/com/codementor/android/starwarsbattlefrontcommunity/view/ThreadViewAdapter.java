package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.util.List;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class ThreadViewAdapter extends RecyclerView.Adapter<ThreadViewAdapter.ThreadHolder> {

    private List<Post> mPosts;

    public ThreadViewAdapter(@NonNull List<Post> posts){
        mPosts = posts;
    }


    @Override
    public ThreadViewAdapter.ThreadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThreadHolder(new PostThreadView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ThreadViewAdapter.ThreadHolder holder, int position) {

        final Post post = mPosts.get(position);
        holder.mPostThreadView.populateView(post);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ThreadHolder extends RecyclerView.ViewHolder {

        PostThreadView mPostThreadView;

        public ThreadHolder(PostThreadView postThreadView) {
            super(postThreadView);
            mPostThreadView = postThreadView;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
