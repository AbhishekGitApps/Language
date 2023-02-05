package com.abhishek.language;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class FlashCardActivity extends AppCompatActivity  {

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

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
                Toast.makeText(FlashCardActivity.this, "swipe Up", Toast.LENGTH_SHORT).show();


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
                            Toast.makeText(FlashCardActivity.this, "swipe Up", Toast.LENGTH_SHORT).show();
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