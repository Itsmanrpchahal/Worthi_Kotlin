package io.worthi.submitQualifier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import io.worthi.R;
import io.worthi.feedScreen.fragments.feeds.adapter.GetCampainsAdapter;
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    Context context;
    ArrayList<GetCampainsResponse.Question> getCampainsResponses =new ArrayList<>();
    private ArrayList<String> ids = new ArrayList<>();


    public QuestionsAdapter(Context context, ArrayList<GetCampainsResponse.Question> getCampainsResponses) {
        this.context = context;
        this.getCampainsResponses = getCampainsResponses;
    }

    @NonNull
    @NotNull
    @Override
    public QuestionsAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_questions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull QuestionsAdapter.ViewHolder holder, int position) {
        holder.questiontv.setText(getCampainsResponses.get(position).getQualifierQuestion());

        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.yes.setBackgroundResource(R.drawable.themeborder);
                holder.either.setBackgroundResource(R.color.white);
                holder.no.setBackgroundResource(R.color.white);
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.yes.setBackgroundResource(R.color.white);
                holder.either.setBackgroundResource(R.color.white);
                holder.no.setBackgroundResource(R.drawable.themeborder);
            }
        });

        holder.either.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.yes.setBackgroundResource(R.color.white);
                holder.either.setBackgroundResource(R.drawable.themeborder);
                holder.no.setBackgroundResource(R.color.white);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getCampainsResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questiontv ,yes,no,either;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            questiontv =itemView.findViewById(R.id.questiontv);
            yes =itemView.findViewById(R.id.yes);
            no =itemView.findViewById(R.id.no);
            either =itemView.findViewById(R.id.either);
        }
    }
}
