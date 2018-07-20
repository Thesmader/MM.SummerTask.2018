package thesmader.com.mondaymorning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.util.List;
import java.util.Objects;

public class FeaturedFragment extends Fragment {
    public static FeaturedFragment newInstance() {
        FeaturedFragment fragment = new FeaturedFragment();
        return fragment;
    }

    private static final String TAG = "Hihi";
    private static final String DATA_URL_FEATURED = "http://mondaymorning.nitrkl.ac.in/api/post/get/featured";
    RecyclerView news_recycler;
    NewsAdapter newsAdapter;
    private List<AllNewsData> listItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        news_recycler = v.findViewById(R.id.news_recycler);
        listItems = new ArrayList<>();
        loadRecyclerViewData();
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void loadRecyclerViewData() {

        StringRequest request = new StringRequest(Request.Method.GET, DATA_URL_FEATURED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray arr = obj.getJSONObject("top4").getJSONArray("posts");
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
                                o.getString("featured_image"),
                                getTag(o),
                                o.getInt("post_id"));
                        listItems.add(data);
                    }
                    Log.e(TAG, "Loaded " + arr.length());
                    FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                    newsAdapter = new NewsAdapter(getContext(), listItems, fm);
                    news_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    news_recycler.setAdapter(newsAdapter);
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

        RequestQueue rq = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        rq.add(request);


    }

    public String getAuthors(JSONObject jo) {

        String str = "";
        try {
            JSONArray arr_auth = jo.getJSONArray("authors");
            for (int i = 0; i < arr_auth.length(); ++i) {
                if (i == arr_auth.length() - 1)
                    str += arr_auth.get(i);
                else
                    str += arr_auth.get(i) + ",";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getTag(JSONObject jo) {
        String str = "";
        try {
            JSONArray arr_categories = jo.getJSONArray("categories");
            str = arr_categories.getJSONObject(0).getString("post_category_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}