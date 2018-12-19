package com.videlo.videlo.v_Fragment;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


import com.videlo.videlo.R;
import com.videlo.videlo.v_Activity.VideloActivity;
import com.videlo.videlo.v_Adapter.AdapterHome;
import com.videlo.videlo.v_Adapter.AdapterHomeTwo;
import com.videlo.videlo.v_Adapter.AdapterVid;
import com.videlo.videlo.v_Adapter.MyImageAdapter;
import com.videlo.videlo.v_model.ApiModel;
import com.videlo.videlo.v_model.ModelHome;
import com.videlo.videlo.v_model.ModelHomeTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements AdapterHome.OnHomeClkListener, AdapterHomeTwo.ProdictClick {
    private String HOME_URL = "base_url";
    private String homeUrl = "https://videlo.com.my/";


    //Slider Declarartion
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] MAIN_SLIDER = {R.drawable.bnr, R.drawable.t_banner_a, R.drawable.t_banner_b,
            R.drawable.t_banner_c, R.drawable.t_banner_d, R.drawable.t_banner_e};
    private ArrayList<Integer> SliderArray = new ArrayList<Integer>();


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerViewTwo;
    private RecyclerView.LayoutManager layoutManagerTwo;
    private AdapterHomeTwo adapterHomeTwo;
    private List<ModelHomeTwo> modelHomeTwos;

    private List<ApiModel> apiModelList;
    private ApiModel apiModelReal;
    private AdapterVid adapterVid;
    private CircleIndicator indicator;

    // HOME ADAPTER

    private AdapterHome adapterHome;
    private List<ModelHome> modelHomes;


    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//RecyclerView call

        recyclerView = view.findViewById(R.id.rcv);
        recyclerViewTwo = view.findViewById(R.id.rcvTwo);


        mPager = view.findViewById(R.id.viewpgr);
        indicator = view.findViewById(R.id.indicator);
        appBarLayout = view.findViewById(R.id.appbar);
        collapsingToolbar = view.findViewById(R.id.collapsing_toolbar);


        mainView();
        mainSlider();


        mainViewTwo();
        homePageDisplayTwo();


        //    videloTest();
        homePageDisplay();


        initCollapsingToolbar();
    }

    private void mainSlider() {
        for (int i = 0; i < MAIN_SLIDER.length; i++)
            SliderArray.add(MAIN_SLIDER[i]);


        mPager.setAdapter(new MyImageAdapter(getContext(), SliderArray));

        indicator.setViewPager(mPager);


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == MAIN_SLIDER.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 10000, 10000);
    }


    private void mainView() {

        modelHomes = new ArrayList<>();
        //   recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapterHome = new AdapterHome(getContext(), modelHomes);
        recyclerView.setAdapter(adapterHome);
        adapterHome.setOnHomeClkListener(HomeFragment.this);

    }


    private void mainViewTwo() {
        modelHomeTwos = new ArrayList<>();
        layoutManagerTwo = new GridLayoutManager(getContext(), 2);
        recyclerViewTwo.setLayoutManager(layoutManagerTwo);
        adapterHomeTwo = new AdapterHomeTwo(modelHomeTwos, getContext());
        recyclerViewTwo.setAdapter(adapterHomeTwo);
        adapterHomeTwo.setOnClikListner(HomeFragment.this);

    }


    private void initCollapsingToolbar() {

        collapsingToolbar.setTitle(" ");


        appBarLayout.setExpanded(true);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;


            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    //  collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = false;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }

        });

    }


    public void homePageDisplay() {

        int[] image = new int[]{
                R.drawable.s_consumelec,
                R.drawable.t_mens_appearels,
                R.drawable.t_womens_appearel,
                R.drawable.s_garden_pets,
                R.drawable.t_home_appliance,
                R.drawable.s_care_beauty,
                R.drawable.s_jewellery,
                R.drawable.s_watch,
                R.drawable.s_bags,
                R.drawable.s_computer,
                R.drawable.s_babies,
                R.drawable.s_sports,
                R.drawable.s_books,
                R.drawable.s_office,
                R.drawable.s_motorcycle,
                R.drawable.s_agriculture,
                R.drawable.s_machinery,
                R.drawable.s_printing,
        };

        String[] title = new String[]

                {

                        getString(R.string.consumer_electronics),
                        getString(R.string.men_s_apparel),
                        getString(R.string.women_s_apparel),
                        getString(R.string.home_garden_and_pets),
                        getString(R.string.home_appliances),
                        getString(R.string.personal_care_and_beauty),
                        getString(R.string.jewellery),
                        getString(R.string.watches),
                        getString(R.string.bags_wallets_and_shoes),
                        getString(R.string.computers_and_technologies),
                        getString(R.string.babies_toys_and_kids),
                        getString(R.string.sports_and_outdoors),
                        getString(R.string.books_games_and_entertainment),
                        getString(R.string.office_and_security),
                        getString(R.string.automobiles_and_motorcycles),
                        getString(R.string.agriculture_and_food),
                        getString(R.string.machinery_hardware_and_tools),
                        getString(R.string.packaging_printing_and_advertising)


                };
        ModelHome modelHome;
        for (int i = 0; i < image.length; i++) {
            modelHome = new ModelHome(title[i], image[i]);
            modelHomes.add(modelHome);
        }
        adapterHome.notifyDataSetChanged();
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
                List<ModelHomeTwo> myList = new ArrayList<>();
                for (ModelHomeTwo model : modelHomeTwos) {
                    String prd_name = model.getName().toLowerCase();
                    if (prd_name.contains(textList)) {
                        myList.add(model);
                    }
                }
                adapterHomeTwo.filterItem(myList);
                return false;
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

//recyclerviewtwo


    public void homePageDisplayTwo() {

        int[] image = new int[]{
                R.drawable.s_consumelec,
                R.drawable.t_mens_appearels,
                R.drawable.t_womens_appearel,
                R.drawable.s_garden_pets,
                R.drawable.t_home_appliance,
                R.drawable.s_care_beauty,
                R.drawable.s_jewellery,
                R.drawable.s_watch,
                R.drawable.s_bags,
                R.drawable.s_computer,
                R.drawable.s_babies,
                R.drawable.s_sports,
                R.drawable.s_books,
                R.drawable.s_office,
                R.drawable.s_motorcycle,
                R.drawable.s_agriculture,
                R.drawable.s_machinery,
                R.drawable.s_printing,
        };

        String[] title = new String[]

                {

                        getString(R.string.consumer_electronics),
                        getString(R.string.men_s_apparel),
                        getString(R.string.women_s_apparel),
                        getString(R.string.home_garden_and_pets),
                        getString(R.string.home_appliances),
                        getString(R.string.personal_care_and_beauty),
                        getString(R.string.jewellery),
                        getString(R.string.watches),
                        getString(R.string.bags_wallets_and_shoes),
                        getString(R.string.computers_and_technologies),
                        getString(R.string.babies_toys_and_kids),
                        getString(R.string.sports_and_outdoors),
                        getString(R.string.books_games_and_entertainment),
                        getString(R.string.office_and_security),
                        getString(R.string.automobiles_and_motorcycles),
                        getString(R.string.agriculture_and_food),
                        getString(R.string.machinery_hardware_and_tools),
                        getString(R.string.packaging_printing_and_advertising)


                };
        ModelHomeTwo modelHomeTwo;
        for (int i = 0; i < image.length; i++) {
            modelHomeTwo = new ModelHomeTwo(title[i], image[i]);
            modelHomeTwos.add(modelHomeTwo);
        }
        adapterHomeTwo.notifyDataSetChanged();
    }


    @Override
    public void OnClkHome(int pos) {
        Intent intent = new Intent(getContext(), VideloActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(HOME_URL, homeUrl);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    @Override
    public void itemClick(int pos) {
        Intent intent = new Intent(getContext(), VideloActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(HOME_URL, homeUrl);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
