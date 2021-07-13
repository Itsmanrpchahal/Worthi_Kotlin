package io.worthi.submitQualifier.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import io.worthi.R;
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse;
import io.worthi.submitQualifier.SubmitQualifierScreen;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    Context context;
    ArrayList<String> answers = new ArrayList<>();
    ArrayList<String> Sanswers = new ArrayList<>();
    ArrayList<GetCampainsResponse.Question> questions = new ArrayList<>();
    int pos = 0;
    private int selectedPosition = -1;

    public AnswerAdapter(Context context, ArrayList<GetCampainsResponse.Question> questions, int position) {
        this.context = context;
        this.questions = questions;
        this.pos = position;
    }

    @NonNull
    @NotNull
    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_answer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AnswerAdapter.ViewHolder holder, int position) {
        answers = new ArrayList<>();
        answers.add("Yes");
        answers.add("No");
        answers.add("Either");
        holder.textView.setText(answers.get(position));

        if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            holder.textView.setBackgroundResource(R.drawable.themeborder);
        } else {
            holder.itemView.setSelected(false);
            holder.textView.setBackgroundResource(R.color.white);
        }

        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPosition >= 0)

                    notifyItemChanged(selectedPosition);
                    selectedPosition = holder.getAdapterPosition();
                    notifyItemChanged(selectedPosition);


                    Sanswers.add(answers.get(selectedPosition));

                jsonObject.addProperty("question_id",questions.get(pos).getId());
                jsonObject.addProperty("answer",answers.get(selectedPosition));
                jsonArray.add(jsonObject);
                Log.d("JSON",""+jsonObject);
                SubmitQualifierScreen.Companion.getGetQA_IF().getQAIF(jsonObject);
                Log.d("Answers",pos +"  "+Sanswers);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.yes);
        }
    }
}
