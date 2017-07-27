package id.mzennis.contact.network;

import id.mzennis.contact.model.Contact;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ennur on 6/25/16.
 */
public interface NetworkService {

    @GET(ApiConfig.GET_CONTACT)
    Observable<Contact[]> getContact();

}
