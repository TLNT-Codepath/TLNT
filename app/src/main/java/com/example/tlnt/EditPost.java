package com.example.tlnt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class EditPost extends AppCompatActivity {
    private TextView etTitle;
    private EditText etDescription;
    private EditText etContact;
    private Button btnPost;
    private Button btnDelete;
    String name, description, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etContact = findViewById(R.id.etContact);
        btnPost = findViewById(R.id.btnPost);
        btnDelete = findViewById(R.id.btnDelete);
        etTitle.setText(getIntent().getStringExtra("Title"));
        etDescription.setText(getIntent().getStringExtra("Description"));
        etContact.setText(getIntent().getStringExtra("Contact"));
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etTitle.getText().toString();
                description = etDescription.getText().toString();
                contact = etContact.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    etTitle.setError("Please enter Title");
                } else if (TextUtils.isEmpty(description)) {
                    etDescription.setError("Please enter  Description");
                } else if (TextUtils.isEmpty(contact)) {
                    etContact.setError("Please enter Contact");
                } else {
                    updateData(name, description, contact);
                }
            }
        });

        // Adding on click listener for delete button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etTitle.getText().toString();
                deleteCourse(name);
            }
        });
    }

    private void updateData(String name, String description, String contact) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("title", name);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    String objectID = object.getObjectId().toString();
                    query.getInBackground(objectID, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.put("title", name);
                                object.put("description", description);
                                object.put("contactInfo", contact);
                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(EditPost.this, "Post Updated..", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(EditPost.this, "Fail to update data " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(EditPost.this, "Fail to update course " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Log.d("error", e.getMessage());
                    Toast.makeText(EditPost.this, "Fail to get object ID..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void deleteCourse(String courseName) {

        // Configure Query with our query.
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        // adding a condition where our course name
        // must be equal to the original course name
        query.whereEqualTo("title", courseName);

        // on below line we are finding the course with the course name.
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                // if the error is null.
                if (e == null) {
                    // on below line we are getting the first course and
                    // calling a delete method to delete this course.
                    objects.get(0).deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            // inside done method checking if the error is null or not.
                            if (e == null) {
                                // if the error is not null then we are displaying a toast message and opening our home activity.
                                Toast.makeText(EditPost.this, "Post Deleted..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(EditPost.this, MainActivity.class);
                                startActivity(i);
                            } else {
                                // if we get error we are displaying it in below line.
                                Toast.makeText(EditPost.this, "Fail to delete course..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // if we don't get the data in our database then we are displaying below message.
                    Toast.makeText(EditPost.this, "Fail to get the object..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}