package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.Comment;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tonyk_000 on 1/8/2016.
 */
public class CommentViewAdapter extends RecyclerView.Adapter<CommentViewAdapter.CommunityContentHolder> {

    private List<Comment> mComments;
    private Post mPost;

    private static final int POST_TYPE = 0;
    private static final int COMMENT_TYPE = 1;

    public CommentViewAdapter(@NonNull List<Comment> comments, Post post) {
        mComments = comments;
        mPost = post;
    }

    @Override
    public CommunityContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {//viewType is the differentiator

        if (viewType == POST_TYPE){
            return new PostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false));
        } else {
            return new CommentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_general, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(CommunityContentHolder holder, int position) {

        if (position == getPostPosition()){

//            final Post post = mPosts.get(position);

            ((PostHolder)holder).mThreadTitle.setText(mPost.getTitle());
            holder.mAuthorName.setText(mPost.getAuthor());
            holder.mAuthorPhoto.setImageResource(mPost.getAuthorPhoto());
            holder.mPostContent.setText(mPost.getContent());
            ((PostHolder)holder).mCommentBubble.setImageResource(R.drawable.chat_bubble);
            ((PostHolder)holder).mCommentCount.setText(Integer.toString(mPost.getComments().size()));

        } else if (position > getPostPosition()){

            final Comment comment = mComments.get(position-1);
            holder.mAuthorName.setText(comment.getAuthor());
            holder.mDatePosted.setText(comment.getDate());
            holder.mPostContent.setText(comment.getContent());
            holder.mAuthorPhoto.setImageResource(comment.getAuthorPhoto());
        }
    }

    @Override
    public int getItemCount() {
        return mPost.getComments().size()+1;
    }

    @Override
    public int getItemViewType(int position){ //this is called by onCreateViewHolder
            if (position == 0){
                return POST_TYPE;
            } else {
                return COMMENT_TYPE;
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

        Post mPost;

        public PostHolder(View v) {
            super(v);
            v.setBackgroundColor(Color.WHITE);

            mThreadTitle = (TextView) v.findViewById(R.id.thread_title);
            mCommentBubble = (ImageView) v.findViewById(R.id.comment_bubble);
            mCommentCount = (TextView) v.findViewById(R.id.comment_count);

            mCommentBubble.setVisibility(View.VISIBLE);
            mCommentCount.setVisibility(View.VISIBLE);
        }

        public void bindPostHolder(Post post){
            mPost = post;
            mThreadTitle.setText(mPost.getTitle());
            mCommentBubble.setImageResource(R.drawable.chat_bubble);
            mCommentCount.setText(Integer.toString(mPost.getComments().size()));
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
