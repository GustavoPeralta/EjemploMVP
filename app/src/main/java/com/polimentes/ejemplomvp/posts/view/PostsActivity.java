package com.polimentes.ejemplomvp.posts.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.polimentes.ejemplomvp.R;
import com.polimentes.ejemplomvp.model.Post;
import com.polimentes.ejemplomvp.posts.PostsInterfaces;
import com.polimentes.ejemplomvp.posts.presenter.PostAdapter;
import com.polimentes.ejemplomvp.posts.presenter.PostsPresenter;

import java.util.List;

/**
 * Created by Grupo BECM 8 on 26/02/2018.
 */

public class PostsActivity extends AppCompatActivity implements PostsInterfaces.IPostsView, View.OnClickListener {
    private ProgressBar progress;
    private TextView txtNoNetwork;
    private TextView txtNoItems;
    private RecyclerView items;

    private PostsInterfaces.IPostsPresentes presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_posts);

        progress = findViewById(R.id.progress);
        txtNoItems = findViewById(R.id.txtNoItems);
        txtNoNetwork = findViewById(R.id.txtNoNetwork);
        items = findViewById(R.id.items);

        txtNoItems.setOnClickListener(this);
        txtNoNetwork.setOnClickListener(this);

        presenter = new PostsPresenter(this,this);
        presenter.requestPost();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        txtNoNetwork.setVisibility(View.GONE);
        txtNoItems.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyState() {
        txtNoItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorState() {
        txtNoNetwork.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPosts(List<Post> posts) {
        PostAdapter adapter = new PostAdapter(this,posts);
        items.setLayoutManager(new LinearLayoutManager(this));
        items.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        presenter.requestPost();
    }
}
