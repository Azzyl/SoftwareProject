package com.example.eWaiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eWaiter.basket.Basket;

import java.util.HashMap;

public class BasketActivity extends AppCompatActivity {

    HashMap<String, Integer> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        set = Basket.getAll();
        String[] list = set.keySet().toArray(new String[0]);

        int tvID = 1001;
        int btID = 2001;

        for (int i = 0; i < list.length; i++) {
            TextView tv = new TextView(this);
            tv.setId(tvID);
            tvID++;
            tv.setText(list[i] + "    " + String.valueOf(set.get(list[i])));
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tv.setLeft(0);
            Button bt = new Button(this);
            bt.setId(btID);
            btID++;
            bt.setOnClickListener(bOnClickListener);
            LinearLayout ll = findViewById(R.id.linearlayout3);
            ll.addView(tv);
            ll.addView(bt);
        }
    }


    final View.OnClickListener bOnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            System.out.println(v.getId());
//            String text = dishes.get(v.getId());
//            Intent intent = new Intent(DishActivity.this, InfoActivity.class);
//            intent.putExtra("category", category);
//            intent.putExtra("BD", restName);
//            intent.putExtra("Dish", text);
//            startActivity(intent);
        }
    };

    final View.OnClickListener tOnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            System.out.println(v.getId());
//            String text = dishes.get(v.getId());
//            Intent intent = new Intent(DishActivity.this, InfoActivity.class);
//            intent.putExtra("category", category);
//            intent.putExtra("BD", restName);
//            intent.putExtra("Dish", text);
//            startActivity(intent);
        }
    };



}
