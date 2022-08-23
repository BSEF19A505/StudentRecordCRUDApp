package com.example.studentrecordcrudapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpdateStudent extends AppCompatActivity {
    TextView currentRollno, newName;
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
                int rollno= Integer.parseInt(currentRollno.getText().toString().trim());
                AlertDialog.Builder builder=new AlertDialog.Builder(UpdateStudent.this);
                if(rollno<=0){
                    String name=newName.getText().toString().trim();
                        StudentData student=new StudentData(name,rollno);
                        DBHelper db=new DBHelper(UpdateStudent.this);
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

                builder.setCancelable(true);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });
    }
}