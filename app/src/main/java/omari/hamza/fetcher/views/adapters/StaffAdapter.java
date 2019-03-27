package omari.hamza.fetcher.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.models.Person;
import omari.hamza.fetcher.core.utils.RatingDialog;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.MyViewHolder> {

    private Activity mActivity;
    private ArrayList<Person> staff;

    public StaffAdapter(Activity mActivity, ArrayList<Person> staff) {
        this.mActivity = mActivity;
        this.staff = staff;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_application_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Person currentPerson = staff.get(i);

        myViewHolder.nameTextView.setText(currentPerson.getName());
        myViewHolder.typeTextView.setText(currentPerson.isTrainee() ? "Trainee" : "Employee");
        myViewHolder.rateButton.setOnClickListener(v -> {
            new RatingDialog(mActivity, currentPerson).show();
        });

    }

    @Override
    public int getItemCount() {
        return staff.size();
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
