package omari.hamza.fetcher.views.fragments.company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.CompanyController;
import omari.hamza.fetcher.core.models.Offer;
import omari.hamza.fetcher.core.models.response.OffersResponse;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.views.adapters.OffersAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOffersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LoadingDialog mLoadingDialog;
    private ArrayList<Offer> offers = new ArrayList<>();

    public MyOffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_offers, container, false);
        findViewsById(view);
        getData();
        return view;
    }

    private void getData() {
        if (getContext() == null) return;
        mLoadingDialog.show();
        CompanyController.getCompanyJobs(getContext(), new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getOffers() != null)
                        offers.addAll(response.body().getOffers());
                    if (getContext() == null) return;
                    CompanyController.getCompanyTrainings(getContext(), new Callback<OffersResponse>() {
                        @Override
                        public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                            mLoadingDialog.dismiss();
                            if (response.isSuccessful()) {
                                if (response.body().getOffers() != null)
                                    offers.addAll(response.body().getOffers());
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                mRecyclerView.setAdapter(new OffersAdapter(getActivity(), offers));
                            } else {
                                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<OffersResponse> call, Throwable t) {
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
            public void onFailure(Call<OffersResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViewsById(View view) {
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        mLoadingDialog = new LoadingDialog(getActivity());
    }

}
