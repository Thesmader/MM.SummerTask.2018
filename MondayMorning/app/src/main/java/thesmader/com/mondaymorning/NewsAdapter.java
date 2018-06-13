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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    Context ctx;
    private String[] mTitleset/*,mbyLineSet,mdateLineSet,mtagSet*/;

    public NewsAdapter(Context ct, String[] titleSet/*, String[] byLineSet, String[] dateLineSet, String[] tagSet*/) {
        ctx = ct;
        mTitleset = titleSet;

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
        holder.t1.setText(mTitleset[position]);
    }

    @Override
    public int getItemCount() {
        return mTitleset.length;
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView t1;

        public NewsHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card);
            t1 = itemView.findViewById(R.id.card_title_text);
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
