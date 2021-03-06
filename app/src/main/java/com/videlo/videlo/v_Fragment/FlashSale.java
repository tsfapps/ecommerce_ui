package com.videlo.videlo.v_Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.videlo.videlo.v_Adapter.VideloAdaptert;
import com.videlo.videlo.v_model.VideloModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FlashSale extends Fragment implements VideloAdaptert.OnItmCickListener {


    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private VideloAdaptert videloAdaptert;
    private List<VideloModel> videloModelList;
    private static final String BASE_URL = "base_url";
    private String urlLink;


    private RecyclerView recyclerViewtwo;
    private RecyclerView.LayoutManager layoutManagertwo;


    private RecyclerView recyclerViewththee;
    private RecyclerView.LayoutManager layoutManagerthree;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flash_sale, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recyclerViewMain);
        layoutManager =new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
     //   layoutManager.setMeasuredDimension(40,40);
        recyclerView.setLayoutManager(layoutManager);

// second recyclerviw
        recyclerViewtwo = view.findViewById(R.id.recyclerViewMaint);
        layoutManagertwo = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerViewtwo.setLayoutManager(layoutManagertwo);


        // rcv three

        recyclerViewththee = view.findViewById(R.id.recyclerViewMainthree);
        layoutManagerthree = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerViewththee.setLayoutManager(layoutManagerthree);


        phoneShow();

        rmm();
        rcvthre();
    }


    public void phoneShow() {

        Api api = ApiClient.getApiClient().create(Api.class);
        Call<List<VideloModel>> listCall = api.getPhone();

        listCall.enqueue(new Callback<List<VideloModel>>() {
            @Override
            public void onResponse(Call<List<VideloModel>> call, Response<List<VideloModel>> response) {

                videloModelList = response.body();
                videloAdaptert = new VideloAdaptert(getContext(), videloModelList);
                recyclerView.setAdapter(videloAdaptert);
               videloAdaptert.setOnItmClkListener(FlashSale.this);
            }

            @Override
            public void onFailure(Call<List<VideloModel>> call, Throwable t) {

                Toast.makeText(getContext(), "faill", Toast.LENGTH_LONG).show();

            }
        });

    }


    public void rmm() {

        Api api = ApiClient.getApiClient().create(Api.class);
        Call<List<VideloModel>> listCall = api.getPhone();

        listCall.enqueue(new Callback<List<VideloModel>>() {
            @Override
            public void onResponse(Call<List<VideloModel>> call, Response<List<VideloModel>> response) {

                videloModelList = response.body();
                videloAdaptert = new VideloAdaptert(getContext(), videloModelList);
                recyclerViewtwo.setAdapter(videloAdaptert);
                videloAdaptert.setOnItmClkListener(FlashSale.this);
            }

            @Override
            public void onFailure(Call<List<VideloModel>> call, Throwable t) {

                Toast.makeText(getContext(), "faill", Toast.LENGTH_LONG).show();

            }
        });

    }


    public void rcvthre() {

        Api api = ApiClient.getApiClient().create(Api.class);
        Call<List<VideloModel>> listCall = api.getPhone();

        listCall.enqueue(new Callback<List<VideloModel>>() {
            @Override
            public void onResponse(Call<List<VideloModel>> call, Response<List<VideloModel>> response) {

                videloModelList = response.body();
                videloAdaptert = new VideloAdaptert(getContext(), videloModelList);
                recyclerViewththee.setAdapter(videloAdaptert);
                videloAdaptert.setOnItmClkListener(FlashSale.this);
            }

            @Override
            public void onFailure(Call<List<VideloModel>> call, Throwable t) {

                Toast.makeText(getContext(), "faill", Toast.LENGTH_LONG).show();

            }
        });

    }




    @Override
    public void OnItmClk(int pos) {
        VideloModel getItemClicked = videloModelList.get(pos);

        Intent i = new Intent(getContext(), VideloActivity.class);
        Bundle b = new Bundle();
        b.putString(BASE_URL, getItemClicked.getUrl());
        i.putExtras(b);
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
                videloAdaptert.filterItem(myList);
                return false;
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}

