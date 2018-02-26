package com.polimentes.ejemplomvp.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.polimentes.ejemplomvp.R;
import com.polimentes.ejemplomvp.login.LoginInterfaces;
import com.polimentes.ejemplomvp.login.presenter.LoginPresenter;
import com.polimentes.ejemplomvp.posts.view.PostsActivity;

/**
 * Created by Grupo BECM 8 on 23/02/2018.
 */

public class LoginActivity extends AppCompatActivity implements LoginInterfaces.ILoginVIew {

    private EditText email;
    private EditText password;
    private Button btnLogin;
    private ProgressBar progress;

    private LoginInterfaces.ILoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        progress = findViewById(R.id.progress);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestLogin(email.getText().toString(),password.getText().toString());
            }
        });

        presenter = new LoginPresenter(this);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setError(String error) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToNext() {
        startActivity(new Intent(this, PostsActivity.class));
    }
}
