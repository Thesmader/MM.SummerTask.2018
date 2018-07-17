package thesmader.com.mondaymorning;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    Context ctx;
    private String[] mTitleset/*,mbyLineSet,mdateLineSet,mtagSet*/;
    private List<AllNewsData> list;
    FragmentManager fm;
    int post_id;

    public NewsAdapter(Context ct, List<AllNewsData> listItems,FragmentManager fm) {
        ctx = ct;
        list = listItems;
        this.fm = fm;

    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View myView = inflater.inflate(R.layout.card_layout, parent, false);
        final NewsHolder nh = new NewsHolder(myView);
        /*CardView cv = myView.findViewById(R.id.card);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("POST_ID", post_id);
                ctx.startActivity(new Intent(ctx, ArticleActivity.class).putExtra("POST_INFO",b));
            }
        });*/
        return nh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        //holder.t1.setText(mTitleset[position]);
        AllNewsData data = list.get(position);
        holder.t1.setText(data.getTitle());
        holder.t2.setText(data.getByLine());
        holder.t3.setText(data.getDateLine());
        holder.t4.setText(data.getCategories());
        Glide.with(ctx).load(data.getImg_url())
                .thumbnail(0.25f)
                .apply(new RequestOptions().centerCrop())
                .into(holder.article_card_image);
        post_id = data.getId();
        holder.itemView.findViewById(R.id.card).setTag(post_id);
        holder.itemView.findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();
                Bundle b = new Bundle();
                b.putInt("POST_ID", (int) v.getTag());
                ctx.startActivity(new Intent(ctx, ArticleActivity.class).putExtra("POST_INFO", b));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView t1,t2,t3,t4;
        public ImageView article_card_image;

        public NewsHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card);
            t1 = itemView.findViewById(R.id.card_title_text);
            t2 = itemView.findViewById(R.id.card_byline);
            t3 = itemView.findViewById(R.id.card_date_line);
            t4 = itemView.findViewById(R.id.card_articleTag);
            article_card_image = itemView.findViewById(R.id.card_imageview);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StateListAnimator animator = AnimatorInflater.loadStateListAnimator(ctx, R.animator.lift);
                cardView.setStateListAnimator(animator);
            }
            /*cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putInt("POST_ID", post_id);
                    ctx.startActivity(new Intent(ctx, ArticleActivity.class).putExtra("POST_INFO",b));
                }
            });*/
        }
    }
}
