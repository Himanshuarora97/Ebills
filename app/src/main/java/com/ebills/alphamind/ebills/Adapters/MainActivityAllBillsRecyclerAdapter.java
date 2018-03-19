package com.ebills.alphamind.ebills.Adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebills.alphamind.ebills.MainActivity;
import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Storage.CacheStorage.CacheStorage;
import com.ebills.alphamind.ebills.Storage.Recent.RecentBillStore;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;


public class MainActivityAllBillsRecyclerAdapter extends RecyclerView.Adapter<MainActivityAllBillsRecyclerAdapter.ViewHolder> {

    JSONArray jsonArray;
    Context ctx;

    public MainActivityAllBillsRecyclerAdapter(Context ctx , JSONArray jsonArray) {
        Log.e("onBindViewHolder: ", String.valueOf(jsonArray));
        this.jsonArray = jsonArray;
        this.ctx = ctx;
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

                        //Save
                        SaveBill();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    public void SaveInRecent(String pName, String sName, String priceName) throws JSONException {
        RecentBillStore recentBillStore = new RecentBillStore(ctx);
        recentBillStore.saveBill(pName, sName, priceName);
    }

    //Save Bill
    private void SaveBill() throws JSONException {
        // HTML saved

        CacheStorage cacheStorage = new CacheStorage(ctx);

        // Saving file
        String str = null;
        cacheStorage.saveFile(str);

    }

}
