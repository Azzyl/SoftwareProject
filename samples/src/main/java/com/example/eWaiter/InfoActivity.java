package com.example.eWaiter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    DatabaseReference db;
    private final ArrayList<String> infos = new ArrayList<>();
    private String category;
    private String restName;
    private String dishName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info);

        db = FirebaseDatabase.getInstance().getReference();
        category = this.getIntent().getExtras().getString("category");
        restName = this.getIntent().getExtras().getString("BD");
        dishName = this.getIntent().getExtras().getString("Dish");

        db.child("RestName").child(restName).child("Menu").child(category).child(dishName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot categorySnapshot: dataSnapshot.getChildren()) {
                    String entry = (String) categorySnapshot.getValue();
                    infos.add(entry);
                }
                InfoActivity.this.drawText(infos);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private void drawText(ArrayList<String> dishes) {

        Typeface font_ = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");

        TextView edit0 = findViewById(R.id.textview0);
        edit0.setText(dishes.get(0));
        TextView edit1 = findViewById(R.id.textview1);
        edit1.setText(dishes.get(1));
        TextView edit2 = findViewById(R.id.textview2);
        edit2.setText(dishes.get(2));

        edit0.setTypeface(font_);
        edit1.setTypeface(font_);
        edit2.setTypeface(font_);
    }

    public void addToBasket(View view) {
        Intent intent = new Intent(InfoActivity.this, AmountActivity.class);
        intent.putExtra("dishName", dishName);
        startActivity(intent);
    }

    public void openBasket(View view) {
        Intent intent = new Intent(this, BasketActivity.class);
        startActivity(intent);
    }

}
