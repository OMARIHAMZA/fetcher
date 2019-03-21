package omari.hamza.fetcher.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.views.adapters.CategoriesAdapter;

public class CategoriesFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        getData();
        return view;
    }

    private void getData() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(new CategoriesAdapter(getActivity()));
    }

}
