package com.abhishek.language;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WordsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    WordsActivityAdapter adapter1;
    DatabaseReference dbReference;
    String languageName;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

//        dbReference = getIntent().getExtras().getParcelable("db");
        languageName = getIntent().getStringExtra("languageName");
        categoryName = getIntent().getStringExtra("categoryName");

        recyclerView = findViewById(R.id.words_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbReference = FirebaseDatabase.getInstance().getReference("languages").child(languageName).child("category").child(categoryName).child("words");
        FirebaseRecyclerOptions<DataModel2> options = new FirebaseRecyclerOptions.Builder<DataModel2>()
                .setQuery(dbReference, DataModel2.class).build();
        String key = dbReference.getKey();
        String x = dbReference.child(key).get().toString();

        adapter1 = new WordsActivityAdapter(this, options, dbReference);
        recyclerView.setAdapter(adapter1);

//        DataModel2 model = new DataModel2("test1", "test2");
//        dbReference.setValue(model);
//        dbReference.push().setValue(model);
//
//        Toast toast=Toast. makeText(getApplicationContext(),languageName + " + " + x, Toast. LENGTH_SHORT);
//        toast. show();


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