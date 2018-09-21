package com.droidman.exhangeapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.droidman.exhangeapp.R;

public class ViewPagerActivity extends FragmentActivity {

    private DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    private ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.setPageTransformer(false, new IntroPageTransformer());
    }

    public void cancel(View v){
        Intent i = new Intent(this, UserActivity.class);
        startActivity(i);
    }

    private class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return IntroPageFragment.newInstance(Color.parseColor("#1400FF"), position); // blue
                default:
                    return IntroPageFragment.newInstance(Color.parseColor("#4EFF40D6"), position); // green
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private class IntroPageTransformer implements ViewPager.PageTransformer{

        @Override
        public void transformPage(View page, float position) {

            int pagePosition = (int) page.getTag();

            int pageWidth = page.getWidth();
            float pageWidthTimesPosition = pageWidth * position;
            float absPosition = Math.abs(position);
            if (position <= -1.0f || position >= 1.0f) {

            } else if (position == 0.0f) {

            } else {

                View title = page.findViewById(R.id.title);
                title.setAlpha(1.0f - absPosition);
                View description = page.findViewById(R.id.description);
                description.setTranslationY(-pageWidthTimesPosition / 2f);
                description.setAlpha(1.0f - absPosition);
                View computer = page.findViewById(R.id.computer);
                if (pagePosition == 0 && computer != null) {
                    computer.setAlpha(1.0f - absPosition);
                    computer.setTranslationX(-pageWidthTimesPosition * 1.5f);
                }
                if (position < 0) {

                } else {

                }
            }
        }

    }
}
