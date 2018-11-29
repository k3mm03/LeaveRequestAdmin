package com.newwavetech.leaverequestadmindemo1.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.activities.RequestDetailActivity;
import com.newwavetech.leaverequestadmindemo1.pojo.Requests;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.MyViewHolder> {
    private static List<Requests> requestsList;

    public RequestsAdapter(List<Requests> requestsList) {
        this.requestsList = requestsList;
        notifyDataSetChanged();
    }

    public void setData(List<Requests> list) {
        this.requestsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.requests_recyclerview_items, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.employee_name_tv.setText(requestsList.get(i).getName());
        myViewHolder.totalday_tv.setText(String.valueOf(requestsList.get(i).getTotaldays()));
        myViewHolder.startdate_tv.setText(requestsList.get(i).getStartdate().substring(0, 10));
        myViewHolder.enddate_tv.setText(requestsList.get(i).getEnddate().substring(0, 10));
        myViewHolder.helper_tv.setText(requestsList.get(i).getHelpername());
        if (requestsList.get(i).getExplained() == 1) {
            myViewHolder.explained_tv.setText("Yes");
        } else {
            myViewHolder.explained_tv.setText("No");
        }
        myViewHolder.reason_tv.setText(requestsList.get(i).getReason());

        myViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(myViewHolder.itemView.getContext(), RequestDetailActivity.class);
            intent.putExtra("Request", requestsList.get(i));
            myViewHolder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.employee_name_tv)
        TextView employee_name_tv;
        @BindView(R.id.totalday_tv)
        TextView totalday_tv;
        @BindView(R.id.startdate_tv)
        TextView startdate_tv;
        @BindView(R.id.enddate_tv)
        TextView enddate_tv;
        @BindView(R.id.helper_tv)
        TextView helper_tv;
        @BindView(R.id.explained_tv)
        TextView explained_tv;
        @BindView(R.id.reason_tv)
        TextView reason_tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
