package com.videlo.videlo.v_Fragment;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.videlo.videlo.Api;
import com.videlo.videlo.ApiClient;
import com.videlo.videlo.R;
import com.videlo.videlo.v_Activity.VideloActivity;
import com.videlo.videlo.v_Adapter.VideloAdapter;
import com.videlo.videlo.v_model.VideloModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutomobilesFrag extends Fragment implements VideloAdapter.OnItmCickListener {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private VideloAdapter videloAdapter;
    private List<VideloModel> videloModelList;
    private static final String BASE_URL = "base_url";

    private String urlLink;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);



    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewMain);
        recyclerView = view.findViewById(R.id.recyclerViewMain);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        automobile();


    }



    public void automobile (){

        Api api = ApiClient.getApiClient().create(Api.class);
        Call<List<VideloModel>> listCall = api.getAutomobile();

        listCall.enqueue(new Callback<List<VideloModel>>() {
            @Override
            public void onResponse(Call<List<VideloModel>> call, Response<List<VideloModel>> response) {

                videloModelList = response.body();
                videloAdapter = new VideloAdapter(getContext(), videloModelList);
                recyclerView.setAdapter(videloAdapter);
                videloAdapter.setOnItmClkListener(AutomobilesFrag.this);
            }

            @Override
            public void onFailure(Call<List<VideloModel>> call, Throwable t) {

                Toast.makeText(getContext(),"faill",Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void OnItmClk(int pos) {
        VideloModel getItemClicked = videloModelList.get(pos);

        Intent i = new Intent(getContext(),VideloActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BASE_URL,getItemClicked.getUrl());
        i.putExtras(bundle);
        startActivity(i);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textList) {
                textList = textList.toLowerCase();
                List<VideloModel> myList = new ArrayList<>();
                for (VideloModel model : videloModelList) {
                    String prd_name = model.getTitle().toLowerCase();
                    if (prd_name.contains(textList)) {
                        myList.add(model);
                    }
                }
                videloAdapter.filterItem(myList);
                return false;
            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}

