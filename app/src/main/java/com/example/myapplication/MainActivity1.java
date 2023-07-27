package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class MainActivity1 extends AppCompatActivity {
    private ListView listView;
    private ArrayList<DataModel> dataList;
    private MyListAdapter myListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        listView = findViewById(R.id.listView);
        dataList = new ArrayList<>();
        myListAdapter = new MyListAdapter(dataList);
        listView.setAdapter(myListAdapter);
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity1.this);
        ArrayList<DataModel> data = dbHelper.getData();
        if (data != null) {
            dataList.clear();
            dataList.addAll(data);
            myListAdapter.notifyDataSetChanged();
        }
    }
    private static class ViewHolder {
        TextView principalTextView, rateTextView, monthsTextView, interestTextView, dateTimeTextView;
    }
    private class MyListAdapter extends BaseAdapter {
        private final ArrayList<DataModel> data;
        public MyListAdapter(ArrayList<DataModel> data) {
            this.data = data;
        }
        @Override
        public int getCount() {
            return data.size();
        }
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.principalTextView = convertView.findViewById(R.id.principalTextView);
                viewHolder.rateTextView = convertView.findViewById(R.id.rateTextView);
                viewHolder.monthsTextView = convertView.findViewById(R.id.monthsTextView);
                viewHolder.interestTextView = convertView.findViewById(R.id.interestTextView);
                viewHolder.dateTimeTextView = convertView.findViewById(R.id.dateTimeTextView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            DataModel dataModel = (DataModel) getItem(position);
            viewHolder.principalTextView.setText(String.format("Principal: %.2f", dataModel.getPrincipal()));
            viewHolder.rateTextView.setText(String.format("Rate: %.2f%%", dataModel.getRate()));
            viewHolder.monthsTextView.setText(String.format("Months: %.2f", dataModel.getMonths()));
            viewHolder.interestTextView.setText(String.format("Interest: %.2f", dataModel.getInterest()));
            viewHolder.dateTimeTextView.setText(String.format("Date: %s", dataModel.getDate()));
            return convertView;
        }
    }
}
