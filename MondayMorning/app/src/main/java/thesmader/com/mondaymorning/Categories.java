package thesmader.com.mondaymorning;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class Categories extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GridView grid;
    String[] Categories = {"DEPARTMENTS", "CAMPUS", "VIEWS", "CAREER", "ALUMNI", "DD & CWC"};
    int[] images = {R.drawable.dep, R.drawable.campus, R.drawable.views, R.drawable.career, R.drawable.alumni, R.drawable.desk};


    public Categories() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Categories newInstance() {
        Categories fragment = new Categories();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        CategoryGridAdapter adapter = new CategoryGridAdapter(container.getContext(), Categories, images);

        grid = (GridView) rootView.findViewById(R.id.categoryGrid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}
