package id.mzennis.contact.contact;

import id.mzennis.contact.model.Contact;

/**
 * Created by mzennis on 7/26/17.
 */

public interface ContactView {
    void initView();
    void onStartGetContact();
    void onShowContact(Contact[] contacts);
    void onFinishGetContact();
    void onFailure(String appErrorMessage);
}
