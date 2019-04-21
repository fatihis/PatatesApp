package com.mcbu.patates;

import android.content.Intent;
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

public class SingInActivity extends AppCompatActivity {
    EditText userEmailSignText,userPassSignText;
    Button signPageButton;
    FirebaseAuth mAuthSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
    userEmailSignText = findViewById(R.id.emailSignIn);
    userPassSignText = findViewById(R.id.passwordSignIn);
    signPageButton = findViewById(R.id.buttonSignIn);

    signPageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (userEmailSignText.getText().toString().equals("") || userPassSignText.getText().toString().equals("")) {
                Toast.makeText(SingInActivity.this, "You have to fill the blanks", Toast.LENGTH_SHORT).show();
            } else{
                mAuthSign = FirebaseAuth.getInstance();
            final String emailSign = userEmailSignText.getText().toString();
            final String passSign = userPassSignText.getText().toString();
            mAuthSign.signInWithEmailAndPassword(emailSign, passSign).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SingInActivity.this, "Sign in completed", Toast.LENGTH_SHORT).show();
                        Intent toMainPage = new Intent(SingInActivity.this, MainPageActivity.class);
                        startActivity(toMainPage);
                        SingInActivity.this.finish();
                    } else {
                        Toast.makeText(SingInActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        }
    });

    }
}
