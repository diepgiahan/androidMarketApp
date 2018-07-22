package com.example.admin.market;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarmenu);

        setSupportActionBar(toolbar);
        new SlidingRootNavBuilder(this).withToolbarMenuToggle(toolbar).withMenuOpened(false).withMenuLayout(R.layout.menu_left_drawer).inject();


        View checkMenu= LayoutInflater.from(this).inflate(R.layout.menu_left_drawer,null,false);
        Button homebtn;
        Button cartbtn;
        Button accountbtn;

        homebtn=(Button)findViewById(R.id.homebtn);
        cartbtn=(Button)findViewById(R.id.cartbtn);
        accountbtn=(Button)findViewById(R.id.accountbtn);


        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // init();
                //getSupportFragmentManager().beginTransaction().commit();
                TextView title=(TextView)findViewById(R.id.toolbarTitle);
                title.setText("Home");
                android.support.v4.app.Fragment homeselected=null;
                homeselected=new HomeActivity();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeselected).commit();
            }
        });

        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                TextView title=(TextView)findViewById(R.id.toolbarTitle);
                title.setText("Cart");
                android.support.v4.app.Fragment cartselected=null;
                cartselected=new CartActivity();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,cartselected).commit();

            }
        });
        accountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView title=(TextView)findViewById(R.id.toolbarTitle);
                LinearLayout sliderdotspanel = (LinearLayout)findViewById(R.id.SliderDots);
                title.setText("Account");
                android.support.v4.app.Fragment accselected=null;
                accselected=new AccountActivity();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,accselected).commit();

            }
        });




    }
    void init()
    {
        TextView title=(TextView)findViewById(R.id.toolbarTitle);
        title.setText("Home");
        android.support.v4.app.Fragment homeselected=null;
        homeselected=new HomeActivity();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeselected).commit();

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout)findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

    }


    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }

}
