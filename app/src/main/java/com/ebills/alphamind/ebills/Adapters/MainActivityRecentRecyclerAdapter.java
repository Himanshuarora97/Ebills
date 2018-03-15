package com.ebills.alphamind.ebills.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebills.alphamind.ebills.R;

import org.json.JSONArray;
import org.json.JSONException;


public class MainActivityRecentRecyclerAdapter extends RecyclerView.Adapter<MainActivityRecentRecyclerAdapter.ViewHolder> {

    JSONArray jsonArray;

    public MainActivityRecentRecyclerAdapter(JSONArray jsonArray) {

        this.jsonArray = jsonArray;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView pName , sName , priceName;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.ClipArt);
            pName = itemView.findViewById(R.id.ProductName);
            sName = itemView.findViewById(R.id.ShopName);
            priceName = itemView.findViewById(R.id.PriceName);

        }
    }


    @Override
    public MainActivityRecentRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainActivityRecentRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_fragments_card,parent,false));
    }

    @Override
    public void onBindViewHolder(final MainActivityRecentRecyclerAdapter.ViewHolder holder, int position) {

        for (int i = 0; i<jsonArray.length() ; i++){
            try {
                holder.pName.setText(jsonArray.getJSONObject(i).getString("product_name"));
                holder.sName.setText(jsonArray.getJSONObject(i).getString("shop_name"));
                holder.priceName.setText(jsonArray.getJSONObject(i).getString("price_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return jsonArray.length();
    }

}

