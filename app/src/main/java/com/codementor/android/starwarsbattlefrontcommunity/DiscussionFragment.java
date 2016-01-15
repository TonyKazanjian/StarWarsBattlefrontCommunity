package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.view.CommentViewAdapter;

/**
 * Created by tonyk_000 on 1/14/2016.
 */
public class DiscussionFragment extends Fragment {

    private Post post;

    private RecyclerView mCommentView;
    private CommentViewAdapter mCommentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        post = getActivity().getIntent().getParcelableExtra(DiscussionActivity.EXTRA_POST);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_discussion, container, false);

        mCommentView = (RecyclerView) v.findViewById(R.id.rv_comment_view);
        mCommentView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mCommentList = new CommentViewAdapter(post.getComments(), post);
        mCommentView.setAdapter(mCommentList);

        return v;
    }
}
