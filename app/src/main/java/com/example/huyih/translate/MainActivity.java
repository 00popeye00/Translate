package com.example.huyih.translate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;
    private TextView key;
    private TextView us;
    private TextView uk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= (EditText) findViewById(R.id.editText);
        tv= (TextView) findViewById(R.id.textView1);
        key= (TextView) findViewById(R.id.key);
        us= (TextView) findViewById(R.id.us);
        uk= (TextView) findViewById(R.id.uk);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readNet("http://fanyi.youdao.com/openapi.do?keyfrom=EToCpath&key=478871880&type=data&doctype=json&version=1.1&q="+et.getText());
            }
        });
    }

    private void readNet(String url) {

        new AsyncTask<Object,Object,String>(){

            @Override
            protected String doInBackground(Object... objects) {

                try {
                    URL url=new URL((String) objects[0]);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    InputStream is =connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is,"utf-8"));
                    String line;
                    StringBuffer builder=new StringBuffer();

                    while ((line=reader.readLine())!=null){
                        builder.append(line);
                    }
                    reader.close();
                    return builder.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
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
                        JSONObject json=new JSONObject(s);
//                        解析数组
//                        JSONArray jsonArray=json.getJSONArray("basic");
                        JSONObject basic=json.getJSONObject("basic");

//                        System.out.println("英："+basic.getString("us-phonetic"));
//                        System.out.println("翻译的词："+json.getString("query"));
//                        System.out.println("翻译："+json.getString("translation"));

                        key.setText(json.getString("query"));
                        us.setText("美："+basic.getString("us-phonetic"));
                        uk.setText("英："+basic.getString("uk-phonetic"));
                        tv.setText(json.getString("translation"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.execute(url);
    }


}
