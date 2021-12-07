package com.example.tlnt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvDescription;
        private Button btnEdit;
        private Button btnApply;
        private TextView tvPostedBy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnApply = itemView.findViewById(R.id.btnApply);
            tvPostedBy = itemView.findViewById(R.id.tvPostedBy);
        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvTitle.setText(post.getTitle());
            tvPostedBy.setText("Posted by " + post.getUser().get("full_name") + " on " + post.getCreatedAt());
            // if this post does not belong to the user
            if (!post.getUser().getUsername().equals(ParseUser.getCurrentUser().getUsername())) {
                btnEdit.setVisibility(View.GONE);
            }
            if (!ParseUser.getCurrentUser().getBoolean("isTalent")) {
                btnApply.setVisibility(View.GONE);
            }
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, EditPostActivity.class);
                    i.putExtra("title", post.getTitle());
                    i.putExtra("description", post.getDescription());
                    i.putExtra("contact", post.getContact());
                    i.putExtra("postId", post.getObjectId());
                    context.startActivity(i);
                }
            });
            btnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:" + post.getContact() + "?subject=" + Uri.encode("Applying for: " + post.getTitle()));
                    intent.setData(data);
                    context.startActivity(intent);
                }
            });
        }

    }
}
