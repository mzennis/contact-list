package id.mzennis.contact.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import id.mzennis.contact.R;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
