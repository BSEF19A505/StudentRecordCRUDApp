package com.example.studentrecordcrudapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class UpdateStudent {
    TextView currentRollno, newName, newAge, newEmail;
    Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        currentRollno=findViewById(R.id.currentRollno);
        newName=findViewById(R.id.newName);
        newAge=findViewById(R.id.newAge);
        newEmail=findViewById(R.id.newEmail);
        updateBtn=findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rollno=currentRollno.getText().toString().trim();
                AlertDialog.Builder builder=new AlertDialog.Builder(updateStudentActivity.this);
                if(rollno.length()!=0){
                    String name=newName.getText().toString().trim();
                    String age=newAge.getText().toString().trim();
                    String email=newEmail.getText().toString().trim();
                    if(age.length()!=0 ||name.length()!=0 ||email.length()!=0){
                        studentModel student=new studentModel(name,age,rollno,email);
                        dbHelper db=new dbHelper(updateStudentActivity.this);
                        try {
                            int result = db.updateStudent(rollno,student);
                            if(result>=1){
                                builder.setMessage("Updation successful");
                            }else{
                                builder.setMessage("There is no student under this roll no");
                            }
                        }catch(Exception e){
                            System.out.println(e);
                            builder.setTitle("Error");
                            builder.setMessage("There is another student with this email");
                            builder.setCancelable(true);
                            AlertDialog alertDialog=builder.create();
                            alertDialog.show();
                        }
                    }else{
                        builder.setTitle("Error");
                        builder.setMessage("Please enter name, age or email to update");
                    }
                }else{
                    builder.setTitle("Error");
                    builder.setMessage("Please enter rollno to find and update");
                }
                builder.setCancelable(true);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });
    }
}
