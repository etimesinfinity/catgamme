package com.example.cat;
//大橘猫
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static java.lang.String.valueOf;

public class Drag1_2 extends android.support.v7.widget.AppCompatButton {
    private int lastX,offsetX,downX,inst;
    private int lastY,offsetY,downY;
    private SharedPreferences.Editor editor;
    public int count=0;
    private MediaPlayer music;
    public Drag1_2(Context context) {
        super(context);
        initView();
    }

    public Drag1_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Drag1_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    //初始化,重点1在这里
    @SuppressLint("CommitPrefEdits")
    private void initView() {
        //读取缓存的X，Y
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        float x= preferences.getFloat("X", 0);
        float y= preferences.getFloat("Y", 0);
        //设置自定义View的X，Y
       /* setX(x);
        setY(y);*/
        setX(0);
        setY(0);
        Context context = this.getContext();
        if(context instanceof level1){
            inst=0;
            Log.e("activitychange", "1");
        }
        else if(context instanceof level2){
            inst=1;
            Log.e("activitychange", "2");
        }
        else if(context instanceof level3){
            inst=2;
            Log.e("activitychange", "3");
        }
        //inst=class_map.mapcount;
    }

    //判断位置
    public int pos(float x,float y)
    {
        if(x<240)
        {
            if(y<240)return 0;if(y<480)return 4;if(y<720)return 8;if(y<960)return 12;if(y<1200)return 16;
        }
        if(x<480)
        {
            if(y<240)return 1;if(y<480)return 5;if(y<720)return 9;if(y<960)return 13;if(y<1200)return 17;
        }
        if(x<720)
        {
            if(y<240)return 2;if(y<480)return 6;if(y<720)return 10;if(y<960)return 14;if(y<1200)return 18;
        }
        if(x<960)
        {
            if(y<240)return 3;if(y<480)return 7;if(y<720)return 11;if(y<960)return 15;if(y<1200)return 19;
        }
        return -1;
    }

    //判断offset是否合法 返回唯一非零offset合法化值
    public void manageoffset()
    {


        if(offsetX==0&&offsetY>0)
        {
            while(lastY+offsetY>720||class_map.map[inst][pos(lastX,lastY+offsetY)+4]!=0){offsetY-=240;Log.e("太靠", "下  lastY:"+lastY+"  offsety:"+offsetY);}
            //return offsetY;
        }
        if(offsetX==0&&offsetY<0)
        {
            while(lastY+offsetY<0||class_map.map[inst][pos(lastX,lastY+offsetY)]!=0 ){offsetY+=240;Log.e("太靠", "上  lastY:"+lastY+"  offsety:"+offsetY);}
            //return offsetY;
        }
        if(offsetY==0&&offsetX<0)
        {
            while(lastX+offsetX<0||class_map.map[inst][pos(lastX+offsetX,lastY)]!=0||class_map.map[inst][pos(lastX+offsetX,lastY)+4]!=0){
                Log.e("check pos", String.valueOf(pos(lastX,lastY)));offsetX+=240;Log.e("太靠", "左  lastX:"+lastX+"  offsetX:"+offsetX);}
            //return offsetX;
        }
        if(offsetY==0&&offsetX>0)
        {
            while(lastX+offsetX>720||class_map.map[inst][pos(lastX+offsetX,lastY)+4]!=0||class_map.map[inst][pos(lastX+offsetX,lastY)]!=0){offsetX-=240;Log.e("太靠", "右  lastX:"+lastX+"  offsetX:"+offsetX);}
            //return offsetX;
        }
        //return 0;
    }

    //标准化offset
    public int setoffset(float o)
    {
        if(o>0)
        {
            if(o<240)return 240;
            if(o<480)return 480;
            if(o<720)return 720;
        }
        else
        {
            if(o>-240)return -240;
            if(o>-480)return -480;
            if(o>-720)return -720;
        }

        return 0;
    }
    //触控事件处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 视图坐标系方式获取坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            //单点触摸按下事件
            case MotionEvent.ACTION_DOWN:
                if(count==0){
                    lastX= getLeft();lastY=getTop();
                    Log.e("提取X坐标", String.valueOf(lastX));Log.e("提取Y坐标", String.valueOf(lastY));}
                // 记录触摸点坐标
                downX = x;
                downY = y;
                Log.e("lastX lasty", valueOf(lastX)+lastY+x);
                class_map.map[inst][pos(lastX,lastY)]--;class_map.map[inst][pos(lastX,lastY)+4]--;
                break;
            //单点触摸移动事件
            /*case MotionEvent.ACTION_MOVE:
                // 计算偏移量


                // 在当前left、top、right、bottom的基础上加上偏移量
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);

                break;*/
            //重点2在这里，单点触摸离开事件
            case MotionEvent.ACTION_UP:
                float dX=x-downX;float dY=y-downY;
                Log.e("保存X坐标", "x:"+dX+"y:"+dY);
                if((dX*dX)>(dY*dY))
                {
                    offsetX = setoffset(dX);
                    offsetY=0;
                    manageoffset();
                }
                else
                {
                    offsetY = setoffset(dY);
                    offsetX=0;
                    manageoffset();
                }
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                lastX+=offsetX; lastY+=offsetY;
                class_map.map[inst][pos(lastX,lastY)]++;class_map.map[inst][pos(lastX,lastY)+4]++;
                editor.putFloat("X", getX());
                editor.putFloat("Y", getY());
                editor.apply();
                music = MediaPlayer.create(getContext(), R.raw.shorthigh);
                music.start();
                count++;
                break;
        }
        return true;
    }
}