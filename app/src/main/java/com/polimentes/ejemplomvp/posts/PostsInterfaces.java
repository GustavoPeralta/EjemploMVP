package com.polimentes.ejemplomvp.posts;

import com.polimentes.ejemplomvp.model.Post;

import java.util.List;

/**
 * Created by Grupo BECM 8 on 23/02/2018.
 */

public interface PostsInterfaces {

    interface IPostsView {
        void showProgress();
        void hideProgress();
        void showEmptyState();
        void showErrorState();
        void setPosts(List<Post> posts);
    }

    interface IPostsPresentes {
        void requestPost();
    }

    interface IPostsInteractor {
        void getPosts();
    }

    interface IPostsListener {
        void onPostsReady(List<Post> posts);
        void onNoItems();
        void onInternetError();
    }

}
