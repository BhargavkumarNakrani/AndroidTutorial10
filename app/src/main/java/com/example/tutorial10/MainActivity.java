package com.example.tutorial10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ListView lstData;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstData=findViewById(R.id.lstData);
        //Setup the data source
        new MyAsyncTask().execute();

        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, UserData.class);
                intent.putExtra("userdata",position);
                startActivity(intent);
            }
        });
    }

    class MyAsyncTask extends AsyncTask {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            StringBuffer response = new StringBuffer();
            try {
                URL url =new URL(MyUtil.URL_USERS);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String inputLine = null;

                while ((inputLine = bufferedReader.readLine()) != null){
                        response.append(inputLine);
                }
                bufferedReader.close();
                inputStreamReader.close();
                MyUtil.userdata = new JSONArray(response.toString());

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            //instantiate the custom list adapter
            adapter =new MyAdapter(MainActivity.this,MyUtil.userdata);
            lstData.setAdapter(adapter);
            if (dialog.isShowing())dialog.dismiss();
            super.onPostExecute(o);
        }
    }

}