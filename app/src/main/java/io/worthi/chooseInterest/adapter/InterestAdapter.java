package io.worthi.chooseInterest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.worthi.R;
import io.worthi.chooseInterest.ChooseInterestScreen;
import io.worthi.chooseInterest.response.GetInterestsResponse;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.ViewHolder> {
    Context context;
    ArrayList<GetInterestsResponse> getInterestsResponseArrayList = new ArrayList<>();
    ArrayList<String> selected = new ArrayList<>();

    public InterestAdapter(Context context, ArrayList<GetInterestsResponse> getInterestsResponseArrayList) {
        this.context = context;
        this.getInterestsResponseArrayList = getInterestsResponseArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_interest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.interesttv.setText(getInterestsResponseArrayList.get(position).getName());

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    ChooseInterestScreen.Companion.getGetselectedinterestIf().getID(getInterestsResponseArrayList.get(position).getValue().toString(), "1");
                } else  {
                    ChooseInterestScreen.Companion.getGetselectedinterestIf().getID(getInterestsResponseArrayList.get(position).getId().toString(), "0");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getInterestsResponseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout background ;
        TextView interesttv;
        CheckBox checkbox;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.background);
            interesttv = itemView.findViewById(R.id.interesttv);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }
}
