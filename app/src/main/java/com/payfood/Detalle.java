package com.payfood;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.payfood.fragments.Info;
import com.payfood.fragments.MenuA;
import com.payfood.fragments.Promociones;
import com.payfood.fragments.Reservas;
import com.payfood.ui.SlidingTabLayout;


public class Detalle extends ActionBarActivity {

    private static String[] titles = {"INFORMACION", "MENU", "PROMOCIONES", "RESERVAS"};
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    public static final String key = "POS";
    private int item_pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item_pos = getIntent().getIntExtra(key,0);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(MenUtil.rest[item_pos]);
        setContentView(R.layout.activity_detalle);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new AppSectionsPagerAdapter(getSupportFragmentManager(), item_pos));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        setSlidingTabLayoutContentDescriptions();
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mSlidingTabLayout.announceForAccessibility(titles[position]);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setSlidingTabLayoutContentDescriptions() {
        for (int i = 0; i < titles.length; i++) {
            mSlidingTabLayout.setContentDescription(i, titles[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class AppSectionsPagerAdapter extends FragmentStatePagerAdapter {

        Bundle bundle;

        public AppSectionsPagerAdapter(FragmentManager fm, int pos) {
            super(fm);
            bundle = new Bundle();
            bundle.putInt(key, pos);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment frag;
            switch (i) {
                case 0:
                    frag = new Info();
                    break;
                case 1:
                    frag =  new MenuA();
                    break;
                case 2:
                    frag = new Promociones();
                    break;
                case 3:
                    frag = new Reservas();
                    break;
                default:
                    frag = new Info();
                    break;
            }
            frag.setArguments(bundle);
            return frag;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
