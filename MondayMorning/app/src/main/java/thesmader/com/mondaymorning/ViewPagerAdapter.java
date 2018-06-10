package thesmader.com.mondaymorning;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

public ViewPagerAdapter(FragmentManager fm){
    super(fm);
}

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NewsFragment();
            case 1:
                return new FeaturedFragment();
            //case 2:
              //  return null;
            default:
                return null;
        }
    }
}
