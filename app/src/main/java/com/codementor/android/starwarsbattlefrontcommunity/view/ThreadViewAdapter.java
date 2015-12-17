package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.thread_item,parent,false);

        ThreadHolder threadHolder = new ThreadHolder(v);

        return threadHolder;
    }

    @Override
    public void onBindViewHolder(ThreadViewAdapter.ThreadHolder holder, int position) {

        final Post post = mPosts.get(position);
        holder.mThreadTitle.setText(post.getTitle());
        holder.mAuthorName.setText(post.getAuthor());
        holder.mDatePosted.setText(post.getDate());
        holder.mPostContent.setText(post.getContent());
        holder.mAuthorPhoto.setImageResource(post.getAuthorPhoto());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ThreadHolder extends RecyclerView.ViewHolder {

        private TextView mThreadTitle;
        private TextView mAuthorName;
        private TextView mDatePosted;
        private TextView mPostContent;
        private ImageView mAuthorPhoto;

        public ThreadHolder(View v) {
            super(v);

            mThreadTitle = (TextView) v.findViewById(R.id.thread_title);
            mAuthorName = (TextView) v.findViewById(R.id.author_name);
            mDatePosted = (TextView) v.findViewById(R.id.post_date);
            mPostContent = (TextView) v.findViewById(R.id.post_content);
            mAuthorPhoto = (ImageView) v.findViewById(R.id.author_photo);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "You clicked " + mThreadTitle.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
