package com.example.dgois.labtest01;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView iView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ShapeDrawable square = new ShapeDrawable(new RectShape());
        square.getPaint().setColor(Color.BLUE);
        square.setIntrinsicHeight(5);
        square.setIntrinsicWidth(5);
        iView = findViewById(R.id.ImageView1);
        iView.setImageDrawable(square);

        ConstraintLayout root =  (ConstraintLayout) findViewById(R.id.rootLayout);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        iView.getLocationOnScreen( originalPos );

        final int yDest = dm.heightPixels - 330;
        final int xDest = dm.widthPixels;
        int velocity = 550;
        final int time = yDest/velocity * 1000;
        System.out.println("**********" + yDest + "   **********  " + dm.heightPixels);

        final ObjectAnimator oAni = ObjectAnimator.ofFloat(iView, "translationY", yDest);
        oAni.setDuration(2000);
        oAni.setRepeatCount(Animation.INFINITE);
        oAni.setRepeatMode(ValueAnimator.REVERSE);
        oAni.start();

        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText speedField = (EditText) findViewById(R.id.speed);
                System.out.println("Speed input : " + speedField.getText());
                int velocity = Integer.parseInt(speedField.getText().toString());
                int time = yDest/velocity * 1000;
                oAni.setDuration(time);

            }
        });

        Button changeColor = (Button) findViewById(R.id.changeColor);
        changeColor.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("change color");
                iView.setColorFilter(getRandomColor());
            }
        });

        Button pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Pause");
                oAni.pause();
            }
        });

        Button resume = (Button) findViewById(R.id.resume);
        resume.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Resume");
                oAni.resume();
            }
        });

        Button up = (Button) findViewById(R.id.up);
        up.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                oAni.cancel();
                System.out.println("Up");
                final ObjectAnimator oAni = ObjectAnimator.ofFloat(iView, "translationY", yDest);
                oAni.setDuration(time);
                oAni.setRepeatCount(Animation.INFINITE);
                oAni.setRepeatMode(ValueAnimator.REVERSE);
                oAni.start();

            }
        });

        Button down = (Button) findViewById(R.id.down);
        down.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Down");

            }
        });

        Button right = (Button) findViewById(R.id.right);
        right.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                oAni.cancel();
                System.out.println("Right : " + xDest);
                final ObjectAnimator oAni = ObjectAnimator.ofFloat(iView, "translationX", xDest/2);
                oAni.setDuration(2000);
                oAni.setRepeatCount(Animation.INFINITE);
                oAni.setRepeatMode(ValueAnimator.REVERSE);
                oAni.start();
            }
        });

        Button left = (Button) findViewById(R.id.left);
        left.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Left");

            }
        });
    }

    private Animator.AnimatorListener createAnimatorListener() {
        Animator.AnimatorListener animatorListener
                = new Animator.AnimatorListener() {

            public void onAnimationStart(Animator animation) {

            }

            public void onAnimationRepeat(Animator animation) {

            }

            public void onAnimationEnd(Animator animation) {

            }

            public void onAnimationCancel(Animator animation) {

            }
        };
        return animatorListener;
    }


    private int getMaxSize() {
        int maxWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int maxHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        return (((maxWidth < maxHeight ? maxWidth : maxHeight)/10)*10);
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return 0xFF000000 | rnd.nextInt(0xFFFFFF);
    }
}
