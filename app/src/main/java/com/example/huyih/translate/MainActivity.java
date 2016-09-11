package com.example.huyih.translate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= (EditText) findViewById(R.id.editText);
        tv= (TextView) findViewById(R.id.textView1);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readNet();
            }
        });
    }

    private void readNet() {

        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    BufferedReader reader=new BufferedReader(new InputStreamReader(new URL("http://fanyi.youdao.com/openapi.do?keyfrom=EToCpath&key=478871880&type=data&doctype=json&version=1.1&q="+et.getText()).openStream(),"utf-8"));
                    String line=null;
                    StringBuffer content=new StringBuffer();

                    while ((line=reader.readLine())!=null){
                        content.append(line);
                    }
                    reader.close();
                    return content.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(s!=null){
                    try {
                        JSONArray josnArr=new JSONArray(s);
                        JSONObject


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.execute();
    }


}
