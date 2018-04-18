package com.example.eWaiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DishActivity extends AppCompatActivity {

    DatabaseReference db;
    private final ArrayList<String> dishes = new ArrayList<>();
    private String category;
    private String restName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dish);

        db = FirebaseDatabase.getInstance().getReference();
        category = this.getIntent().getExtras().getString("category");
        restName = this.getIntent().getExtras().getString("BD");

        db.child("RestName").child(restName).child("Menu").child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot categorySnapshot: dataSnapshot.getChildren()) {
                    String entry = categorySnapshot.getKey();
                    dishes.add(entry);
                }
                DishActivity.this.drawButtons(dishes);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private void drawButtons(ArrayList<String> dishes) {
        ViewGroup linearLayout = findViewById(R.id.linearlayout2);
        int i = 0;
        for (String item: dishes) {
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
            String text = dishes.get(v.getId());
            Intent intent = new Intent(DishActivity.this, InfoActivity.class);
            intent.putExtra("category", category);
            intent.putExtra("BD", restName);
            intent.putExtra("Dish", text);
            startActivity(intent);
        }
    };
}
