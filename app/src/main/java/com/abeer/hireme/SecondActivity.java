package com.abeer.hireme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private ImageView Car,noha,truck;
    private DatabaseReference databaseReference;

  //  private Button logout;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Car = findViewById(R.id.Car);
        noha = findViewById(R.id.noha);
        truck = findViewById(R.id.truck);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-8d036.firebaseio.com/User/notiData");
        databaseReference.keepSynced(true);

        firebaseAuth = FirebaseAuth.getInstance();
        //logout = (Button)findViewById(R.id.logout);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,car.class));
            }
        });

        noha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,Noha.class));
            }
        });

        truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,Truck.class));
            }
        });





    }

    @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<SingleItem,SingleItemViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SingleItem, SingleItemViewHolder>
                (SingleItem.class,R.layout.singleitem,SingleItemViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(SingleItemViewHolder viewHolder, SingleItem model, int position) {

                viewHolder.settext1(model.getText1());
                viewHolder.settext2(model.getText2());
                viewHolder.settext3(model.getText3());


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class SingleItemViewHolder extends RecyclerView.ViewHolder{

        View mview;

        public SingleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
        }
        public void settext1(String text1){
            TextView textView1 = (TextView)mview.findViewById(R.id.text1);
            textView1.setText(text1);

        }
        public void settext2(String text2){
            TextView textView2 = (TextView)mview.findViewById(R.id.text2);
            textView2.setText(text2);

        }
        public void settext3(String text3){
            TextView textView3 = (TextView)mview.findViewById(R.id.text3);
            textView3.setText(text3);

        }
    }

    /* private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this,MainActivity.class));

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.ProfileMenu:{
                startActivity(new Intent(SecondActivity.this,ProfileActivity.class));
            }

        }
        return super.onOptionsItemSelected(item);
    }






}
