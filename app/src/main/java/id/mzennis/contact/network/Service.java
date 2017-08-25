package id.mzennis.contact.network;

import id.mzennis.contact.model.Contact;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {

    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getContactList(final DefaultCallback callback) {

        return networkService.getContact()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Contact[]>>() {
                    @Override
                    public Observable<? extends Contact[]> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Contact[]>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(Contact[] contacts) {
                        callback.onSuccess(contacts);
                    }
                });
    }

    public interface DefaultCallback {
        void onSuccess(Contact[] contacts);
        void onError(NetworkError networkError);
    }
}
