package com.videlo.videlo.v_Activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.Toast;

import com.videlo.videlo.Storage.SharedPrefManager;
import com.videlo.videlo.v_Fragment.AgricultureFrag;
import com.videlo.videlo.v_Fragment.AppliancesFrag;
import com.videlo.videlo.v_Fragment.AutomobilesFrag;
import com.videlo.videlo.v_Fragment.BabiesFrag;
import com.videlo.videlo.v_Fragment.BagsFrag;
import com.videlo.videlo.v_Fragment.BooksFrag;
import com.videlo.videlo.v_Fragment.ComputersFrag;
import com.videlo.videlo.v_Fragment.GardenFrag;
import com.videlo.videlo.v_Fragment.HomeFragment;
import com.videlo.videlo.v_Fragment.ConsumerFrag;
import com.videlo.videlo.R;
import com.videlo.videlo.v_Fragment.JewelleryFrag;
import com.videlo.videlo.v_Fragment.MachineryFrag;
import com.videlo.videlo.v_Fragment.MenFrag;
import com.videlo.videlo.v_Fragment.OfficeFrag;
import com.videlo.videlo.v_Fragment.PackagingFrag;
import com.videlo.videlo.v_Fragment.PersonalFrag;
import com.videlo.videlo.v_Fragment.PhoneFrag;
import com.videlo.videlo.v_Fragment.SportsFrag;
import com.videlo.videlo.v_Fragment.WatchesFrag;
import com.videlo.videlo.v_Fragment.WomenFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private String CART_URL = "base_url";
    private SearchView searchView;
    //TabLayout
    TabLayout myTab;
    ViewPager myViewPager;

    final int[] icons = {R.drawable.ic_white_home, R.drawable.ic_white_electronics, R.drawable.ic_white_men_apparel,
            R.drawable.ic_white_women_apparel, R.drawable.ic_white_pets, R.drawable.ic_white_home_appliances,
            R.drawable.ic_white_personal_care, R.drawable.ic_white_jewellery, R.drawable.ic_white_watch,
            R.drawable.ic_white_bag, R.drawable.ic_white_computer, R.drawable.ic_white_babies,
            R.drawable.ic_white_outdoors, R.drawable.ic_white_phone, R.drawable.ic_white_books,
            R.drawable.ic_white_security, R.drawable.ic_white_automobiles, R.drawable.ic_white_agriculture,
            R.drawable.ic_white_tools, R.drawable.ic_white_packaging};

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayFragment(new Fragment());
        myTab = findViewById(R.id.view);



        myViewPager = findViewById(R.id.mypager);
        myViewPager.setAdapter(new MyOwnPagerAdapter(getSupportFragmentManager()));
        myTab.setupWithViewPager(myViewPager);

        myTab.setSelectedTabIndicatorHeight(8);
        myTab.setTabTextColors(
                getResources().getColor(R.color.gen_white),
                getResources().getColor(R.color.black));
        for (int i = 0; i < icons.length; i++) {
            myTab.getTabAt(i).setIcon(icons[i]);
        }


        //int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.white);
        //  myTab.setTabIconTint(ColorStateList.valueOf(R.color.white));
        myTab.setSelectedTabIndicatorColor(R.color.white);
        myTab.setSelectedTabIndicatorHeight(15);

        // int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.white);
        // myTab.setTabIconTint(setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN));

        myTab.setTabIconTintResource(R.color.white);

        // myTab.setSelectedTabIndicatorColor(Color.parseColor("#fff"));
        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myViewPager.setCurrentItem(tab.getPosition());
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.white);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.white);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intentFloat = new Intent(MainActivity.this, VideloActivity.class);

                Bundle bundleFloat = new Bundle();
                bundleFloat.putString(CART_URL, "https://www.videlo.com.my/cart/");
                intentFloat.putExtras(bundleFloat);
                startActivity(intentFloat);

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fab:
                Toast.makeText(this, "touch", Toast.LENGTH_LONG).show();
                break;

        }


    }


    class MyOwnPagerAdapter extends FragmentPagerAdapter {

        String data[] = {getString(R.string.home), "Electronics", "Men's", "Women's", "Garden and Pets ",
                "Home Appliance", "Beauty Care", "Jewellery", "watches", "Bags and Shoes", "Computer Technology", "Baby Toys Kids",
                "Sports", "phone", "Books Game", "Office Security", "Automobile", "Agriculture", "Machinery",
                "Advertising"};


        public MyOwnPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {

                return new HomeFragment();
            }


            if (position == 1) {

                return new ConsumerFrag();
            }
            if (position == 2) {

                return new MenFrag();
            }
            if (position == 3) {

                return new WomenFrag();
            }
            if (position == 4) {

                return new GardenFrag();
            }
            if (position == 5) {

                return new AppliancesFrag();
            }
            if (position == 6) {

                return new PersonalFrag();
            }
            if (position == 7) {

                return new JewelleryFrag();
            }
            if (position == 8) {

                return new WatchesFrag();
            }
            if (position == 9) {

                return new BagsFrag();
            }
            if (position == 10) {

                return new ComputersFrag();
            }
            if (position == 11) {

                return new BabiesFrag();
            }
            if (position == 12) {

                return new SportsFrag();
            }
            if (position == 13) {

                return new PhoneFrag();
            }
            if (position == 14) {

                return new BooksFrag();
            }
            if (position == 15) {

                return new OfficeFrag();
            }
            if (position == 16) {

                return new AutomobilesFrag();
            }
            if (position == 17) {

                return new AgricultureFrag();
            }
            if (position == 18) {

                return new MachineryFrag();
            }
            if (position == 19) {

                return new PackagingFrag();
            }

            return null;


        }


        @Override
        public int getCount() {
            return data.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return data[position];
        }
    }


    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

     /*   SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);*/
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.user_sign) {


            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_wishlist:

                String url = "https://www.videlo.com.my/wishlist/";
                Intent i = new Intent(MainActivity.this, VideloActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(CART_URL, url);
                i.putExtras(bundle);
                startActivity(i);
                break;

            case R.id.nav_my_account:
                String urlacc = "https://www.videlo.com.my/my-account/";

                Intent j = new Intent(MainActivity.this, VideloActivity.class);
                Bundle b = new Bundle();
                b.putString(CART_URL, urlacc);
                j.putExtras(b);
                startActivity(j);

                break;
            case R.id.nav_help:
                String urlhelp = "https://www.videlo.com.my/contact/";
                Intent intent = new Intent(MainActivity.this, VideloActivity.class);
                Bundle bun = new Bundle();
                bun.putString(CART_URL, urlhelp);
                intent.putExtras(bun);
                startActivity(intent);


                break;
            case R.id.nav_customer:

                String UrlCus = "https://www.videlo.com.my/";

                Intent intent1 = new Intent(MainActivity.this, VideloActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString(CART_URL, UrlCus);
                intent1.putExtras(bundle1);
                startActivity(intent1);


                break;
            case R.id.nav_settings:
                String UrlSet = "https://www.videlo.com.my/";

                Intent intent2 = new Intent(MainActivity.this, VideloActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString(CART_URL, UrlSet);
                intent2.putExtras(bundle2);
                startActivity(intent2);


                break;
            case R.id.nav_share:
                String UrlShare = "https://www.videlo.com.my/";
                Intent intent3 = new Intent(MainActivity.this, VideloActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString(CART_URL, UrlShare);
                intent3.putExtras(bundle3);
                startActivity(intent3);

                break;


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
