package com.ebills.alphamind.ebills.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebills.alphamind.ebills.R;

/**
 * Created by anmol on 20/3/18.
 */

public class Shop_Page_Vertical extends RecyclerView.Adapter<Shop_Page_Vertical.ViewHolder> {

    Context ctx;

    public Shop_Page_Vertical(Context ctx) {
        this.ctx = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public Shop_Page_Vertical.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Shop_Page_Vertical.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_vertical_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final Shop_Page_Vertical.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

