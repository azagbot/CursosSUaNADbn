package com.example.tvcom.cursos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tvcom on 02/11/16.
 */

public class IntroActivity extends AppCompatActivity {
    private IntroManager mIntroManager;
    private ViewPager mViewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private int[] layouts;
    private Button mSKIP;
    private Button mNEXT;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntroManager = new IntroManager(this);
        if (!mIntroManager.Check()){
            mIntroManager.setFirst(false);
            startActivity(new Intent(IntroActivity.this,cursos.class));
            finish();
        }
        if(Build.VERSION.SDK_INT >=21){
            getWindow().getDecorView().setSystemUiVisibility
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.intro_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout)findViewById(R.id.layout_dots);
        mSKIP=(Button)findViewById(R.id.btn_skip);
        mNEXT=(Button)findViewById(R.id.btn_next);
        layouts = new int[] {R.layout.intro_screen_1,R.layout.intro_screen_2,R.layout.intro_screen_3};
        addBottomDots(0);
        changeStatusbarColor();
        viewPagerAdapter = new ViewPagerAdapter();
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(viewListener);
        mSKIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,cursos.class));
                finish();
            }
        });
        mNEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if(current<layouts.length){
                    mViewPager.setCurrentItem(current);
                } else{
                    startActivity(new Intent(IntroActivity.this,cursos.class));
                    finish();
                }
            }
        });
    }
    private void addBottomDots(int position){
        dots = new TextView[layouts.length];
        int[] active = getResources().getIntArray(R.array.dot_active);
        int[] inactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for (int i=0;i<dots.length;i++){
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(inactive[position]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(inactive[position]);
        }
    }
    private int getItem(int i){
        return mViewPager.getCurrentItem()+1;
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if (position==layouts.length-1){
                mNEXT.setText("PROCEED");
                mSKIP.setVisibility(View.GONE);
            }
            else{
                mNEXT.setText("NEXT");
                mSKIP.setVisibility(View.VISIBLE);
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void changeStatusbarColor(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }
        @Override
        public int getCount() {
            return layouts.length;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            View v = (View) object;
            container.removeView(v);
        }
    }
}
