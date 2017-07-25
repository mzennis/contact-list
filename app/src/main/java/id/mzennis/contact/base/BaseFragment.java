package id.mzennis.contact.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by mzennis on 7/25/17.
 */

public class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}