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
import com.ebills.alphamind.ebills.Storage.CacheStorage.CacheStorage;
import com.ebills.alphamind.ebills.Storage.Recent.RecentBillStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    JSONArray jsonArray;
    Context ctx;

    public ProductsAdapter(Context ctx , JSONArray jsonArray) {
        Log.e("onBindViewHolder: ", String.valueOf(jsonArray));
        this.jsonArray = jsonArray;
        this.ctx = ctx;
    }

    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_fragments_card, parent, false));
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter.ViewHolder holder, int position) {


        try {
            String pN = jsonArray.getJSONObject(position).getString("STOCKITEMNAME");
            String Quat = jsonArray.getJSONObject(position).getString("BILLEDQTY");
            String amt = jsonArray.getJSONObject(position).getString("AMOUNT");

            holder.pName.setText(pN);
            holder.quant.setText(Quat);
            holder.priceName.setText(amt);
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
        TextView pName,quant, priceName;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            pName = itemView.findViewById(R.id.ProductName);
            quant = itemView.findViewById(R.id.Quantity);
            priceName = itemView.findViewById(R.id.PriceName);

        }
    }

}
