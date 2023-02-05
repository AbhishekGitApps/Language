package com.abhishek.language;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WordsActivityAdapter extends FirebaseRecyclerAdapter<DataModel2, WordsActivityAdapter.myviewholder> {
    private Context context;
    String chidId;
    DatabaseReference dbReference;

    public WordsActivityAdapter(Context context, @NonNull FirebaseRecyclerOptions<DataModel2> options, DatabaseReference dbReference) {
        super(options);
        this.context = context;
        this.dbReference = dbReference;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull DataModel2 model) {
        holder.english_textView.setText(model.getEnglish_text());
        holder.native_textView.setText(model.getNative_text());

//        holder.english_textView.setText("english");
//        holder.native_textView.setText("native");
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_layout, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView english_textView;
        TextView native_textView;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            english_textView = itemView.findViewById(R.id.english_textView);
            native_textView = itemView.findViewById(R.id.native_textView);


        }
    }
}
