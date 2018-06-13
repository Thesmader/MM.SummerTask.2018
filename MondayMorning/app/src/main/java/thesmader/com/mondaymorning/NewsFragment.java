package thesmader.com.mondaymorning;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import static android.support.v7.widget.DividerItemDecoration.HORIZONTAL;

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

        //Get the String Arrays
        String mtitleSet[] = getResources().getStringArray(R.array.titleText);
        //String mbyLineSet[] = getResources().getStringArray(R.array.byLine);
        //String mdateLineSet[] = getResources().getStringArray(R.array.dateLine);
        //String mtagSet[] = getResources().getStringArray(R.array.Tag);

        //Recycler View
        news_recycler = rootView.findViewById(R.id.news_recycler);
        news_recycler.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(getContext(),mtitleSet/*,mbyLineSet,mdateLineSet,mtagSet*/);
        news_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        news_recycler.setAdapter(newsAdapter);
         //Divider
        //DividerItemDecoration itemDecoration = new DividerItemDecoration(news_recycler.getContext(), HORIZONTAL);
        //news_recycler.addItemDecoration(itemDecoration);

        //Card elevation
        final CardView cv;
        cv = rootView.findViewById(R.id.card);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            StateListAnimator animator = AnimatorInflater.loadStateListAnimator(getContext(),R.animator.lift);
            cv.setStateListAnimator(animator);
        }*/
        /*cv.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    cv.setCardElevation(8);
                }
                if (event.getAction() == MotionEvent.ACTION_UP){
                    cv.setCardElevation(0);
                }
                return false;
            }
        });*/

        return rootView;
    }
}