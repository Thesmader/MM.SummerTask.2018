package thesmader.com.mondaymorning;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
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

    public NewsAdapter(Context ct, List<AllNewsData> listItems) {
        ctx = ct;
        list = listItems;

    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View myView = inflater.inflate(R.layout.card_layout, parent, false);
        return new NewsHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        //holder.t1.setText(mTitleset[position]);
        AllNewsData data = list.get(position);
        holder.t1.setText(data.getTitle());
        holder.t2.setText(data.getByLine());
        holder.t3.setText(data.getDateLine());
        //holder.t4.setText(data.getTag());
        Glide.with(ctx).load(data.getImg_url())
                .thumbnail(0.25f)
                .apply(new RequestOptions().centerCrop())
                .into(holder.article_card_image);


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
            //t4 = itemView.findViewById(R.id.card_articleTag);
            article_card_image = itemView.findViewById(R.id.card_imageview);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StateListAnimator animator = AnimatorInflater.loadStateListAnimator(ctx, R.animator.lift);
                cardView.setStateListAnimator(animator);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ctx,ArticleActivity.class);
                        ctx.startActivity(i);
                    }
                });
            }
        }
    }
}
