package com.example.cat;

import android.content.Intent;

import com.example.cat.DragView1.OnDownActionListener;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.view.View.VISIBLE;
import static com.example.cat.class_map.mapcopy;

public class level3 extends AppCompatActivity {

    private TextView mTextMessage;
    Button b;
    DragView1 cat;
    int score,pscore=0,reward;
    private DBHelper dbHelper;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(level3.this,showReward.class);
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
        setContentView(R.layout.activity_level3);
        for(int i=0;i<3;i++)
        {
            class_map.map[i]=class_map.mapcopy[i].clone();
        }
        Log.e("1", "copymap");
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        b=findViewById(R.id.button_next);
        cat=findViewById(R.id.button);
        final Drag1_1 button1_1=findViewById(R.id.button1_1);
        final Drag1_1 button1_1_1=findViewById(R.id.button1_1_1);
        final Drag1_1 button1_1_2=findViewById(R.id.button1_1_2);
        final Drag1_1 button1_1_3=findViewById(R.id.button1_1_3);
        final Drag1_2 button1_2_1=findViewById(R.id.button1_2_1);
        final Drag1_2 button1_2=findViewById(R.id.button1_2);
        final Drag1_2 button2_1_1=findViewById(R.id.button1_2_2);
        final Drag1_2 button2_1_2=findViewById(R.id.button1_2_3);
        final Drag2_1 button2_1=findViewById(R.id.button2_1);
        final TextView s=findViewById(R.id.Score);
        cat.setOnDownActionListener(new OnDownActionListener() {
            public void OnDown() {
                Log.e("beforebutton", " ");
                b.setVisibility(VISIBLE);
                b.setEnabled(true);
                Log.e("afterbutton", " ");
                score=cat.count+button1_1.count+button1_1_1.count+button1_1_2.count+button1_1_3.count+button1_2_1.count+button1_2.count+button2_1_1.count+button2_1_2.count+button2_1.count;
                dbHelper=new DBHelper(level3.this);
                SQLiteDatabase rdb=dbHelper.getReadableDatabase();
                String selectQuery="SELECT "+
                        DBlevel.KEY_score+","+
                        DBlevel.KEY_reward+" FROM "+DBlevel.TABLE+" WHERE "+DBlevel.KEY_name+" = 'level3'";
                Cursor c=rdb.rawQuery(selectQuery,null);
                if(c.moveToFirst()){
                    do{
                        pscore=c.getInt(c.getColumnIndex("score"));
                        reward=c.getInt(c.getColumnIndex("reward"));
                    }while(c.moveToNext());
                }
                c.close();
                rdb.close();
                if(pscore>score)
                {
                    s.setText("新高分: "+score+"步!");
                    SQLiteDatabase wdb=dbHelper.getWritableDatabase();
                    wdb.execSQL("UPDATE Level SET score = "+score+" WHERE id = 3;");
                }
                else {
                    s.setText("本次成绩: "+score+"   最佳成绩："+pscore);
                }
                if(reward==0){
                    SQLiteDatabase wdb=dbHelper.getWritableDatabase();
                    wdb.execSQL("UPDATE Level SET reward = 1 WHERE id = 3;");
                }
                class_map.map=mapcopy;
            }
        });
    }
    public void nextlevel(View view) {
        //Intent intent = new Intent(level2.this,level3.class);
        //startActivity(intent);
    }

}
