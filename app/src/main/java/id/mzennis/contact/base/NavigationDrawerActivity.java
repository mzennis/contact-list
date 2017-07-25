package id.mzennis.contact.base;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mzennis.contact.R;
import id.mzennis.contact.helper.KeyboardHelper;
import id.mzennis.contact.helper.LoggerHelper;

/**
 * Created by mzennis on 7/25/17.
 */

public abstract class NavigationDrawerActivity extends BaseActivity {

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view) NavigationView navigationView;

    public View viewHeader;
    public MenuItem menuItem;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    protected boolean dOpenedOrOpening = false;

    protected abstract BaseFragment getFragment(int itemId);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);

        actionBarDrawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        setTitle(getString(R.string.app_name));
        setupDrawerContent(navigationView);

        setUpMenu(R.menu.menu_main);

        this.getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager f = getSupportFragmentManager();
                Fragment fg = f.findFragmentById(R.id.fl_content);
                if (fg != null) {
                    updatTitleNavDrawer(fg);
                }
            }
        });

        selectFirstFragment();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,  R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                onNavigationDrawerClosed();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                onNavigationDrawerOpened();
            }
        };
    }

    public View getViewHeader() {
        return this.viewHeader;
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    public void selectDrawerItem(final MenuItem menuItem) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                BaseFragment fragment = getFragment(menuItem.getItemId());
                if (fragment != null) {
                    if (!fragment.isVisible() && !menuItem.isChecked()) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fl_content, fragment, menuItem.getTitle().toString());
                        ft.commit();

                    } else {
                        LoggerHelper.wtf("Fragment Visible");
                    }

                    menuItem.setChecked(true);
                    closeNavigationDrawer();
                    onNavigationDrawerClosed();
                } else {
                    LoggerHelper.wtf("Fragment NULL");
                }
            }
        });
    }

    protected void selectFirstFragment() { // create method select first fragment
        final MenuItem menuItem = navigationView.getMenu().getItem(0);
        this.menuItem = menuItem;
        if (menuItem != null) {
            selectDrawerItem(menuItem);
        }
    }

    public void setUpMenu(@MenuRes int resId){
        navigationView.getMenu().clear();
        navigationView.inflateMenu(resId);
    }

    public View setUpHeader(@LayoutRes int res) {
        return navigationView.inflateHeaderView(res);
    }

    public void setItemBackground(@DrawableRes Drawable resId) {
        navigationView.setItemBackground(resId);
    }

    public boolean navigationDrawerOpenedOrOpening() {
        return dOpenedOrOpening;
    }

    public void openNavigationDrawer() {
        drawerLayout.openDrawer(navigationView);
    }

    public void closeNavigationDrawer() {
        drawerLayout.closeDrawer(navigationView);
    }

    protected void onNavigationDrawerOpened() {
        dOpenedOrOpening = true;
        supportInvalidateOptionsMenu();
        KeyboardHelper.hideKeyboard(this);
    }

    protected void onNavigationDrawerClosed() {
        dOpenedOrOpening = false;
        supportInvalidateOptionsMenu();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            if (! navigationDrawerOpenedOrOpening()) {
//                onNavigationDrawerOpened();
//            } else {
//                onNavigationDrawerClosed();
//            }
//            return true;
//        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (dOpenedOrOpening) {
            closeNavigationDrawer();
        } else {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 1) {
                fm.popBackStack();
            } else {
                this.finish();
            }
        }
    }

    public void updatTitleNavDrawer(Fragment f) {
        this.setTitle(f.getTag());
    }
}
