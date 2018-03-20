package com.ebills.alphamind.ebills.Adapters;

import android.content.Context;
import android.content.res.TypedArray;
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


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    JSONArray jsonArray;
    Context ctx;

    public ProductsAdapter(Context ctx, JSONArray jsonArray) {
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

        Log.e("onBindViewHolder: ",jsonArray.toString() );
        int color = Color.parseColor(getRandomMaterialColor("400"));
        try {
            String pN = jsonArray.getJSONObject(position).getString("STOCKITEMNAME");
            String Quat = jsonArray.getJSONObject(position).getString("BILLEDQTY");
            String amt = jsonArray.getJSONObject(position).getString("AMOUNT");
            TextDrawable myDrawable = TextDrawable.builder().beginConfig()
                    .textColor(Color.WHITE)
                    .useFont(Typeface.DEFAULT)
                    .toUpperCase()
                    .endConfig()
                    .buildRound(pN.substring(0, 1), color);
            holder.imageView.setImageDrawable(myDrawable);
            holder.pName.setText(pN);
            holder.quant.setText("Quantity" + Quat);
            holder.priceName.setText(amt);
        } catch (JSONException e) {
            Log.e( "onBindViewHolder: ",e.toString() );
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView pName, quant, priceName;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            pName = itemView.findViewById(R.id.ProductName);
            quant = itemView.findViewById(R.id.Quantity);
            priceName = itemView.findViewById(R.id.PriceName);

        }
    }

    public String getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = ctx.getResources().getIdentifier("colors_" + typeColor, "array", ctx.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = ctx.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return String.format("#%06X", (0xFFFFFF & returnColor));
    }

}
