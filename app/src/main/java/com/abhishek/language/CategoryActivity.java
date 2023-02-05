package com.abhishek.language;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoryActivityAdapter adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.category_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String name = getIntent().getStringExtra("key").toLowerCase();


        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("languages").child(name).child("category");
        FirebaseRecyclerOptions<DataModel> options = new FirebaseRecyclerOptions.Builder<DataModel>()
                .setQuery(dbReference, DataModel.class).build();

        adapter1 = new CategoryActivityAdapter(this, options, dbReference, name);
        recyclerView.setAdapter(adapter1);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Toast.makeText(CategoryActivity.this,"scroll", Toast. LENGTH_SHORT).show();
                if (!recyclerView.canScrollVertically(1)) {
//                    Toast.makeText(CategoryActivity.this,"scroll", Toast. LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapter1.stopListening();
    }

    @Override
    protected  void onResume() {
        super.onResume();
    }
}