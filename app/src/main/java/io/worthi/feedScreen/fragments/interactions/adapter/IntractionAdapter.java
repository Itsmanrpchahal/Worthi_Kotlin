package io.worthi.feedScreen.fragments.interactions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import io.worthi.R;
import io.worthi.feedScreen.fragments.feeds.adapter.GetCampainsAdapter;
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse;
import io.worthi.feedScreen.fragments.interactions.response.GetInteractionResponse;

public class IntractionAdapter extends RecyclerView.Adapter<IntractionAdapter.ViewHolder> {
    Context context;
    ArrayList<GetInteractionResponse> getInteractionResponses =new ArrayList<>();

    public IntractionAdapter(Context context, ArrayList<GetInteractionResponse> getInteractionResponses) {
        this.context = context;
        this.getInteractionResponses = getInteractionResponses;
    }

    @NonNull
    @NotNull
    @Override
    public IntractionAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_interaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull IntractionAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(getInteractionResponses.get(position).getAddDisplay().getAddDisplayImage()).placeholder(R.drawable.logo).into(holder.image);
        holder.title.setText(getInteractionResponses.get(position).getName());
        holder.subtile.setText(getInteractionResponses.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return getInteractionResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title ;
        TextView subtile;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subtile = itemView.findViewById(R.id.subtile);
        }
    }
}
