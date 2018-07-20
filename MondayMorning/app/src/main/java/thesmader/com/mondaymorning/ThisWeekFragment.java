package thesmader.com.mondaymorning;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


public class ThisWeekFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ThisWeekFragment() {
        // Required empty public constructor
    }

    /*public static ThisWeekFragment newInstance(String param1, String param2) {
        ThisWeekFragment fragment = new ThisWeekFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_this_week, container, false);
         /*final ViewPager viewPager = v.findViewById(R.id.nest_view_pager);

        //ViewPager
        //setUpViewPager(viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NewsFragment(),"AllNews");
        adapter.addFragment(new FeaturedFragment(),"Featured");
        adapter.addFragment(new Buzz(),"Buzz");
        viewPager.setAdapter(adapter);

        //Bottom Navigation
        BottomNavigationView bnv = v.findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.news:
                        setItem(0,viewPager);
                        break;
                    case R.id.featured:
                        setItem(1,viewPager);
                        break;
                    case R.id.buzz:
                        setItem(2, viewPager);
                        break;
                }
                return true;
            }
        });

        bnv.getMenu().getItem(0).setChecked(true);

        //BottomNavigationView Behavior
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bnv.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());
        //bnv.setVisibility(View.VISIBLE);*/

        return v;
    }

    //@Override
    /*public void onAttach(Context context) {
        super.onAttach(context);
        final ViewPager viewPager = Objects.requireNonNull(getView()).findViewById(R.id.nest_view_pager);

        //ViewPager
        //setUpViewPager(viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NewsFragment(),"AllNews");
        adapter.addFragment(new FeaturedFragment(),"Featured");
        adapter.addFragment(new Buzz(),"Buzz");
        viewPager.setAdapter(adapter);

        //Bottom Navigation
        BottomNavigationView bnv = getView().findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.news:
                        setItem(0,viewPager);
                        break;
                    case R.id.featured:
                        setItem(1,viewPager);
                        break;
                    case R.id.buzz:
                        setItem(2, viewPager);
                        break;
                }
                return true;
            }
        });

        bnv.getMenu().getItem(0).setChecked(true);

        //BottomNavigationView Behavior
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bnv.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());
        //bnv.setVisibility(View.VISIBLE);
    }*/

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NewsFragment(), "AllNews");
        adapter.addFragment(new FeaturedFragment(), "Featured");
        adapter.addFragment(new Buzz(), "Buzz");
        viewPager.setAdapter(adapter);
    }


    public void setItem(int fragmentNumber, ViewPager viewPager) {
        viewPager.setCurrentItem(fragmentNumber);
    }
}
