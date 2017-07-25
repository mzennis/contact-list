package id.mzennis.contact.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import id.mzennis.contact.base.BaseFragment;
import id.mzennis.contact.base.NavigationDrawerActivity;

/**
 * Created by mzennis on 7/25/17.
 */

public class ContactActivity extends NavigationDrawerActivity {

    @Override
    protected BaseFragment getFragment(int itemId) {
        return new ContactFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
