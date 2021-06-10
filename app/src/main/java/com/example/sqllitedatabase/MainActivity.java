package com.example.sqllitedatabase;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
     static DatabaseAdapter databaseAdapter;
//    DatabaseAdapter databaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        ListView lv=findViewById(R.id.user_list) ;
        databaseAdapter = new DatabaseAdapter(this);
        final SimpleCursorAdapter simpleCursorAdapter= databaseAdapter.GetListViewFromDB();
        lv.setAdapter(simpleCursorAdapter);

        ImageView imageView=findViewById(R.id.btn_logout);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });

        FloatingActionButton floatingActionButton=findViewById(R.id.imageAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyNotes.class);
                startActivity(intent);
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Cursor cursor=(Cursor)simpleCursorAdapter.getItem(position);
             String name=cursor.getString(1);
             String location=cursor.getString(2);
             String designation=cursor.getString(3);

                Intent intent = new Intent(MainActivity.this, EditInfo.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("location", location);
                intent.putExtra("designation", designation);
                startActivity(intent);
                finish();
            }

        });

    }
    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }
}