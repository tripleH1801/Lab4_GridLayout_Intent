package com.example.gridlistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class List_Product_InCart extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ListView listView;
    private CartAdapter cartAdt;
    private List<Product> listProduct;
    private List<String> listSize;
    private Intent intent;
    private Bundle bundle;
    private List<Integer> listQuantity;

    public List_Product_InCart() {
        // Required empty public constructor
    }

    public static List_Product_InCart newInstance(String param1, String param2) {
        List_Product_InCart fragment = new List_Product_InCart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list__product__in_cart, container, false);
        intent = getActivity().getIntent();
        bundle = intent.getBundleExtra("data");
        List<Product> list = bundle.getParcelableArrayList("cart");
        listView = view.findViewById(R.id.listViewCart);

        listProduct = bundle.getParcelableArrayList("cart");
        listSize = bundle.getStringArrayList("listsize");
        listQuantity = new ArrayList<>();
        if(bundle.getIntegerArrayList("listquantity") != null){
            listQuantity = bundle.getIntegerArrayList("listquantity");
        }
        else{
            for (Product p: listProduct) {
                listQuantity.add(1);
            }
        }
        bundle.putIntegerArrayList("listquantity", (ArrayList<Integer>) listQuantity);
        cartAdt = new CartAdapter(getActivity(), R.layout.fragment_items_cart, listProduct, listSize, listQuantity);

        listView.setAdapter(cartAdt);
        return view;
    }

    public Intent getIntentFromFragment(){
        intent = getActivity().getIntent();
        return intent;
    }
}