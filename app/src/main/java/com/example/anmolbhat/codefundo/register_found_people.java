package com.example.anmolbhat.codefundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;

public class register_found_people extends AppCompatActivity {

    private MobileServiceClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_found_people);

        Button add = findViewById(R.id.bttnadd);
        EditText name = findViewById(R.id.etname);
        EditText loc = findViewById(R.id.etloc);

        try {
            mClient = new MobileServiceClient(
                    "https://my--mobile.azurewebsites.net",
                    this
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addpeople(name.getText().toString(),loc.getText().toString());
            }
        });
    }

    public void addpeople(String name, String loc){
        TodoItem item = new TodoItem();
        item.name = name;
        item.loc = loc;
        mClient.getTable(TodoItem.class).insert(item);
        Toast.makeText(this,"DataBase Updated",Toast.LENGTH_LONG).show();
        finish();
    }
}
