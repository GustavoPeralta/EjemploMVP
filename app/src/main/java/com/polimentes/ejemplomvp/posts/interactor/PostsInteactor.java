package com.polimentes.ejemplomvp.posts.interactor;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.polimentes.ejemplomvp.model.Post;
import com.polimentes.ejemplomvp.posts.PostsInterfaces;
import com.polimentes.ejemplomvp.request.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Grupo BECM 8 on 26/02/2018.
 */

public class PostsInteactor implements PostsInterfaces.IPostsInteractor {
    private PostsInterfaces.IPostsListener listener;
    private Activity activity;

    public PostsInteactor(PostsInterfaces.IPostsListener listener, Activity activity) {
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    public void getPosts() {
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, "http://jsonplaceholder.typicode.com/posts", (String) null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type listType =  new TypeToken<List<Post>>() {
                }.getType();
                List<Post> posts = new Gson().fromJson(response.toString(),listType);
                listener.onPostsReady(posts);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onInternetError();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
               5000,
                2,
                4));
        stringRequest.setTag("GetPosts");
        RequestSingleton.getInstance(activity).getRequestQueue().add(stringRequest);
    }
}
