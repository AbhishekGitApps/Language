package com.abhishek.language;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FlashCardActivity extends AppCompatActivity  {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetector gestureDetector;
    TextView nativeTextView;
    TextView englishTextView;
    View hiding_view;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        nativeTextView = findViewById(R.id.native_textView);
        englishTextView = findViewById(R.id.english_textView);
        hiding_view = findViewById(R.id.hiding_view);

        dbReference = FirebaseDatabase.getInstance().getReference("languages").child("spanish").child("category").child("phrases").child("words");
        dbReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

            }
        });


//        ViewGroup.LayoutParams params = hiding_view.getLayoutParams();
//        params.height = nativeTextView.getHeight();
//        hiding_view.setLayoutParams(params);



        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
//                Toast.makeText(FlashCardActivity.this, "swipe Up", Toast.LENGTH_SHORT).show();
            }

            @Override
//          public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
                boolean result = false;
                float diffY = moveEvent.getY() - downEvent.getY();
                float diffX = moveEvent.getX() - downEvent.getX();

//              Determine which is greater movement along X-axis or Y-axis.
                if(Math.abs(diffX) > Math.abs(diffY)) {
//                  right or left swipe.
                    if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if(diffX > 0) {
//                       swipe right.
                        } else {
//                         swipe left.
                        }
                        result = true;
                    }
                } else {
//                   up or downl swipe.
                    if(Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if(diffY > 0) {
//                    swipe Down.
                        } else {
//                    swipe Up.



                            String str = Integer.toString(nativeTextView.getHeight());
//                            Toast.makeText(FlashCardActivity.this, str, Toast.LENGTH_SHORT).show();
                            englishTextView.animate().translationX(englishTextView.getWidth()).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    englishTextView.setText("Hello");
                                    englishTextView.animate().translationX(0);

                                }
                            });

                            nativeTextView.animate().translationY(nativeTextView.getHeight()).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    nativeTextView.setText("Hello");
                                    nativeTextView.animate().translationY(0).setDuration(200);
                                }
                            });

                        }
                        result = true;
                    }
                }
                return result;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}