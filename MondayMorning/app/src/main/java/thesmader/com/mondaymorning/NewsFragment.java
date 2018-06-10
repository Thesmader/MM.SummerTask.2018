package thesmader.com.mondaymorning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class NewsFragment extends Fragment {

    public NewsFragment(){
        //Required empty constructor
    }
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    RecyclerView news_recycler;
    NewsAdapter newsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news,container,false);
        String mtitleSet[] = getResources().getStringArray(R.array.titleText);
        //String mbyLineSet[] = getResources().getStringArray(R.array.byLine);
        //String mdateLineSet[] = getResources().getStringArray(R.array.dateLine);
        //String mtagSet[] = getResources().getStringArray(R.array.Tag);
        news_recycler = rootView.findViewById(R.id.news_recycler);
        news_recycler.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(getContext(),mtitleSet/*,mbyLineSet,mdateLineSet,mtagSet*/);
        news_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        news_recycler.setAdapter(newsAdapter);
        return rootView;
    }
}