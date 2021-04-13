package com.example.gridlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private Intent intent;
    private Bundle bundle;
    private double subPrice;
    private double totalPrice;

    private TextView tvSub;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        intent = getIntent();
        bundle = intent.getBundleExtra("data");
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("data", bundle);

        subPrice = 0;
        totalPrice = 0;
        List<Product> listProduct = new ArrayList<>();
        listProduct = bundle.getParcelableArrayList("cart");
        List<Integer> listQuantity = new ArrayList<>();
        if(bundle.getIntegerArrayList("listquantity") != null){
            listQuantity = bundle.getIntegerArrayList("listquantity");
            listQuantity.add(1);
        }
        else{
            for (Product p: listProduct) {
                listQuantity.add(1);
            }
        }

        int i = 0;
        for (Product p: listProduct) {
            int c = listQuantity.get(i);
            subPrice += p.getPrice() * c;
            i++;
        }

        Locale localeDefault = Locale.getDefault();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(localeDefault);
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("$#.###", dfs);

        tvSub= findViewById(R.id.tvSub);
        tvSub.setText(df.format(subPrice));


        tvTotal= findViewById(R.id.tvTotalPrice);
        tvTotal.setText(df.format(subPrice));

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentCartList, List_Product_InCart.newInstance("","")).commit();
        Button btnCheck = findViewById(R.id.btnCheckout);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("data", bundle);
                List<Product> list = new ArrayList<>();
                list = bundle.getParcelableArrayList("cart");
                startActivity(intent);
            }
        });
    }
}