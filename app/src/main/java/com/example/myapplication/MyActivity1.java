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
public class MyActivity1 extends AppCompatActivity {
    private ListView listView;
    private ArrayList<DataModel1> dataList;
    private MyListAdapter myListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my1);
        listView = findViewById(R.id.listView);
        dataList = new ArrayList<>();
        myListAdapter = new MyListAdapter(dataList);
        listView.setAdapter(myListAdapter);
        DatabaseHelper dbHelper = new DatabaseHelper(MyActivity1.this);
        ArrayList<DataModel1> data = dbHelper.getDataInterest();

        if (data != null) {
            dataList.clear();
            dataList.addAll(data);
            myListAdapter.notifyDataSetChanged();
        }
    }
    private static class ViewHolder {
        TextView principalTxtView, interestTxtView;
    }
    private class MyListAdapter extends BaseAdapter {
        private final ArrayList<DataModel1> data;
        public MyListAdapter(ArrayList<DataModel1> data) {
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
            MyActivity1.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itemm, parent, false);
                viewHolder = new MyActivity1.ViewHolder();
                viewHolder.principalTxtView = convertView.findViewById(R.id.principalTxtView);
                viewHolder.interestTxtView = convertView.findViewById(R.id.interestTxtView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (MyActivity1.ViewHolder) convertView.getTag();
            }
            DataModel1 dataModel = (DataModel1) getItem(position);
            viewHolder.principalTxtView.setText(String.format("Total Principal: %.2f", dataModel.getTotalPrincipal()));
            viewHolder.interestTxtView.setText(String.format("Total Interest: %.2f", dataModel.getTotalInterest()));
            return convertView;
        }
    }
}