package com.example.semester_project;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    myadapterclass adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Card Loader");

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new myadapterclass(dataqueue(),getApplicationContext());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.loc:
//                FragmentManager frm=getSupportFragmentManager();
//        FragmentTransaction frt=frm.beginTransaction();
//        frt.add(R.id.recview,new MapsFragment());
//        frt.addToBackStack(null);
//        frt.commit();
//                Intent intent=new Intent(MainActivity.this,MapsFragment.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Location Clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.set:
                Toast.makeText(getApplicationContext(),"Setting Clicked",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public ArrayList<mymodelclass> dataqueue()
    {
        ArrayList<mymodelclass> holder=new ArrayList<>();


        mymodelclass obj=new mymodelclass();
        obj.setHeader("Jazz");
        obj.setDesc("Load Card");
        obj.setImgname(R.drawable.jazz);
        holder.add(obj);


        mymodelclass obj1=new mymodelclass();
        obj1.setHeader("Zong");
        obj1.setDesc("Load Card");
        obj1.setImgname(R.drawable.zong);
        holder.add(obj1);

        mymodelclass obj2=new mymodelclass();
        obj2.setHeader("Ufone");
        obj2.setDesc("Load Card");
        obj2.setImgname(R.drawable.ufone);
        holder.add(obj2);

        mymodelclass obj3=new mymodelclass();
        obj3.setHeader("Telenor");
        obj3.setDesc("Load Card");
        obj3.setImgname(R.drawable.telenor);
        holder.add(obj3);
        return holder;

    }
}