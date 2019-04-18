package com.mcbu.patates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button toSignInBut,toSignUpBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toSignInBut = findViewById(R.id.toSignInButton);
        toSignUpBut = findViewById(R.id.toSignUpPageButton);

        toSignUpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainToSignUp = new Intent(MainActivity.this,SingUpActivity.class);
                startActivity(mainToSignUp);
            }
        });
        toSignInBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainToSignIn = new Intent(MainActivity.this,SingInActivity.class);
                startActivity(mainToSignIn);
                MainActivity.this.finish();
            }
        });

    }
}
