package com.example.studentrecordcrudapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpdateStudent extends AppCompatActivity {
    TextView currentRollno, newName, newRollno;
    Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        currentRollno=findViewById(R.id.currentRollno);
        newName=findViewById(R.id.newName);


        updateBtn=findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rollno= Integer.parseInt(currentRollno.getText().toString());
                int newrollno=Integer.parseInt(newRollno.getText().toString());
                AlertDialog.Builder builder=new AlertDialog.Builder(UpdateStudent.this);
                if(rollno>0){
                    String name=newName.getText().toString().trim();
                        StudentData student=new StudentData(name,newrollno);
                        DBHelper db=new DBHelper(UpdateStudent.this);

                            int result = db.updateStudent(rollno,student);
                            if(result>=1){
                                builder.setMessage("Updation successful");
                            }else{
                                builder.setMessage("There is no student under this roll no");
                            }

                    }else{
                        builder.setTitle("Error");
                        builder.setMessage("Please enter name and rollno to update");
                    }

                builder.setCancelable(true);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });
    }
}