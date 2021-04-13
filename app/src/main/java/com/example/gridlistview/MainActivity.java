package com.example.gridlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    private Intent intent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        intent = getIntent();

        TextView tv = findViewById(R.id.tv);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);

        changeBackground(tv, tv1, tv2);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, Fragment_ListItem.newInstance("just for you")).commit();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground(tv, tv1, tv2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, Fragment_ListItem.newInstance("just for you")).commit();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground(tv1, tv, tv2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, Fragment_ListItem.newInstance("winter collection")).commit();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackground(tv2, tv1, tv);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, Fragment_ListItem.newInstance("special collection")).commit();
            }
        });
    }

    private void changeBackground(TextView tv,TextView tv1, TextView tv2){
        tv.setBackgroundResource(R.drawable.custom_text_selected);
        tv1.setBackgroundResource(R.color.white);
        tv2.setBackgroundResource(R.color.white);
        tv.setTypeface(null, Typeface.BOLD);
        tv1.setTypeface(null, Typeface.NORMAL);
        tv2.setTypeface(null, Typeface.NORMAL);
    }
}