package com.example.roy.numbertrivia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NumberQuoteAdapter extends RecyclerView.Adapter<NumberQuoteViewHolder>{

    private Context context;
    public List<NumberQuote> listNumberQuote;

    public NumberQuoteAdapter(Context context, List<NumberQuote> listNumberQuote) {
        this.context = context;
        this.listNumberQuote = listNumberQuote;
    }

    @Override
    public NumberQuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new NumberQuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NumberQuoteViewHolder holder, final int position) {
        final NumberQuote numberQuote = listNumberQuote.get(position);

        holder.numberTextView.setText(numberQuote.getNumber().toString());
        holder.quoteTextView.setText(numberQuote.getText());

    }


    @Override
    public int getItemCount() {
        return listNumberQuote.size();
    }
}
