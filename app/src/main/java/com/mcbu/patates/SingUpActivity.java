package com.mcbu.patates;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText userEmail,userPass;
    Button toSignFireButton;
// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        mAuth = FirebaseAuth.getInstance();
        userEmail = findViewById(R.id.userEmailText);
        userPass = findViewById(R.id.userPasswordText);
        toSignFireButton = findViewById(R.id.singUpToFire);

        toSignFireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString();
                final String password = userPass.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SingUpActivity.this,"Islem Basarılı",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SingUpActivity.this,"Islem basarısız",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




    }
}
