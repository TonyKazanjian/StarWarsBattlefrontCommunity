package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.R;
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
        return new ThreadHolder(new PostThread(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ThreadViewAdapter.ThreadHolder holder, int position) {
        holder.mThreadTitle.setText(mPosts.get(position).getTitle());
        holder.mAuthorName.setText(mPosts.get(position).getAuthor());
        holder.mDatePosted.setText(mPosts.get(position).getDate());
        holder.mPostContent.setText(mPosts.get(position).getContent());
        holder.mAuthorPhoto.setImageResource(mPosts.get(position).getAuthorPhoto());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ThreadHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;

        private TextView mThreadTitle;
        private TextView mAuthorName;
        private TextView mDatePosted;
        private TextView mPostContent;
        private ImageView mAuthorPhoto;

        public ThreadHolder(PostThread postThread) {
            super(postThread);

            mCardView = (CardView)postThread.findViewById(R.id.cv_thread);
            mThreadTitle = (TextView) postThread.findViewById(R.id.thread_title);
            mAuthorName = (TextView) postThread.findViewById(R.id.author_name);
            mDatePosted = (TextView) postThread.findViewById(R.id.post_date);
            mPostContent = (TextView) postThread.findViewById(R.id.post_content);
            mAuthorPhoto = (ImageView) postThread.findViewById(R.id.author_photo);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
