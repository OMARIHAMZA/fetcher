package omari.hamza.fetcher.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.AppController;
import omari.hamza.fetcher.core.models.response.CateogriesResponse;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.views.adapters.CategoriesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LoadingDialog mLoadingDialog;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        mLoadingDialog = new LoadingDialog(getActivity());
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        getData();
        return view;
    }

    private void getData() {
        mLoadingDialog.show();
        AppController.getAllCategories(new Callback<CateogriesResponse>() {
            @Override
            public void onResponse(Call<CateogriesResponse> call, Response<CateogriesResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()){
                    mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    mRecyclerView.setAdapter(new CategoriesAdapter(getActivity(), response.body().getCategories()));
                }else{
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CateogriesResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
