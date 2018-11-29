package com.newwavetech.leaverequestadmindemo1.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.pojo.Decision;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DecisionDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_decision_detail)
    Toolbar toolbar;
    @BindView(R.id.imgDecision)
    ImageView imgDecision;
    @BindView(R.id.tvDecision)
    TextView tvDecision;
    @BindView(R.id.tvDecisionName) TextView employeeName;
    @BindView(R.id.tvDecisionTotalDays) TextView totalDays;
    @BindView(R.id.tvDecisionStartDate) TextView startDate;
    @BindView(R.id.tvDecisionEndDate) TextView endDate;
    @BindView(R.id.tvDecisionLeaveType) TextView leaveType;
    @BindView(R.id.tvDecisionReason) TextView reason;
    @BindView(R.id.tvDecisionHelperId) TextView helperId;
    @BindView(R.id.tvDecisionAssignTask) TextView assignTask;
    @BindView(R.id.tvDecisionExplained) TextView explain;
    @BindView(R.id.tvDecisionAdminName) TextView adminName;
    @BindView(R.id.tvDecisionTime) TextView decisionTime;
    @BindView(R.id.tvDecisionRemark) TextView remark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision_detail);

        ButterKnife.bind(this);

        //SetToolbar
        toolbar.setTitle("Decision Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        if(getIntent() != null){
            Decision decision = (Decision)getIntent().getSerializableExtra("decision");
            employeeName.setText(decision.getName());
            totalDays.setText(decision.getTotaldays()+"");
            startDate.setText(decision.getStartdate().substring(0, 10));
            endDate.setText(decision.getEnddate().substring(0, 10));
            leaveType.setText(decision.getTitle());
            reason.setText(decision.getReason());
            helperId.setText(decision.getHelpingempid()+"");
            assignTask.setText(decision.getAssigntask());
            if(decision.getExplained() == 1)
                explain.setText("Yes");
            else explain.setText("No");
            adminName.setText(decision.getAdminname());
            remark.setText(decision.getRemark());
            decisionTime.setText(decision.getDecisiontime().substring(0, 10));
            if(TextUtils.equals("accept", decision.getDetermine())){
                tvDecision.setText("Request accepted by "+ decision.getAdminname());
                tvDecision.setTextColor(getResources().getColor(R.color.colorAccent));
                imgDecision.setImageResource(R.drawable.ic_leave_accept);
            }
            else{
                tvDecision.setText("Request denied by "+ decision.getAdminname());
                tvDecision.setTextColor(getResources().getColor(R.color.colorAlertRed));
                imgDecision.setImageResource(R.drawable.ic_leave_denied);
            }
        }
    }
}
