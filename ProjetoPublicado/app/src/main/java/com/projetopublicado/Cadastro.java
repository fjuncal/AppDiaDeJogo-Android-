package com.projetopublicado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {
    EditText cadastroname_edittext;
    ConstraintLayout telacadastro_constraint;
    EditText cadastroemail_edittext;
    EditText cadastropassword_edittext;
    FirebaseAuth mAuth;
    String email = "";
    String senha = "";
    EditText confirmpassword_edittext;
    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastroemail_edittext = findViewById(R.id.cadastroemail_edittext);
        cadastropassword_edittext = findViewById(R.id.cadastropassword_edittext);
        confirmpassword_edittext = findViewById(R.id.confirmpassword_edittext);
        telacadastro_constraint = findViewById(R.id.telacadastro_constraint);
        cadastroname_edittext = findViewById(R.id.cadastroname_edittext);


        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



    }

    public void btnCadastro(View view) {
        final Intent intent = new Intent(this, Login.class);
        email = String.valueOf(cadastroemail_edittext.getText());
        senha = String.valueOf(cadastropassword_edittext.getText());

        if (cadastroname_edittext.getText().toString().equals("")&& cadastroname_edittext.getText().toString().isEmpty()){
            Snackbar.make(telacadastro_constraint,"Name inválid", Snackbar.LENGTH_LONG).show();

        } else if (cadastroemail_edittext.getText().toString().equals("")&& cadastroemail_edittext.getText().toString().isEmpty()){
            Snackbar.make(telacadastro_constraint,"E-mail inválid", Snackbar.LENGTH_LONG).show();
        }else if (cadastropassword_edittext.getText().toString().equals("")&& cadastropassword_edittext.getText().toString().isEmpty()){
            Snackbar.make(telacadastro_constraint,"Password inválid", Snackbar.LENGTH_LONG).show();
        } else if (confirmpassword_edittext.getText().toString().equals("")&& confirmpassword_edittext.getText().toString().isEmpty()){
            Snackbar.make(telacadastro_constraint,"Password inválid", Snackbar.LENGTH_LONG).show();
        } else if (cadastropassword_edittext.getText().toString().compareTo(confirmpassword_edittext.getText().toString()) != 0){
            Snackbar.make(telacadastro_constraint,"Different password", Snackbar.LENGTH_LONG).setAction("Clear", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cadastropassword_edittext.setText("");
                    confirmpassword_edittext.setText("");
                }
            }).show();

        } else {
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();

                        Map<String, Object> us = new HashMap<>();
                        us.put("email", email);
                        us.put("senha", senha);

                        DocumentReference doc = db.collection("users").document(user.getUid());
                        doc.set(us).addOnSuccessListener(aVoid ->{
                            startActivity(intent);
                                })
                                .addOnFailureListener(e ->{
                            //informar falha.
                                    Snackbar.make(telacadastro_constraint, task.getException().getMessage(), Snackbar.LENGTH_LONG).show();

                                });
                        Snackbar.make(telacadastro_constraint,"User was created", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(telacadastro_constraint, task.getException().getMessage(), Snackbar.LENGTH_LONG).show();

                    }
                }
            });
        }


    }}
