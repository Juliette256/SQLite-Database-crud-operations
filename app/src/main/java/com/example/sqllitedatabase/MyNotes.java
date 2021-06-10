package com.example.sqllitedatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyNotes extends AppCompatActivity {
    EditText name, loc, desig;
    Button saveBtn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txtName);
        loc = findViewById(R.id.txtLocation);
        desig = findViewById(R.id.txtDesignation);
        saveBtn = findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString()+"\n";
                String location = loc.getText().toString();
                String designation = desig.getText().toString();

                DatabaseAdapter databaseAdapter = new DatabaseAdapter(MyNotes.this);
                databaseAdapter.insertUserDetails(username,location,designation);

                Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_LONG).show();
                name.setText("");
                loc.setText("");
                desig.setText("");

                intent = new Intent(MyNotes.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    }