package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class CommunityFragment extends Fragment {

    private TopicFragment mTopicFragment;
    private RecyclerView mRecyclerView;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_community, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.rv_thread_view);

        mTopicFragment = new TopicFragment();

            FragmentTransaction fragmentTransaction = this.getChildFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.community_host, mTopicFragment);
            fragmentTransaction.commit();

        return v;
    }

}
