package com.example.tlnt.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tlnt.EditProfile;
import com.example.tlnt.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    TextView full_name, user_name, password, role;
    CircleImageView profile_image;
    ImageView pick_image;
    Button edit_profile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profile_image = view.findViewById(R.id.profile_image);
        pick_image = view.findViewById(R.id.pick_image);
        full_name = view.findViewById(R.id.full_name);
        user_name = view.findViewById(R.id.user_name);
        password = view.findViewById(R.id.password);
        edit_profile = view.findViewById(R.id.edit_profile);
        role = view.findViewById(R.id.role);
        String full_name_str = ParseUser.getCurrentUser().getString("full_name");
        String user_name_str = ParseUser.getCurrentUser().getString("user_name");
        String role_str = ParseUser.getCurrentUser().getString("role");
        String photo = ParseUser.getCurrentUser().getString("photo");
        full_name.setText(full_name_str);
        user_name.setText(user_name_str);
        role.setText(role_str);
        Bitmap bitmap = StringToBitMap(photo);
        profile_image.setImageBitmap(bitmap);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EditProfile.class));
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String full_name_str = ParseUser.getCurrentUser().getString("full_name");
        String user_name_str = ParseUser.getCurrentUser().getString("user_name");
        String role_str = ParseUser.getCurrentUser().getString("role");
        String photo = ParseUser.getCurrentUser().getString("photo");
        full_name.setText(full_name_str);
        user_name.setText(user_name_str);
        role.setText(role_str);
        Bitmap bitmap = StringToBitMap(photo);
        profile_image.setImageBitmap(bitmap);
    }
}