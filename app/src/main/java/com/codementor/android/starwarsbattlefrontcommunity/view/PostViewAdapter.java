package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.DiscussionActivity;
import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.ContentObject;
import com.codementor.android.starwarsbattlefrontcommunity.model.PostObject;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.PostHolder> {

    private List<PostObject> mPosts;

    private static final int IMAGE_TYPE = 0;
    private static final int NO_IMAGE_TYPE = 1;

    public PostViewAdapter(@NonNull List<PostObject> posts){
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

        final PostObject post = mPosts.get(position);
        PostObject.AuthorEntity author = post.getAuthor();
        PostObject.ContentEntity content = post.getContent();
        holder.mThreadTitle.setText(post.getTitle());
        holder.mAuthorName.setText(author.getName());
        holder.mDatePosted.setText(post.getCreated_at());
        holder.mPostContent.setText(content.getBody());
        holder.mCommentCount.setText(String.valueOf(post.getComment_count()));

        Picasso.with(holder.itemView.getContext()).load(author.getProfile_image_url()).into(holder.mAuthorPhoto);

        ContentObject.ContentEntity.Image[] images = content.getImage_urls();

        if (images.length != 0){
            ImageView attachedImage = ((ImagePostHolder) holder).mAttachedImage;
            attachedImage.setVisibility(View.VISIBLE);
            for (ContentObject.ContentEntity.Image image : images) {
                String imageUrl = image.getImage_url();
                Picasso.with(holder.itemView.getContext()).load(imageUrl)
                        .into(attachedImage);
            }
        }

        //get bitmap
//            final Bitmap localBitmap = post.getContentImageFromFileSystem(holder.itemView.getContext().getContentResolver());

//            if (localBitmap != null) {
//                final ImageView attachedImage = ((ImagePostHolder) holder).mAttachedImage;
//                attachedImage.setVisibility(View.VISIBLE);
//                final int width = attachedImage.getWidth();
//                final int height = attachedImage.getHeight();
//
//                final Context context = holder.itemView.getContext();
//
//                BitmapDecoderAsyncTask.BitmapDecoderListener decoderListener = new BitmapDecoderAsyncTask.BitmapDecoderListener() {
//                    @Override
//                    public void onBitmapDecoded(Bitmap bitmap) {
//                        Picasso.with(context).load(post.getContentImageUri()).fit().config(Bitmap.Config.RGB_565).centerCrop()
//                                .placeholder(R.drawable.bb8).into(attachedImage);
//                    }
//                };
//
//                decoderListener.onBitmapDecoded(localBitmap);
//                BitmapDecoderAsyncTask bitmapDecoderAsyncTask = new BitmapDecoderAsyncTask(localBitmap, width, height, decoderListener);
//                bitmapDecoderAsyncTask.execute();
//            }
//
//            holder.mCommentCount.setText(Integer.toString(mPosts.get(position).getComments().size()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
//                Intent intent = MainActivity.discussionIntent(context, mPosts.get(position));
//                context.startActivity(intent);
                Intent discussionIntent =  new Intent(context, DiscussionActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("post",mPosts.get(position));
                discussionIntent.putExtras(b);
                context.startActivity(discussionIntent);
            }
        });
    }

//    public Uri getScaledBitmapUri(Context context, Bitmap bitmap, int width, int height){
//
//        bitmap = PictureUtils.decodeBitmapFromFile(String.valueOf(mPosts.get(0).getContentImageUri()),width,height);
//
//        return PictureUtils.getImageUri(context, bitmap);
//    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

//    public void addPost(Post post) {
//        if(post != null && mPosts != null) {
//            mPosts.add(0, post);
//            notifyItemInserted(0);
//        }
//    }

//    @Override
//    public int getItemViewType(int position){ //this is called by onCreateViewHolder
//        if (mPosts.get(position).getContentImageUri() == null){
//            return NO_IMAGE_TYPE;
//        } else {
//            return IMAGE_TYPE;
//        }
//    }

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

    public boolean isImageType(){
        if (this.getItemViewType(0) == IMAGE_TYPE){
            return true;
        }
        return false;
    }

}