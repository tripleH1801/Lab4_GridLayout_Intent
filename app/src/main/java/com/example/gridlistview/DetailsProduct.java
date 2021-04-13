package com.example.gridlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailsProduct extends AppCompatActivity {

    private Intent intent;
    private Intent testIntent;
    private Product product;
    private Button btnAddCart;
    private Bundle bundle;
    private List<String> listSize;
    private String size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);

        intent = getIntent();
        bundle = new Bundle();
        listSize = new ArrayList<>();

        product = new Product();
        if(intent.getBundleExtra("data")!= null){
            bundle = intent.getBundleExtra("data");
            product = bundle.getParcelable("product");
            if(bundle.getStringArrayList("listsize") != null)
                listSize = bundle.getStringArrayList("listsize");
        }
        else{
            Toast.makeText(this, "Loi da xay ra", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
        }

        TextView tvName = findViewById(R.id.nameDetails);
        tvName.setText(product.getName());
        TextView tvPrice = findViewById(R.id.priceDetails);
        tvPrice.setText(String.valueOf(product.getPrice()));
        ImageView img = findViewById(R.id.imgDetails);
        img.setImageResource(product.getImg());

        ImageButton btnX = findViewById(R.id.btnSizeX);
        ImageButton btnXL = findViewById(R.id.btnSizeXL);
        ImageButton btnL = findViewById(R.id.btnSizeL);
        ImageButton btnS = findViewById(R.id.btnSizeS);

        btnAddCart = findViewById(R.id.btnCheckout);

        intent = new Intent(this, CartActivity.class);
        size ="x";

        //---------------------
        testIntent = new Intent(this, MainActivity.class);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Product> list = new ArrayList<>();
                if(bundle.getParcelableArrayList("cart") != null)
                    list = bundle.getParcelableArrayList("cart");

                list.add(product);
                bundle.putParcelableArrayList("cart", list);

                listSize.add(size);
                bundle.putStringArrayList("listsize", (ArrayList<String>) listSize);
                intent.putExtra("data", bundle);
                testIntent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnX.setImageAlpha(50);
                size = "x";
            }
        });
        btnXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnXL.setImageAlpha(50);
                size = "xl";
            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnL.setImageAlpha(50);
                size = "l";
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnS.setImageAlpha(50);
                size = "s";
            }
        });

    }
}