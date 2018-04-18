package com.example.eWaiter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        for (int i = 0; i < list.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(list[i] + "    " + String.valueOf(set.get(list[i])));
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout ll = findViewById(R.id.linearlayout3);
            ll.addView(tv);
        }
    }


}
