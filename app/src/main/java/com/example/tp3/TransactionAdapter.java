package com.example.tp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TransactionAdapter extends BaseAdapter {
    private Context context;
    private List<Transaction> transactionList;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return transactionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false);
        }

        Transaction transaction = transactionList.get(position);

        ImageView iconView = convertView.findViewById(R.id.transactionIcon);
        TextView titleView = convertView.findViewById(R.id.transactionTitle);
        TextView dateView = convertView.findViewById(R.id.transactionDate);
        TextView amountView = convertView.findViewById(R.id.transactionAmount);

        iconView.setImageResource(transaction.getIcon());
        titleView.setText(transaction.getTitle());
        dateView.setText(transaction.getDate());
        amountView.setText(transaction.getAmount());

        return convertView;
    }
}
