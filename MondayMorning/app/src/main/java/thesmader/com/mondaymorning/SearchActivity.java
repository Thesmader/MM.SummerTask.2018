package thesmader.com.mondaymorning;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;

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

public class SearchActivity extends AppCompatActivity {

    String DATA_URL_SEARCH = "http://mondaymorning.nitrkl.ac.in/api/search/";
    private RecyclerView news_recycler;
    private List<AllNewsData> listItems;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        news_recycler = findViewById(R.id.news_recycler);
        listItems = new ArrayList<>();
        Intent i = getIntent();
        DATA_URL_SEARCH += i.getStringExtra("query");
        loadRecyclerViewData();
        FragmentManager fm = Objects.requireNonNull(this.getSupportFragmentManager());
    }

    private void loadRecyclerViewData() {
        StringRequest request = new StringRequest(Request.Method.GET, DATA_URL_SEARCH, new Response.Listener<String>() {
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
                                getAuthors(o),
                                o.getString("post_publish_date"),
                                o.getString("featured_image"),
                                getTag(o),
                                o.getInt("post_id"));
                        listItems.add(data);
                    }
                    FragmentManager fm = Objects.requireNonNull(getSupportFragmentManager());
                    newsAdapter = new NewsAdapter(SearchActivity.this, listItems, fm);
                    news_recycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
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

        RequestQueue rq = Volley.newRequestQueue(SearchActivity.this);
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
