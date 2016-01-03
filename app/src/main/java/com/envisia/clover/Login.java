package com.envisia.clover;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etUsername,etPassword;
    TextView tvRegisterLink;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        tvRegisterLink = (TextView)findViewById(R.id.tvRegisterLink);

        bLogin = (Button) findViewById(R.id.bLogin);

        tvRegisterLink.setOnClickListener(this);

        bLogin.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                User u = new User(username, password);

                authenticate(u);

                userLocalStore.storeUserData(u);
                userLocalStore.setUserLoggedIn(true);
                break;

            case R.id.tvRegisterLink:

                startActivity(new Intent(this,Register.class));
                break;
        }
    }

    private void authenticate(User u){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground (u, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null){
                    showErrorMessege();
                }else{
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessege(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Invalid user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser){
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));
    }
}


