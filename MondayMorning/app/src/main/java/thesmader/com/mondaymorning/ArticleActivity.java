package thesmader.com.mondaymorning;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

public class ArticleActivity extends AppCompatActivity {

    RecyclerView articleRecycler;
    private ArrayList<ArticleModel> articleList = new ArrayList<>();
    int articleID;
    private String articleURLPrefix = "http://mondaymorning.nitrkl.ac.in/api/post/get/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        //Collapsing toolbar
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.article_collapse_toolbar);
        ctl.setTitle("Article Title");
        ctl.setExpandedTitleColor(getResources().getColor(android.R.color.white));

        //Make URL ready
        Bundle bundle = getIntent().getBundleExtra("POST_INFO");
        articleID = bundle.getInt("POST_ID");
        articleURLPrefix += Integer.toString(articleID);

        //Recycler view and server work
        //articleList.add(new ArticleModel(ArticleModel.TEXT,0,"Hello Hey!!!"));
        //articleList.add(new ArticleModel(ArticleModel.IMAGE, R.drawable.smiley,"Image"));
        loadContent();
        ArticleAdapter adapter = new ArticleAdapter(articleList,ArticleActivity.this,2);
        RecyclerView rv = findViewById(R.id.article_recycler);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

    }

    private void loadContent() {
        StringRequest request = new StringRequest(Request.Method.GET, articleURLPrefix, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject jsonObject = object.getJSONObject("post");
                    JSONArray array = jsonObject.getJSONArray("post_content");
                    for(int i=0; i<array.length(); ++i){
                        JSONObject o = array.getJSONObject(i);
                        if (o.getInt("type") != 1) {
                            ArticleModel model = new ArticleModel(ArticleModel.TEXT,o.getString("content"));
                            articleList.add(model);
                        }
                        else{
                            ArticleModel model = new ArticleModel(ArticleModel.IMAGE, o.getString("content"));
                            articleList.add(model);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
