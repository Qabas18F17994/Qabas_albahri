package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class about_us extends AppCompatActivity {


    Button ins,web,twe;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        ins=findViewById(R.id.ins);
        web=findViewById(R.id.web);
        twe=findViewById(R.id.twe);

        twe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent ins = new Intent(Intent.ACTION_VIEW);
                ins.setData(Uri.parse("https://twitter.com/OmanMHT"));
                startActivity(ins);

            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent ins = new Intent(Intent.ACTION_VIEW);
                ins.setData(Uri.parse("https://mht.gov.om/"));
                startActivity(ins);

            }
        });
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent ins = new Intent(Intent.ACTION_VIEW);
                ins.setData(Uri.parse("https://www.instagram.com/omanmht/"));
                startActivity(ins);

            }
        });
    }
}