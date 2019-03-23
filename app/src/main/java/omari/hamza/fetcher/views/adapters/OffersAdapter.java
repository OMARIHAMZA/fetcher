package omari.hamza.fetcher.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.models.Offer;
import omari.hamza.fetcher.views.activities.OfferDetailsActivity;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private Activity mActivity;
    private ArrayList<Offer> offers;

    public OffersAdapter(Activity mActivity, ArrayList<Offer> offers) {
        this.mActivity = mActivity;
        this.offers = offers;
    }

    @NonNull
    @Override
    public OffersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersAdapter.MyViewHolder viewHolder, int i) {

        Offer currentOffer = offers.get(i);

        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, OfferDetailsActivity.class);
            intent.putExtra("offer", currentOffer);
            mActivity.startActivity(intent);
        });

        viewHolder.titleTextView.setText(currentOffer.getTitle());
        viewHolder.companyTextView.setText(currentOffer.getCompanyName());
        viewHolder.locationTextView.setText(currentOffer.getLocation());
        viewHolder.typeTextView.setText(currentOffer.isTraining() ? mActivity.getString(R.string.training) : mActivity.getString(R.string.job_offer));
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView titleTextView, companyTextView, locationTextView, typeTextView;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            titleTextView = itemView.findViewById(R.id.offer_title_textView);
            companyTextView = itemView.findViewById(R.id.company_textView);
            locationTextView = itemView.findViewById(R.id.location_textView);
            typeTextView = itemView.findViewById(R.id.offer_type_textView);
        }
    }
}
