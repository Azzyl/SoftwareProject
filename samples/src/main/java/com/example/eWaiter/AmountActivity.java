package com.example.eWaiter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AmountActivity extends AppCompatActivity {

    static int amount = 0;
    TextView tv = findViewById(R.id.amountOfDish);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);
        tv.setText(amount);
    }

    public void increase(View view) {
        amount += 1;
        tv.setText(amount);
    }

    public void decrease(View view) {
        if (amount > 0)
            amount -= 1;
        tv.setText(amount);
    }

}
