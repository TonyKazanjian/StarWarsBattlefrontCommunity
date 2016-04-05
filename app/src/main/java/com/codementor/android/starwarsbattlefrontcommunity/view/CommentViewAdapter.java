package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.image.FullScreenImageActivity;
import com.codementor.android.starwarsbattlefrontcommunity.model.CommentObject;
import com.codementor.android.starwarsbattlefrontcommunity.model.Content;
import com.codementor.android.starwarsbattlefrontcommunity.model.ContentObject;
import com.codementor.android.starwarsbattlefrontcommunity.model.PostObject;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tonyk_000 on 1/8/2016.
 */
public class CommentViewAdapter extends RecyclerView.Adapter<CommentViewAdapter.CommunityContentHolder> {

    private List<CommentObject> mComments;
    private final PostObject mPost;

    private static final int POST_TYPE = 0;
    private static final int COMMENT_TYPE = 1;
    private static final int IMAGE_TYPE = 2;

    public CommentViewAdapter(@NonNull List<CommentObject> comments, PostObject post) {
        mComments = comments;
        mPost = post;
    }

    @Override
    public CommunityContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {//viewType is the differentiator

        if (viewType == POST_TYPE){
            return new PostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_post, parent, false));
        } else if (viewType == IMAGE_TYPE){
            return new ImageCommentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comment, parent, false));
        } else {
            return new CommentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comment,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(CommunityContentHolder holder, int position) {

        if (position == getPostPosition()) {

            PostObject.AuthorEntity author = mPost.getAuthor();
            PostObject.ContentEntity content = mPost.getContent();

            ((PostHolder) holder).mThreadTitle.setText(mPost.getTitle());
            holder.mAuthorName.setText(author.getName());
            Picasso.with(holder.itemView.getContext()).load(author.getProfile_image_url()).into(holder.mAuthorPhoto);
            holder.mDatePosted.setText(mPost.getCreated_at());
            holder.mPostContent.setText(content.getBody());
            ((PostHolder) holder).mCommentCount.setText(String.valueOf(mPost.getComment_count()));
            ((PostHolder) holder).mCommentBubble.setVisibility(View.VISIBLE);
            ((PostHolder) holder).mCommentCount.setVisibility(View.VISIBLE);

            ContentObject.ContentEntity.Image[] images = content.getImage_urls();

            if (images.length != 0){
                ImageView attachedImage = ((PostHolder) holder).mAttachedImage;
                attachedImage.setVisibility(View.VISIBLE);
                for (ContentObject.ContentEntity.Image image : images) {
                    String imageUrl = image.getImage_url();
                    Picasso.with(holder.itemView.getContext()).load(imageUrl)
                            .into(attachedImage);
                }
            }

//            final Bitmap localBitmap = mPost.getContentImageFromFileSystem(holder.itemView.getContext().getContentResolver());
//            if (localBitmap != null) {
//                final ImageView attachPostImage = ((PostHolder) holder).mAttachedImage;
//                attachPostImage.setVisibility(View.VISIBLE);
//
//                final int width = attachPostImage.getWidth();
//                final int height = attachPostImage.getHeight();
//
//                final Context context = holder.itemView.getContext();
//
//                BitmapDecoderAsyncTask.BitmapDecoderListener decoderListener = new BitmapDecoderAsyncTask.BitmapDecoderListener() {
//                    @Override
//                    public void onBitmapDecoded(Bitmap bitmap) {
//                        Picasso.with(context).load(mPost.getContentImageUri()).fit().config(Bitmap.Config.RGB_565).centerCrop()
//                                .placeholder(R.drawable.bb8).into(attachPostImage);
//                    }
//                };
//
//                decoderListener.onBitmapDecoded(localBitmap);
//                BitmapDecoderAsyncTask bitmapDecoderAsyncTask = new BitmapDecoderAsyncTask(localBitmap, width, height, decoderListener);
//                bitmapDecoderAsyncTask.execute();
//                attachPostImage.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        fullScreenIntent(v, PictureUtils.getImageUri(context,localBitmap));
//                    }
//                });
//            }

        } else if (position > getPostPosition()){

            final CommentObject comment = mComments.get(position - 1);
            CommentObject.AuthorEntity author = comment.getAuthor();
            CommentObject.ContentEntity content = comment.getContent();

            holder.mAuthorName.setText(author.getName());
            holder.mDatePosted.setText(comment.getCreated_at());
            holder.mPostContent.setText(content.getBody());
            Picasso.with(holder.itemView.getContext()).load(author.getProfile_image_url()).into(holder.mAuthorPhoto);

            List<CommentObject.ContentEntity.Image> images = content.getImages();

            if (!images.isEmpty()){
                ImageView attachedImage = ((PostHolder) holder).mAttachedImage;
                attachedImage.setVisibility(View.VISIBLE);
                for (int i = 0; i < images.size(); i++){
                    String imageUrl = images.get(i).getImage_url();
                    Picasso.with(holder.itemView.getContext()).load(imageUrl)
                            .into(attachedImage);
                }
            }

//            final Bitmap localBitmap = comment.getContentImageFromFileSystem(holder.itemView.getContext().getContentResolver());
//            if(localBitmap != null) {
//                final ImageView attachCommentImage = ((ImageCommentHolder)holder).mAttachedImage;
//                attachCommentImage.setVisibility(View.VISIBLE);
//
//                final int width = attachCommentImage.getWidth();
//                final int height = attachCommentImage.getHeight();
//
//                final Context context = holder.itemView.getContext();
//
//                BitmapDecoderAsyncTask.BitmapDecoderListener decoderListener = new BitmapDecoderAsyncTask.BitmapDecoderListener() {
//                    @Override
//                    public void onBitmapDecoded(Bitmap bitmap) {
//                        Picasso.with(context).load(comment.getContentImageUri()).fit().config(Bitmap.Config.RGB_565).centerCrop()
//                                .placeholder(R.drawable.bb8).into(attachCommentImage);
//                    }
//                };
//
//                decoderListener.onBitmapDecoded(localBitmap);
//                BitmapDecoderAsyncTask bitmapDecoderAsyncTask = new BitmapDecoderAsyncTask(localBitmap, width, height, decoderListener);
//                bitmapDecoderAsyncTask.execute();
//
//                attachCommentImage.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        fullScreenIntent(v, PictureUtils.getImageUri(context,localBitmap));
//                    }
//                });
//            }
        }
    }

    public void fullScreenIntent(View v, Uri photoUri){
        Context context = v.getContext();
        Bundle b = new Bundle();
        b.putParcelable(Content.FULLSCREEN_IMAGE_EXTRA, photoUri);
        Intent intent = new Intent(context, FullScreenImageActivity.class);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mPost.getComments().size()+1;
    }

    @Override
    public int getItemViewType(int position){ //this is called by onCreateViewHolder
            if (position == 0){
                return POST_TYPE;
//            } else if (mComments.get(position-1).getContentImageUri()!= null){
//                return IMAGE_TYPE;
            } else {
                return COMMENT_TYPE;
            }
    }

//    public void addComment(Comment comment) {
//        if(comment != null && mComments != null) {
//            mComments.add(comment);
//            notifyItemInserted(mComments.size() - 1);
//        }
//    }

    public class ImageCommentHolder extends CommunityContentHolder{

        public ImageView mAttachedImage;

        public ImageCommentHolder(View itemView) {
            super(itemView);

            mAttachedImage = (ImageView)itemView.findViewById(R.id.attached_image);
        }
    }

    public class CommentHolder extends CommunityContentHolder{

        public CommentHolder(View itemView) {
            super(itemView);
        }
    }

    public class PostHolder extends CommunityContentHolder {

        //for PostHolder
        public TextView mThreadTitle;
        public ImageView mCommentBubble;
        public TextView mCommentCount;
        public ImageView mAttachedImage;

        public PostHolder(View v) {
            super(v);
            v.setBackgroundColor(Color.WHITE);

            mThreadTitle = (TextView) v.findViewById(R.id.thread_title);
            mCommentBubble = (ImageView) v.findViewById(R.id.comment_bubble);
            mCommentCount = (TextView) v.findViewById(R.id.comment_count);
            mAttachedImage = (ImageView)v.findViewById(R.id.attached_image);
        }
    }

    public class CommunityContentHolder extends RecyclerView.ViewHolder{

        private TextView mAuthorName;
        private TextView mDatePosted;
        private TextView mPostContent;
        private CircleImageView mAuthorPhoto;

        public CommunityContentHolder(View itemView) {
            super(itemView);

            mAuthorName = (TextView) itemView.findViewById(R.id.author_name);
            mDatePosted = (TextView) itemView.findViewById(R.id.post_date);
            mPostContent = (TextView) itemView.findViewById(R.id.post_content);
            mAuthorPhoto = (CircleImageView) itemView.findViewById(R.id.author_photo);
        }
    }

    private int getPostPosition(){
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
