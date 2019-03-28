package omari.hamza.fetcher.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.UserController;
import omari.hamza.fetcher.core.models.Company;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.core.utils.RatingDialog;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.MyViewHolder> {

    private Activity mActivity;
    private ArrayList<Company> companies;
    private LoadingDialog mLoadingDialog;

    public CompaniesAdapter(Activity mActivity, ArrayList<Company> companies) {
        this.mActivity = mActivity;
        this.companies = companies;
        this.mLoadingDialog = new LoadingDialog(mActivity);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_application_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nameTextView.setText(companies.get(i).getName());
        myViewHolder.typeTextView.setText(companies.get(i).getEmail());
        myViewHolder.rateButton.setOnClickListener(v -> new RatingDialog(mActivity, null, companies.get(i)).show());
    }


    @Override
    public int getItemCount() {
        return companies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        CardView acceptCardView, rejectCardView;
        TextView nameTextView, typeTextView;
        Button rateButton;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.acceptCardView = itemView.findViewById(R.id.accept_cardView);
            this.rejectCardView = itemView.findViewById(R.id.reject_cardView);
            this.nameTextView = itemView.findViewById(R.id.name_textView);
            this.typeTextView = itemView.findViewById(R.id.email_textView);
            this.rateButton = itemView.findViewById(R.id.rate_button);

            this.acceptCardView.setVisibility(View.GONE);
            this.rejectCardView.setVisibility(View.GONE);
            this.rateButton.setVisibility(View.VISIBLE);
        }
    }
}
