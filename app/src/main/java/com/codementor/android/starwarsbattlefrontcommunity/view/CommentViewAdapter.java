package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.Comment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tonyk_000 on 1/8/2016.
 */
public class CommentViewAdapter extends RecyclerView.Adapter<CommentViewAdapter.CommentHolder> {

    private List<Comment> mComments;

    public CommentViewAdapter(@NonNull List<Comment> comments) {
        mComments = comments;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_general, parent, false);

        CommentHolder commentHolder = new CommentHolder(v);

        return commentHolder;
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        final Comment comment = mComments.get(position);
        holder.mAuthorName.setText(comment.getAuthor());
        holder.mDatePosted.setText(comment.getDate());
        holder.mPostContent.setText(comment.getContent());
        holder.mAuthorPhoto.setImageResource(comment.getAuthorPhoto());
        holder.mCommentBubble.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder{

        private TextView mAuthorName;
        private TextView mDatePosted;
        private TextView mPostContent;
        private CircleImageView mAuthorPhoto;
        private ImageView mCommentBubble;

        public CommentHolder(View itemView) {
            super(itemView);

            mAuthorName = (TextView) itemView.findViewById(R.id.author_name);
            mDatePosted = (TextView) itemView.findViewById(R.id.post_date);
            mPostContent = (TextView) itemView.findViewById(R.id.post_content);
            mAuthorPhoto = (CircleImageView) itemView.findViewById(R.id.author_photo);
            mCommentBubble = (ImageView) itemView.findViewById(R.id.comment_bubble);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
