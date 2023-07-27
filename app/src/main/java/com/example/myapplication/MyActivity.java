package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private Button calBtn, calBtn1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        dbHelper = new DatabaseHelper(this);
        calBtn = findViewById(R.id.activityBtn);
        calBtn1 =findViewById(R.id.activityBtn1);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.clearData();
                Toast.makeText(MyActivity.this, "Data cleared", Toast.LENGTH_SHORT).show();
            }
        });
        calBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, MyActivity1.class);
                startActivity(intent);
            }
        });
    }
}
