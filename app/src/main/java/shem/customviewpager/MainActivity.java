package shem.customviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressView prog = (ProgressView) findViewById(R.id.progress);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                prog.setProgress(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        findViewById(R.id.stage_1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPager.setCurrentItem(0, true);
//            }
//        });
//        findViewById(R.id.stage_2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPager.setCurrentItem(1, true);
//            }
//        });
//        findViewById(R.id.stage_3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPager.setCurrentItem(2, true);
//            }
//        });
//        findViewById(R.id.stage_4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPager.setCurrentItem(3, true);
//            }
//        });
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
    public Fragment getItem(int position) {
            return TextFragment.newInstance(position + "");
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
