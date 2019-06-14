package com.example.cat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //数据库版本
    private static final int DATABASE_VERSION=3;

    //数据库名称
    private static final String DATABASE_NAME="sqlitestudy.db";


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据表
        String CREATE_TABLE_STUDENT="CREATE TABLE IF NOT EXISTS "+ DBlevel.TABLE+"("
                +DBlevel.KEY_ID+" INTEGER PRIMARY KEY ,"
                +DBlevel.KEY_name+" TEXT, "
                +DBlevel.KEY_score+" INTEGER, "
                +DBlevel.KEY_reward+" INTEGER)";
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL("    INSERT INTO Level (id,name,score,reward)\n" +
                "    VALUES (1, 'level1', 99, 0);");
        db.execSQL("    INSERT INTO Level (id,name,score,reward)\n" +
                "    VALUES (2, 'level2', 99, 0);");
        db.execSQL("    INSERT INTO Level (id,name,score,reward)\n" +
                "    VALUES (3, 'level3', 99, 0);");
/*        ContentValues values=new ContentValues();
        values.put(DBlevel.KEY_name,"level1");
        values.put(DBlevel.KEY_score,0);
        values.put(DBlevel.KEY_reward,0);*/
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果旧表存在，删除，所以数据将会消失
        //db.execSQL("DROP TABLE IF EXISTS "+ DBlevel.TABLE);
        //再次创建表
        //onCreate(db);
    }
}
