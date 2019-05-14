package com.example.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Task> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.buttonInsert);
        btnGetTasks = findViewById(R.id.buttonTask);
        tvResults = findViewById(R.id.textViewResults);
        lv = findViewById(R.id.lv);

        task = new ArrayList<Task>();
        aa = new TaskAdapter(this, R.layout.rows, task);
        // Create the DBHelper object, passing in the
        // activity's Context
        DBHelper db = new DBHelper(MainActivity.this);
        ArrayList<Task> data = db.getTasks();
        aa = new TaskAdapter(this, R.layout.rows,data);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Insert a task


                // Link this Activity object, the row.xml layout for
                //  each row and the food String array together
                DBHelper db = new DBHelper(MainActivity.this);


                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();


                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

            }
        });
    }

}

