package com.newwavetech.leaverequestadmindemo1.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.pojo.Requests;
import com.newwavetech.leaverequestadmindemo1.viewmodels.DecisionViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.EmployeeViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.LeaveTypesViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.RequestsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_request_detail)
    Toolbar toolbar;
    @BindView(R.id.tvRequestName)
    TextView tvName;
    @BindView(R.id.tvRequestStartDate)
    TextView tvStartDate;
    @BindView(R.id.tvRequestEndDate)
    TextView tvEndDate;
    @BindView(R.id.tvRequestLeavesDay)
    TextView tvTotalDays;
    @BindView(R.id.tvRequestReason)
    TextView tvReason;
    @BindView(R.id.requestHelperSpinner)
    Spinner helperSpinner;
    @BindView(R.id.tvRequestTypeOfDuty)
    TextView tvDuty;
    @BindView(R.id.tvRequestSubmitDuty)
    TextView tvExplain;
    @BindView(R.id.requestLeaveTypeSpinner)
    Spinner leavetypeSpinner;
    @BindView(R.id.edRequestRemark)
    EditText edRemark;
    private String helper;
    private int helperid;
    private int leavetypeid;
    private int requestid;
    private int employeeid;
    private String remark;

    private EmployeeViewModel employeeViewModel;
    private LeaveTypesViewModel leaveTypesViewModel;
    private RequestsViewModel requestsViewModel;
    private DecisionViewModel decisionViewModel;
    private Requests request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);

        ButterKnife.bind(this);
        //SetToolbar
        toolbar.setTitle("Request Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        requestsViewModel = ViewModelProviders.of(this).get(RequestsViewModel.class);
        decisionViewModel = ViewModelProviders.of(this).get(DecisionViewModel.class);

        try {
            if (getIntent() != null) {
                request = (Requests) getIntent().getSerializableExtra("Request");
                tvName.setText(request.getName());
                tvStartDate.setText(request.getStartdate().substring(0, 10));
                tvEndDate.setText(request.getEnddate().substring(0, 10));
                tvTotalDays.setText(request.getTotaldays() + "");
                tvReason.setText(request.getReason());
                tvDuty.setText(request.getAssigntask());
                requestid = request.getId();
                employeeid = request.getEmployeeid();
                helper = request.getHelpername();
                if (request.getExplained() == 1) {
                    tvExplain.setText("Yes");
                } else tvExplain.setText("No");

                setUpHelperSpinner();
            }
        } catch (NullPointerException e) {
        }

        setUpLeaveTypeSpinner();

    }

    @OnClick(R.id.btnRquestAccept)
    public void onClickAccept() {
        remark = edRemark.getText().toString();
        requestsViewModel.updateRequest(requestid, "accept", helperid);
        decisionViewModel.insertDecision(requestid, employeeid, AdminManagementActivity.adminid, 1, leavetypeid, remark);
    }

    @OnClick(R.id.btnRequestDecline)
    public void onClickDeny() {
        remark = edRemark.getText().toString();
        requestsViewModel.updateRequest(requestid, "deny", helperid);
        decisionViewModel.insertDecision(requestid, employeeid, AdminManagementActivity.adminid, 1, 0, remark);
    }

    private void setUpHelperSpinner() {
        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        employeeViewModel.getEmployees().observe(this, employeeList -> {
            int[] idArray;
            try {
                int position = 0;
                String[] helperArray = new String[employeeList.getEmployeesList().size()];
                idArray = new int[employeeList.getEmployeesList().size()];
                for (int i = 0; i < helperArray.length; i++) {
                    helperArray[i] = employeeList.getEmployeesList().get(i).getName();
                    idArray[i] = employeeList.getEmployeesList().get(i).getId();
                }
                if (!TextUtils.isEmpty(helper)) {
                    for (int i = 0; i < helperArray.length; i++) {
                        if (TextUtils.equals(helper, helperArray[i])) {
                            position = i;
                            break;
                        }
                    }
                    String firstElement = helperArray[0];
                    helperArray[0] = helper;
                    helperArray[position] = firstElement;
                    int firstId = idArray[0];
                    idArray[0] = idArray[position];
                    idArray[position] = firstId;
                }
                ArrayAdapter<String> helperAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, helperArray);
                helperSpinner.setAdapter(helperAdapter);

                helperSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        helper = helperArray[position];
                        helperid = idArray[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            } catch (NullPointerException e) {
            }
        });
    }

    private void setUpLeaveTypeSpinner() {
        leaveTypesViewModel = ViewModelProviders.of(this).get(LeaveTypesViewModel.class);
        leaveTypesViewModel.getLeaveTypes().observe(this, leaveTypesList -> {
            String[] leaveTypeArray = new String[leaveTypesList.getLeaveTypesList().size()];
            int[] idArray = new int[leaveTypesList.getLeaveTypesList().size()];
            for (int i = 0; i < leaveTypeArray.length; i++) {
                leaveTypeArray[i] = leaveTypesList.getLeaveTypesList().get(i).getLeaveTypesName();
                idArray[i] = leaveTypesList.getLeaveTypesList().get(i).getLeaveTypesId();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, leaveTypeArray);
                leavetypeSpinner.setAdapter(adapter);

                leavetypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        leavetypeid = idArray[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }
}
