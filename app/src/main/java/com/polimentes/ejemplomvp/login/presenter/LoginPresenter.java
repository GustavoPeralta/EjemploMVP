package com.polimentes.ejemplomvp.login.presenter;

import com.polimentes.ejemplomvp.login.LoginInterfaces;
import com.polimentes.ejemplomvp.login.interactor.LoginInteractor;

/**
 * Created by Grupo BECM 8 on 23/02/2018.
 */

public class LoginPresenter implements LoginInterfaces.ILoginPresenter, LoginInterfaces.ILoginListener {

    private LoginInterfaces.ILoginVIew view;
    private LoginInterfaces.ILoginInteractor interactor;

    public LoginPresenter(LoginInterfaces.ILoginVIew view) {
        this.view = view;
        interactor = new LoginInteractor(this);
    }

    @Override
    public void requestLogin(String email, String password) {
        view.showProgress();
        interactor.performLogin(email, password);
    }

    @Override
    public void onLoginCorrect() {
        view.hideProgress();
        view.goToNext();
    }

    @Override
    public void onLoginError(String error) {
        view.hideProgress();
        view.setError(error);
    }
}
