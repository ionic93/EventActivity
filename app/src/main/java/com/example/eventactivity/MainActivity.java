package com.example.eventactivity;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        TextView textView;
        GestureDetector detector;
        ScrollView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        sv = findViewById(R.id.scrollView);
        View view = findViewById(R.id.view);

        view.setOnTouchListener((v, motionEvent) -> {
            int action = motionEvent.getAction();

            float curX = motionEvent.getX();
            float curY = motionEvent.getY();

            if (action == MotionEvent.ACTION_DOWN) {
                println("손가락 눌림 "+curX+","+curY);
            } else if (action == MotionEvent.ACTION_MOVE) {
                println("손가락 움직임 "+curX+","+curY);
            } else if (action == MotionEvent.ACTION_UP) {
                println("손가락 뗌 "+curX+","+curY);
            }
            autoScroll();
            return true;
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress() 호출됨.");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp() 호출됨.");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float x, float y) {
                println("onScroll() 호출됨 : " + x + "," + y);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress() 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float x, float y) {
                println("onFling() 호출됨 : " + x + "," + y);
                return true;
            }
        });
        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                autoScroll();
                detector.onTouchEvent(event);
                return true;
            }
        });
    }
    public void println(String data) {
        textView.append(data+"\n");
    }

    public void autoScroll() {
        sv.smoothScrollTo((int)sv.getX()+5,(int)sv.getY()+5);
    }
}