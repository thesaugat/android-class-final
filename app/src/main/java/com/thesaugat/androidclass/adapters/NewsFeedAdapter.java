package com.thesaugat.androidclass.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thesaugat.androidclass.R;
import com.thesaugat.androidclass.dataClases.FeedData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {

    List<FeedData> feedDataList;
    Context context;
    private LayoutInflater layoutInflater;

    public NewsFeedAdapter(List<FeedData> feedDataList, Context context) {
        this.feedDataList = feedDataList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_facebook_feed, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dateTime.setText(feedDataList.get(position).getDateTime());
        holder.userName.setText(feedDataList.get(position).getPersonName());
        holder.feedDesc.setText(feedDataList.get(position).getDescription());
        Picasso.get().load(feedDataList.get(position).getImageUrl()).into(holder.userProfileImage);

    }


    @Override
    public int getItemCount() {
        return feedDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, feedDesc, dateTime;
        CircleImageView userProfileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userNameTV);
            feedDesc = itemView.findViewById(R.id.tvLongDesc);
            dateTime = itemView.findViewById(R.id.dateTimeTV);
            userProfileImage = itemView.findViewById(R.id.ivUserImage);

        }
    }

}
