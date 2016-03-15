package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.utils.PictureUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.PostHolder> {

    private List<Post> mPosts;

    private static final int IMAGE_TYPE = 0;
    private static final int NO_IMAGE_TYPE = 1;

    public PostViewAdapter(@NonNull List<Post> posts){
        mPosts = posts;
    }

    @Override
    public PostViewAdapter.PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_post, parent, false);
        if (viewType == IMAGE_TYPE){
            return new ImagePostHolder(v);
        } else {
            return new PostHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(final PostViewAdapter.PostHolder holder, final int position) {

            final Post post = mPosts.get(position);
            holder.mThreadTitle.setText(post.getTitle());
            holder.mAuthorName.setText(post.getAuthor());
            holder.mDatePosted.setText(DateFormat.format("EEE, MMM dd, h:mm a", post.getDate()));
            holder.mPostContent.setText(post.getContent());
            holder.mAuthorPhoto.setImageResource(post.getAuthorPhoto());
            //get bitmap
            final Bitmap bitmap = post.getContentImageFromFileSystem(holder.itemView.getContext().getContentResolver());

            if (bitmap != null) {
                ((ImagePostHolder) holder).mAttachedImage.setVisibility(View.VISIBLE);

                Context context = holder.itemView.getContext();

                Picasso.with(context).load(PictureUtils.getImageUri(context, bitmap))
                        .resize(200, 200).centerCrop().onlyScaleDown()
                        .into(((ImagePostHolder) holder).mAttachedImage);
            }

            holder.mCommentCount.setText(Integer.toString(mPosts.get(position).getComments().size()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = MainActivity.discussionIntent(context, mPosts.get(position));
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
        }
    }

    @Override
    public int getItemViewType(int position){ //this is called by onCreateViewHolder
        if (mPosts.get(position).getContentImageUri() == null){
            return NO_IMAGE_TYPE;
        } else {
            return IMAGE_TYPE;
        }
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private TextView mThreadTitle;
        private TextView mAuthorName;
        private TextView mDatePosted;
        private TextView mPostContent;
        private CircleImageView mAuthorPhoto;
        private ImageView mCommentBubble;
        private TextView mCommentCount;

        public PostHolder(View v) {
            super(v);

            mThreadTitle = (TextView) v.findViewById(R.id.thread_title);
            mAuthorName = (TextView) v.findViewById(R.id.author_name);
            mDatePosted = (TextView) v.findViewById(R.id.post_date);
            mPostContent = (TextView) v.findViewById(R.id.post_content);
            mAuthorPhoto = (CircleImageView) v.findViewById(R.id.author_photo);
            mCommentBubble = (ImageView) v.findViewById(R.id.comment_bubble);
            mCommentCount = (TextView) v.findViewById(R.id.comment_count);

            mCommentBubble.setVisibility(View.VISIBLE);
            mCommentCount.setVisibility(View.VISIBLE);
        }
    }

    public class ImagePostHolder extends PostHolder {

        private ImageView mAttachedImage;

        public ImagePostHolder(View v) {
            super(v);

            mAttachedImage = (ImageView) v.findViewById(R.id.attached_image);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}