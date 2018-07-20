package thesmader.com.mondaymorning;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {

    RecyclerView articleRecycler;
    private ArrayList<ArticleModel> articleList = new ArrayList<>();
    int articleID;
    private String articleURLPrefix = "http://mondaymorning.nitrkl.ac.in/api/post/get/", imageURL;
    private String Me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_this_week);

        setContentView(R.layout.activity_article);

        //Make URLs ready
        Bundle bundle = getIntent().getBundleExtra("POST_INFO");
        articleID = bundle.getInt("POST_ID");
        articleURLPrefix += Integer.toString(articleID);
        imageURL = bundle.getString("POST_IMAGE");


        //Collapsing toolbar
        AppBarLayout appBarLayout = findViewById(R.id.article_appbar);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.article_toolbar);
        toolbar.setNavigationIcon(R.drawable.whiteback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArticleActivity.this, ThisWeek.class));
                finish();
            }
        });
        final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.article_collapse_toolbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    ctl.setTitle("Title");
                    isShow = true;
                } else if (isShow) {
                    ctl.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        ctl.setTitle("Article Title");

        //ctl image
        ImageView imageView = findViewById(R.id.article_ctl_image);
        Glide.with(this).load(imageURL).thumbnail(0.25f).apply(new RequestOptions().centerCrop()).into(imageView);

        //share fab click
        final FloatingActionButton fab = findViewById(R.id.share_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Title");
                    String sAux = "\n";
                    sAux = sAux + articleURLPrefix + "\n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Share via"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //Recycler view and server work
        //articleList.add(new ArticleModel(ArticleModel.TEXT,0,"Hello Hey!!!"));
        //articleList.add(new ArticleModel(ArticleModel.IMAGE, R.drawable.smiley,"Image"));
        loadContent();
        ArticleAdapter adapter = new ArticleAdapter(articleList,ArticleActivity.this,2);
        RecyclerView rv = findViewById(R.id.article_recycler);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        //show and hide fab
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

    }

    private void loadContent() {

        StringRequest request = new StringRequest(Request.Method.GET, articleURLPrefix, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject jsonObject = object.getJSONObject("post");
                    JSONArray array = jsonObject.getJSONArray("post_content");
                    Log.e(Me, Integer.toString(array.length()));
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
                    ArticleAdapter adapter = new ArticleAdapter(articleList, ArticleActivity.this, 2);
                    RecyclerView rv = findViewById(R.id.article_recycler);
                    rv.setLayoutManager(new LinearLayoutManager(ArticleActivity.this, LinearLayoutManager.VERTICAL, false));
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(adapter);

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
