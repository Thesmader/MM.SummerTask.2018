package thesmader.com.mondaymorning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryGridAdapter extends BaseAdapter {

    private final String[] web;
    private final int[] image;
    private Context ctx;

    public CategoryGridAdapter(Context c, String[] web, int[] Imageid) {
        this.ctx = c;
        this.image = Imageid;
        this.web = web;
    }


    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(ctx);
            grid = inflater.inflate(R.layout.category_grid_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.category);
            ImageView imageView = (ImageView) grid.findViewById(R.id.category_image);
            textView.setText(web[position]);
            imageView.setImageResource(image[position]);
            grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
