package com.example.ashwani.sharedpreferences;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

import static com.example.ashwani.sharedpreferences.R.id.ed2;
import static com.example.ashwani.sharedpreferences.R.layout.fablayout;


public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView ;
    Button b;
    FloatingActionButton floatingActionButton;
    SharedPreferences sharedPreferences;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<Data> dataarrayList = new ArrayList<>();
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        b = (Button) findViewById(R.id.b);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        sharedPreferences  = getSharedPreferences("MyApp", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
               alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(" To-do Entry")
                        .setMessage("pls Enter title and Description")
                        .setView(R.layout.fablayout)
                        .setPositiveButton("SUBMIT",new  DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                               EditText ed3 = (EditText) alertDialog.findViewById(R.id.ed3);
                               EditText ed2 = (EditText) alertDialog.findViewById(R.id.ed2);

                                String s1 = ed3.getText().toString();
                                String s2 = ed2.getText().toString();
                                //String s3 =s1+s2;

                                Data data = new Data(s1,s2);

                                Gson gson  = new Gson();
                                String json = gson.toJson(data);

                                Calendar c = Calendar.getInstance();
                                String date= c.get(Calendar.YEAR)+"-"+c.get(Calendar.DAY_OF_MONTH)+"-"+c.get(Calendar.HOUR_OF_DAY)+"-"+c.get(Calendar.MINUTE);
                                Log.e("TAG", "date is" + date);
                                editor.putString(" "+date, json);
                                editor.apply();
                                arrayList.add(date);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext() , "no fields added", Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alertDialog.show();

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> mp = (Map<String, String>) sharedPreferences.getAll();

                if (mp.size() == 0) {
                    Toast.makeText(getBaseContext(), "nothing to show here", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "no entry");
                } else {
                    Log.e("TAG", " "+mp.size());
                    Log.e("TAG", " entry is here");
                    for (int i = 0; i < arrayList.size(); i++) {
                        String date = arrayList.get(i);
                        String json = mp.get(" " + date);
                        Gson gson = new Gson();
                        Data data = gson.fromJson(json, Data.class);
                        dataarrayList.add(data);
                    }


                    MyAdapter myAdapter = new MyAdapter(MainActivity.this, dataarrayList);
                    recyclerView.setAdapter(myAdapter);


                }
            }
        });
    }


}
