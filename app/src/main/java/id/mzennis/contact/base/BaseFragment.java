package id.mzennis.contact.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.io.File;

import javax.inject.Inject;

import id.mzennis.contact.config.Config;
import id.mzennis.contact.config.DaggerConfig;
import id.mzennis.contact.network.NetworkModule;
import id.mzennis.contact.network.Service;

/**
 * Created by mzennis on 7/25/17.
 */

public class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    protected Config config;

    @Inject
    public Service service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getActivity().getCacheDir(), "responses");
        config = DaggerConfig.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public Config getConfig() {
        return config;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public boolean isTablet(){
        return getResources().getConfiguration().smallestScreenWidthDp >= 700;
    }

    protected void setTitle(final String title) {
        if (isAdded()) {
            getmActivity().setTitle(title);
        }
    }

    protected void setSubtitle(final String subtitle) {
        if (isAdded()) {
            getmActivity().setSubtitle(subtitle);
        }
    }

    public BaseActivity getmActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}