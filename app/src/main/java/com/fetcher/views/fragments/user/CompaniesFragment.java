package com.fetcher.views.fragments.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import com.fetcher.R;
import com.fetcher.core.controllers.UserController;
import com.fetcher.core.models.Company;
import com.fetcher.core.models.response.CompaniesResponse;
import com.fetcher.core.utils.LoadingDialog;
import com.fetcher.views.adapters.CompaniesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompaniesFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private LoadingDialog mLoadingDialog;
    private View view;
    private ArrayList<Company> companies = new ArrayList<>();

    public CompaniesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_companies, container, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mLoadingDialog = new LoadingDialog(getActivity());
        getData();
        return view;
    }

    private void getData() {
        mLoadingDialog.show();
        UserController.getEmploymentCompanies(getContext(), new Callback<CompaniesResponse>() {
            @Override
            public void onResponse(Call<CompaniesResponse> call, Response<CompaniesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCompanies() != null)
                        companies.addAll(response.body().getCompanies());
                    if (getContext() == null) return;
                    UserController.getTrainingCompanies(getContext(), new Callback<CompaniesResponse>() {
                        @Override
                        public void onResponse(Call<CompaniesResponse> call, Response<CompaniesResponse> response) {
                            mLoadingDialog.dismiss();
                            if (!response.isSuccessful()) return;
                            if (response.body().getCompanies() != null)
                                companies.addAll(response.body().getCompanies());
                            mRecyclerView.setAdapter(new CompaniesAdapter(getActivity(), companies));
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                                    DividerItemDecoration.VERTICAL);
                            mRecyclerView.addItemDecoration(dividerItemDecoration);
                        }

                        @Override
                        public void onFailure(Call<CompaniesResponse> call, Throwable t) {
                            mLoadingDialog.dismiss();
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    mLoadingDialog.dismiss();
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CompaniesResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private View findViewById(int id) {
        return view.findViewById(id);
    }


}
