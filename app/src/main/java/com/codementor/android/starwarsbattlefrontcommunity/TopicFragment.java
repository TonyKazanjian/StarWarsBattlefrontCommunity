package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.view.PostThread;
import com.codementor.android.starwarsbattlefrontcommunity.view.ThreadViewAdapter;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class TopicFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ThreadViewAdapter mViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_topic,container,false);

        PostThread postThread = new PostThread(getParentFragment().getActivity());

        if (savedInstanceState == null) {
            mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_thread_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            mViewAdapter = new ThreadViewAdapter(postThread.createThreads());
            mRecyclerView.setAdapter(mViewAdapter);
        }
        return v;
    }
}
