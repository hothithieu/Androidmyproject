package com.example.android_dialy_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dialyDetail extends AppCompatActivity {

    private TextView textTitle;
    private TextView TextContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialy_detail);

        final Button btn_cancel = (Button) findViewById(R.id.btnCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dialyDetail.this, ShowDialyList.class);
                startActivity(intent);

            }
        });

        final Button btn_Home = (Button) findViewById(R.id.btnHome);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dialyDetail.this, MainActivity.class);
                startActivity(intent);

            }
        });

        textTitle = (TextView)findViewById(R.id.textvTitle);
        TextContent = (TextView)findViewById(R.id.textvContent);
        //getExtras() nhan data
        Intent intent= getIntent();
        Bundle getInfo = intent.getExtras();
        String Titlte = getInfo.getString("Title");
        String Content = getInfo.getString("Content");
        if(Titlte!=null || Content!=null)
        {
            textTitle.setText(Titlte);
            TextContent.setText(Content);
        }



    }
}
