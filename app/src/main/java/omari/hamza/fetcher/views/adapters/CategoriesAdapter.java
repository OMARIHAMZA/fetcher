package omari.hamza.fetcher.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.views.activities.OffersActivity;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private Activity mActivity;

    public CategoriesAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mActivity.startActivity(new Intent(mActivity, OffersActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
