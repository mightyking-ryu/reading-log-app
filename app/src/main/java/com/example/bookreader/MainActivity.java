package com.example.bookreader;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView mMainNav;

    private HomeFragment homeFragment;
    private HistoryFragment historyFragment;
    private BookmarkFragment bookmarkFragment;
    private SearchFragment searchFragment;
    MenuItem prevMenuItem;

    private ViewPager viewPager;

    public static HistoryDatabase historyDatabase;
    public static BookmarkDatabase bookmarkDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyDatabase = Room.databaseBuilder(getApplicationContext(), HistoryDatabase.class, "historydb").allowMainThreadQueries().build();
        bookmarkDatabase = Room.databaseBuilder(getApplicationContext(), BookmarkDatabase.class, "bookmarkdb").allowMainThreadQueries().build();



        viewPager = (ViewPager) findViewById(R.id.main_view);
        mMainNav = findViewById(R.id.main_nav);



        // setup navigation bar to open fragments
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {

                switch (Item.getItemId()) {

                    case R.id.nav_home :
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_history :
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_bookmark :
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.nav_search :
                        viewPager.setCurrentItem(3);
                        break;

                }
                return false;

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null) {
                } else {
                    mMainNav.getMenu().getItem(0).setChecked(false);
                }

                Log.d("page", "onPageSelected: "+position);
                mMainNav.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mMainNav.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        historyFragment = new HistoryFragment();
        bookmarkFragment = new BookmarkFragment();
        searchFragment = new SearchFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(historyFragment);
        adapter.addFragment(bookmarkFragment);
        adapter.addFragment(searchFragment);
        viewPager.setAdapter(adapter);
    }






}
