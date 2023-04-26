package com.hci2023.recipeme;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodSelectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GridView foodGridView;
    private View rootView;

    // list of pairs of Food Name and image url
    private final Pair<String, String>[] FOOD_ELEMENTS = new Pair[] {
            new Pair<>("Tomatoes", "https://img.icons8.com/color/512/tomato.png"),
            new Pair<>("Cheese", "https://img.icons8.com/fluency/512/cheese.png")
    };


    public FoodSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodSelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodSelectionFragment newInstance(String param1, String param2) {
        FoodSelectionFragment fragment = new FoodSelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_food_selection, container, false);

        // Find the grid view in the layout
        GridView gridView = view.findViewById(R.id.food_grid);

        // Create a new adapter for the grid view
        ArrayAdapter<Pair<String, String>> adapter = new ArrayAdapter<Pair<String, String>>(
                getContext(),
                R.layout.food_element,
                FOOD_ELEMENTS
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Inflate the food element layout
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    view = inflater.inflate(R.layout.food_element, parent, false);
                }

                // Get the food name and icon URL from the current element in the array
                Pair<String, String> foodElement = getItem(position);
                String foodName = foodElement.first;
                String iconUrl = foodElement.second;

                // Find the food name and icon views in the layout
                TextView foodNameView = view.findViewById(R.id.food_name);
                ImageView iconView = view.findViewById(R.id.food_icon);

                // Set the food name and icon views to display the current food element
                foodNameView.setText(foodName);
                Picasso.get().load(iconUrl).into(iconView);

                return view;
            }
        };

        // Set the adapter for the grid view
        gridView.setAdapter(adapter);

        return view;
    }

    private class FoodGridAdapter extends BaseAdapter {

        private Context context;
        private String[] foodNames;
        private int[] foodIcons;

        public FoodGridAdapter(Context context, String[] foodNames, int[] foodIcons) {
            this.context = context;
            this.foodNames = foodNames;
            this.foodIcons = foodIcons;
        }

        @Override
        public int getCount() {
            return foodNames.length;
        }

        @Override
        public Object getItem(int position) {
            return foodNames[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Inflate the item view if needed
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.grid_item_food, parent, false);
            }

            // Get the views in the item view
            ImageView iconView = convertView.findViewById(R.id.food_icon);
            TextView nameView = convertView.findViewById(R.id.food_name);

            // Set the icon and name for this item
            iconView.setImageResource(foodIcons[position]);
            nameView.setText(foodNames[position]);

            return convertView;
        }
    }
}