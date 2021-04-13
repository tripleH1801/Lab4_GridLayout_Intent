package com.example.gridlistview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ListItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ListItem extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private GridView gridView;
    private CustomAdapter adt;
    private ArrayList<Product> arrayList;
    private ArrayList<Product> cartList;
    private Context ctx;
    private Intent intent;
    private Bundle bundle;

    public Fragment_ListItem() {
    }

    protected Fragment_ListItem(Parcel in) {
        mParam1 = in.readString();
        mParam2 = in.readString();
    }

    public static Fragment_ListItem newInstance(String param1) {
        Fragment_ListItem fragment = new Fragment_ListItem();
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
        ctx = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment__list_item, container, false);
        arrayList = new ArrayList<>();
        ArrayList<String> ar = new ArrayList<>();
        cartList = new ArrayList<>();
        intent = getActivity().getIntent();

        bundle = new Bundle();
        if(intent != null){
            bundle = intent.getBundleExtra("data");
            if(bundle == null){
                bundle = new Bundle();
            }
            if(intent.getBundleExtra("data") != null){
                cartList = intent.getBundleExtra("data").getParcelableArrayList("cart");
            }
        }


        ar.add("XL");
        ar.add("XXL");
        ar.add("L");
        ar.add("M");
        ar.add("S");
        if(mParam1 == "just for you"){
            arrayList.removeAll(arrayList);
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
            arrayList.add(new Product("computer", 80, ar, R.drawable.middle_term_nhom_t5_2));
            arrayList.add(new Product("Black Google", 80, ar, R.drawable.middle_term_nhom_t5_3));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Pink Google", 80, ar, R.drawable.images));
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
            arrayList.add(new Product("computer", 80, ar, R.drawable.middle_term_nhom_t5_2));
            arrayList.add(new Product("Black Google", 80, ar, R.drawable.middle_term_nhom_t5_3));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Pink Google", 80, ar, R.drawable.images));
        }
        else  if(mParam1 == "winter collection"){
            arrayList.removeAll(arrayList);
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
            arrayList.add(new Product("Google", 80, ar, R.drawable.middle_term_nhom_t5_1));
        }
        else  if(mParam1 == "special collection"){
            arrayList.removeAll(arrayList);
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
            arrayList.add(new Product("Yellow Google", 80, ar, R.drawable.yellow_google));
        }

        gridView = view.findViewById(R.id.gridView);
        adt = new CustomAdapter(getActivity(), R.layout.item_product, arrayList);
        gridView.setAdapter(adt);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Product item = (Product) adt.getItem(position); //xài 2 cách lấy item đều dc
                Product item = arrayList.get(position);
                intent = new Intent(getActivity(), DetailsProduct.class);
                bundle.putParcelable("product", item); // bắt buộc Class product phải implement Parcelable
                bundle.putParcelableArrayList("cart", cartList);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
        return view;
    }
}