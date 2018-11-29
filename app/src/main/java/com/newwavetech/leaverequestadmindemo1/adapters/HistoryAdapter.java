package com.newwavetech.leaverequestadmindemo1.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.activities.DecisionDetailActivity;
import com.newwavetech.leaverequestadmindemo1.pojo.Decision;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private static List<Decision> decisionList;
    private Context context;

    public HistoryAdapter(List<Decision> decisionList, Context context) {
        this.decisionList = decisionList;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setData(List<Decision> list) {
        this.decisionList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_recyclerview_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvTotaldays.setText(decisionList.get(i).getTotaldays() + "");
        myViewHolder.tvName.setText(decisionList.get(i).getName());
        myViewHolder.tvLeavetype.setText(decisionList.get(i).getTitle());
        myViewHolder.tvAdmin.setText(decisionList.get(i).getAdminname());
        if (TextUtils.equals(decisionList.get(i).getDetermine(), "accept")) {
            myViewHolder.imageView.setImageResource(R.drawable.ic_leave_accept);
        } else if (TextUtils.equals(decisionList.get(i).getDetermine(), "deny")) {
            myViewHolder.imageView.setImageResource(R.drawable.ic_leave_denied);
        }

        myViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(myViewHolder.itemView.getContext(), DecisionDetailActivity.class);
            intent.putExtra("decision", decisionList.get(i));
            myViewHolder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return decisionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        @BindView(R.id.tvDecisionTotalDays)
        TextView tvTotaldays;
        @BindView(R.id.tvDecisionName)
        TextView tvName;
        @BindView(R.id.tvDecisionLeaveType)
        TextView tvLeavetype;
        @BindView(R.id.tvDecisionAdminName)
        TextView tvAdmin;
        @BindView(R.id.imgDecision)
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

    }
}

