package com.example.tlnt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MyPosts extends AppCompatActivity {
    public static final String TAG = "PostsFragmant";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;
    SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
        rvPosts = findViewById(R.id.rvPosts);

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(MyPosts.this, allPosts);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(MyPosts.this));
        swipeContainer = findViewById(R.id.swipeContainer);

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
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                ParseUser currentUser = ParseUser.getCurrentUser();

                for (int i = 0; i < posts.size(); i++) {
                    Log.d(TAG, posts.get(i).getUser().getUsername() + "" + currentUser.getUsername());
                    if (posts.get(i).getUser().getUsername().equals(currentUser.getUsername())) {
                        Post post = new Post();
                        post.setUser(currentUser);
                        post.setContact(posts.get(i).getContact());
                        post.setTitle(posts.get(i).getTitle());
                        post.setDescription(posts.get(i).getDescription());
                        allPosts.add(post);
                        adapter.notifyDataSetChanged();
                    }
                }
                swipeContainer.setRefreshing(false);
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }

            }
        });
    }

}