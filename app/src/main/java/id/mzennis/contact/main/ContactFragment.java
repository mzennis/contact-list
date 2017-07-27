package id.mzennis.contact.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mzennis.contact.R;
import id.mzennis.contact.base.BaseFragment;
import id.mzennis.contact.model.Contact;

/**
 * Created by mzennis on 7/25/17.
 */

public class ContactFragment extends BaseFragment implements ContactView {

    @BindView(R.id.sr_contact) SwipeRefreshLayout swContact;
    @BindView(R.id.rv_contact) RecyclerView rvContact;
    @BindView(R.id.fab_add_contact) FloatingActionButton fabAddContact;
    @BindView(R.id.rl_loading) RelativeLayout rlLoading;

    private ContactPresenter presenter;
    private ContactAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getConfig().inject(this);

        presenter = new ContactPresenter(service, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);
        presenter.onCreate(getActivity());
        return view;
    }

    @Override
    public void initView() {
        adapter = new ContactAdapter(getActivity());

        rvContact.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvContact.setHasFixedSize(true);
        rvContact.setAdapter(adapter);

        swContact.setDistanceToTriggerSync(30);
        swContact.setSize(SwipeRefreshLayout.DEFAULT);

        swContact.setColorSchemeResources(R.color.colorPrimary);

        swContact.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                adapter.notifyDataSetChanged();
                swContact.setRefreshing(true);
                presenter.getContacts();
            }
        });

        presenter.getContacts();
    }

    @Override
    public void onStartGetContact() {
        if (!swContact.isRefreshing())
            rlLoading.setVisibility(View.VISIBLE);
        else rlLoading.setVisibility(View.GONE);
    }

    @Override
    public void onShowContact(Contact[] contacts) {
        List<Contact> contactList = Arrays.asList(contacts);
        adapter.addAll(contactList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFinishGetContact() {
        if (swContact.isRefreshing())
            swContact.setRefreshing(false);
        else
            rlLoading.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Network Error")
                .setMessage(appErrorMessage)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}