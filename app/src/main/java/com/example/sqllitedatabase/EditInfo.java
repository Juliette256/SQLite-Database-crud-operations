package com.example.sqllitedatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.sqllitedatabase.MainActivity.databaseAdapter;

public class EditInfo extends AppCompatActivity {
    EditText et_name, et_location, et_designation;
    long id;
    Button Update, Delete;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_ifo);

        id = getIntent().getExtras().getLong("id");
        String Name = getIntent().getExtras().getString("name");
        String Location = getIntent().getExtras().getString("location");
        String Designation = getIntent().getExtras().getString("designation");

        et_name = findViewById(R.id.txtName);
        et_location = findViewById(R.id.txtLocation);
        et_designation = findViewById(R.id.txtDesignation);
        Update = findViewById(R.id.btnUpdate);
        Delete = findViewById(R.id.btnDelete);

        et_name.setText(Name);
        et_location.setText(Location);
        et_designation.setText(Designation);


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String New_name = et_name.getText().toString();
                String New_location = et_location.getText().toString();
                String New_designation = et_designation.getText().toString();

                databaseAdapter.UpdateUserDetails((int) id, New_name, New_location, New_designation);

                Toast.makeText(getApplicationContext(), "Details have been Updated", Toast.LENGTH_SHORT).show();
                et_name.setText("");
                et_location.setText("");
                et_designation.setText("");

                startActivity(new Intent(EditInfo.this, MainActivity.class));
                finish();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAdapter databaseAdapter = new DatabaseAdapter(EditInfo.this);
                databaseAdapter.delete((int) id);

                AlertDialog.Builder builder = new AlertDialog.Builder(EditInfo.this);
                builder.setTitle("Delete Alert");
                builder.setIcon(R.drawable.ex_mark);
                builder.setMessage("Are you sure about deleting the member?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_name.setText("");
                        et_location.setText("");
                        et_designation.setText("");
                        Toast.makeText(getApplicationContext(), "Member had been deleted", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(EditInfo.this, MainActivity.class));
                        finish();
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        et_name.setText("");
        et_location.setText("");
        et_designation.setText("");

        startActivity(new Intent(EditInfo.this, MainActivity.class));
    }
}




