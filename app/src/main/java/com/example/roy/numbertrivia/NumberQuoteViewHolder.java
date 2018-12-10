package com.example.roy.numbertrivia;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NumberQuoteViewHolder extends RecyclerView.ViewHolder {
    public TextView quoteTextView;
    public TextView numberTextView;
    public View view;

    public NumberQuoteViewHolder(View itemView) {
        super(itemView);
        quoteTextView = itemView.findViewById(R.id.quoteTextView);
        numberTextView = itemView.findViewById(R.id.numberTextView);
        view = itemView;
    }
}
