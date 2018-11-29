package com.newwavetech.leaverequestadmindemo1.activities;

import android.app.Dialog;
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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.pojo.Employees;
import com.newwavetech.leaverequestadmindemo1.viewmodels.DepartmentsViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.EmployeeViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.RolesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmployeeDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_employee_detail)
    Toolbar toolbar;
    @BindView(R.id.employeedetail_code_ed)
    EditText codeed;
    @BindView(R.id.employeedetail_name_ed)
    EditText nameed;
    @BindView(R.id.employeedetail_pwd_ed)
    EditText pwded;
    @BindView(R.id.employeedetail_role_spinner)
    Spinner roleSpinner;
    @BindView(R.id.employeedetail_department_spinner)
    Spinner departmentSpinner;
    @BindView(R.id.employeedetail_entryday_tv)
    TextView entryDayTv;
    @BindView(R.id.employeedetail_salary_ed)
    EditText salaryed;
    @BindView(R.id.employeedetail_leavedays_ed)
    EditText leavesed;
    @BindView(R.id.employeedetail_calendar_img)
    ImageView entryDayCalendar;
    int id;
    private EmployeeViewModel employeeViewModel;
    private RolesViewModel rolesViewModel;
    private DepartmentsViewModel departmentsViewModel;
    private String role;
    private int roleId;
    private String department;
    private int departId;
    private boolean updateFlag = false;
    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        ButterKnife.bind(this);

        //SetToolBar
        toolbar.setTitle("Employee Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> finish());

        try {
            if (getIntent() != null) {
                updateFlag = true;
                Employees emp = (Employees) getIntent().getSerializableExtra("employee");
                codeed.setText(emp.getCode());
                nameed.setText(emp.getName());
                pwded.setText(emp.getPassword());
                entryDayTv.setText(emp.getStartworkingdate().substring(0, 10));
                salaryed.setText(String.valueOf(emp.getSalary()));
                leavesed.setText(String.valueOf(emp.getRemainingleave()));
                role = emp.getRoletitle();
                department = emp.getDepartmenttitle();
                id = emp.getId();
            }
        } catch (NullPointerException e) {
        }

        //Set Spinner
        setupRoleSpinner();
        setupDepartmentSpinner();

        entryDayTv.setOnClickListener(v -> showPopUpCalendar());
        entryDayCalendar.setOnClickListener(v -> showPopUpCalendar());

        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
    }

    @OnClick(R.id.employeedetail_save_btn)
    public void onClickSave() {
        boolean nullFlag = nullTest(codeed.getText().toString(), nameed.getText().toString(), pwded.getText().toString(), salaryed.getText().toString(), leavesed.getText().toString());
        boolean numericFlag, validDateFlag;
        if (nullFlag) {
            numericFlag = numerictest(salaryed.getText().toString(), leavesed.getText().toString());
            if (numericFlag) {
                validDateFlag = validDateTest(entryDayTv.getText().toString());
                if (validDateFlag) {
                    //Null Test Success!!
                    if (updateFlag) {
                        employeeViewModel.updateEmployee(id, codeed.getText().toString(), departId, nameed.getText().toString(), pwded.getText().toString(), Integer.parseInt(leavesed.getText().toString()), roleId, Integer.parseInt(salaryed.getText().toString()), entryDayTv.getText().toString());

                    } else
                        employeeViewModel.insertEmployee(new Employees(codeed.getText().toString(), nameed.getText().toString(), pwded.getText().toString(), Integer.parseInt(salaryed.getText().toString()), roleId, departId, Integer.parseInt(leavesed.getText().toString()), entryDayTv.getText().toString()));

                } else
                    Toast.makeText(this, "Please choose the EntryDay", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Salary and LeaveDays fields have to be integers", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please make sure to fill all the fields!!!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.employeedetail_cancel_btn)
    public void onClickCancel() {
        finish();
    }

    private void showPopUpCalendar() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_calendar_view);
        CalendarView calendarView = dialog.findViewById(R.id.employeedetail_calendar_view);
        dialog.show();
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            entryDayTv.setText(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(dayOfMonth));
            dialog.dismiss();
        });
    }

    private void setupRoleSpinner() {
        rolesViewModel = ViewModelProviders.of(this).get(RolesViewModel.class);
        rolesViewModel.getRoles().observe(this, rolesList -> {
            int[] idArray;
            try {
                int position = 0;
                String[] roleArray = new String[rolesList.getRolesList().size()];
                idArray = new int[rolesList.getRolesList().size()];
                for (int i = 0; i < roleArray.length; i++) {
                    roleArray[i] = rolesList.getRolesList().get(i).getRoleTitle();
                    idArray[i] = rolesList.getRolesList().get(i).getRoleId();
                }
                if (!TextUtils.isEmpty(role)) {
                    for (int i = 0; i < roleArray.length; i++) {
                        if (TextUtils.equals(role, roleArray[i])) {
                            position = i;
                            break;
                        }
                    }
                    String firstElement = roleArray[0];
                    roleArray[0] = role;
                    roleArray[position] = firstElement;
                    int firstId = idArray[0];
                    idArray[0] = idArray[position];
                    idArray[position] = firstId;
                }
                ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roleArray);
                roleSpinner.setAdapter(roleAdapter);

                roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        roleId = idArray[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            } catch (NullPointerException e) {
            }
        });
    }

    private void setupDepartmentSpinner() {
        departmentsViewModel = ViewModelProviders.of(this).get(DepartmentsViewModel.class);
        departmentsViewModel.getDepartments().observe(this, departmentsList -> {
            int idArray[];
            try {
                int position = 0;
                String[] departmentArray = new String[departmentsList.getDepartmentsList().size()];
                idArray = new int[departmentsList.getDepartmentsList().size()];
                for (int i = 0; i < departmentArray.length; i++) {
                    departmentArray[i] = departmentsList.getDepartmentsList().get(i).getDepartmentTitle();
                    idArray[i] = departmentsList.getDepartmentsList().get(i).getDepartmentId();
                }
                if (!TextUtils.isEmpty(department)) {
                    for (int i = 0; i < departmentArray.length; i++) {
                        if (TextUtils.equals(role, departmentArray[i])) {
                            position = i;
                            break;
                        }
                    }
                    String firstElement = departmentArray[0];
                    int firstId = idArray[0];
                    departmentArray[0] = department;
                    departmentArray[position] = firstElement;
                    idArray[0] = idArray[position];
                    idArray[position] = idArray[0];
                }
                ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departmentArray);
                departmentSpinner.setAdapter(departmentAdapter);

                departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        departId = idArray[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } catch (NullPointerException e) {
            }
        });
    }

    private boolean numerictest(String st1, String st2) {
        try {
            Integer.parseInt(st1);
            Integer.parseInt(st2);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private boolean nullTest(String st1, String st2, String st3, String st4, String st5) {
        if (TextUtils.isEmpty(st1) || TextUtils.isEmpty(st1) || TextUtils.isEmpty(st2) || TextUtils.isEmpty(st3) || TextUtils.isEmpty(st4) || TextUtils.isEmpty(st5)) {
            return false;
        }
        return true;
    }

    private boolean validDateTest(String st) {
        if (TextUtils.equals(st, "Pick the Entry Day!!")) return false;
        return true;
    }
}