package com.example.eWaiter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        int tvID = 1001; //ID for TextView for name of dish
        int amID = 1501; //ID for TextView for amount of dishes
        int btID = 2001; //ID for button to delete

        for (int i = 0; i < list.length; i++) {
            if (set.get(list[i]) > 0) {

                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                Typeface font_ = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");


                TextView tv = new TextView(this);
                tv.setId(tvID);
                tvID++;
                tv.setText(list[i]);

                LinearLayout.LayoutParams params0 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                int dp = (int) (300 / Resources.getSystem().getDisplayMetrics().density);
                params0.width = dp;
                params0.weight = 4.0f;
                tv.setLayoutParams(params0);
                tv.setTypeface(font_);
                tv.setTextSize(25);

                TextView am = new TextView(this);
                am.setId(amID);
                amID++;
                am.setText(String.valueOf(set.get(list[i])));
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.weight = 1.0f;
                am.setLayoutParams(params1);
                am.setTypeface(font_);
                am.setTextSize(25);

                Button bt = new Button(this);
                bt.setId(btID);
                bt.setText("remove");
                bt.setBackgroundResource(R.drawable.button_transparent);
                btID++;
                bt.setOnClickListener(bOnClickListener);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params2.weight = 2.0f;
                bt.setLayoutParams(params2);

                layout.addView(tv);
                layout.addView(am);
                layout.addView(bt);

                LinearLayout ll = findViewById(R.id.linearlayout3);
                ll.addView(layout);
            }
        }
    }

    public void call(View view) {
        Snackbar mySnackbar = Snackbar.make(view, "Waiter was called", Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

    public void checkout(View view) {
        Intent intent = new Intent(BasketActivity.this, CheckoutActivity.class);
        startActivity(intent);
    }

    final View.OnClickListener bOnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            @SuppressLint("ResourceType") int tvID = v.getId() - 1000;
            @SuppressLint("ResourceType") int amID = v.getId() - 500;
            TextView tv = findViewById(tvID);
            Basket.remove(tv.getText().toString());
            ((ViewManager)findViewById(v.getId()).getParent()).removeView(findViewById(v.getId()));
            ((ViewManager)findViewById(tvID).getParent()).removeView(findViewById(tvID));
            ((ViewManager)findViewById(amID).getParent()).removeView(findViewById(amID));
        }
    };

}