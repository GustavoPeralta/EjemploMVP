package com.polimentes.ejemplomvp.posts.presenter;

import android.app.Activity;

import com.polimentes.ejemplomvp.model.Post;
import com.polimentes.ejemplomvp.posts.PostsInterfaces;
import com.polimentes.ejemplomvp.posts.interactor.PostsInteactor;

import java.util.List;

/**
 * Created by Grupo BECM 8 on 26/02/2018.
 */

public class PostsPresenter implements PostsInterfaces.IPostsPresentes, PostsInterfaces.IPostsListener {

    private PostsInterfaces.IPostsView view;
    private PostsInterfaces.IPostsInteractor interactor;

    public PostsPresenter(PostsInterfaces.IPostsView view, Activity activity) {
        this.view = view;
        interactor = new PostsInteactor(this,activity);
    }

    @Override
    public void requestPost() {
        view.showProgress();
        interactor.getPosts();
    }

    @Override
    public void onPostsReady(List<Post> posts) {
        view.hideProgress();
        view.setPosts(posts);
    }

    @Override
    public void onNoItems() {
        view.showEmptyState();
        view.hideProgress();
    }

    @Override
    public void onInternetError() {
        view.hideProgress();
        view.showErrorState();
    }
}
