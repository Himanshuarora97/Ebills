package com.ebills.alphamind.ebills.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Storage.Recent.RecentBillStore;

import org.json.JSONArray;
import org.json.JSONException;


public class MainActivityAllBillsRecyclerAdapter extends RecyclerView.Adapter<MainActivityAllBillsRecyclerAdapter.ViewHolder> {

    JSONArray jsonArray;
    Context ctx;

    public MainActivityAllBillsRecyclerAdapter(JSONArray jsonArray) {
        Log.e("onBindViewHolder: ", String.valueOf(jsonArray));
        this.jsonArray = jsonArray;
        this.ctx = ctx;
    }

    public void SaveInRecent(String pName, String sName, String priceName) throws JSONException {
        RecentBillStore recentBillStore = new RecentBillStore(ctx);
        recentBillStore.saveBill(pName, sName, priceName);
    }


    @Override
    public MainActivityAllBillsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainActivityAllBillsRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_fragments_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainActivityAllBillsRecyclerAdapter.ViewHolder holder, int position) {

            try {
                holder.pName.setText(jsonArray.getJSONObject(position).getString("product_name"));
                holder.sName.setText(jsonArray.getJSONObject(position).getString("shop_name"));
                holder.priceName.setText(jsonArray.getJSONObject(position).getString("price_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView pName, sName, priceName;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ClipArt);
            pName = itemView.findViewById(R.id.ProductName);
            sName = itemView.findViewById(R.id.ShopName);
            priceName = itemView.findViewById(R.id.PriceName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        SaveInRecent(pName.getText().toString(), sName.getText().toString(), priceName.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

}
