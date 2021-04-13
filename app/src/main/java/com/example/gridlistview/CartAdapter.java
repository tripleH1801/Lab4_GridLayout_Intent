package com.example.gridlistview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends BaseAdapter{
    private Context ctx;
    private int layout;
    private List<Product> listProduct;
    private List<String> listSize;
    private List<Integer> listQuantity;
    private List<Integer> listQ;
    private int quantity;
    private int subPrice;
    private TextView tvSub;
    private TextView tvTotal;

    public CartAdapter(Context ctx, int layout, List<Product> listProduct, List<String> listSize, List<Integer> listQuantity) {
        this.ctx = ctx;
        this.layout = layout;
        this.listProduct = listProduct;
        this.listSize = listSize;
        this.listQuantity = listQuantity;
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

        TextView tvName = view.findViewById(R.id.tvNameCart);
        TextView tvPrice = view.findViewById(R.id.tvPriceCart);
        TextView tvSize = view.findViewById(R.id.tvSizeCart);
        ImageView img = view.findViewById(R.id.imageViewCart);
        TextView tvQuantity = view.findViewById(R.id.tvQuantity);
        //thieu thuế

        quantity = listQuantity.get(position);

        tvName.setText(listProduct.get(position).getName());
        tvSize.setText(listSize.get(position));
        tvQuantity.setText(String.valueOf(quantity));
        img.setImageResource(listProduct.get(position).getImg());

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("$#.###", dfs);
        tvPrice.setText(df.format(listProduct.get(position).getPrice() * quantity));


        Activity a = (Activity) ctx;
        Intent intent = a.getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        tvSub= a.findViewById(R.id.tvSub);
        tvTotal= a.findViewById(R.id.tvTotalPrice);

        listQ = null;
        if(intent != null){
            if(bundle != null){
                List<Product> list = bundle.getParcelableArrayList("cart");
                listProduct = bundle.getParcelableArrayList("cart");
                listQ = bundle.getIntegerArrayList("listquantity");
            }
        }

        subPrice = 0;
        int i = 0;
        for (Product p: listProduct) {
            int c = listQuantity.get(i);
            subPrice += p.getPrice() * c;
            i++;
        }

        ImageButton btnAdd = view.findViewById(R.id.imgbtnAdd);
        btnAdd.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(tvQuantity.getText().toString());
                quantity ++;
                tvQuantity.setText(String.valueOf(quantity));
                tvPrice.setText(df.format(listProduct.get(position).getPrice()*quantity));

                //cong gia tri tong hoa don
                subPrice += listProduct.get(position).getPrice();
                //set lai gia tri tong hoa don o Cart activity
                tvTotal.setText(df.format(subPrice));
                tvSub.setText(df.format(subPrice));

                listQ.set(position, quantity);
                bundle.putIntegerArrayList("listquantity", (ArrayList<Integer>) listQ);
                intent.putExtra("data", bundle);
            }
        });
        ImageButton btnMinus = view.findViewById(R.id.imgbtnMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(tvQuantity.getText().toString());
                quantity --;
                tvQuantity.setText(String.valueOf(quantity));
                tvPrice.setText(df.format(listProduct.get(position).getPrice()*quantity));

                //tru gia tri tong hoa don
                subPrice -= listProduct.get(position).getPrice();
                //set lai gia tri tong hoa don o Cart activity
                tvTotal.setText(df.format(subPrice));
                tvSub.setText(df.format(subPrice));

                listQuantity.set(position, quantity);
                bundle.putIntegerArrayList("listquantity", (ArrayList<Integer>) listQuantity);
                intent.putExtra("data", bundle);
            }
        });

        return view;
    }
}
