package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.model.PostResponse;
import com.codementor.android.starwarsbattlefrontcommunity.model.Topic;
import com.codementor.android.starwarsbattlefrontcommunity.view.PostViewAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tonyk_000 on 12/18/2015.
 */
public class TopicFragment extends Fragment {

    private static final String TAG = "TopicFragment";

    private static final String ARGS_TOPIC = "topic";

    private RecyclerView mRecyclerView;
    private PostViewAdapter mViewAdapter;

    private Topic mTopic;

    public static TopicFragment newInstance(Topic topic){
        Bundle args = new Bundle();
        args.putParcelable(ARGS_TOPIC, topic);

        TopicFragment topicFragment = new TopicFragment();
        topicFragment.setArguments(args);
        return topicFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTopic = getArguments().getParcelable(ARGS_TOPIC);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_topic, container, false);

        populateTopic();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_thread_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return v;
    }

    public void populateTopic(){

        BattlefrontClient client = APIServiceGenerator.createService(BattlefrontClient.class);
        Call<PostResponse> call = client.getPosts(mTopic.getId());
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful()) {

                    mViewAdapter = new PostViewAdapter(response.body().getPosts());
                }
                mRecyclerView.setAdapter(mViewAdapter);
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });

    }

    public void addPostToList(Post post){
        mViewAdapter.addPost(post);
    }


}

