package com.example.cat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Filehandler {
    public void read()
    {
        try {
            FileInputStream fis = new FileInputStream("/data/user/0/com.example.cat/files/info.txt");
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            char[] input = new char[fis.available()];  //available()用于获取filename内容的长度
            isr.read(input);  //读取并存储到input中
            isr.close();
            fis.close();//读取完成后关闭
            String str = new String(input); //将读取并存放在input中的数据转换成String输出
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void write(String name, String pass) {
        File file = new File("/data/user/0/com.example.cat/files/info.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write("");
            osw.flush();
            fos.flush();  //flush是为了输出缓冲区中所有的内容
            osw.close();
            fos.close();  //写入完成后，将两个输出关闭
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
