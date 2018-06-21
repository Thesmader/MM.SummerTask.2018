package thesmader.com.mondaymorning;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Buzz extends Fragment {
    public static Buzz newInstance() {
        Buzz fragment = new Buzz();
        return fragment;
    }

    //RecyclerView news_recycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buzz, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
