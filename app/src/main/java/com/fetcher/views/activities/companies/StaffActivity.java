package com.fetcher.views.activities.companies;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import com.fetcher.R;
import com.fetcher.core.controllers.CompanyController;
import com.fetcher.core.models.Person;
import com.fetcher.core.models.response.StaffResponse;
import com.fetcher.core.utils.LoadingDialog;
import com.fetcher.views.adapters.StaffAdapter;
import com.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffActivity extends MasterActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<Person> staff = new ArrayList<>();
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_staff);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.mRecyclerView);

        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void assignActions() {

    }

    @Override
    protected void getData() {
        mLoadingDialog.show();
        CompanyController.getEmployees(getApplicationContext(), new Callback<StaffResponse>() {
            @Override
            public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStaff() != null)
                        staff.addAll(response.body().getStaff());
                    CompanyController.getTrainees(getApplicationContext(), new Callback<StaffResponse>() {
                        @Override
                        public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                            mLoadingDialog.dismiss();
                            if (response.isSuccessful()) {
                                if (response.body().getStaff() != null) {
                                    for (Person person : response.body().getStaff()) {
                                        person.setTrainee(true);
                                        staff.addAll(response.body().getStaff());
                                    }
                                }
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                mRecyclerView.setAdapter(new StaffAdapter(StaffActivity.this, staff));
                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                                        DividerItemDecoration.VERTICAL);
                                mRecyclerView.addItemDecoration(dividerItemDecoration);
                            } else {
                                Toast.makeText(StaffActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<StaffResponse> call, Throwable t) {
                            mLoadingDialog.dismiss();
                            Toast.makeText(StaffActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    mLoadingDialog.dismiss();
                    Toast.makeText(StaffActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StaffResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(StaffActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }
}
