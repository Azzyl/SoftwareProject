package com.example.eWaiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.eWaiter.basket.Basket;

public class AmountActivity extends AppCompatActivity {

    int amount = 0;
    String dishName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_amount);

        TextView tv = findViewById(R.id.amountOfDish);
        tv.setText(String.valueOf(amount));
        dishName = this.getIntent().getStringExtra("dishName");
    }

    public void increase(View view) {
        amount += 1;
        TextView tv = findViewById(R.id.amountOfDish);
        tv.setText(String.valueOf(amount));
    }

    public void decrease(View view) {
        if (amount > 0)
            amount -= 1;
        TextView tv = findViewById(R.id.amountOfDish);
        tv.setText(String.valueOf(amount));
    }

    public void add(View view) {
        Basket.add(dishName, amount);
        finish();
//        System.out.println(added);
//        System.out.println(Basket.getsize());
    }


}
