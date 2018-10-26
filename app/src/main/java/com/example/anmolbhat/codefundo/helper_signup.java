package com.example.anmolbhat.codefundo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class helper_signup extends AppCompatActivity {

    private MobileServiceClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_signup);

        EditText name = findViewById(R.id.etname);
        EditText email = findViewById(R.id.etemail);
        EditText date = findViewById(R.id.etdate);
        EditText phone = findViewById(R.id.etphone);
        RadioGroup rg = findViewById(R.id.rbgagdonag);
        Button next = findViewById(R.id.bttnnext);
        Button back = findViewById(R.id.bttnback);

        try {
            mClient = new MobileServiceClient(
                    "https://my-mobile.azurewebsites.net",
                    this
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rbb = findViewById(rg.getCheckedRadioButtonId());
                String s = rbb.getText().toString();
                if(s.equals("I Agree")) {
                    try {
                        TodoItem item = new TodoItem();
                        item.Text = "Text";
                        item.name = name.getText().toString();
                        item.email = email.getText().toString();
                        item.phone = phone.getText().toString();

                        mClient.getTable(TodoItem.class).insert(item);
                        System.out.println("Done");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(helper_signup.this,"DataBase Updated",Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
                else
                    Toast.makeText(helper_signup.this,"Please Agree to our Terms and Conditions",Toast.LENGTH_LONG).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
