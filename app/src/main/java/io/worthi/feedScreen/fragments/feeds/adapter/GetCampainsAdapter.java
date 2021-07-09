package io.worthi.feedScreen.fragments.feeds.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.worthi.R;
import io.worthi.chooseInterest.adapter.InterestAdapter;
import io.worthi.explore.ExploreScreen;
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse;

public class GetCampainsAdapter extends RecyclerView.Adapter<GetCampainsAdapter.ViewHolder> {
    Context context;
    ArrayList<GetCampainsResponse> getCampainsResponses =new ArrayList<>();

    public GetCampainsAdapter(Context context, ArrayList<GetCampainsResponse> getCampainsResponses) {
        this.context = context;
        this.getCampainsResponses = getCampainsResponses;
    }

    @Override
    public GetCampainsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_feed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GetCampainsAdapter.ViewHolder holder, int position) {

        Picasso.get().load(getCampainsResponses.get(position).getCallToAction().getCallToActionImage()).into(holder.image);
//        Glide.with(context).load(getCampainsResponses.get(position).getCallToAction().getCallToActionImage()).placeholder(R.drawable.logo).into(holder.image);
        holder.title.setText(getCampainsResponses.get(position).getName());
        holder.subtile.setText(getCampainsResponses.get(position).getDescription());

        holder.explorebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ExploreScreen.class).putExtra("pos",String.valueOf(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getCampainsResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,subtile;
        Button explorebt;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subtile = itemView.findViewById(R.id.subtile);
            explorebt =itemView.findViewById(R.id.explorebt);
        }
    }
}
