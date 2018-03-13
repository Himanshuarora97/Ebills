package com.ebills.alphamind.ebills.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebills.alphamind.ebills.R;


public class mainActivityAllBillsRecyclerAdapter extends RecyclerView.Adapter<mainActivityAllBillsRecyclerAdapter.ViewHolder> {


    public mainActivityAllBillsRecyclerAdapter() {
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }


    @Override
    public mainActivityAllBillsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mainActivityAllBillsRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_fragments_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final mainActivityAllBillsRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
