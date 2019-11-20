package com.abeer.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name,Password;
    private TextView Info,Signup;
    private Button Login;
    private  int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.userpassword);
        Info = (TextView)findViewById(R.id.info);
        Login = (Button)findViewById(R.id.login);
        Signup = (TextView)findViewById(R.id.signup);
        Info.setText("Attempts Remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });




        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    validate(Name.getText().toString(),Password.getText().toString());

            }
        });








    }

    private void validate(String userName, String userPassword){


        progressDialog.setMessage("Verification is going on please wait!!");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressDialog.dismiss();

                    if(task.isSuccessful()){
                       // progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,SecondActivity.class));

                    }else{
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        counter--;
                        if(counter==0){
                            Login.setEnabled(false);
                        }
                        Info.setText("Attempts Remaining: "+ String.valueOf(counter));
                        //progressDialog.dismiss();

                    }

            }
        });


    }







}
