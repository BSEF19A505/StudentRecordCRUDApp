package com.example.studentrecordcrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonAdd, buttonViewAll;
    EditText editName, editRollNumber;
    TextView nameText, rollText;
    Switch switchIsActive;
    ListView listViewStudent;
    ArrayList<StudentModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        editName = findViewById(R.id.editTextName);
        editRollNumber = findViewById(R.id.editTextRollNumber);
        nameText = findViewById(R.id.nameTextView);
        rollText = findViewById(R.id.rollTextView);
        switchIsActive = findViewById(R.id.switchStudent);
        listViewStudent = findViewById(R.id.listViewStudent);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;

            @Override
            public void onClick(View v) {
                try {
                    studentModel = new StudentModel(editName.getText().toString(), (editRollNumber.getText().toString()), switchIsActive.isChecked());
                    //Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DBHelper dbHelper  = new DBHelper(MainActivity.this);
                dbHelper.addStudent(studentModel);
            }
        });

        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                list = dbHelper.getAllStudents();
//                ArrayList<StudentModel> studentModelArrayList = new ArrayList<StudentModel>();
//                ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>
//                        (MainActivity.this, android.R.layout.simple_list_item_1,list);
//                listViewStudent.setAdapter(arrayAdapter);
                if (list==null)
                {
                    Toast.makeText(MainActivity.this, "List is Empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    myAdapter adapter = new myAdapter(MainActivity.this, list);
                    listViewStudent.setAdapter(adapter);
                    listViewStudent.setClickable(true);
                }
            }
        });

        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG=====", "onItemClick: "+list.get(i).getName());
            }
//            public void editOnClick(View view) {
//                Log.d("TAG =======", "Edit on Click "+list.get(0).getName());
//            }
        });
    }


//    public void editOnClick(View view) {
//
//        Log.d("TAG =======", "Edit on Click");
//    }
//
//    public void deleteOnClick(View view) {
//        Log.d("TAG =======", "Delete on Click"+list.get(0).getName());
//    }
}