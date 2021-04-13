package com.example.gridlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context ctx;
    int layout;
    ArrayList<Product> listProduct;

    Fragment fragment_listItem;

    public CustomAdapter(Context ctx, int layout, ArrayList<Product> listProduct) {
        this.ctx = ctx;
        this.layout = layout;
        this.listProduct = listProduct;
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(ctx).inflate(layout, parent, false);
        TextView tvName = view.findViewById(R.id.name);
        TextView tvPrice = view.findViewById(R.id.price);
        ImageView img = view.findViewById(R.id.img);

        tvName.setText(listProduct.get(position).getName());
        tvPrice.setText(String.valueOf(listProduct.get(position).getPrice()));
        img.setImageResource(listProduct.get(position).getImg());
        return view;
    }
}
