package io.worthi.submitQualifier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import io.worthi.R;
import io.worthi.feedScreen.fragments.feeds.adapter.GetCampainsAdapter;
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse;
import io.worthi.submitQualifier.SubmitQualifierScreen;
import io.worthi.submitQualifier.model.qAndA;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    Context context;
    ArrayList<GetCampainsResponse.Question> getCampainsResponses =new ArrayList<>();
    ArrayList<qAndA> qAndA =new ArrayList<qAndA>();
    ArrayList<String> questions =new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();


    public QuestionsAdapter(Context context, ArrayList<GetCampainsResponse.Question> getCampainsResponses,ArrayList<String> questions) {
        this.context = context;
        this.getCampainsResponses = getCampainsResponses;
        this.questions = questions;

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
//                io.worthi.submitQualifier.model.qAndA qAndA1 = new qAndA(questions.get(position),answers.get(position));
//                qAndA1.setQuestion_id(getCampainsResponses.get(position).getId().toString());
//                qAndA1.setAnswer("Yes");
                holder.yes.setBackgroundResource(R.drawable.themeborder);
                holder.either.setBackgroundResource(R.color.white);
                holder.no.setBackgroundResource(R.color.white);
               // SubmitQualifierScreen.Companion.getGetqandaIf().getQandA(questions.get(position),"Yes", String.valueOf(position));
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.yes.setBackgroundResource(R.color.white);
                holder.either.setBackgroundResource(R.color.white);
                holder.no.setBackgroundResource(R.drawable.themeborder);
//                io.worthi.submitQualifier.model.qAndA qAndA1 = new qAndA(questions.get(position),answers.get(position));
//                qAndA1.setQuestion_id(getCampainsResponses.get(position).getId().toString());
//                qAndA1.setAnswer("No");
               // SubmitQualifierScreen.Companion.getGetqandaIf1().getQandA1(questions.get(position),"No", String.valueOf(position));
            }
        });

        holder.either.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.yes.setBackgroundResource(R.color.white);
                holder.either.setBackgroundResource(R.drawable.themeborder);
                holder.no.setBackgroundResource(R.color.white);
//                io.worthi.submitQualifier.model.qAndA qAndA1 = new qAndA(questions.get(position),answers.get(position));
//                qAndA1.setQuestion_id(getCampainsResponses.get(position).getId().toString());
//                qAndA1.setAnswer("Either");
                //SubmitQualifierScreen.Companion.getGetqandaIf2().getQandA2(questions.get(position),"Either", String.valueOf(position));
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(context,3);
        holder.answer.setLayoutManager(layoutManager);
        AnswerAdapter answerAdapter =new AnswerAdapter(context,getCampainsResponses,position);
        holder.answer.setAdapter(answerAdapter);

    }

    @Override
    public int getItemCount() {
        return getCampainsResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questiontv ,yes,no,either;
        RecyclerView answer;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            questiontv =itemView.findViewById(R.id.questiontv);
            yes =itemView.findViewById(R.id.yes);
            no =itemView.findViewById(R.id.no);
            either =itemView.findViewById(R.id.either);
            answer = itemView.findViewById(R.id.answers);

        }
    }
}
