package com.ebills.alphamind.ebills.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebills.alphamind.ebills.R;

import org.json.JSONArray;

/**
 * Created by anmol on 15/3/18.
 */

public class ShopPageActivity_HorizontalProductsAdapter extends RecyclerView.Adapter<ShopPageActivity_HorizontalProductsAdapter.ViewHolder> {

    Context ctx;

    public ShopPageActivity_HorizontalProductsAdapter(Context ctx) {
        this.ctx = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ShopPageActivity_HorizontalProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopPageActivity_HorizontalProductsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_products_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final ShopPageActivity_HorizontalProductsAdapter.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

