package com.fetcher.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fetcher.R;
import com.fetcher.core.models.Project;

import java.util.ArrayList;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.MyViewHolder> {

    private Activity activity;
    private ArrayList<Project> projects;

    public ProjectsAdapter(Activity activity, ArrayList<Project> projects) {
        this.activity = activity;
        this.projects = projects;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.project_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Project currentProject = projects.get(i);
        myViewHolder.titleTextView.setText(currentProject.getName());
        myViewHolder.descriptionTextView.setText(currentProject.getDescription());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, descriptionTextView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.titleTextView = itemView.findViewById(R.id.title_textView);
            this.descriptionTextView = itemView.findViewById(R.id.description_textView);
        }
    }
}
