package com.newwavetech.leaverequestadmindemo1.api;

import com.newwavetech.leaverequestadmindemo1.pojo.DecisionList;
import com.newwavetech.leaverequestadmindemo1.pojo.Departments;
import com.newwavetech.leaverequestadmindemo1.pojo.DepartmentsList;
import com.newwavetech.leaverequestadmindemo1.pojo.EmployeeList;
import com.newwavetech.leaverequestadmindemo1.pojo.Employees;
import com.newwavetech.leaverequestadmindemo1.pojo.EmployerList;
import com.newwavetech.leaverequestadmindemo1.pojo.LeaveTypes;
import com.newwavetech.leaverequestadmindemo1.pojo.LeaveTypesList;
import com.newwavetech.leaverequestadmindemo1.pojo.RequestsList;
import com.newwavetech.leaverequestadmindemo1.pojo.Roles;
import com.newwavetech.leaverequestadmindemo1.pojo.RolesList;

import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/leaves")
    Single<LeaveTypesList> getLeaveTypes();

    @GET("api/department")
    Single<DepartmentsList> getDepartments();

    @GET("api/role")
    Single<RolesList> getRoles();

    @GET("api/employee?columns=employee.*,role.title,department.subtitle&join=left,role,roleid,eq,role.id&join=left,department,departmentid,eq,department.id")
    Single<EmployeeList> getEmployees();

    @GET("api/decision?columns=decision.*,request.*,employee.name,leaves.title,employer.name@adminname&join=left,employee,employeeid,eq,employee.id&join=left,leaves,leavetypeid,eq,leaves.id&join=left,employer,adminid,eq,employer.id&join=left,request,requestid,eq,request.id")
    Single<DecisionList> getDecision();

    @GET("apy/employee/allrequest")
    Single<RequestsList> getRequests(@Query("determine") String determine);

    @GET("api/department/{id}")
    Single<DepartmentsList> getDepartmentWithId(@Path("id") int id);

    @GET("api/role/{id}")
    Single<RolesList> getRoleWithId(@Path("id") int id);

    @FormUrlEncoded
    @POST("api/leaves")
    Single<LeaveTypes> insertLeaveType(
            @Field("title") String title,
            @Field("description") String description,
            @Field("createddate") String createddate,
            @Field("updateddate") String updateddate);

    @FormUrlEncoded
    @POST("api/department")
    Single<Departments> insertDepartment(
            @Field("subtitle") String title,
            @Field("description") String description,
            @Field("createddate") String createddate,
            @Field("updateddate") String updateddate);

    @FormUrlEncoded
    @POST("api/role")
    Single<Roles> insertRole(
            @Field("title") String title,
            @Field("description") String description,
            @Field("createddate") String createddate,
            @Field("updateddate") String updateddate);

    @FormUrlEncoded
    @POST("api/employee")
    Single<Employees> insertEmployee(
            @Field("code") String code,
            @Field("departmentid") int departmentid,
            @Field("name") String name,
            @Field("password") String password,
            @Field("remainingleave") int remainingleave,
            @Field("roleid") int roleid,
            @Field("salary") int salary,
            @Field("startworkingdate") String startworkingdate,
            @Field("createddate") String createddate,
            @Field("updateddate") String updateddate
    );

    @FormUrlEncoded
    @POST("api/decision")
    Single<Integer> insertDecision(
            @Field("requestid") int requestid,
            @Field("employeeid") int employeeid,
            @Field("adminid") int adminid,
            @Field("approve") int approve,
            @Field("decisiontime") String decisiontime,
            @Field("leavetypeid") int leavetypeid,
            @Field("remark") String remark,
            @Field("createddate") String createddate,
            @Field("updateddate") String updateddate
    );

    @FormUrlEncoded
    @PUT("api/leaves/{id}")
    Completable updateLeaveType(@Path("id") int id,
                                @Field("title") String title,
                                @Field("description") String description);

    @FormUrlEncoded
    @PUT("api/department/{id}")
    Single<Departments> updateDepartment(@Path("id") int id,
                                         @Field("subtitle") String title,
                                         @Field("description") String description);

    @FormUrlEncoded
    @PUT("api/role/{id}")
    Completable updateRole(@Path("id") int id,
                           @Field("title") String title,
                           @Field("description") String description);

    @FormUrlEncoded
    @PUT("api/employee/{id}")
    Completable updateEmployee(@Path("id") int id,
                               @Field("code") String code,
                               @Field("departmentid") int departmentid,
                               @Field("name") String name,
                               @Field("password") String password,
                               @Field("remainingleave") int remainingleave,
                               @Field("roleid") int roleid,
                               @Field("salary") int salary,
                               @Field("startworkingdate") String startworkingdate,
                               @Field("createddate") String createddate,
                               @Field("updateddate") String updateddate);

    @FormUrlEncoded
    @PUT("api/request/{id}")
    Single<Integer> updateRequest(@Path("id") int id,
                                  @Field("determine") String determine,
                                  @Field("helpingempid") int helpingempid);

    @DELETE("api/leaves/{id}")
    Completable deleteLeaveType(@Path("id") int id);

    @DELETE("api/department/{id}")
    Single<Integer> deleteDepartment(@Path("id") int id);

    @DELETE("api/role/{id}")
    Completable deleteRole(@Path("id") int id);

    @DELETE("api/employee/{id}")
    Completable deleteEmployee(@Path("id") int id);

    @DELETE("api/employee")
    Single<Integer> deleteEmployeeWithDepartment(@Query("w") String dpid);

    @DELETE("api/employee")
    Single<Integer> deleteEmployeeWithRole(@Query("w") String id);

    @GET("authapi/loginemployer?")
    Single<EmployerList> loginEmployer(@Query("name") String name,
                                       @Query("password") String password);

}