package com.example.anmolbhat.codefundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class people_activity extends AppCompatActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<TodoItem> mToDoTable;
    private MobileServiceList<TodoItem> mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_activity);

        ListView peoplelist = findViewById(R.id.foundlist);
        Button add = findViewById(R.id.bttnaddpeople);
        final ArrayList<String> list = new ArrayList<String>();
        try {
            mClient = new MobileServiceClient(
                    "https://my--mobile.azurewebsites.net",
                    this
            );
            mToDoTable = mClient.getTable(TodoItem.class);
            System.out.println("first");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            mlist = mToDoTable.where().field("name").eq(false).execute().get();
            System.out.println("Second");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(int i=0; i<mlist.getTotalCount();i++){
            list.add(mlist.get(i).name);
            System.out.println("Third");
            System.out.println(mlist.get(i).name);
        }

        list.add("Anmol Bhat");
        list.add("Abhishek Shetty");
        list.add("Shravan G");
        list.add("Dhanush");
        list.add("Rohit Sharma");

        final ListAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        peoplelist.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }

    public void openActivity(){
        Intent newAct = new Intent(this, register_found_people.class);
        startActivity(newAct);
    }
}
