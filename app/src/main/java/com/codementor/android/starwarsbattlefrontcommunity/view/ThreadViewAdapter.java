package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.MainActivity;
import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class ThreadViewAdapter extends RecyclerView.Adapter<ThreadViewAdapter.ThreadHolder> {

    private List<Post> mPosts;
    private Post mPost;

    public ThreadViewAdapter(@NonNull List<Post> posts){
        mPosts = posts;
    }

    public ThreadViewAdapter(Post post){
        mPost = post;
    }


    @Override
    public ThreadViewAdapter.ThreadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_general, parent, false);

        ThreadHolder threadHolder = new ThreadHolder(v);

        return threadHolder;
    }

    @Override
    public void onBindViewHolder(ThreadViewAdapter.ThreadHolder holder, final int position) {

        final Post post = mPosts.get(position);
        holder.mThreadTitle.setText(post.getTitle());
        holder.mAuthorName.setText(post.getAuthor());
        holder.mDatePosted.setText(post.getDate());
        holder.mPostContent.setText(post.getContent());
        holder.mAuthorPhoto.setImageResource(post.getAuthorPhoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = MainActivity.newIntent(context, mPosts.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public Post getItem(){
        return mPost;
    }

    public class ThreadHolder extends RecyclerView.ViewHolder {

        private TextView mThreadTitle;
        private TextView mAuthorName;
        private TextView mDatePosted;
        private TextView mPostContent;
        private CircleImageView mAuthorPhoto;

        public ThreadHolder(View v) {
            super(v);

            mThreadTitle = (TextView) v.findViewById(R.id.thread_title);
            mAuthorName = (TextView) v.findViewById(R.id.author_name);
            mDatePosted = (TextView) v.findViewById(R.id.post_date);
            mPostContent = (TextView) v.findViewById(R.id.post_content);
            mAuthorPhoto = (CircleImageView) v.findViewById(R.id.author_photo);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
