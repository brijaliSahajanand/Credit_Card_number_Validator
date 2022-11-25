package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    public Context context;
    public ArrayList<HashMap<String, String>> fileArrayList;

    public LayoutInflater layoutInflater;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cardnumber, scheme;

        public ViewHolder(@NonNull HistoryAdapter fileListAdapter, View view) {
            super(view);

            this.cardnumber = (TextView) view.findViewById(R.id.cardnumber);
            this.scheme = (TextView) view.findViewById(R.id.scheme);
        }
    }

    public HistoryAdapter(Context context2, ArrayList<HashMap<String, String>> arrayList) {
        this.context = context2;
        this.fileArrayList = arrayList;

    }

    public int getItemCount() {
        return fileArrayList.size();

    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.cardnumber.setText(fileArrayList.get(i).get("name"));
        viewHolder.scheme.setText(fileArrayList.get(i).get("designation"));
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ViewHolder(this, this.layoutInflater.inflate(R.layout.item_history, viewGroup, false));
    }
}


