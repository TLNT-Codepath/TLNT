package com.example.tlnt.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tlnt.Post;
import com.example.tlnt.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ComposeFragment extends Fragment {

    public static final String TAG = "ComposeFragment";
    private EditText etTitle;
    private EditText etDescription;
    private EditText etContact;
    private Button btnPost;

    public ComposeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etDescription = view.findViewById(R.id.etDescription);
        etTitle = view.findViewById(R.id.etTitle);
        etContact = view.findViewById(R.id.etContact);
        btnPost = view.findViewById(R.id.btnSave);


        //       queryPosts();
        btnPost.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString();
                String title = etTitle.getText().toString();
                String contact = etContact.getText().toString();
                if (description.isEmpty()){
                    Toast.makeText(getContext(), "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (title.isEmpty()){
                    Toast.makeText(getContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (contact.isEmpty()){
                    Toast.makeText(getContext(), "Contact info cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(description, title, contact, currentUser);


            }
        });

    }
    private void savePost(String description, String title, String contact, ParseUser currentUser) {
        Post post = new Post();
        post.setDescription(description);
        post.setTitle(title);
        post.setContact(contact);
        post.setUser(currentUser);

        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Post save was successful!!");
                etDescription.setText("");
                etTitle.setText("");
                etContact.setText("");
            }
        });
    }

}





