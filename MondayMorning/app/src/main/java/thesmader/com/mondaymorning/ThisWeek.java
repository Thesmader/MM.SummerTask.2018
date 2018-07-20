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
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    DrawerLayout mDrawer;
    Toolbar mToolbar;
    MenuInflater inflater;
    MenuItem mSearch;
    private BottomNavigationView bnv;
    private ViewPager viewPager;
    //RecyclerView news_recycler;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
        mDrawer = findViewById(R.id.drawer);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Adding tabs to the tabLayout
        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("THIS WEEK"), 0);
        tabLayout.addTab(tabLayout.newTab().setText("CATEGORIES"), 1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame, NewsFragment.newInstance());
                        transaction.remove(Categories.newInstance());
                        transaction.commit();*/
                        //setItem(3);
                        setItem(3);
                        //bnv.setVisibility(View.GONE);
                        break;
                    case 0:
                        /*
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame, Categories.newInstance());
                        transaction.commit();*/
                        setItem(0);
                        //setItem(0);
                        //bnv.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Setup the PagerAdapter
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager(viewPager);





        //Handling bottom navigation clicks

        bnv = findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.news:
                        setItem(0);

                        break;
                    case R.id.featured:
                        setItem(1);
                        break;
                    case R.id.buzz:
                        setItem(2);
                        break;
                }
                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.frame, selectedFragment);
                //transaction.remove(selectedFragment);
                //transaction.addToBackStack(null);
                //transaction.commit();
                return true;
            }
        });

        bnv.getMenu().getItem(0).setChecked(true);
        //BottomNavigationView Behavior
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bnv.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());
        //bnv.setVisibility(View.VISIBLE);

    }

    private void setUpTabPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new ThisWeekFragment(), "ThisWeek");
        adapter.addFragment(new Categories(), "Categories");
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsFragment(), "AllNews");
        adapter.addFragment(new FeaturedFragment(), "Featured");
        adapter.addFragment(new Buzz(), "Buzz");
        adapter.addFragment(new Categories(), "Categories");
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        mSearch = menu.findItem(R.id.ic_action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchIntent = new Intent(ThisWeek.this, SearchActivity.class);
                searchIntent.putExtra("query", query);
                startActivity(searchIntent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    public void setItem(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }

    public void setTabItem(int fragNumber) {
        viewPager.setCurrentItem(fragNumber);
    }

    public void linClick(View view) {
        startActivity(new Intent(this, Login.class));
    }
}
