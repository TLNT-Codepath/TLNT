package com.example.tlnt;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tlnt.fragments.ComposeFragment;
import com.example.tlnt.fragments.PostsFragment;
import com.example.tlnt.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.parceler.Parcels;

public class EditPostActivity extends AppCompatActivity {

    public static final String TAG = "EditPostActivity";
    private Button btnSave;
    private Button btnDelete;
    private EditText etEditPostTitle;
    private EditText etEditPostDescription;
    private EditText etEditPostContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        etEditPostTitle = findViewById(R.id.etEditPostTitle);
        etEditPostDescription = findViewById(R.id.etEditPostDescription);
        etEditPostContact = findViewById(R.id.etEditPostContact);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
                query.getInBackground(getIntent().getStringExtra("postId"), new GetCallback<Post>() {
                    public void done(Post post, ParseException e) {
                        if (e == null) {
                           post.setTitle(etEditPostTitle.getText().toString());
                           post.setDescription(etEditPostDescription.getText().toString());
                           post.setContact(etEditPostContact.getText().toString());
                           post.saveInBackground();
                        } else {
                            // something went wrong
                            Log.e(TAG, e.toString());
                        }
                        finish();
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
                query.getInBackground(getIntent().getStringExtra("postId"), new GetCallback<Post>() {
                    public void done(Post post, ParseException e) {
                        if (e == null) {
                            post.deleteInBackground();
                        } else {
                            // something went wrong
                            Log.e(TAG, e.toString());
                        }
                        finish();
                    }
                });
            }
        });

        etEditPostTitle.setText(getIntent().getStringExtra("title"));
        etEditPostDescription.setText(getIntent().getStringExtra("description"));
        etEditPostContact.setText(getIntent().getStringExtra("contact"));

    }
}