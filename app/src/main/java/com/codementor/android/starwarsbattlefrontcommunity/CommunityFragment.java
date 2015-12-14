package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class CommunityFragment extends Fragment {

    TopicFragment mTopicFragment;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            mTopicFragment = new TopicFragment();
            fragmentTransaction.add(R.id.fragment_topic, mTopicFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_community, container, false);
        return v;
    }

}
