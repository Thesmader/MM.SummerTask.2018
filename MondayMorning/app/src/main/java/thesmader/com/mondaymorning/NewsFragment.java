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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.Iterator;
import java.util.List;
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
    private static final String TAG = "Hihi";
    private static final String DATA_URL_ALLNEWS = "http://mondaymorning.nitrkl.ac.in/api/post/get/thisweek";
    private List<AllNewsData> listItems;

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
        //news_recycler.setHasFixedSize(false);
        //newsAdapter = new NewsAdapter(getContext(),mtitleSet/*,mbyLineSet,mdateLineSet,mtagSet*/);
        //news_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //news_recycler.setAdapter(newsAdapter);

        //RecyclerView items from server
        listItems = new ArrayList<>();
        loadRecyclerViewData();
        newsAdapter = new NewsAdapter(getContext(), listItems);
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

    private void loadRecyclerViewData() {

        StringRequest request = new StringRequest(Request.Method.GET, DATA_URL_ALLNEWS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray arr = obj.getJSONArray("posts");
                    //JSONArray arr_authors = obj.getJSONArray("posts").getJSONObject()
                    String authors[] = new String[arr.length()];
                    //System.arraycopy(authors, 0, arr.getString(0), 0, );
                    String categories[] = new String[arr.length()];

                    for (int i = 0; i < arr.length(); ++i) {
                        JSONObject o = arr.getJSONObject(i);
                        getAuthors(o);
                        AllNewsData data = new AllNewsData(
                                o.getString("post_title"),
                                /*o.getJSONArray("authors").toString()*/ getAuthors(o),
                                o.getString("post_publish_date"),
                                o.getString("featured_image"));
                        listItems.add(data);
                    }
                    Log.e(TAG, "Loaded " + obj.length());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        RequestQueue rq = Volley.newRequestQueue(getContext());
        rq.add(request);


    }

    public String getAuthors(JSONObject jo){

        String str= "";
        try {
            JSONArray arr_auth = jo.getJSONArray("authors");
            for(int i=0; i<arr_auth.length(); ++i) {
                if (i == arr_auth.length()-1)
                    str += arr_auth.get(i);
                else
                    str += arr_auth.get(i) + ",";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}