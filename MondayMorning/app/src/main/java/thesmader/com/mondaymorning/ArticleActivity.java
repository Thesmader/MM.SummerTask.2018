package thesmader.com.mondaymorning;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ViewCompat.setTransitionName(findViewById(R.id.article_appbar),"EXTRA_IMAGE");
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.article_collapse_toolbar);
        ctl.setTitle("Article Title");
        ctl.setExpandedTitleColor(getResources().getColor(android.R.color.white));
    }
}
