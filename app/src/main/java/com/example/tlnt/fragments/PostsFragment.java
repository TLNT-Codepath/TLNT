package com.example.tlnt.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tlnt.Post;
import com.example.tlnt.PostsAdapter;
import com.example.tlnt.R;
import com.example.tlnt.databinding.FragmentPostsBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragmant";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;
    SwipeRefreshLayout swipeContainer;


    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeContainer = view.findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "fetching new data!");
                queryPosts();
                allPosts.clear();
            }
        });




        queryPosts();

    }

    protected void queryPosts(){
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                swipeContainer.setRefreshing(false);
                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts){
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                if (ParseUser.getCurrentUser().getBoolean("isTalent")) {
                    allPosts.addAll(posts);
                } else {
                    for (int i = 0; i < posts.size(); i++) {
                        Post currentPost = posts.get(i);
                        if (currentPost.getUser().getUsername().equals(ParseUser.getCurrentUser().getUsername())) {
                            allPosts.add(currentPost);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}