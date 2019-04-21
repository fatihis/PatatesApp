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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;

public class SingUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText userEmail,userPass;
    Button toSignFireButton;
    public final static String USER_ID = "USR_ID";
    public final static String USER_EMAIL ="USR_EMAIL";
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
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


        //final DatabaseReference myRef = database.getReference("message");

        toSignFireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString();
                final String password = userPass.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(SingUpActivity.this,"Islem Basarılı",Toast.LENGTH_SHORT).show();
                            dbSaver();
                        }
                        else{
                            Toast.makeText(SingUpActivity.this,"Islem basarısız",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




    }
    public void dbSaver(){
        final String email = userEmail.getText().toString();
        final String userID = mAuth.getUid();
        Map<String,Object> user =new HashMap<>();
        user.put(USER_ID,userID);
        user.put(USER_EMAIL,email);
        WriteBatch batchS = FirebaseFirestore.getInstance().batch();
        DocumentReference doc= FirebaseFirestore.getInstance().collection("Users").document(userID);
        batchS.set(doc,user).commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SingUpActivity.this, "EKLENDİ", Toast.LENGTH_SHORT).show();
                    Intent signUpToSignIn = new Intent(SingUpActivity.this,SingInActivity.class);
                    startActivity(signUpToSignIn);
                }
            }
        });
        /*db.collection("USERS").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
             Toast.makeText(SingUpActivity.this,"DB BAŞARILI",Toast.LENGTH_LONG).show();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
             Toast.makeText(SingUpActivity.this,"Başarısız",Toast.LENGTH_LONG).show();
            }
        });*/
        /*db.collection("USERS").document("userlist").set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SingUpActivity.this,"DB BAŞARILI",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SingUpActivity.this,"Başarısız",Toast.LENGTH_LONG).show();
            }
        });*/

    }
}
