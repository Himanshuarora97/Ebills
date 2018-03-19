package com.ebills.alphamind.ebills.Adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.utils.TextDrawable;

import org.json.JSONArray;
import org.json.JSONException;


public class MainActivityRecentRecyclerAdapter extends RecyclerView.Adapter<MainActivityRecentRecyclerAdapter.ViewHolder> {

    private JSONArray jsonArray;

    public MainActivityRecentRecyclerAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView date, sName, priceName;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            date = itemView.findViewById(R.id.date);
            sName = itemView.findViewById(R.id.shop_name);
            priceName = itemView.findViewById(R.id.PriceName);

        }
    }


    @Override
    public MainActivityRecentRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainActivityRecentRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_bills_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainActivityRecentRecyclerAdapter.ViewHolder holder, int position) {

        int color = Color.parseColor("#000000");

        try {
            String shopName = jsonArray.getJSONObject(position).getJSONObject("seller").getString("name");
            String price1 = jsonArray.getJSONObject(position).getJSONObject("invoice").getString("amount");
            String price = String.valueOf(Float.parseFloat(price1)*-1);
            String date = jsonArray.getJSONObject(position).getJSONObject("invoice").getString("date");
            String year = date.substring(0,4);
            String month = date.substring(4,6);
            String dat = date.substring(6);
            date = dat + "/" + month + "/" + year;
            TextDrawable myDrawable = TextDrawable.builder().beginConfig()
                    .textColor(Color.WHITE)
                    .useFont(Typeface.DEFAULT)
                    .toUpperCase()
                    .endConfig()
                    .buildRound(shopName.substring(0, 1), color);
            holder.imageView.setImageDrawable(myDrawable);
            holder.sName.setText(shopName);
            holder.date.setText(date);
            holder.priceName.setText(price);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

}

