package com.polimentes.ejemplomvp.login;

/**
 * Created by Grupo BECM 8 on 23/02/2018.
 */

public interface LoginInterfaces {

    interface ILoginVIew {
        void showProgress();
        void hideProgress();
        void setError(String error);
        void goToNext();
    }

    interface ILoginPresenter {
        void requestLogin(String email, String password);
    }

    interface ILoginInteractor {
        void performLogin(String email, String password);
    }

    interface ILoginListener {
        void onLoginCorrect();
        void onLoginError(String error);
    }

}
