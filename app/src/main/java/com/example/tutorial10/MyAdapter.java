package com.example.tutorial10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAdapter extends BaseAdapter {

    Context context;
    JSONArray itemsJSONArray;

    public MyAdapter(Context context, JSONArray itemsJSONArray) {
        this.context = context;
        this.itemsJSONArray = itemsJSONArray;
    }

    @Override
    public int getCount() {
        return this.itemsJSONArray.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        }

        TextView txtName = (TextView) convertView.findViewById(R.id.txtid);
        TextView txtEmail = (TextView) convertView.findViewById(R.id.email);

        try {
            JSONObject object = itemsJSONArray.getJSONObject(position);
            txtName.setText(object.getString("name"));
            txtEmail.setText(object.getString("email"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
