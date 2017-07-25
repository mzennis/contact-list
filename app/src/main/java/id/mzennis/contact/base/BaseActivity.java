package id.mzennis.contact.base;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import id.mzennis.contact.R;
import id.mzennis.contact.helper.ApplicationHelper;
import id.mzennis.contact.helper.LoggerHelper;
import id.mzennis.contact.helper.VersionHelper;

/**
 * Created by mzennis on 7/25/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar();
    }

    @TargetApi(VersionHelper.API_21)
    public void init21() {
        statusBarColor(getWindow(), ContextCompat.getColor(this, R.color.colorPrimaryDark));

        final String name = ApplicationHelper.getName();
        final Bitmap icon = ApplicationHelper.getIcon();
        final int color = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        final ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(name, icon, color);
        setTaskDescription(taskDescription);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @TargetApi(VersionHelper.API_21)
    public boolean statusBarColor(@NonNull final Window window, @ColorInt final int color) {
        if (VersionHelper.sdk() >= VersionHelper.API_21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
            return true;
        }
        return false;
    }

    @Override
    public void setContentView(final View view) {
        super.setContentView(view);
        setupToolbar();
    }

    @Override
    public void setContentView(final View view, final ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        setupToolbar();
    }

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.textColorPrimary));
            toolbar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
            setTitle(ApplicationHelper.getName());
        }

        if (VersionHelper.sdk() >= VersionHelper.API_21) {
            init21();
        }
    }

    @Override
    public void setSupportActionBar(final Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        this.toolbar = toolbar;
    }

    // Lifecycle
    @Override
    protected void onStart() {
        super.onStart();
        supportInvalidateOptionsMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setDisplayHome(final boolean showBackButton) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            LoggerHelper.warning("ActionBar was NULL");
            return;
        }
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(showBackButton);
    }

    public void setIcon(@DrawableRes int drawable) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            LoggerHelper.warning("ActionBar was NULL");
            return;
        }
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setIcon(ContextCompat.getDrawable(this, drawable));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void setTitle(final String title) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            LoggerHelper.warning("ActionBar was NULL");
            return;
        }
        actionBar.setDisplayShowTitleEnabled(!TextUtils.isEmpty(title));
        actionBar.setTitle(title);
    }

    public void setSubtitle(final String subtitle) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            LoggerHelper.warning("ActionBar was NULL");
            return;
        }
        actionBar.setDisplayShowTitleEnabled(!TextUtils.isEmpty(subtitle));
        actionBar.setSubtitle(subtitle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return true;
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
