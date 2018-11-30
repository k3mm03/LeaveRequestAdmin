package com.newwavetech.leaverequestadmindemo1.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.adapters.RequestsAdapter;
import com.newwavetech.leaverequestadmindemo1.pojo.Requests;
import com.newwavetech.leaverequestadmindemo1.viewmodels.RequestsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RequestsListFragment extends Fragment {
    @BindView(R.id.requests_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.requests_search_box)
    SearchView searchView;

    private RequestsViewModel requestsViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_admin_requests, container, false);

        ButterKnife.bind(this, layout);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        final RequestsAdapter adapter = new RequestsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        requestsViewModel = ViewModelProviders.of((FragmentActivity) layout.getContext()).get(RequestsViewModel.class);
        requestsViewModel.getRequests("pending").observe((FragmentActivity) layout.getContext(), requestsList -> {
            List<Requests> requests = requestsList.getRequestlist();
            adapter.setData(requests);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(layout.getContext(), s + "", Toast.LENGTH_SHORT).show();
                List<Requests> searchedList = new ArrayList<>();
                for(Requests request : requests){
                    if(TextUtils.equals(s, request.getName())){
                        searchedList.add(request);
                    }
                }
                adapter.setData(searchedList);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                if(TextUtils.equals(s.trim(), "")){
                    adapter.setData(requests);
                }
                return false;
            }
        });

        return layout;
    }
}
