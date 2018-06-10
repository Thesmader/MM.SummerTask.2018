package thesmader.com.mondaymorning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    Context ctx;
    private String[] mTitleset,mbyLineSet,mdateLineSet,mtagSet;

    public NewsAdapter(Context ct, String[] titleSet/*, String[] byLineSet, String[] dateLineSet, String[] tagSet*/){
        ctx = ct;
        mTitleset = titleSet;
        //mbyLineSet = byLineSet;
        //mdateLineSet = dateLineSet;
        //mtagSet = tagSet;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View myView = inflater.inflate(R.layout.card_layout,parent,false);
        return new NewsHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        holder.t1.setText(mTitleset[position]);
        //holder.t2.setText(mbyLineSet[position]);
        //holder.t3.setText(mdateLineSet[position]);
        //holder.t4.setText(mtagSet[position]);
    }

    @Override
    public int getItemCount() {
        return mTitleset.length;
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView t1,t2,t3,t4;
        public ImageView i1,i2;

        public NewsHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card);
            t1 = itemView.findViewById(R.id.card_title_text);
            //t2 = itemView.findViewById(R.id.card_byline);
            //t3 = itemView.findViewById(R.id.card_date_line);
            //t4 = itemView.findViewById(R.id.card_articleTag);
            //i1 = itemView.findViewById(R.id.card_imageview);
            //i2 = itemView.findViewById(R.id.card_noOfViews);
        }
    }
}
