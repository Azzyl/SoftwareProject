package com.example.eWaiter;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    DatabaseReference db;
    private final ArrayList<String> categories = new ArrayList<>();
    private String restName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category);

        db = FirebaseDatabase.getInstance().getReference();
        restName = getIntent().getExtras().getString("BD");

        db.child("RestName").child(restName).child("Menu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot categorySnapshot: dataSnapshot.getChildren()) {
                    String entry = categorySnapshot.getKey();
                    categories.add(entry);
                }
                CategoryActivity.this.drawButtons(categories);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private void drawButtons(ArrayList<String> cats) {
        ViewGroup linearLayout = findViewById(R.id.linearlayout1);
        int i = 0;

        Button btn = new Button(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(params1);
        btn.setBackgroundResource(R.drawable.ic_shopping_basket_black_24dp);
        btn.setOnClickListener(basketOnClickListener);

        linearLayout.addView(btn);

        for (String item: cats) {
            Button bt = new Button(this);
            bt.setText(item);
            bt.setId(i);
            bt.setBackgroundResource(R.drawable.button);
            i++;
            bt.setOnClickListener(mGlobal_OnClickListener);
            bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(bt);
        }
    }

    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            String text = categories.get(v.getId());
            Intent intent = new Intent(CategoryActivity.this, DishActivity.class);
            intent.putExtra("category", text);
            intent.putExtra("BD", restName);
            startActivity(intent);
        }
    };

    final View.OnClickListener basketOnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            Intent intent = new Intent(CategoryActivity.this, BasketActivity.class);
            startActivity(intent);
        }
    };
}
