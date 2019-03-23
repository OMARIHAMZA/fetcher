package omari.hamza.fetcher.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import java.util.ArrayList;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.models.Category;
import omari.hamza.fetcher.views.activities.OffersActivity;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private Activity mActivity;
    private ArrayList<Category> categories;

    public CategoriesAdapter(Activity mActivity, ArrayList<Category> categories) {
        this.mActivity = mActivity;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, OffersActivity.class);
            intent.putExtra("category", categories.get(i));
            mActivity.startActivity(intent);
        });

        myViewHolder.categoryTitleTextView.setText(categories.get(i).getName());
        SvgLoader.pluck()
                .with(mActivity)
                .load(categories.get(i).getImageUrl(), myViewHolder.categoryImageView);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView categoryTitleTextView;
        ImageView categoryImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            categoryTitleTextView = itemView.findViewById(R.id.category_title_textview);
            categoryImageView = itemView.findViewById(R.id.category_imageView);
        }
    }
}
