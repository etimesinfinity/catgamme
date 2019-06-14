package com.example.cat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class showReward extends AppCompatActivity {
    int r1,r2,r3,r[]={0,0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reward);
        Button b1 = findViewById(R.id.r1),b2=findViewById(R.id.r2),b3=findViewById(R.id.r3);//找到你要设透明背景的layout 的id
        DBHelper dbHelper=new DBHelper(showReward.this);
        SQLiteDatabase rdb=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                DBlevel.KEY_reward+" FROM "+DBlevel.TABLE;
        Cursor c=rdb.rawQuery(selectQuery,null);
        int i=0;
        while(c.moveToNext())
        {
            r[i]=c.getInt(c.getColumnIndex("reward"));
            i++;
        }
       /* String selectQuery="SELECT "+
                DBlevel.KEY_reward+" FROM "+DBlevel.TABLE+" WHERE "+DBlevel.KEY_name+" = 'level1'";
        Cursor c=rdb.rawQuery(selectQuery,null);
        r1=c.getInt(c.getColumnIndex("reward"));
        selectQuery="SELECT "+
                DBlevel.KEY_reward+" FROM "+DBlevel.TABLE+" WHERE "+DBlevel.KEY_name+" = 'level2'";
        c=rdb.rawQuery(selectQuery,null);
        r2=c.getInt(c.getColumnIndex("reward"));
        selectQuery="SELECT "+
                DBlevel.KEY_reward+" FROM "+DBlevel.TABLE+" WHERE "+DBlevel.KEY_name+" = 'level3'";
        c=rdb.rawQuery(selectQuery,null);
        r3=c.getInt(c.getColumnIndex("reward"));*/
/*        if(r1!=1)b1.getBackground().setAlpha(100);else b1.getBackground().setAlpha(255);
        if(r2!=1)b2.getBackground().setAlpha(100);else b2.getBackground().setAlpha(255);
        if(r3!=1)b3.getBackground().setAlpha(100);else b3.getBackground().setAlpha(255);*/
if(r[0]==1)b1.getBackground().setAlpha(225);else b1.getBackground().setAlpha(80);
        if(r[1]==1)b2.getBackground().setAlpha(225);else b2.getBackground().setAlpha(80);
        if(r[2]==1)b3.getBackground().setAlpha(225);else b3.getBackground().setAlpha(80);
        c.close();
        rdb.close();
    }
}
