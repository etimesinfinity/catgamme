package com.example.cat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.example.cat.class_map.mapcopy;
import static com.example.cat.class_map.mapcount;
import com.example.cat.DBHelper;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(MainActivity.this,showReward.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Log.i("codecraeer", "getFilesDir = " + getFilesDir());
        Log.i("codecraeer", "getExternalFilesDir = " + getExternalFilesDir("exter_test").getAbsolutePath());


    }

    public void level1(View view) {
        Intent intent = new Intent(MainActivity.this, level1.class);
        startActivity(intent);
    }

    public void level2(View view) {
        Intent intent = new Intent(MainActivity.this, level2.class);
        startActivity(intent);
    }
    public void level3(View view) {
        Intent intent = new Intent(MainActivity.this, level3.class);
        startActivity(intent);
    }



}
/*

I/codecraeer: getFilesDir = /data/user/0/com.example.cat/files
        I/codecraeer: getExternalFilesDir = /storage/emulated/0/Android/data/com.example.cat/files/exter_test*/
