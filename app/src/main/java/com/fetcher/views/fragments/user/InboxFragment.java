package com.fetcher.views.fragments.user;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fetcher.R;
import com.fetcher.core.controllers.UserController;
import com.fetcher.core.models.response.MessagesResponse;
import com.fetcher.core.utils.LoadingDialog;
import com.fetcher.views.adapters.InboxAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InboxFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LoadingDialog mLoadingDialog;

    public InboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        mLoadingDialog = new LoadingDialog(getActivity());
        findViewsById(view);
        getData();
        return view;
    }

    private void getData() {
        if (getContext() == null) return;
        mLoadingDialog.show();
        UserController.getInbox(getContext(), new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mRecyclerView.setAdapter(new InboxAdapter(getActivity(), response.body().getMessages()));
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViewsById(View view) {
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
    }

}
