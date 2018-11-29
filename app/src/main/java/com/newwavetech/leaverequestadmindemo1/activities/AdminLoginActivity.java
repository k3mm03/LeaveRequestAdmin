package com.newwavetech.leaverequestadmindemo1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.api.ApiService;
import com.newwavetech.leaverequestadmindemo1.api.RetrofitApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AdminLoginActivity extends AppCompatActivity {

    @BindView(R.id.admin_tv_login_alert)
    TextView admin_tv_login_alert;
    @BindView(R.id.admin_ed_uname)
    EditText admin_ed_name;
    @BindView(R.id.admin_ed_pwd)
    EditText admin_ed_pwd;

    private Retrofit retrofit;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.admin_btn_login)
    public void onClick() {
        String uname = String.valueOf(admin_ed_name.getText());
        String pwd = String.valueOf(admin_ed_pwd.getText());
        if (validateLogin(uname, pwd)) {
            logintest(uname, pwd);
        } else {
            admin_tv_login_alert.setVisibility(View.VISIBLE);
        }

    }

    private boolean validateLogin(String name, String password) {
        if (name.trim().equals("") || password.trim().equals("")) {
            admin_tv_login_alert.setText("Please Re-enter the credentials");
            admin_tv_login_alert.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    private void logintest(String name, String password) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        apiService.loginEmployer(name, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employer -> {
                    try {
                        if (TextUtils.equals(employer.getEmployer().get(0).getName(), name)) {
                            Intent intent = new Intent(AdminLoginActivity.this, AdminManagementActivity.class);
                            intent.putExtra("adminid", employer.getEmployer().get(0).getAdminid());
                            startActivity(intent);
                        } else {
                            admin_tv_login_alert.setText("Incorrect Admin Name or Password");
                            admin_tv_login_alert.setVisibility(View.VISIBLE);
                        }
                    } catch (IndexOutOfBoundsException ex) {
                        Toast.makeText(AdminLoginActivity.this, ex.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                        admin_tv_login_alert.setVisibility(View.VISIBLE);
                    }
                }, throwable -> {
                    Toast.makeText(AdminLoginActivity.this, throwable.getLocalizedMessage() + "", Toast.LENGTH_LONG).show();
                });
    }
}
