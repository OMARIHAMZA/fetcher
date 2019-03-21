package omari.hamza.fetcher.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.views.activities.OfferDetailsActivity;

public class OffersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;

    public OffersAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, OfferDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
