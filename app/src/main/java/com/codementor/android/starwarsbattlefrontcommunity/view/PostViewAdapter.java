package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.MainActivity;
import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.image.FullScreenImageActivity;
import com.codementor.android.starwarsbattlefrontcommunity.model.Content;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.PostHolder> {

    private List<Post> mPosts;

    public PostViewAdapter(@NonNull List<Post> posts){
        mPosts = posts;
    }

    @Override
    public PostViewAdapter.PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_post, parent, false);

        PostHolder postHolder = new PostHolder(v);

        return postHolder;
    }

    @Override
    public void onBindViewHolder(PostViewAdapter.PostHolder holder, final int position) {

        final Post post = mPosts.get(position);
        holder.mThreadTitle.setText(post.getTitle());
        holder.mAuthorName.setText(post.getAuthor());
        holder.mDatePosted.setText(DateFormat.format("EEE, MMM dd, h:mm a", post.getDate()));
        holder.mPostContent.setText(post.getContent());
        holder.mAuthorPhoto.setImageResource(post.getAuthorPhoto());
        //get bitmap
        final Bitmap bitmap = post.getContentImageFromFileSystem(holder.itemView.getContext().getContentResolver());
        if(bitmap != null) {
            holder.mAttachedImage.setImageBitmap(bitmap);
            holder.mAttachedImage.setVisibility(View.VISIBLE);

            holder.mAttachedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, FullScreenImageActivity.class);
                    Bundle b = new Bundle();
                    b.putParcelable(Content.FULLSCREEN_IMAGE_EXTRA, bitmap);
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }
        holder.mCommentCount.setText(Integer.toString(mPosts.get(position).getComments().size()));

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

    public void addPost(Post post) {
        if(post != null && mPosts != null) {
            mPosts.add(0, post);
            notifyItemInserted(0);
            notifyDataSetChanged();
        }
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private TextView mThreadTitle;
        private TextView mAuthorName;
        private TextView mDatePosted;
        private TextView mPostContent;
        private CircleImageView mAuthorPhoto;
        private ImageView mAttachedImage;
        private ImageView mCommentBubble;
        private TextView mCommentCount;

        public PostHolder(View v) {
            super(v);

            mThreadTitle = (TextView) v.findViewById(R.id.thread_title);
            mAuthorName = (TextView) v.findViewById(R.id.author_name);
            mDatePosted = (TextView) v.findViewById(R.id.post_date);
            mPostContent = (TextView) v.findViewById(R.id.post_content);
            mAuthorPhoto = (CircleImageView) v.findViewById(R.id.author_photo);
            mAttachedImage = (ImageView) v.findViewById(R.id.attached_image);
            mCommentBubble = (ImageView) v.findViewById(R.id.comment_bubble);
            mCommentCount = (TextView) v.findViewById(R.id.comment_count);

            mCommentBubble.setVisibility(View.VISIBLE);
            mCommentCount.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}