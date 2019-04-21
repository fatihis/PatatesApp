package com.mcbu.patates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainPageActivity extends AppCompatActivity {
    ImageView calisView,istatisView,toDosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toast.makeText(MainPageActivity.this,"HOŞGELDİNİZ",Toast.LENGTH_SHORT).show();
        calisView = findViewById(R.id.calisanView);
        istatisView = findViewById(R.id.istatistikView);
        toDosView = findViewById(R.id.toDoView);

        calisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPageActivity.this,"Calisan listesi geliyor",Toast.LENGTH_LONG).show();
                Intent toUserList= new Intent(MainPageActivity.this,UserListActivity.class);
                startActivity(toUserList);
            }
        });

    }
}
