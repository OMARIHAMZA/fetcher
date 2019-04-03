package com.fetcher.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.fetcher.R;
import com.fetcher.core.models.Message;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.MyViewHolder> {

    private Activity mActivity;
    private ArrayList<Message> messages;

    public InboxAdapter(Activity mActivity, ArrayList<Message> messages) {
        this.mActivity = mActivity;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Message currentMessage = messages.get(i);
        myViewHolder.title.setText(currentMessage.getTitle());
        myViewHolder.website.setText(currentMessage.getWebsite());
        myViewHolder.body.setText(currentMessage.getBody());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        TextView title, website, body;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.title = itemView.findViewById(R.id.title_textView);
            this.website = itemView.findViewById(R.id.address_textView);
            this.body = itemView.findViewById(R.id.message_textView);
        }
    }
}
