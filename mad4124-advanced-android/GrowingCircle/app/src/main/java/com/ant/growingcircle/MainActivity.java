package com.ant.growingcircle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.Color;
import 	android.animation.*;
import android.widget.ImageView;
import android.view.animation.*;
import android.content.res.Resources;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int circleSize = 50;
    ImageView iView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int maxWidth = getMaxSize();

        final ShapeDrawable circle = new ShapeDrawable(new OvalShape());
        circle.getPaint().setColor(Color.GREEN);
        circle.setIntrinsicHeight(5);
        circle.setIntrinsicWidth(5);

        iView = findViewById(R.id.ImageView1);
        iView.setImageDrawable(circle);

        System.out.println(getMaxSize());
        System.out.println(getMaxScale());
        System.out.println(getDuration());

        Animation anim = new ScaleAnimation(
                1f, getMaxScale(), // Start and end values for the X axis scaling
                1f, getMaxScale(), // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(getDuration());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.REVERSE);
        iView.startAnimation(anim);

        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), getRandomColor(), getRandomColor());
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                int animatedValue = (int)updatedAnimation.getAnimatedValue();
                //iView.setColorFilter(animatedValue);
            }
        });

        valueAnimator.addListener(createAnimatorListener());
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }

    private int getDuration() {
        int maxSize = getMaxSize();

        return (maxSize - circleSize)*10;
    }

    private float getMaxScale() {
        int maxSize = getMaxSize();

        return (maxSize)/Float.valueOf(circleSize);
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

    private Animator.AnimatorListener createAnimatorListener() {
        Animator.AnimatorListener animatorListener
                = new Animator.AnimatorListener() {

            public void onAnimationStart(Animator animation) {

            }

            public void onAnimationRepeat(Animator animation) {
                iView.setColorFilter(getRandomColor());
            }

            public void onAnimationEnd(Animator animation) {

            }

            public void onAnimationCancel(Animator animation) {

            }
        };
        return animatorListener;
    }
}

