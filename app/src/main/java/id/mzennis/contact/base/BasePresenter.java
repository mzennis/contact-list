package id.mzennis.contact.base;

import android.content.Context;
import android.view.View;

import id.mzennis.contact.network.Service;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mzennis on 7/26/17.
 */

public abstract class BasePresenter<T> implements IBasePresenter {

    private Service service;
    private CompositeSubscription subscriptions;

    private Context mContext;
    public T mIView;

    public BasePresenter(Service service, T view) {
        this.mIView = view;
        this.service = service;
        this.subscriptions = new CompositeSubscription();
    }

    public Service getService() {
        return service;
    }

    public CompositeSubscription getSubscriptions() {
        return subscriptions;
    }

    @Override
    public Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate(Context context){
        this.mContext = context;
    }

    @Override
    public void onResume(){

    }

    @Override
    public void onPaused(){

    }

    @Override
    public void onDestroy(){

    }

    @Override
    public void onAttach(Context context){

    }

    @Override
    public void onCreateView(View view){

    }

    @Override
    public void onViewCreated(View view){

    }
}
