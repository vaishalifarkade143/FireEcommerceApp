package com.example.fireecommerceapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fireecommerceapp.R;
import com.example.fireecommerceapp.Adapter.sliderAdapter;

public class OnBoardingActivity extends AppCompatActivity {

    //23.
    ViewPager viewPager;

    //27*
    Button getbtn;
    LinearLayout dotsLayout;
    sliderAdapter slideadapter;

    //25.
    TextView[] dots;

    //29. before that create anim directory
    Animation animation;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_on_boarding);

        //hide toolbar/actionBar
        getSupportActionBar().hide();

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);

        //27 *
        getbtn = findViewById(R.id.get_started_btn);//

        addDots(0);

                //27*
        viewPager.addOnPageChangeListener(changeListener);

            //24  onboard_activity ka form hum set kar rahe on addapter //call adapter
        slideadapter = new sliderAdapter(this);
        viewPager.setAdapter(slideadapter);

        //30.
        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, RegistrationActivity.class));
                //31*
                finish();//no go to menimest n change intent to RegisterationActivity
            }
        });
    }

    //25.method to show three dots
    private void addDots(int position)
    {
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0 ; i< dots.length;i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length > 0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.pink));
        }
    }
    //26.
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener()
    {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {
            //27. for visibility n invisible of button
            addDots(position);

            if(position == 0)
            {
                getbtn.setVisibility(View.INVISIBLE);
            }
            else if(position == 1)
            {
                getbtn.setVisibility(View.INVISIBLE);
            }
            else {
                //29.*  for slider, animation on button
                animation = AnimationUtils.loadAnimation(OnBoardingActivity.this,R.anim.solid_animation);
                getbtn.setAnimation(animation);//
                getbtn.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}