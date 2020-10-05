package com.example.tutorial10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class UserData extends AppCompatActivity {

    TextView id,name,username,email;
    TextView street,suite,city,zipcode;
    TextView lat,lng,phone,website;
    TextView companyName,catchPhrase,bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        id =findViewById(R.id.id);
        name =findViewById(R.id.name);
        username =findViewById(R.id.userName);
        email =findViewById(R.id.email);
        street =findViewById(R.id.street);
        suite =findViewById(R.id.suite);
        city =findViewById(R.id.city);
        zipcode =findViewById(R.id.zipcode);
        lat =findViewById(R.id.lat);
        lng =findViewById(R.id.lng);
        phone =findViewById(R.id.phone);
        website =findViewById(R.id.website);
        companyName =findViewById(R.id.companyName);
        catchPhrase =findViewById(R.id.catchPhrase);
        bs =findViewById(R.id.bs);
        Intent intent = getIntent();
        int i = intent.getIntExtra("userdata",0);
        try {
            JSONObject firstObj = MyUtil.userdata.getJSONObject(i);
            id.setText(firstObj.getString("id"));
            name.setText(firstObj.getString("name"));
            username.setText(firstObj.getString("username"));
            email.setText(firstObj.getString("email"));

            JSONObject secondObj = firstObj.getJSONObject("address");
            street.setText(secondObj.getString("street"));
            suite.setText(secondObj.getString("suite"));
            city.setText(secondObj.getString("city"));
            zipcode.setText(secondObj.getString("zipcode"));

            JSONObject thirdObj = secondObj.getJSONObject("geo");
            lat.setText(thirdObj.getString("lat"));
            lng.setText(thirdObj.getString("lng"));
            phone.setText(firstObj.getString("phone"));
            website.setText(firstObj.getString("website"));

            JSONObject fourthObj = firstObj.getJSONObject("company");
            companyName.setText(fourthObj.getString("name"));
            catchPhrase.setText(fourthObj.getString("catchPhrase"));
            bs.setText(fourthObj.getString("bs"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}