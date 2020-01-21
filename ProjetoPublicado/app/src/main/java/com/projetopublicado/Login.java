package com.projetopublicado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText emaillogin_edittext;
    EditText passwordlogin_edittext;
    FirebaseAuth mAuth;
    String email = "";
    String senha = "";
    ConstraintLayout telalogin_constraint;
    LoginButton loginButton;
    CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emaillogin_edittext = findViewById(R.id.emaillogin_edittext);
        passwordlogin_edittext = findViewById(R.id.passwordlogin_edittext);
        telalogin_constraint = findViewById(R.id.telalogin_constraint);
        loginButton = findViewById(R.id.login_button);

        mCallbackManager  = CallbackManager.Factory.create();


        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getBaseContext(), EscolhaTime.class);
                startActivity(intent);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();


            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void Registrar(View view) {
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
    }

    public void Login(View view) {
        final Intent intent = new Intent(this, EscolhaTime.class);
        if (emaillogin_edittext.getText().toString().equals("")&& emaillogin_edittext.getText().toString().isEmpty()){
            Snackbar.make(telalogin_constraint,"E-mail inválid", Snackbar.LENGTH_LONG).show();

        } else if (passwordlogin_edittext.getText().toString().equals("") && passwordlogin_edittext.getText().toString().isEmpty()){
            Snackbar.make(telalogin_constraint,"Password inválid", Snackbar.LENGTH_LONG).show();

        } else{
            email = String.valueOf(emaillogin_edittext.getText());
            senha = String.valueOf(passwordlogin_edittext.getText());
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    startActivity(intent);
                } else {
                    Snackbar.make(telalogin_constraint,"Unregistered user", Snackbar.LENGTH_LONG).setAction("Clear", view1 -> {
                        emaillogin_edittext.setText("");
                        passwordlogin_edittext.setText("");
                    }).show();

                }
            });
        }










    }
}
