package com.example.eWaiter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
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

        int tvID = 1001;                        //ID for TextView for name of dish
        int amID = 1501;                        //ID for TextView for amount of dishes
        int btID = 2001;                        //ID for button to delete

        for (int i = 0; i < list.length; i++) {
            if (set.get(list[i]) > 0) {

                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);

                TextView tv = new TextView(this);
                tv.setId(tvID);
                tvID++;
                tv.setText(list[i]);

                TextView am = new TextView(this);
                am.setId(amID);
                amID++;
                am.setText(String.valueOf(set.get(list[i])));

                Button bt = new Button(this);
                bt.setId(btID);
                bt.setText("remove");
                btID++;
                bt.setOnClickListener(bOnClickListener);

                layout.addView(tv);
                layout.addView(am);
                layout.addView(bt);

                LinearLayout ll = findViewById(R.id.linearlayout3);
                ll.addView(layout);
            }
        }
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
