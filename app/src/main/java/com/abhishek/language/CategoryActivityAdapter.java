package com.abhishek.language;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

public class CategoryActivityAdapter extends FirebaseRecyclerAdapter<DataModel, CategoryActivityAdapter.myviewholder> {
    private Context context;
    String languageName;
    DatabaseReference dbReference;

    public CategoryActivityAdapter(Context context, @NonNull FirebaseRecyclerOptions<DataModel> options, DatabaseReference dbReference, String languageName) {
        super(options);
        this.context = context;
        this.dbReference = dbReference;
        this.languageName = languageName;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull DataModel model) {

        holder.name.setText(model.getName());
        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WordsActivity.class);
                intent.putExtra("categoryName", model.getName().toLowerCase());
                intent.putExtra("languageName", languageName.toLowerCase());
                context.startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_layout, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        TextView name;
        CardView button;
        ImageView logo;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_textView);
            button = itemView.findViewById(R.id.card_button);
        }
    }
}
