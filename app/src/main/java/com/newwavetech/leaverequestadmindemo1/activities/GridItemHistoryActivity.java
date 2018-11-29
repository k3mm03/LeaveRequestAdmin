package com.newwavetech.leaverequestadmindemo1.activities;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.adapters.HistoryAdapter;
import com.newwavetech.leaverequestadmindemo1.pojo.Decision;
import com.newwavetech.leaverequestadmindemo1.viewmodels.DecisionViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridItemHistoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_history)
    Toolbar toolbar;

    @BindView(R.id.recyclerviewHistory)
    RecyclerView recyclerView;
    @BindView(R.id.fromdate_btn)
    MaterialButton fromDateBtn;
    @BindView(R.id.todate_btn)
    MaterialButton toDateBtn;
    List<Decision> decisions = new ArrayList<>();
    private Dialog dialog;
    private DecisionViewModel decisionViewModel;
    private int adminid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_griditem_history);

        ButterKnife.bind(this);

        adminid = AdminManagementActivity.adminid;
        Toast.makeText(this, adminid + "", Toast.LENGTH_SHORT).show();
        //SetToolbar
        toolbar.setTitle("Decision");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        fromDateBtn.setOnClickListener(v -> showAndGetFromDate());

        toDateBtn.setOnClickListener(v -> showAndGettoDate());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final HistoryAdapter adapter = new HistoryAdapter(new ArrayList<>(), GridItemHistoryActivity.this);
        recyclerView.setAdapter(adapter);

        decisionViewModel = ViewModelProviders.of(this).get(DecisionViewModel.class);
        decisionViewModel.getDecisions().observe(this, decisionList -> {
            decisions.addAll(decisionList.getDecisionList());
            adapter.setData(decisions);
        });
    }

    private void showAndGetFromDate() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_calendar_view);
        CalendarView calendarView = dialog.findViewById(R.id.employeedetail_calendar_view);
        dialog.show();
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            fromDateBtn.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(month) + "-" + String.valueOf(year));
            dialog.dismiss();
        });
    }

    private void showAndGettoDate() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_calendar_view);
        CalendarView calendarView = dialog.findViewById(R.id.employeedetail_calendar_view);
        dialog.show();
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            toDateBtn.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(month) + "-" + String.valueOf(year));
            dialog.dismiss();
        });
    }

}
