package id.mzennis.contact.main;

import android.content.Context;

import id.mzennis.contact.base.BasePresenter;
import id.mzennis.contact.model.Contact;
import id.mzennis.contact.network.NetworkError;
import id.mzennis.contact.network.Service;
import rx.Subscription;

/**
 * Created by mzennis on 7/26/17.
 */

public class ContactPresenter extends BasePresenter<ContactView> {

    public ContactPresenter(Service service, ContactView view) {
        super(service, view);
    }

    @Override
    public void onCreate(Context context) {
        super.onCreate(context);
        mIView.initView();
    }

    public void getContacts() {
        mIView.onStartGetContact();
        Subscription subscription = getService().getContactList(new Service.GetContactListCallback() {
            @Override
            public void onSuccess(Contact[] contacts) {
                mIView.onFinishGetContact();
                mIView.onShowContact(contacts);
            }

            @Override
            public void onError(NetworkError networkError) {
                mIView.onFinishGetContact();
                mIView.onFailure(networkError.getAppErrorMessage());
            }
        });
        getSubscriptions().add(subscription);
    }
}
