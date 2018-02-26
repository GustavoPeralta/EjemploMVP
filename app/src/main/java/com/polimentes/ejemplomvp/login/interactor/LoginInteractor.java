package com.polimentes.ejemplomvp.login.interactor;

import android.os.AsyncTask;

import com.polimentes.ejemplomvp.login.LoginInterfaces;

/**
 * Created by Grupo BECM 8 on 23/02/2018.
 */

public class LoginInteractor implements LoginInterfaces.ILoginInteractor {

    LoginInterfaces.ILoginListener listener;

    public LoginInteractor(LoginInterfaces.ILoginListener listener) {
        this.listener = listener;
    }

    @Override
    public void performLogin(String email, String password) {
        String data[] = new String[2];
        data[0] = email;
        data[1] = password;
        new LoginTask().execute(data);
    }

    private class LoginTask extends AsyncTask<String,Void,Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            String email = strings[0];
            String password = strings[1];
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return email.equals("roberto@polimentes.com")&&password.equals("Temporal123.");
        }

        @Override
        protected void onPostExecute(Boolean correct) {
            if(correct){
                listener.onLoginCorrect();
            }
            else {
                listener.onLoginError("Email y contraseña incorrectas");
            }
        }
    }
}
