package thesmader.com.mondaymorning;

import android.app.Fragment;
import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThisWeek extends AppCompatActivity {

    Typeface typeface;
    Toolbar mToolbar;
    MenuInflater inflater;
    MenuItem mSearch;
    private BottomNavigationView bnv;
    //RecyclerView news_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make the activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_this_week);

        SearchView sv;
        sv = findViewById(R.id.ic_action_search);
        //sv.setQueryHint("Search");
        //int id = sv.getContext().getResources().getIdentifier("android:id/search_src_text",null, null);
        //TextView textView = (TextView) sv.findViewById(id);
        //textView.setTextColor(Color.BLACK);


        //Setting up the appbar
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Adding tabs to the tabLayout
        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("THIS WEEK"), 0);
        tabLayout.addTab(tabLayout.newTab().setText("CATEGORIES"), 1);

        //Setup the PagerAdapter
        final ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);


        //BottomNavigationView
        bnv = (BottomNavigationView) findViewById(R.id.bottom_nav);
        //BottomNavigationView Behavior
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bnv.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());


        //Handling bottom navigation clicks
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.news:
                        selectedFragment = NewsFragment.newInstance();
                        break;
                    case R.id.featured:
                        selectedFragment = FeaturedFragment.newInstance();
                        break;
                    case R.id.buzz:
                        selectedFragment = Buzz.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, selectedFragment);
                //transaction.remove(selectedFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
        });

        //Manually displaying News fragment
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, NewsFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();*/

        bnv.getMenu().getItem(0).setChecked(true);

        //TabLayout Clicks
        /*tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(1)){
                    startActivity(new Intent(ThisWeek.this,CategoriesActivity.class));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        mSearch = menu.findItem(R.id.ic_action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        return super.onCreateOptionsMenu(menu);

    }
}
