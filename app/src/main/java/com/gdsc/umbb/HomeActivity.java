package com.gdsc.umbb;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

import com.gdsc.umbb.Fragments.AboutUsFragment;
import com.gdsc.umbb.Fragments.HomeFragment;
import com.gdsc.umbb.Fragments.InformationFragment;
import com.gdsc.umbb.Fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView drawerNav;
    private ActionBarDrawerToggle mDrawerToggle;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        this.drawerNav = (NavigationView) findViewById(R.id.drawer_nav_view);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        drawerNav.setCheckedItem(R.id.drawer_nav_home);

        // Set the back arrow in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(false);
        }
        initDrawer(toolbar);
        initStatusBar(toolbar);

        drawerNav.bringToFront();

        // Show drawer icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_fragment_container, new HomeFragment()).commit();

        // Toolbar items.
        MenuItem search_item = toolbar.getMenu().findItem(R.id.search);
        MenuItem right_icon_item = toolbar.getMenu().findItem(R.id.right_icon);

        drawerNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.drawer_nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_fragment_container, new HomeFragment()).commit();
                        search_item.setVisible(true);
                        right_icon_item.setIcon(R.drawable.icon_home);
                        break;
                    case R.id.drawer_nav_information:
                        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_fragment_container, new InformationFragment()).commit();
                        search_item.setVisible(false);
                        right_icon_item.setIcon(R.drawable.icon_information);
                        break;
                    case R.id.drawer_nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_fragment_container, new SettingsFragment()).commit();
                        search_item.setVisible(false);
                        right_icon_item.setIcon(R.drawable.icon_settings);
                        break;
                    case R.id.drawer_nav_abouts:
                        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_fragment_container, new AboutUsFragment()).commit();
                        search_item.setVisible(false);
                        right_icon_item.setIcon(R.drawable.icon_about);
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Search Icon in Home.

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        // Search.
                        searchView = (SearchView) item.getActionView();
                        searchView.setQueryHint("Looking for...");
                        searchView.clearFocus();
                        searchView.setIconified(false);
                        //note_add.setVisibility(View.GONE);
                        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View view, boolean hasFocus) {
                                if (hasFocus) {
                                    // searchView expanded
                                    //note_add.setVisibility(View.GONE);
                                    right_icon_item.setVisible(false);
                                } else {
                                    // searchView not expanded
                                    //note_add.setVisibility(View.VISIBLE);
                                    //item.setIcon()
                                    right_icon_item.setVisible(true);
                                }
                            }
                        });
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String s) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String s) {
                                right_icon_item.setVisible(false);

                                /*searchingNotes = s;
                                List<Notes> searchList = new ArrayList<>();
                                for (Notes viewSearch : notes) {
                                    if (viewSearch.getTitle().toLowerCase().contains(s.toLowerCase())) {
                                        searchList.add(viewSearch);
                                    } else if (viewSearch.getDescription().toLowerCase().contains(s.toLowerCase())) {
                                        searchList.add(viewSearch);
                                    }
                                }
                                recyclerViewNotesAdapter.setSearchList(NoteActivity.this, searchList);
                                runLayoutAnimation();*/
                                return true;
                            }
                        });
                        break;
                    case R.id.right_icon:

                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if (mDrawerToggle != null) mDrawerToggle.syncState();
    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            // slide drawer from right to left
            //((DrawerLayout.LayoutParams) drawer.getChildAt(1).getLayoutParams()).gravity = GravityCompat.END;
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.setDrawerIndicatorEnabled(false);
            toggle.syncState();
            mDrawerToggle = toggle;
        }
        this.drawer = drawer;
    }

    protected void initStatusBar(View toolbar) {
        // Ensure `setStatusBarImmersiveMode()`
        if (Build.VERSION.SDK_INT >= 19) { // 19, 4.4, KITKAT
            // Ensure content view `fitsSystemWindows` is false.
            ViewGroup contentParent = (ViewGroup) findViewById(android.R.id.content);
            View content = contentParent.getChildAt(0);
            // If using `DrawerLayout`, must ensure its subviews `fitsSystemWindows` are all false.
            // Because in some roms, such as MIUI, it will fits system windows for each subview.
            setFitsSystemWindows(content, false, true);

            // Add padding to hold the status bar place.
            clipToStatusBar(toolbar);

            // Add a view to hold the status bar place.
            // Note: if using appbar_scrolling_view_behavior of CoordinatorLayout, however,
            // the holder view could be scrolled to outside as it above the app bar.
            //holdStatusBar(toolbar, R.color.colorPrimary);
        }
    }

    protected void setFitsSystemWindows(View view, boolean fitSystemWindows, boolean applyToChildren) {
        if (view == null) return;
        view.setFitsSystemWindows(fitSystemWindows);
        if (applyToChildren && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0, n = viewGroup.getChildCount(); i < n; i++) {
                viewGroup.getChildAt(i).setFitsSystemWindows(fitSystemWindows);
            }
        }
    }

    protected void clipToStatusBar(View view) {
        final int statusBarHeight = getStatusBarHeight(this);
        view.getLayoutParams().height += statusBarHeight;
        view.setPadding(0, statusBarHeight, 0, 0);
    }

    protected int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    protected void setStatusBarImmersiveMode(@ColorInt int color) {
        Window win = getWindow();

        // StatusBar
        if (Build.VERSION.SDK_INT >= 19) { // 19, 4.4, KITKAT
            win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= 21) { // 21, 5.0, LOLLIPOP
            win.getAttributes().systemUiVisibility |=
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(color);
        }

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setStatusBarImmersiveMode(getResources().getColor(R.color.status));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (drawer != null) {
            getMenuInflater().inflate(R.menu.drawer_menu, menu);
        }
        //TintUtils.tintList(this, menu, R.color.bar_icon_color);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}