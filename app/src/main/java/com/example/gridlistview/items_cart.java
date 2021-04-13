package com.example.gridlistview;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class items_cart extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private SendData_Interface sending;
    private int quantity;
    private int priceBefore;

    private String mParam1;
    public items_cart() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sending = (SendData_Interface) context;
    }

    public static items_cart newInstance(String param1, String param2) {
        items_cart fragment = new items_cart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items_cart, container, false);

//        TextView tvQuantity = view.findViewById(R.id.tvQuantity);
//        quantity = Integer.parseInt(String.valueOf(tvQuantity.getText()));
//
//        TextView tvPrice = view.findViewById(R.id.tvPriceCart);
//        priceBefore = Integer.parseInt(String.valueOf(tvPrice.getText())) / quantity;
//
//        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
//        dfs.setDecimalSeparator(',');
//        dfs.setGroupingSeparator('.');
//        DecimalFormat df = new DecimalFormat("$#.###", dfs);
//
//        ImageButton btnAdd = view.findViewById(R.id.imgbtnAdd);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quantity = Integer.parseInt(tvQuantity.getText().toString());
//                quantity ++;
//                tvQuantity.setText(String.valueOf(quantity));
//                tvPrice.setText(df.format(priceBefore*quantity));
//            }
//        });
//        ImageButton btnMinus = view.findViewById(R.id.imgbtnMinus);
//        btnMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quantity = Integer.parseInt(tvQuantity.getText().toString());
//                quantity --;
//                tvQuantity.setText(String.valueOf(quantity));
//                tvPrice.setText(df.format(priceBefore*quantity));
//            }
//        });

        return view;
    }

//    public void flowData(){
//        sending.sendData();
//    }

}