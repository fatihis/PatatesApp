package com.mcbu.patates;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class UserListActivity extends AppCompatActivity {
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    //DocumentReference documentReference = db.collection("USERS").document("userlist");
    TextView usrIdView,usrMailView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        usrIdView = findViewById(R.id.userIdView);
        usrMailView = findViewById(R.id.userMailView);
        loadData();

    }
    void loadData(){
        /*documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String uids = documentSnapshot.getString("USR_ID");
                    String email = documentSnapshot.getString("USR_EMAIL");
                usrIdView.setText("Your UID:"+ uids);
                usrMailView.setText("Your Mail:"+ email);

                }
                else{
                    Toast.makeText(UserListActivity.this,"Load Successfully done",Toast.LENGTH_LONG).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserListActivity.this, "Load couldn't done", Toast.LENGTH_SHORT).show();
            }
        });*/
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("USERS").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UserListActivity.this, "OLDU", Toast.LENGTH_SHORT).show();
                    String usname =task.getResult().getString("userID");
                    String mail =task.getResult().getString("email");
                    usrIdView.setText(usname);
                    usrMailView.setText(mail);
                }
                else{
                    Toast.makeText(UserListActivity.this, "OLMADI", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
