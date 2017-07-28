package com.sdacademy.zientara.rafal.awesomeapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.models.Product;
import com.sdacademy.zientara.rafal.awesomeapp.viewHolders.ProductViewHolder;

import java.util.List;

/**
 * Created by Evil on 28.07.2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private final LayoutInflater inflater;
    private List<Product> productList;

    public ProductsAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        Integer isPurchased = product.getIsPurchased();
        if(isPurchased!=null)
            holder.checkBox.setChecked(isPurchased != 0);

        //// TODO: 28.07.2017 more details
        holder.details.setText(product.getDetails());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setIsPurchased(isChecked ? 1 : 0);
                product.save();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
