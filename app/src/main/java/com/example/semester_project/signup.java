package com.example.semester_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edt1,edt2,edt3,edt4,edt5;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Card Loader");
        mAuth = FirebaseAuth.getInstance();

        edt1=(EditText)findViewById(R.id.editTextTextPersonName);
        edt2=(EditText)findViewById(R.id.editTextTextPersonName2);
        edt3=(EditText)findViewById(R.id.editTextTextEmailAddress);
        edt4=(EditText)findViewById(R.id.editTextTextPassword);
        edt5=(EditText)findViewById(R.id.editTextTextPassword2);
        btn=(Button)findViewById(R.id.button);
        signupFragment fragment=new signupFragment();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.signupmain,fragment);
        transaction.commit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fullname=edt1.getText().toString().trim();
                String Age=edt2.getText().toString().trim();
                String Email=edt3.getText().toString().trim();
                String Pass=edt4.getText().toString().trim();
                String Re_Pass=edt5.getText().toString().trim();


                if(Fullname.isEmpty()){
                    edt1.setError("Name is required");
                    edt1.requestFocus();
                    return;
                }
                if(Age.isEmpty()){
                    edt2.setError("Age is required");
                    edt2.requestFocus();
                    return;
                }
                if(Email.isEmpty()){
                    edt3.setError("Email is required");
                    edt3.requestFocus();
                    return;
                }
                if(Pass.isEmpty()){
                    edt4.setError("Password is required");
                    edt4.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())//email KA pattren dhky ga for example .,@ wgra
                {
                    edt3.setError("Email is required");
                    edt3.requestFocus();
                    return;
                }
                if(Pass.length()<6){
                    edt4.setError("Password Lenght is short");
                    edt4.requestFocus();
                    return;
                }
                if(Re_Pass.isEmpty()){
                    edt5.setError("Confirm Your Password");
                    edt5.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user=new User(Fullname,Age,Email);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(signup.this,login.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"Registration Error",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Registration Error ",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }
}